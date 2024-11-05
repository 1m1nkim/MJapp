package com.example.firebase_test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // 파이어베이스 데이터베이스 연동
    private FirebaseDatabase database;
    private DatabaseReference stuReference;
    private DatabaseReference findReference;
    private DatabaseReference passC_Reference;
    String edit_Num_p; // 클래스 레벨에서 변수를 선언
    public static String name_num;
    public static String find_num;
    public static String login_hak;
    public static String loign_name;

    Dialog findDialog;
    Dialog findDialog_p;
    Dialog findDialognum_g;
    Dialog findDialognum_b;
    Dialog changePass;
    Dialog passDialogb;
    Dialog change_good;
    Dialog notsameDialog;
    String stuNum;
    EditText login_et;
    EditText pass_et;
    boolean success = false;
    String find_name;
    String find_birth;
    String find_num_s;
    Boolean check_find;
    TextView find_id_good;
    EditText edit_name;
    EditText edit_birth;

    public static ArrayList<Student> stulist = new ArrayList<>();
    ArrayList<Student> findlist = new ArrayList<>();
    boolean check_cb = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        login_et = (EditText) findViewById(R.id.Login_num);
        pass_et = (EditText) findViewById(R.id.pas_num);
        CheckBox ch_auto = (CheckBox) findViewById(R.id.check_login);
        Button find_id = (Button) findViewById(R.id.text_find_hak);
        Button find_pass = (Button) findViewById(R.id.text_find_pas);
        Button login_btn = (Button) findViewById(R.id.login_btn);
        TextView failText = (TextView) findViewById(R.id.login_fail_text);
        Button auto_login_fill = (Button) findViewById(R.id.auto_login_fill);


        findDialog = new Dialog(MainActivity.this);
        findDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        findDialog.setContentView(R.layout.findnumber);

        findDialog_p = new Dialog(MainActivity.this);
        findDialog_p.requestWindowFeature(Window.FEATURE_NO_TITLE);
        findDialog_p.setContentView(R.layout.findpass);

        findDialognum_g = new Dialog(MainActivity.this);
        findDialognum_g.requestWindowFeature(Window.FEATURE_NO_TITLE);
        findDialognum_g.setContentView(R.layout.findnum_good);

        findDialognum_b = new Dialog(MainActivity.this);
        findDialognum_b.requestWindowFeature(Window.FEATURE_NO_TITLE);
        findDialognum_b.setContentView(R.layout.findnum_bad);

        changePass = new Dialog(MainActivity.this);
        changePass.requestWindowFeature(Window.FEATURE_NO_TITLE);
        changePass.setContentView(R.layout.find_pass_good);

        passDialogb = new Dialog(MainActivity.this);
        passDialogb.requestWindowFeature(Window.FEATURE_NO_TITLE);
        passDialogb.setContentView(R.layout.find_pass_bad);


        change_good = new Dialog(MainActivity.this);
        change_good.requestWindowFeature(Window.FEATURE_NO_TITLE);
        change_good.setContentView(R.layout.change_pass_good);

        notsameDialog = new Dialog(MainActivity.this);
        notsameDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        notsameDialog.setContentView(R.layout.notsamepass);

        edit_name = (EditText) findDialog.findViewById(R.id.edit_id);
        edit_birth = (EditText) findDialog.findViewById(R.id.edit_birth);

        database = FirebaseDatabase.getInstance();
        stuReference = database.getReference("stu");
        findReference = database.getReference("stu");
        passC_Reference = database.getReference("stu");
        failText.setVisibility(View.INVISIBLE);
        login_btn.setEnabled(false);
        login_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 9) {
                    login_btn.setEnabled(true);
                    login_btn.setTextColor(Color.WHITE);
                    login_btn.setBackgroundResource(R.drawable.login_btn_enable);
                } else {
                    login_btn.setEnabled(false);
                    login_btn.setBackgroundResource(R.drawable.login_btn_design);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        //--------------------------로그인 버튼-----------------------------
        login_btn.setOnClickListener(new View.OnClickListener() {

            String num;
            String paswd;
            String name;
            boolean check_login = false;
            @Override
            public void onClick(View view) {
                stuNum = login_et.getText().toString();

                login_hak = login_et.getText().toString();
                String login_pass = pass_et.getText().toString();

                findReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        stulist.clear();
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            Student stu = snapshot.getValue(Student.class);
                            stulist.add(stu);
                        }
                        for(int i = 0; i<stulist.size(); i++){
                            name = stulist.get(i).getstuName();
                            num = stulist.get(i).getStuNum();
                            paswd = stulist.get(i).getStuPasswd();
                            loign_name = name;          //이름값
                            if (num.equals(login_hak) && paswd.equals(login_pass)) {
                                name_num = name + "(" + num + ")";
                                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                check_login = false;
                                break;
                            } else {
                                check_login = true;
                            }
                        }
                        if(check_login){
                            failText.setVisibility(View.VISIBLE);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    failText.setVisibility(View.INVISIBLE); // 3초뒤 실행할 작업
                                }
                            }, 3000); // 3000==3초
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });


            }
        });

        //--------------------------찾기 버튼-----------------------------
        find_id.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                findDialog.show(); // 다이얼로그 띄우기



                edit_name.setText("");
                edit_birth.setText("");
                //--------------------------찾기-확인 버튼-----------------------------
                Button submit_btn = findDialog.findViewById(R.id.submit_btn);
                submit_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String find_name_et = edit_name.getText().toString();
                        String find_birth_et = edit_birth.getText().toString();
                        stuReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                findlist.clear();
                                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                    Student stu = snapshot.getValue(Student.class);
                                    findlist.add(stu);
                                }
                                for(int i = 0; i<findlist.size(); i++){
                                    find_name = findlist.get(i).getstuName().toString();
                                    find_birth = findlist.get(i).getStuBirth().toString();
                                    if (find_name.equals(find_name_et) && find_birth.equals(find_birth_et)) {
                                        find_num_s = findlist.get(i).getStuNum();
                                        check_find = false;
                                        find_id_good = (TextView) findDialognum_g.findViewById(R.id.find_id_good);
                                        find_id_good.setText(find_num_s);
                                        findDialog.dismiss();
                                        findDialognum_g.show();
                                        break;
                                    } else {
                                        check_find = true;

                                    }
                                }
                                if(check_find){
                                    findDialognum_b.show();
                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError error) {

                            }
                        });
                    }
                });
                //--------------------------찾기-찾았을경우-확인 버튼-----------------------------

                Button submit_btn_ng = findDialognum_g.findViewById(R.id.submit_btn_ng);
                submit_btn_ng.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        findDialognum_g.dismiss();// 다이얼로그 닫기
                    }
                });
                //--------------------------찾기-못찾았을경우-확인 버튼-----------------------------
                Button submit_btn_nb = findDialognum_b.findViewById(R.id.submit_btn_nb);
                submit_btn_nb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        findDialognum_b.dismiss();// 다이얼로그 닫기
                    }
                });
                //--------------------------취소 버튼------------------------------
                Button noBtn = findDialog.findViewById(R.id.cancel_btn);
                noBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        findDialog.dismiss();// 다이얼로그 닫기
                    }
                });
            }
        });
