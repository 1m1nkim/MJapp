package com.example.firebase_test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class CallActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference callReference;
    Button back_btn_han;
    ArrayList<Call> call  = new ArrayList<>();
    ArrayList<String> callList = new ArrayList<>();
    ArrayAdapter callAdapter;
    Button search_btn_call;
    Spinner spinner;
    Call calls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inner_call);

        database = FirebaseDatabase.getInstance();
        callReference = database.getReference("call");
        back_btn_han = findViewById(R.id.back_btn_han);
        back_btn_han.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CallActivity.this, MainActivity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        callReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                call.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    calls = snapshot.getValue(Call.class);
                    call.add(calls);
                }
                for(int i=0; i<call.size(); i++){
                    String callName = call.get(i).getDeName();
                    callList.add(callName);
                }
                 callAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, callList);
                spinner = (Spinner) findViewById(R.id.spin_call);
                spinner.setAdapter(callAdapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
        search_btn_call = (Button) findViewById(R.id.search_btn_call);
        search_btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                int selectedIndex = spinner.getSelectedItemPosition();
                String callNumber = call.get(selectedIndex).getDeNum();
                dialIntent.setData(Uri.parse("tel:" + callNumber));
                startActivity(dialIntent);
            }
        });


    }
}
