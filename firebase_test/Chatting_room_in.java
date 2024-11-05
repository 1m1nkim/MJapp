package com.example.firebase_test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Chatting_room_in extends AppCompatActivity {
    Button back_btn_used;
    Button home_btn_used;
    Button sell_btn_used;
    Button mine_btn_used;
    //----------------------------------------------------------------------------------------------
    RecyclerView cmrecycler;
    RecyclerView cyrecycler;
    Chat_me_adapter cmradapter;
    Chat_you_adapter cyradapter;
    ArrayList<Chat_me> cms;
    ArrayList<Chat_you> cys;
    Button send_btn;
    EditText send_et;
    String currentUser;
    String otherUser;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatting);
        cmrecycler = findViewById(R.id.recyclerView_me);
        cyrecycler = findViewById(R.id.recyclerView_you);
        send_btn = findViewById(R.id.send_btn);
        send_et = findViewById(R.id.send_et);
        cms = new ArrayList<>();
        cys = new ArrayList<>();
        String roomid = Book_mine.chats.get(mine_chat_Adapter.chatdataposition).getChatRoomId().toString();
        String userId1 = Book_mine.chats.get(mine_chat_Adapter.chatdataposition).getUserId1().toString();
        String userId2 = Book_mine.chats.get(mine_chat_Adapter.chatdataposition).getUserId2().toString();

        if(userId1.equals(MainActivity.name_num)){
            currentUser = userId1;
            otherUser = userId2;
        }else if(userId2.equals(MainActivity.name_num)){
            currentUser = userId2;
            otherUser = userId1;
        }
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendet = send_et.getText().toString();
                Chat_me chatm = new Chat_me(sendet);
                Log.d("tag", "-----------------------" + roomid);
                DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("chatRooms").child(roomid).child( currentUser + "messages");
                String key = databaseRef.push().getKey();
                databaseRef.child(key).setValue(chatm);
                send_et.setText("");
            }
        });
        cmradapter = new Chat_me_adapter();
        cyradapter = new Chat_you_adapter();
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("chatRooms").child(roomid).child(currentUser + "messages");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cms.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chat_me chats_me = snapshot.getValue(Chat_me.class);
                    cms.add(0,chats_me);
                }

                cmradapter.setChatsList(cms);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // 에러 처리
            }
        });
        cmrecycler.setAdapter(cmradapter);
        cmrecycler.setLayoutManager(new LinearLayoutManager(Chatting_room_in.this));
        DatabaseReference databaseRef_y = FirebaseDatabase.getInstance().getReference("chatRooms").child(roomid).child(otherUser + "messages");
        databaseRef_y.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cys.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chat_you chats_you = snapshot.getValue(Chat_you.class);
                    cys.add(0,chats_you);
                }

                cyradapter.setChatsList(cys);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // 에러 처리
            }
        });
        cyrecycler.setAdapter(cyradapter);
        cyrecycler.setLayoutManager(new LinearLayoutManager(Chatting_room_in.this));
//--------------------------------------------------------------------------------------------------
        back_btn_used = findViewById(R.id.back_btn_used_home);
        home_btn_used = findViewById(R.id.home_btn_used);
        sell_btn_used = findViewById(R.id.sell_btn_used);
        mine_btn_used = findViewById(R.id.mine_btn_used);

        back_btn_used.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Book_mine.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        home_btn_used.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Book_home.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        sell_btn_used.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Book_seller.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        mine_btn_used.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Book_mine.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
