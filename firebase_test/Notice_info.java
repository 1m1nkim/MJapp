package com.example.firebase_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Notice_info extends AppCompatActivity {

    Button notice_info_btn;
    //asd
    private FirebaseDatabase database;
    private DatabaseReference noticeReference;
    String it_position;
    //-------아래 텍스트는 레이아웃과 연결되는 변수
    TextView info_title;
    TextView info_writer;
    TextView info_date;
    TextView info_main;
    int indexx = 0;
    int i;
    //--------아래 변수들은 인덱스로 데베에서 가져온 값을 넣어서 다시 위에 텍스트뷰로 넘겨는 변수
    String notice_info_title;
    String notice_info_writer;
    String notice_info_date;
    String notice_info_main;

    ArrayList<Notice> noticeList = new ArrayList<>();//Book_home.java파일 보고 만들었음 일단


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_info);

        database = FirebaseDatabase.getInstance();

        //해당 레이아웃과 텍스트뷰 연결 코드
        info_title = findViewById(R.id.info_title);
        info_writer = findViewById(R.id.info_writer);
        info_date = findViewById(R.id.info_date);
        info_main = findViewById(R.id.info_main);

        Intent intent = getIntent();
        if (intent != null) {
            //아래에 코드에서 null값을 반환하는 오류가 있어서 노드값을 바꾸거나 코드 자체를 바꿔야함
            String it_position = intent.getStringExtra("position");
            // it_position = it_position + 1;
        }

        readDB(it_position);

        notice_info_btn = (Button) findViewById(R.id.notice_info_btn);
        notice_info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Notice_main.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    private void readDB(String it_position){
        noticeReference = database.getReference("notice");

        noticeReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                noticeList.clear();
                i = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Notice notice = snapshot.getValue(Notice.class);
                    //화면이 만들어졌을때 데이터베이스의 값을 가져와서 객체에 넣는 작업
                    notice.setnIndex(indexx++);
                    noticeList.add(notice);
                    //여기까지

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }
    //머지
}