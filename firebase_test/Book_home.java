package com.example.firebase_test;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class Book_home extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private Recycler_Adapter_books mRecyclerAdapter;
    public static ArrayList<Book_info> books;
    Button back_btn_used;
    Button home_btn_used;
    Button sell_btn_used;
    Button mine_btn_used;
    EditText search_used_1;
    TextWatcher book_name_ed;
    int i =0;
    int indexI = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.used_home);

        mRecyclerView = findViewById(R.id.recyclerView);
        search_used_1 = findViewById(R.id.search_used_1);

        mRecyclerAdapter = new Recycler_Adapter_books();
        books = new ArrayList<>();

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("books");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                books.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Book_info bookk = snapshot.getValue(Book_info.class);
                    bookk.setIndex(indexI++);
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(Book_home.this));

        book_name_ed = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchText = s.toString().toLowerCase().trim();
                ArrayList<Book_info> filteredList = new ArrayList<>();

                for (Book_info book : books) {
                    if (book.getSell_name_ed().toLowerCase().contains(searchText)) {
                        filteredList.add(book);
                    }
                }

                mRecyclerAdapter.setBooksList(filteredList);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        search_used_1.addTextChangedListener(book_name_ed);

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
