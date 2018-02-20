package com.example.seungyeonlee.uxmlab_assignment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AddAssignmentActivity extends AppCompatActivity {

    //과제명, 상세 내용, 마감날짜, 마감시간
    private EditText asName,asContent,inDate,inTime;

    //마감 날짜, 시간, 과제 등록
    private Button btnDatePicker, btnTimePicker, addAssignment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);


        asName = (EditText) findViewById(R.id.asName);
        asContent = (EditText) findViewById(R.id.asContent);
        inDate = (EditText) findViewById(R.id.in_date);
        inTime = (EditText) findViewById(R.id.in_time);


        //마감 날짜 선택 버튼
        btnDatePicker = (Button)findViewById(R.id.btn_date);
        btnDatePicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogFragment dialogfragment = new DatePickerDialogTheme();
                dialogfragment.show(getFragmentManager(), "datepicker");
            }
        });

        //마감 시간 선택 버튼
        btnTimePicker = (Button)findViewById(R.id.btn_time);
        btnTimePicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogFragment dialogfragment = new TimePickerDialogTheme();
                dialogfragment.show(getFragmentManager(), "timepicker");
            }
        });

        //과제 등록 버튼
        addAssignment = (Button)findViewById(R.id.addAssignment);
        addAssignment.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                formChecker(v);
            }
        });
    }
    public void formChecker(View view){
        String name = String.valueOf(asName.getText());
        String content = String.valueOf(asContent.getText());
        String date = String.valueOf(inDate.getText());
        String time = String.valueOf(inTime.getText());
        String due = date +" "+ time;

        if (name!=null && content!=null && due!=null){
            addAssignment(name,content,due);
        } else{
            Toast.makeText(AddAssignmentActivity.this,"모든 항목을 입력해주세요.",Toast.LENGTH_LONG).show();

        }
    }

    //날짜 선택 정보 받아오기
    public static class DatePickerDialogTheme extends DialogFragment implements DatePickerDialog.OnDateSetListener{
        private TextView txtDate;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_DEVICE_DEFAULT_DARK,this,year,month,day);

            return datepickerdialog;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day){

            txtDate = (TextView) getActivity().findViewById(R.id.in_date);
            txtDate.setText(year+"/"+(month+1)+"/"+day);
        }
    }

    //시간 선택 정보 받아오기
    public static class TimePickerDialogTheme extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),R.style.Theme_AppCompat_Dialog_Alert,this,hour, minute,
                    DateFormat.is24HourFormat(getActivity()));

            return timePickerDialog;
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            TextView txtTime = (TextView) getActivity().findViewById(R.id.in_time);
            txtTime.setText(hourOfDay+":"+minute+":00");
        }
    }

    private void addAssignment(String hwName, String hwContent, String hwDue){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Api api = retrofit.create(Api.class);

        Call <List<Result>> call = api.addAssignment(hwName,hwContent,hwDue);
        call.enqueue(new Callback<List<Result>>() {

            @Override
            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                //등록
                int result = response.body().get(0).getResult();
                if(result==0) { //과제 추가 실패
                    Toast.makeText(getApplicationContext(), "ERROR: 과제 생성 실패", Toast.LENGTH_SHORT).show();

                } else if (result==1) { // 과제 추가 성공
                    Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();

                }
            }

            // 실패시 처리하는 방법을 정하는 메서드
            @Override
            public void onFailure(Call<List<Result>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
