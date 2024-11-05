package com.example.firebase_test;//package com.example.firebase_test;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.database.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {
    TextView text_name;
    String name;
    Button btn_logout; // 로그아웃
    View rectangle_logout;
    TextView text_btn_logout;
    Button btn_login; // 로그인
    View rectangle_8;
    TextView text_btn_login;
    Button btn_notice; // 공지사항 버튼
    Button btn_chat; // 챗봇 버튼
    Button btn_web; // 웹사이트 버튼
    Button btn_bus; // 셔틀버스 버튼
    Button btn_call; // 교내전화 버튼
    Button btn_code; // 학생증 버튼
    Button btn_Date; // 학사일정 버튼
    Button btn_left;
    Button btn_right;
    TextView text_DATE; // 학사일정 년도, 월
    int month;
    TextView text_date_con; // 학사일정 내용
    Schedule schedule;
    MainActivity mainActivity = new MainActivity();
    ArrayList<Schedule> scheduleList = new ArrayList<>();
    TextView text_lunchmenu; // 교내식당 점심
    TextView text_dinnermenu; // 교내식당 저녁
    Button btn_book;
    ImageView img_book1;
    TextView text_bookname1;
    Button btn_book1;
    ImageView img_book2;
    TextView text_bookname2;
    Button btn_book2;
    ImageView img_book3;
    TextView text_bookname3;
    Button btn_book3;
    ImageView img_book4;
    TextView text_bookname4;
    Button btn_book4;
    ImageView img_book5;
    TextView text_bookname5;
    Button btn_book5;

    private FirebaseDatabase database;
    private DatabaseReference menuReference;
    private DatabaseReference scheReference;
    ArrayList<Menu> menulist;
    Date currentDate = new Date();
    String now_date;
    String date;
    String lunch;
    String dinner;
    String dayString;
    String monthString;
    String yearString;

    // 중고서적
    Recycler_Adapter_books mRecyclerAdapter;
    private ArrayList<Book_info> books_main;
    int i = 0;
    int indexI = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        text_name = findViewById(R.id.text_name);
        btn_logout = findViewById(R.id.btn_logout);
        rectangle_logout = findViewById(R.id.rectangle_logout);
        text_btn_logout = findViewById(R.id.text_btn_logout);
        rectangle_8 = findViewById(R.id.rectangle_8);
        text_btn_login = findViewById(R.id.text_btn_login);
        text_dinnermenu = findViewById(R.id.text_dinnermenu);
        text_lunchmenu = findViewById(R.id.text_lunchmenu);
        text_date_con = findViewById(R.id.text_date_con);

        img_book1 = findViewById(R.id.img_book1);
        text_bookname1 = findViewById(R.id.text_bookname1);
        btn_book1 = findViewById(R.id.btn_book1);
        img_book2 = findViewById(R.id.img_book2);
        text_bookname2 = findViewById(R.id.text_bookname2);
        btn_book2 = findViewById(R.id.btn_book2);
        img_book3 = findViewById(R.id.img_book3);
        text_bookname3 = findViewById(R.id.text_bookname3);
        btn_book3 = findViewById(R.id.btn_book3);
        img_book4 = findViewById(R.id.img_book4);
        text_bookname4 = findViewById(R.id.text_bookname4);
        btn_book4 = findViewById(R.id.btn_book4);
        img_book5 = findViewById(R.id.img_book5);
        text_bookname5 = findViewById(R.id.text_bookname5);
        btn_book5 = findViewById(R.id.btn_book5);


        name = mainActivity.name_num;

        // -----------------로그인 페이지 이동
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        // -----------------로그아웃
        btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_name.setText("로그인을 해주세요!");
                name = null;
                btn_logout.setVisibility(View.VISIBLE);
                rectangle_logout.setVisibility(View.VISIBLE);
                text_btn_logout.setVisibility(View.VISIBLE);
                // -----------------로그아웃 버튼 비활성화
                btn_logout.setVisibility(View.GONE);
                rectangle_logout.setVisibility(View.GONE);
                text_btn_logout.setVisibility(View.GONE);
            }
        });
        if (name != null) {
            text_name.setText(name);
            // -----------------로그인 버튼 비활성화
            btn_logout.setVisibility(View.GONE);
            rectangle_logout.setVisibility(View.GONE);
            text_btn_logout.setVisibility(View.GONE);
            // -----------------로그아웃 버튼 활성화
            btn_logout.setVisibility(View.VISIBLE);
            rectangle_logout.setVisibility(View.VISIBLE);
            text_btn_logout.setVisibility(View.VISIBLE);
        } else {
            MainActivity.stulist.clear(); // 데이터베이스 초기화
            // -----------------로그인 버튼 활성화
            btn_logout.setVisibility(View.VISIBLE);
            rectangle_logout.setVisibility(View.VISIBLE);
            text_btn_logout.setVisibility(View.VISIBLE);
            // -----------------로그아웃 버튼 비활성화
            btn_logout.setVisibility(View.GONE);
            rectangle_logout.setVisibility(View.GONE);
            text_btn_logout.setVisibility(View.GONE);
        }

        // -----------------중앙 메뉴 - 공지사항 페이지 이동
        btn_notice = findViewById(R.id.btn_notice);
        btn_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Notice_main.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        // -----------------중앙 메뉴 - 챗봇 페이지 이동
        btn_chat = findViewById(R.id.btn_chat);
        btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Chatbot.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        // -----------------중앙 메뉴 - 웹사이트 페이지 이동
        btn_web = findViewById(R.id.btn_web);
        btn_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WebsiteActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        // -----------------중앙 메뉴 - 셔틀버스 페이지 이동
        btn_bus = findViewById(R.id.btn_bus);
        btn_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShuttleActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        // -----------------중앙 메뉴 - 교내전화 페이지 이동
        btn_call = findViewById(R.id.btn_call);
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CallActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
        // -----------------중앙 메뉴 - 바코드 페이지 이동
        btn_code = findViewById(R.id.btn_code);
        btn_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name != null) {
                    Intent intent = new Intent(getApplicationContext(), BarcodeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else if(name == null){
                    Toast.makeText(getApplicationContext(), "로그인 후 사용할 수 있습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // -----------------학사일정 텍스트
        database = FirebaseDatabase.getInstance();
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat yearFormat = new SimpleDateFormat("YYYY");
        dayString = dayFormat.format(currentDate);
        monthString = monthFormat.format(currentDate);
        yearString = yearFormat.format(currentDate);
        month = Integer.parseInt(monthString) - 1;

        text_DATE = findViewById(R.id.text_DATE);
        text_DATE.setText(yearString + "년 " + monthString + "월");

        scheReference = database.getReference("schedule");
        scheReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                scheduleList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    schedule = snapshot.getValue(Schedule.class);
                    scheduleList.add(schedule);
                }
                if (month >= 0 && month < scheduleList.size()) { // 리스트 크기 검사
                    Schedule currentSchedule = scheduleList.get(month);
                    if (currentSchedule != null && currentSchedule.getSche() != null) { // null 검사
                        String formattedValue = currentSchedule.getSche().replace("\\n", "\n");
                        text_date_con.setText(formattedValue);
                    } else {
                        text_date_con.setText("정보가 없습니다.");
                    }
                } else {
                    text_date_con.setText("해당 월의 정보가 없습니다.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // -----------------학사일정 페이지 이동
        btn_Date = findViewById(R.id.btn_Date);
        btn_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ScheduleActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        // -----------------학시알정 왼쪽 버튼
        btn_left = findViewById(R.id.btn_left);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                month -= 1;
                text_DATE.setText(yearString + "년 " + (month + 1) + "월");
                if (month >= 0 && month < scheduleList.size()) {
                    String formattedValue = scheduleList.get(month).getSche().replace("\\n", "\n");
                    text_date_con.setText(formattedValue);
                } else {
                    // 예외 처리: 월 범위를 벗어나는 경우
                }
            }
        });
        // -----------------학시알정 오른쪽 버튼
        btn_right = findViewById(R.id.btn_right);
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                month += 1;
                text_DATE.setText(yearString + "년 " + (month + 1) + "월");
                if (month >= 0 && month < scheduleList.size()) {
                    String formattedValue = scheduleList.get(month).getSche().replace("\\n", "\n");
                    text_date_con.setText(formattedValue);
                } else {
                    // 예외 처리: 월 범위를 벗어나는 경우
                }
            }
        });
        // -----------------교내식당
        menulist = new ArrayList<Menu>();
        now_date = monthString + dayString ;
        menuReference = database.getReference("menu");
        menuReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                menulist.clear();
                // 데이터 가져오기 성공
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Menu menu = snapshot.getValue(Menu.class);
                    menulist.add(menu);
                }
                // 날짜 비교 및 메뉴 선택
                boolean menuFound = false;
                for (Menu menu : menulist) {
                    date = menu.getDate().toString();
                    lunch = menu.getLunch().toString();
                    dinner = menu.getDinner().toString();

                    if (now_date.equals(date)) {
                        menuFound = true;
                        text_lunchmenu.setText(lunch);
                        text_dinnermenu.setText(dinner);
                        break;  // 일치하는 메뉴를 찾았으면 반복문 종료
                    }
                }
                if (!menuFound) {
                    text_lunchmenu.setText("등록된 메뉴 없음");
                    text_dinnermenu.setText("등록된 메뉴 없음");
                }
            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {
                // 예외 발생 시 처리 로직 추가
                Toast.makeText(MainActivity2.this, "데이터베이스 오류: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // -----------------중고서적 페이지 이동
        btn_book = (Button) findViewById(R.id.btn_book);
        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name != null) {
                    Intent intent = new Intent(getApplicationContext(), Book_home.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else if(name == null){
                    Toast.makeText(getApplicationContext(), "로그인 후 사용할 수 있습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mRecyclerAdapter = new Recycler_Adapter_books();
        books_main = new ArrayList<>();
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("books");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                books_main.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Book_info bookk = snapshot.getValue(Book_info.class);
                    books_main.add(bookk);
                    Glide.with(getApplicationContext())
                            .load(books_main.get(0).getImageUrl()) // getImageUrl() 메서드를 통해 이미지의 URL을 가져옴
                            .into(img_book1);
                    text_bookname1.setText(books_main.get(0).getSell_name_ed());

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // 에러 처리
            }
        });
        DatabaseReference databaseRef_1 = FirebaseDatabase.getInstance().getReference("books");
        databaseRef_1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                books_main.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Book_info bookk = snapshot.getValue(Book_info.class);
                    books_main.add(bookk);
                    if (books_main.size() > 1) {
                        // Set the second item's data (index 1) in img_book2 and text_bookname2
                        Glide.with(getApplicationContext())
                                .load(books_main.get(1).getImageUrl())
                                .into(img_book2);
                        text_bookname2.setText(books_main.get(1).getSell_name_ed());
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // 에러 처리
            }
        });
        DatabaseReference databaseRef_2 = FirebaseDatabase.getInstance().getReference("books");
        databaseRef_2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                books_main.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Book_info bookk = snapshot.getValue(Book_info.class);
                    books_main.add(bookk);
                    if (books_main.size() > 2) {
                        // Set the second item's data (index 1) in img_book2 and text_bookname2
                        Glide.with(getApplicationContext())
                                .load(books_main.get(2).getImageUrl())
                                .into(img_book3);
                        text_bookname3.setText(books_main.get(2).getSell_name_ed());
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // 에러 처리
            }
        });
        DatabaseReference databaseRef_3 = FirebaseDatabase.getInstance().getReference("books");
        databaseRef_3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                books_main.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Book_info bookk = snapshot.getValue(Book_info.class);
                    books_main.add(bookk);
                    if (books_main.size() > 3) {
                        // Set the second item's data (index 1) in img_book2 and text_bookname2
                        Glide.with(getApplicationContext())
                                .load(books_main.get(3).getImageUrl())
                                .into(img_book4);
                        text_bookname4.setText(books_main.get(3).getSell_name_ed());
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // 에러 처리
            }
        });
        DatabaseReference databaseRef_4 = FirebaseDatabase.getInstance().getReference("books");
        databaseRef_4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                books_main.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Book_info bookk = snapshot.getValue(Book_info.class);
                    books_main.add(bookk);
                    if (books_main.size() > 4) {
                        // Set the second item's data (index 1) in img_book2 and text_bookname2
                        Glide.with(getApplicationContext())
                                .load(books_main.get(4).getImageUrl())
                                .into(img_book5);
                        text_bookname5.setText(books_main.get(4).getSell_name_ed());
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // 에러 처리
            }
        });

    }
}