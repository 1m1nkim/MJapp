package com.example.firebase_test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class Book_mine extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private Recycler_Adapter_mine mRecyclerAdapter;
    private mine_chat_Adapter mRecyclerAdapter_c;
    private ArrayList<Book_info> books;
    public static ArrayList<My_chat_info> chats;
    Button back_btn_used;
    Button home_btn_used;
    Button sell_btn_used;
    Button mine_btn_used;
    Button selling_book;
    Button message_book;
    View rectangle_5;
    View rectangle_6;
    RelativeLayout chatList_your;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.used_mine);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        selling_book = findViewById(R.id.selling_book);
        message_book = findViewById(R.id.message_book);
        rectangle_5 = findViewById(R.id.rectangle_5);
        rectangle_6 = findViewById(R.id.rectangle_6);
        chatList_your = findViewById(R.id.chatList_your);

        if (mRecyclerView == null) {
            // Handle the case where mRecyclerView is null (Check your XML layout)
            return;
        }
        /* initiate adapter */
        mRecyclerAdapter = new Recycler_Adapter_mine();
        mRecyclerAdapter_c = new mine_chat_Adapter();
        /* adapt data */
        books = new ArrayList<Book_info>();

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference(MainActivity.name_num + "books");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                books.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Book_info bookk = snapshot.getValue(Book_info.class);
                    books.add(bookk);
                }

                mRecyclerAdapter.setBooksList(books);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // 에러 처리
            }
        });

        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Set data to the adapter

        selling_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rectangle_5.setVisibility(View.VISIBLE);
                rectangle_6.setVisibility(View.INVISIBLE);
                DatabaseReference databaseRef_s = FirebaseDatabase.getInstance().getReference(MainActivity.name_num + "books");
                databaseRef_s.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        books.clear();
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            Book_info bookk = snapshot.getValue(Book_info.class);
                            books.add(bookk);
                        }

                        mRecyclerAdapter.setBooksList(books);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // 에러 처리
                    }
                });

                mRecyclerView.setAdapter(mRecyclerAdapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(Book_mine.this));
            }
        });
        chats = new ArrayList<My_chat_info>();

        message_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rectangle_5.setVisibility(View.INVISIBLE);
                rectangle_6.setVisibility(View.VISIBLE);

                DatabaseReference databaseRef_s = FirebaseDatabase.getInstance().getReference("chatRooms");
                databaseRef_s.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        chats.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            // 직접 데이터 추출
                            String roomId = snapshot.child("roomId").getValue(String.class);
                            String userId1 = snapshot.child("userId1").getValue(String.class);
                            String userId2 = snapshot.child("userId2").getValue(String.class);

                            // 안전성 체크 추가
                            if (userId1 != null && userId2 != null) {
                                // Book_home.books 리스트를 순회하면서 사용자 정보를 가져오기
                                for (Book_info book : Book_home.books) {
                                    if (userId1.equals(MainActivity.name_num) || userId2.equals(MainActivity.name_num)) {
                                        My_chat_info chatInfo = new My_chat_info(roomId, userId1, userId2, null);
                                        chats.add(chatInfo);
                                        break; // 사용자 정보를 찾았으면 더 이상 순회할 필요가 없음
                                    }
                                }
                            }
                        }

                        mRecyclerAdapter_c.setChatsList(chats);
                        mRecyclerView.setAdapter(mRecyclerAdapter_c);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(Book_mine.this));
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // 에러 처리
                    }
                });
            }
        });

        //------------------------------------------------------------------------------------------
        back_btn_used = findViewById(R.id.back_btn_used_home);
        home_btn_used = findViewById(R.id.home_btn_used);
        sell_btn_used = findViewById(R.id.sell_btn_used);
        mine_btn_used = findViewById(R.id.mine_btn_used);

        back_btn_used.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
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
