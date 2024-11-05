package com.example.firebase_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Notice_main extends AppCompatActivity {

    // 검색시 같은 이름이 있는 아이템이 담길 리스트
    ArrayList<Notice_item> search_list = new ArrayList<>();
    // recyclerView에 추가할 아이템 리스트
    ArrayList<Notice_item> original_list = new ArrayList<>();
    // 어댑터
    Notice_Adapter adapter;
    // EditText
    EditText editText;

    Button back_btn_han;
    public static ArrayList<Notice> notices = new ArrayList<>();
    int indexx = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("notice");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                notices.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Notice notice = snapshot.getValue(Notice.class);
                    //공지사항의 가장 첫번째 화면이 켜지면서 "notice"의 데이터베이스 값 모두 가져오기
                    notice.setnIndex(indexx++);
                    notices.add(notice);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                //에러처리
            }
        });
        back_btn_han = (Button) findViewById(R.id.back_btn_han);
        back_btn_han.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        // 리스트에 아이템 추가
        original_list.add(new Notice_item("2023-1학기 교외 국가근로장학생 선발 안내 및 비대면 오리엔..."));
        original_list.add(new Notice_item("미래융합학부 사무공간 리모델링으로 인한 사무실 이전 안내"));
        original_list.add(new Notice_item("[혁신] 2023학년도 기초학습능력 향상교육(수학) 실시 안내"));
        original_list.add(new Notice_item("[혁신] 2023학년도 1학기 상담센터 프로그램 안내"));
        original_list.add(new Notice_item("[혁신] 2023학년도 1학기 상담센터 프로그램 안내"));
        original_list.add(new Notice_item("[혁신] 2023학년도 1학기 학습역량 진단검사 실시 재안내(~6/23)"));
        original_list.add(new Notice_item("[혁신] 바르다 명지인(인성교육 프로그램) 운영 안내(~6/30)"));
        original_list.add(new Notice_item("[안내]교내 금연구역 준수 및 흡연예절 협조 안내"));
        original_list.add(new Notice_item("2023-1 교직원 인권/각종 폭력 및 아동학대 예방교육 실시 안내"));
        original_list.add(new Notice_item("2023학년도 상반기 지역사회 연계 도서관 문화행사 안내"));
        original_list.add(new Notice_item("2023학년도 1학기 융복합 지식콘서트 운영안내(~6/30)"));
        original_list.add(new Notice_item("[혁신] 2023학년도 1학기 스토리북(학습전략 콘텐츠) 운영 안내.."));
        original_list.add(new Notice_item("[필수]2023-1학기 MJC-4C 핵심역량진단 실시 안내(결과점..)"));
        original_list.add(new Notice_item("[혁신]2023학년도 1학기 글로벌어학아카데미 모집안내"));
        original_list.add(new Notice_item("[창업특강] 로스터리 카페 창업과 비전(2022.12.23)"));
        original_list.add(new Notice_item("[생활관운영팀] 2023학년도 1학기 생활관운영팀 근로장학생 .."));
        original_list.add(new Notice_item("[기획평가팀] 2023학년도 하계방학 근로장학생 모집"));
        original_list.add(new Notice_item("[현장실습지원센터] 2023학년도 1학기 국가근로장학생 모집"));
        original_list.add(new Notice_item("[학사지원팀] 2023학년도 1학기 국가근로장학생 선발"));
        original_list.add(new Notice_item("2023 하계 계절학기 원격강좌(통합전공) 증원 안내"));
        //asd


        editText = findViewById(R.id.editText);

        // editText 리스터 작성
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String searchText = editText.getText().toString();
                search_list.clear();

                if(searchText.equals("")){
                    adapter.setItems(original_list);
                }
                else {
                    // 검색 단어를 포함하는지 확인
                    for(int a = 0; a < original_list.size(); a++){
                        if(original_list.get(a).title.toLowerCase().contains(searchText.toLowerCase())){
                            search_list.add(original_list.get(a));
                        }
                        adapter.setItems(search_list);
                    }
                }
            }
        });

        Notice_Adapter.setOnItemClickListener(new Notice_Adapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent intent = new Intent(getApplicationContext(), Notice_info.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        // 리사이클러뷰, 어댑터 연결
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Notice_Adapter(original_list);
        recyclerView.setAdapter(adapter);
    }
}