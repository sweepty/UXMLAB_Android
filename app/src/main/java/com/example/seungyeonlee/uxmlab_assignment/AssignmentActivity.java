package com.example.seungyeonlee.uxmlab_assignment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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
import java.util.HashMap;


public class AssignmentActivity extends AppCompatActivity {
    ListView asListView;
    ArrayAdapter<String> adapter;
    private TextView texts;//본문내용
//    Assignment assignment;
    private Button sendButton;
    String myJSON;
    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    private static String TAG = "phpquerytest";
    private static final String TAG_JSON="homework";
    private static final String TAG_NO = "hw_no";
    private static final String TAG_NAME = "hw_name";
    private static final String TAG_CONTENT ="hw_content";
    private static final String TAG_DUE ="hw_due";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        asListView = (ListView) findViewById(R.id.ListView);
        texts = (TextView) findViewById(R.id.textView);

//        sendButton = (Button) findViewById(R.id.button);

//        getJSON("http://192.168.101.1/Android/getdata.php");
        AssignmentListViewAdapter adapter;

        // Adapter 생성
        adapter = new AssignmentListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        asListView = (ListView) findViewById(R.id.ListView);
        asListView.setAdapter(adapter);

//        // 리스트뷰에 아이템 추가.
        adapter.addTitle("content file");
        adapter.addTitle("제출 상태");
        adapter.addTitle("채점 상태");
        adapter.addTitle("마감 일시");
        adapter.addTitle("제출 파일");
//        adapter.addItem("content file","helloooooooo");
//        adapter.addItem("제출 상태", "제출 전/1시간 전 제출 완료됨") ;
//        adapter.addItem("채점 상태", "채점 완료/채점 전") ;
//        adapter.addItem("마감 일시","------날짜----");
//        adapter.addItem("제출 파일", "내가 제출한 파일명");



        //제출하기 버튼 클릭시.
//        sendButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }


}