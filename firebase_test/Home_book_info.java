package com.example.firebase_test;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class Home_book_info extends AppCompatActivity {
    TextView book_info_name;
    TextView book_info_author_mine;
    TextView info_author_mine;
    TextView book_info_publishd_mine;
    TextView book_info_price;
    TextView explain_info;
    TextView state_d;
    TextView sudan_area;
    TextView sudan_pac;
    TextView want_area_c;
    ImageView image_viewer1;
    ImageView image_viewer2;
    Button get_btn;
    Button home_btn_used;
    Button sell_btn_used;
    Button mine_btn_used;
    Button back_btn_used;
    int i = 0;
    int indexi = -1;
    int pois = Recycler_Adapter_books.bookposition;
    String sell_name_ed;
    String sell_author_ed;
    String sell_publisher_ed;
    String sell_publishd_ed;
    String sell_price_ed;
    String bozon;
    String imageUrl;
    String imageUrl2;
    String sudan;
    String plain;
    String want_area;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sell_book_info);

        ArrayList<Book_info> bookss;
        book_info_name = findViewById(R.id.book_info_name);
        book_info_author_mine = findViewById(R.id.book_info_author_mine);
        info_author_mine = findViewById(R.id.info_author_mine);
        book_info_publishd_mine = findViewById(R.id.book_info_publishd_mine);
        book_info_price = findViewById(R.id.book_info_price);
        explain_info = findViewById(R.id.explain_info);
        state_d = findViewById(R.id.state_d);
        sudan_area = findViewById(R.id.sudan_area);
        sudan_pac = findViewById(R.id.sudan_pac);
        want_area_c = findViewById(R.id.want_area_c);
        image_viewer1 = findViewById(R.id.image_viewer1);
        image_viewer2 = findViewById(R.id.image_viewer2);
        get_btn = findViewById(R.id.get_btn);
        home_btn_used = findViewById(R.id.home_btn_used);
        sell_btn_used = findViewById(R.id.sell_btn_used);
        mine_btn_used = findViewById(R.id.mine_btn_used);
        back_btn_used = findViewById(R.id.back_btn_used);
        bookss = new ArrayList<Book_info>();

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("books");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bookss.clear();
                i=0;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Book_info bookk = snapshot.getValue(Book_info.class);
                    bookss.add(bookk);
                    if(i == pois){
                        indexi = Recycler_Adapter_books.pIndex;
                        sell_name_ed = Book_home.books.get(indexi).getSell_name_ed();
                        book_info_name.setText(sell_name_ed);
                        sell_author_ed = Book_home.books.get(indexi).getSell_author_ed();
                        book_info_author_mine.setText(sell_author_ed);
                        sell_publisher_ed = Book_home.books.get(indexi).getSell_publisher_ed();
                        info_author_mine.setText(sell_publisher_ed);
                        sell_publishd_ed = Book_home.books.get(indexi).getSell_publishd_ed();
                        book_info_publishd_mine.setText(sell_publishd_ed);
                        sell_price_ed = Book_home.books.get(indexi).getSell_price_ed() + "원";
                        book_info_price.setText(sell_price_ed);
                        bozon = Book_home.books.get(indexi).getBozon();
                        state_d.setText(bozon);
                        sudan = Book_home.books.get(indexi).getSudan();
                        if(sudan =="직거래"){
                            sudan_area.setTextColor(Color.parseColor("#000000"));
                            sudan_pac.setTextColor(Color.parseColor("#616161"));
                        }else if(sudan == "택배"){
                            sudan_area.setTextColor(Color.parseColor("#616161"));
                            sudan_pac.setTextColor(Color.parseColor("#000000"));
                        }else if(sudan == "둘다"){
                            sudan_area.setTextColor(Color.parseColor("#000000"));
                            sudan_pac.setTextColor(Color.parseColor("#000000"));
                        }
                        plain = Book_home.books.get(indexi).getPlain();
                        explain_info.setText(plain);
                        want_area = Book_home.books.get(indexi).getArea();
                        want_area_c.setText(want_area);
                        Glide.with(getApplicationContext())
                                .load(Book_home.books.get(indexi).getImageUrl()) // getImageUrl() 메서드를 통해 이미지의 URL을 가져옴
                                .into(image_viewer1);
                        Glide.with(getApplicationContext())
                                .load(Book_home.books.get(indexi).getImageUrl2()) // getImageUrl() 메서드를 통해 이미지의 URL을 가져옴
                                .into(image_viewer2);
                    }
                    i++;
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // 에러 처리
            }
        });

        get_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indexi = Recycler_Adapter_books.pIndex;
                String roomname = Book_home.books.get(indexi).getSell_name_ed().toString();
                String myuser = MainActivity.name_num;
                String selluser = Book_home.books.get(indexi).getName_num();
                Log.d("Tga", "---------------------------" + myuser + selluser);
                createChatRoom(myuser, selluser, roomname);
                loadChatRooms(myuser);
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
        back_btn_used.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    private void createChatRoom(String userId1, String userId2, String roomName) {
        DatabaseReference chatRoomsRef = FirebaseDatabase.getInstance().getReference("chatRooms");

        String roomId = chatRoomsRef.push().getKey(); // 채팅방 고유 ID 생성

        ChatRoom chatRoom = new ChatRoom(roomId, userId1, userId2, null);
        chatRoomsRef.child(roomId).setValue(chatRoom);
    }
    private void loadChatRooms(String userId) {
        DatabaseReference userChatRoomsRef = FirebaseDatabase.getInstance().getReference("userChatRooms").child(userId);

        userChatRoomsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                for (DataSnapshot roomSnapshot : dataSnapshot.getChildren()) {
                    String roomId = roomSnapshot.getKey();

                    // TODO: 채팅방 정보를 사용하여 화면에 표시하거나 목록에 추가하는 코드 작성
                }
            }

            @Override
            public void onCancelled(@NotNull DatabaseError databaseError) {
                // 처리 중 에러가 발생한 경우
            }
        });
    }
}
