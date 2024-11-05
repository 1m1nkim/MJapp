package com.example.firebase_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity {
    Button march_btn;
    Button april_btn;
    Button may_btn;
    Button june_btn;
    Button july_btn;
    Button august_btn;
    Button sep_btn;
    Button octo_btn;
    Button novem_btn;
    Button decem_btn;
    Button back_btn_han;
    Button left_btn_sche;
    Button right_btn_sche;
    Button left_btn_sche_two;
    Button right_btn_sche_two;
    TextView month_text;
    TextView text_sche;
    RelativeLayout sche_one;
    RelativeLayout sche_two;
    boolean laycheck = true;
    private FirebaseDatabase database;
    private DatabaseReference scheReference;
    Schedule schedule;
    ArrayList<Schedule> scheduleList  = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_one);

        march_btn = (Button) findViewById(R.id.march_btn);
        april_btn = (Button) findViewById(R.id.april_btn);
        may_btn = (Button) findViewById(R.id.may_btn);
        june_btn = (Button) findViewById(R.id.june_btn);
        july_btn = (Button) findViewById(R.id.july_btn);
        august_btn = (Button) findViewById(R.id.august_btn);
        sep_btn = (Button) findViewById(R.id.sep_btn);
        octo_btn = (Button) findViewById(R.id.octo_btn);
        novem_btn = (Button) findViewById(R.id.novem_btn);
        decem_btn = (Button) findViewById(R.id.decem_btn);
        back_btn_han = (Button) findViewById(R.id.back_btn_han);
        left_btn_sche = (Button) findViewById(R.id.left_btn_sche);
        right_btn_sche = (Button) findViewById(R.id.right_btn_sche);
        left_btn_sche_two = (Button) findViewById(R.id.left_btn_sche_two);
        right_btn_sche_two = (Button) findViewById(R.id.right_btn_sche_two);
        month_text = (TextView) findViewById(R.id.month_text);
        text_sche = (TextView) findViewById(R.id.text_sche);
        sche_one = (RelativeLayout) findViewById(R.id.sche_one);
        sche_two = (RelativeLayout)
                findViewById(R.id.sche_two);
        database = FirebaseDatabase.getInstance();
        scheReference = database.getReference("schedule");

        scheReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                scheduleList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    schedule = snapshot.getValue(Schedule.class);
                    scheduleList.add(schedule);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        march_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String formattedValue = scheduleList.get(0).getSche().replace("\\n", "\n");
                month_text.setText("3월");
                text_sche.setText(formattedValue);
            }
        });
        april_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String formattedValue = scheduleList.get(0).getSche().replace("\\n", "\n");
                month_text.setText("4월");
                text_sche.setText(formattedValue);
            }
        });
        may_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String formattedValue = scheduleList.get(2).getSche().replace("\\n", "\n");
                month_text.setText("5월");
                text_sche.setText(formattedValue);
            }
        });
        june_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String formattedValue = scheduleList.get(3).getSche().replace("\\n", "\n");
                month_text.setText("6월");
                text_sche.setText(formattedValue);
            }
        });
        july_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String formattedValue = scheduleList.get(4).getSche().replace("\\n", "\n");
                month_text.setText("7월");
                text_sche.setText(formattedValue);
            }
        });
        august_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String formattedValue = scheduleList.get(5).getSche().replace("\\n", "\n");
                month_text.setText("8월");
                text_sche.setText(formattedValue);
            }
        });
        sep_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String formattedValue = scheduleList.get(6).getSche().replace("\\n", "\n");
                month_text.setText("9월");
                text_sche.setText(formattedValue);
            }
        });
        octo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String formattedValue = scheduleList.get(7).getSche().replace("\\n", "\n");
                month_text.setText("10월");
                text_sche.setText(formattedValue);
            }
        });
        novem_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String formattedValue = scheduleList.get(8).getSche().replace("\\n", "\n");
                month_text.setText("11월");
                text_sche.setText(formattedValue);
            }
        });
        decem_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String formattedValue = scheduleList.get(9).getSche().replace("\\n", "\n");
                month_text.setText("12월");
                text_sche.setText(formattedValue);
            }
        });
        back_btn_han.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
        left_btn_sche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(laycheck){
                    sche_one.setVisibility(View.INVISIBLE);
                    sche_two.setVisibility(View.VISIBLE);
                    laycheck = false;
                }else if(laycheck == false){
                    sche_one.setVisibility(View.VISIBLE);
                    sche_two.setVisibility(View.INVISIBLE);
                    laycheck = true;
                }
            }
        });
        right_btn_sche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(laycheck){
                        sche_one.setVisibility(View.INVISIBLE);
                        sche_two.setVisibility(View.VISIBLE);
                        laycheck = false;
                    }else if(laycheck == false){
                        sche_one.setVisibility(View.VISIBLE);
                        sche_two.setVisibility(View.INVISIBLE);
                        laycheck = true;
                    }
                }
            });
        left_btn_sche_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(laycheck){
                    sche_one.setVisibility(View.INVISIBLE);
                    sche_two.setVisibility(View.VISIBLE);
                    laycheck = false;
                }else if(laycheck == false){
                    sche_one.setVisibility(View.VISIBLE);
                    sche_two.setVisibility(View.INVISIBLE);
                    laycheck = true;
                }
            }
        });
        right_btn_sche_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(laycheck){
                    sche_one.setVisibility(View.INVISIBLE);
                    sche_two.setVisibility(View.VISIBLE);
                    laycheck = false;
                }else if(laycheck == false){
                    sche_one.setVisibility(View.VISIBLE);
                    sche_two.setVisibility(View.INVISIBLE);
                    laycheck = true;
                }
            }
        });
        /*
            for or while 사용, 데이터베이스[i]값이 null이 아닐때
            String text = "";

            while(데이터베이스[i] != null){
                text += 데이터베이스[i] + "/n";
                ~.setText(데이터베이스[i]);
                i++;
            }
         */
    }
}