//------------------------------------------------------------------------------------------------------------------------------------------------------


        find_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findDialog_p.show(); // 다이얼로그 띄우기

                final EditText edit_name_p = findDialog_p.findViewById(R.id.edit_name_p);
                final EditText edit_num_p = findDialog_p.findViewById(R.id.edit_num_p);
                edit_name_p.setText("");
                edit_num_p.setText("");

                //--------------------------찾기-확인 버튼-----------------------------
                Button submit_btn_p = findDialog_p.findViewById(R.id.submit_btn_p);
                submit_btn_p.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String edit_Name_p = edit_name_p.getText().toString();
                        edit_Num_p = edit_num_p.getText().toString(); // 값 할당

                        stuReference.orderByChild("stuNum").equalTo(edit_Num_p).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                boolean found = false;
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    Student student = snapshot.getValue(Student.class);
                                    if (student != null && student.getstuName().equals(edit_Name_p) && student.getStuNum().equals(edit_Num_p)) {

                                        // Both values match
                                        found = true;
                                        break;
                                    }
                                }
                                if (found) {

                                    findDialog_p.dismiss();
                                    changePass.show();
                                } else {
                                    edit_name_p.setText("");
                                    edit_num_p.setText("");
                                    passDialogb.show();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                // Error occurred during the query
                            }
                        });
                    }
                });

                //--------------------------찾기-취소 버튼-----------------------------
                Button cancel_Btn_p = findDialog_p.findViewById(R.id.cancel_btn_p);
                cancel_Btn_p.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        findDialog_p.dismiss();
                    }
                });

                //////////////////////////////////////////////////////////////////////

                Button submit_btn_nb = passDialogb.findViewById(R.id.submit_btn_nb);
                submit_btn_nb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        passDialogb.dismiss();
                    }
                });

                Button submit_btn_ng = notsameDialog.findViewById(R.id.submit_btn_ng);
                submit_btn_ng.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        notsameDialog.dismiss();
                    }
                });

                //--------------------------바꾼 후 확인 버튼------------------------------
                EditText ch_pass = changePass.findViewById(R.id.ch_pass);
                EditText ch_pass_check = changePass.findViewById(R.id.ch_pass_check);
                ch_pass.setText("");
                ch_pass_check.setText("");

                Button submit_btn_c = changePass.findViewById(R.id.submit_btn_c);
                submit_btn_c.setOnClickListener(new View.OnClickListener() {        //비밀번호 변경하는부분----------------------------------------------------
                    @Override
                    public void onClick(View view) {
                        String ch_Pass = ch_pass.getText().toString();
                        String ch_Pass_check = ch_pass_check.getText().toString();
                        System.out.println(ch_Pass +"/"+ ch_Pass_check + "-----------------------------------------------------------------------");
                        if (ch_Pass.equals(ch_Pass_check)) {
                            String newPassword = ch_Pass;
                            Query query = passC_Reference.orderByChild("stuNum").equalTo(edit_Num_p);
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                        // 조회된 학생의 비밀번호 변경
                                        DatabaseReference stuRef = childSnapshot.getRef().child("stuPasswd");
                                        stuRef.setValue(newPassword)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        // 변경 성공 시 동작할 코드
                                                        Toast.makeText(MainActivity.this, "비밀번호 변경 성공", Toast.LENGTH_SHORT).show();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(Exception e) {
                                                        // 변경 실패 시 동작할 코드
                                                        Toast.makeText(MainActivity.this, "비밀번호 변경 실패", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    // 쿼리 취소 시 동작할 코드
                                    Toast.makeText(MainActivity.this, "쿼리 취소됨", Toast.LENGTH_SHORT).show();
                                }
                            });
                            changePass.dismiss();
                            change_good.show();
                        } else {
                            ch_pass.setText("");
                            ch_pass_check.setText("");
                            notsameDialog.show();
                        }
                    }
                });


                //--------------------------바꾸기 취소 버튼-----------------------------
                Button ch_nobtn = changePass.findViewById(R.id.cancel_btn_c);
                ch_nobtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        changePass.dismiss();// 다이얼로그 닫기
                    }
                });

                //--------------------------changePass 취소 버튼-----------------------------
                Button noBtn_p = changePass.findViewById(R.id.cancel_btn_c);
                noBtn_p.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        changePass.dismiss();// 다이얼로그 닫기
                    }
                });

                Button submit_btn_pass = change_good.findViewById(R.id.submit_btn_pass);
                submit_btn_pass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        change_good.dismiss();
                    }
                });
            }
        });
        auto_login_fill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check_cb == false){
                    ch_auto.setChecked(true);
                    check_cb = true;
                }else if(check_cb == true){
                    ch_auto.setChecked(false);
                    check_cb = false;
                }
            }
        });
    }
}