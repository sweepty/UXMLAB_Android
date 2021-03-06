package com.example.seungyeonlee.uxmlab_assignment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;


public class AssignmentActivity extends AppCompatActivity {
    //하단 리스트뷰
    ListView asListView;

    //과제 본문 내용 TextView
    TextView texts;

    //위의 TextView에 담길 과제 상세 내용
    private String contentText;

    String mJsonString;
    ArrayList<String> mArrayList;

    AssignmentListViewAdapter adapter;
    AssignmentTextViewAdapter adapterContent;

    private static String TAG = "phpquerytest";
    private static final String TAG_JSON="homework";
    private static final String TAG_CONTENT ="hw_content";
    private static final String TAG_DUE ="hw_due";

    //FileUploadActivity로 넘어가는 버튼
    private Button uploadButton;

    //과제 등록 테스트용
    Button uploadAs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        asListView = (ListView) findViewById(R.id.ListView);
        texts = (TextView) findViewById(R.id.textView);

        //과제 데이터 가져오기
        mArrayList = new ArrayList<>();
        GetData task = new GetData();
        task.execute("http://10.0.2.2/~seungyeonlee/query.php");

        uploadButton = (Button) findViewById(R.id.button3);
        uploadButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent moveToFileUpload =  new Intent(AssignmentActivity.this, FileUploadActivity.class);
                startActivity(moveToFileUpload);
            }
        });
        uploadAs = (Button) findViewById(R.id.button2);
        uploadAs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToUploadAs =  new Intent(AssignmentActivity.this,AddAssignmentActivity.class);
                startActivity(moveToUploadAs);
            }
        });

    }



    private class GetData extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(AssignmentActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);

            if (result == null){
                texts.setText(errorString);
            }
            else {
                mJsonString = result;
                showResult();
            }
        }

        @Override
        protected String doInBackground(String... params) {

            String serverURL = params[0];


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.connect();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }
                bufferedReader.close();
                return sb.toString().trim();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();

                return null;
            }

        }
    }


    private void showResult(){
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);


//                String name = item.getString(TAG_NAME);
                String content = item.getString(TAG_CONTENT);
                String due = item.getString(TAG_DUE);

                mArrayList.add(due);
                contentText = content;
            }

            //아래 리스트
            adapter = new AssignmentListViewAdapter();
            asListView.setAdapter(adapter);
            adapter.addAsList("content file","없음");
            adapter.addAsList("제출 상태","없음");
            adapter.addAsList("채점 상태","없음");
            adapter.addAsList("마감 일시", String.valueOf(mArrayList.get(0)));
            adapter.addAsList("제출 파일","없음");
            adapter.addAsList("제출하기","없음");

            //과제 상세 내용
            adapterContent = new AssignmentTextViewAdapter();
            adapterContent.addContent(contentText);
            texts.setText(contentText);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }


}