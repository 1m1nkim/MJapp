package com.example.firebase_test;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Mine_book_info extends AppCompatActivity {
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
    Button home_btn_used;
    Button sell_btn_used;
    Button mine_btn_used;
    Button exp_btn;
    Button price_re_btn;
    Button ex_re_btn;

    EditText ed_price;
    Button submit_btn_rep;
    Button cancle_btn_rep;

    EditText ed_explain;
    Button submit_btn_reex;
    Button cancle_btn_reex;
    ArrayList<Book_info> rbooks;
    int j = 0;
    int poi = Recycler_Adapter_mine.mineposition;
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
    Dialog re_price_dia;
    Dialog re_explain_dia;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_book_info);

        ArrayList<Book_info> books;
        exp_btn = findViewById(R.id.exp_btn);
        price_re_btn = findViewById(R.id.price_re_btn);
        ex_re_btn = findViewById(R.id.ex_re_btn);
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

        home_btn_used = findViewById(R.id.home_btn_used);
        sell_btn_used = findViewById(R.id.sell_btn_used);
        mine_btn_used = findViewById(R.id.mine_btn_used);

        re_price_dia = new Dialog(Mine_book_info.this);
        re_price_dia.requestWindowFeature(Window.FEATURE_NO_TITLE);
        re_price_dia.setContentView(R.layout.re_price_dia);

        re_explain_dia = new Dialog(Mine_book_info.this);
        re_explain_dia.requestWindowFeature(Window.FEATURE_NO_TITLE);
        re_explain_dia.setContentView(R.layout.re_explain_dia);


        ed_price = re_price_dia.findViewById(R.id.ed_price);
        submit_btn_rep = re_price_dia.findViewById(R.id.submit_btn_rep);
        cancle_btn_rep = re_price_dia.findViewById(R.id.cancle_btn_rep);

        ed_explain = re_explain_dia.findViewById(R.id.ed_explain);
        submit_btn_reex = re_explain_dia.findViewById(R.id.submit_btn_reex);
        cancle_btn_reex = re_explain_dia.findViewById(R.id.cancle_btn_reex);

        books = new ArrayList<Book_info>();

        rbooks = new ArrayList<Book_info>();
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference(MainActivity.name_num + "books");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                books.clear();
                j=0;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Book_info bookk = snapshot.getValue(Book_info.class);
                    books.add(bookk);
                    if(j == poi){
                        sell_name_ed = books.get(j).getSell_name_ed();
                        book_info_name.setText(sell_name_ed);
                        sell_author_ed = books.get(j).getSell_author_ed();
                        book_info_author_mine.setText(sell_author_ed);
                        sell_publisher_ed = books.get(j).getSell_publisher_ed();
                        info_author_mine.setText(sell_publisher_ed);
                        sell_publishd_ed = books.get(j).getSell_publishd_ed();
                        book_info_publishd_mine.setText(sell_publishd_ed);
                        sell_price_ed = books.get(j).getSell_price_ed() + "원";
                        book_info_price.setText(sell_price_ed);
                        bozon = books.get(j).getBozon();
                        state_d.setText(bozon);
                        sudan = books.get(j).getSudan();
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
                        plain = books.get(j).getPlain();
                        explain_info.setText(plain);
                        want_area = books.get(j).getArea();
                        want_area_c.setText(want_area);
                        Glide.with(getApplicationContext())
                                .load(books.get(j).getImageUrl()) // getImageUrl() 메서드를 통해 이미지의 URL을 가져옴
                                .into(image_viewer1);
                        Glide.with(getApplicationContext())
                                .load(books.get(j).getImageUrl2()) // getImageUrl() 메서드를 통해 이미지의 URL을 가져옴
                                .into(image_viewer2);
                    }
                    j++;
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // 에러 처리
            }
        });
        DatabaseReference databaseRef_edp = FirebaseDatabase.getInstance().getReference(MainActivity.name_num + "books");


        price_re_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                re_price_dia.show();

            }
        });

        submit_btn_rep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = databaseRef_edp.orderByChild("sell_price_ed");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String newPrice = ed_price.getText().toString();
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            // 조회된 학생의 비밀번호 변경
                            DatabaseReference stuRef = childSnapshot.getRef().child("sell_price_ed");
                            stuRef.setValue(newPrice)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            // 변경 성공 시 동작할 코드
                                            Toast.makeText(Mine_book_info.this, "가격 변경 성공", Toast.LENGTH_SHORT).show();
                                            re_price_dia.dismiss();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(Exception e) {
                                            // 변경 실패 시 동작할 코드
                                            Toast.makeText(Mine_book_info.this, "가격 변경 성공", Toast.LENGTH_SHORT).show();
                                            re_price_dia.dismiss();
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // 쿼리 취소 시 동작할 코드
                        Toast.makeText(Mine_book_info.this, "쿼리 취소됨", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        exp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseRef_edp = FirebaseDatabase.getInstance().getReference(MainActivity.name_num + "books").child(books.get(poi).getKey());
                databaseRef_edp.removeValue();
                Intent intent = new Intent(getApplicationContext(), Book_mine.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                DatabaseReference databaseRef_edpx = FirebaseDatabase.getInstance().getReference("books").child(books.get(poi).getKey());
                databaseRef_edpx.removeValue();
            }
        });
        ex_re_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                re_explain_dia.show();
            }
        });
        DatabaseReference databaseRef_edex = FirebaseDatabase.getInstance().getReference(MainActivity.name_num + "books");
        submit_btn_reex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = databaseRef_edex.orderByChild("plain");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String newPrice = ed_explain.getText().toString();
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            // 조회된 학생의 비밀번호 변경
                            DatabaseReference stuRef = childSnapshot.getRef().child("plain");
                            stuRef.setValue(newPrice)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            // 변경 성공 시 동작할 코드
                                            Toast.makeText(Mine_book_info.this, "설명 변경 성공", Toast.LENGTH_SHORT).show();
                                            re_explain_dia.dismiss();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(Exception e) {
                                            // 변경 실패 시 동작할 코드
                                            Toast.makeText(Mine_book_info.this, "설명 변경 성공", Toast.LENGTH_SHORT).show();
                                            re_explain_dia.dismiss();
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // 쿼리 취소 시 동작할 코드
                        Toast.makeText(Mine_book_info.this, "쿼리 취소됨", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        cancle_btn_rep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                re_price_dia.dismiss();
            }
        });
        cancle_btn_reex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                re_explain_dia.dismiss();
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
