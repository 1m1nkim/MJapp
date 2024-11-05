package com.example.firebase_test;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Book_seller extends AppCompatActivity {
    public String imageUrl_1 ="";
    public String imageUrl_2 ="";
    Button back_btn_used;
    Button home_btn_used;
    Button sell_btn_used;
    Button mine_btn_used;
    RadioGroup radio_group;
    RadioButton radio1;
    RadioButton radio2;
    RadioButton radio3;
    Button r_button_1;
    Button r_button_2;
    Button r_button_3;
    View sell_box1;
    View sell_box2;
    View sell_box3;
    View sell_box4;
    View sell_box5;
    View bozon_box1;
    View bozon_box2;
    View bozon_box3;
    View rectangle_7;
    View rectangle_8;
    ImageView camera_upload1;
    ImageView camera_upload2;    //카메라 사진 갖고오는 뷰
    TextView text_book_info;
    TextView bozon_pen;
    TextView bozon_boll;
    TextView bozon_clean;
    TextView text_book_bozon;
    EditText sell_name_ed;
    EditText sell_author_ed;
    EditText sell_publisher_ed;
    EditText sell_publishd_ed;
    EditText sell_price_ed;
    TextWatcher textWatcher;
    TextWatcher camera_textWatcher;
    TextWatcher area_textWatcher;
    TextWatcher exp_textWatcher;
    Button sell_next_btn;
    Button bozon_next_btn;
    TextView text_camera;
    ImageView camera_logo1;
    ImageView camera_logo2;
    Button camera_btn;
    Button camera_next_btn;
    Button upload_btn1;
    Button upload_btn2;
    TextView text_sudan;
    View off_box;
    View on_box;
    View both_box;
    RadioGroup radio_group_sudan;
    RadioButton radio1_sudan;
    RadioButton radio2_sudan;
    RadioButton radio3_sudan;
    Button r_button_1_sudan;
    Button r_button_2_sudan;
    Button r_button_3_sudan;
    Button sudan_next_btn;
    TextView off;
    TextView on;
    TextView both;
    TextView text_area;
    EditText ed_area;
    View rectangle_9;
    Button area_next_btn;
    Button area_btn;
    TextView text_explain;
    EditText ed_plain;
    Button exp_next_btn;
    Button exp_btn;
    View rectangle_10;
    TextView text_last;
    TextView pdf_;
    TextView pdf_1;
    TextView pdf_2;
    TextView pdf_3;
    Button last_btn;
    boolean camera_t1 = false;
    boolean camera_t2 = false;
    private static final int PICK_FILE_REQUEST = 1; // 파일 선택 요청 코드
    private static final int PICK_FILE_REQUEST_2 = 2;
    int verinum = 1;
    public void upload_camera1(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*"); // 모든 파일 형식을 선택할 수 있도록
        startActivityForResult(intent, PICK_FILE_REQUEST);
    }
    public void upload_camera2(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*"); // 모든 파일 형식을 선택할 수 있도록
        startActivityForResult(intent, PICK_FILE_REQUEST_2);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK) {
            // 파일 선택 결과를 처리
            Uri selectedFileUri = data.getData();

            // Firebase Storage에 파일 업로드
            StorageReference storageRef = FirebaseStorage.getInstance().getReference("book_images");
            String fileName = MainActivity.name_num + "_" + verinum++ + ".jpeg"; // 파일 이름은 원하는대로 설정
            StorageReference fileRef = storageRef.child(fileName);

            fileRef.putFile(selectedFileUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri downloadUrl) {
                                    imageUrl_1 = downloadUrl.toString();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Glide.with(Book_seller.this).load(downloadUrl).into(camera_upload1);
                                            camera_logo1.setVisibility(View.INVISIBLE);
                                            camera_t1 = true;
                                            camera_textWatcher.onTextChanged("", 0, 0, 0);
                                        }
                                    });
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NotNull Exception e) {
                            // 업로드 중 오류가 발생한 경우 처리
                        }
                    });

        }else if(requestCode == PICK_FILE_REQUEST_2 && resultCode == RESULT_OK){
            // 파일 선택 결과를 처리
            Uri selectedFileUri = data.getData();

            // Firebase Storage에 파일 업로드
            StorageReference storageRef = FirebaseStorage.getInstance().getReference("book_images");
            String fileName = MainActivity.name_num + "_" + verinum++ + ".jpeg"; // 파일 이름은 원하는대로 설정
            StorageReference fileRef = storageRef.child(fileName);

            fileRef.putFile(selectedFileUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri downloadUrl) {
                                    imageUrl_2 = downloadUrl.toString();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Glide.with(Book_seller.this).load(downloadUrl).into(camera_upload2);
                                            camera_logo2.setVisibility(View.INVISIBLE);
                                            camera_t2 = true;
                                            camera_textWatcher.onTextChanged("", 0, 0, 0);
                                        }
                                    });
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NotNull Exception e) {
                            // 업로드 중 오류가 발생한 경우 처리
                        }
                    });

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.used_sell);
        text_camera = findViewById(R.id.text_camera);
        camera_upload1 = findViewById(R.id.camera_upload1);
        camera_upload2 = findViewById(R.id.camera_upload2);
        camera_logo1 = findViewById(R.id.camera_logo1);
        camera_logo2 = findViewById(R.id.camera_logo2);
        camera_btn = findViewById(R.id.camera_btn);
        camera_next_btn = findViewById(R.id.camera_next_btn);
        upload_btn1 = findViewById(R.id.upload_btn1);
        upload_btn2 = findViewById(R.id.upload_btn2);
        text_book_bozon = findViewById(R.id.text_book_bozon);
        text_book_info = findViewById(R.id.text_book_info);
        radio_group = findViewById(R.id.radio_group);
        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);
        r_button_1 = findViewById(R.id.r_button_1);
        r_button_2 = findViewById(R.id.r_button_2);
        r_button_3 = findViewById(R.id.r_button_3);
        sell_box1 = findViewById(R.id.sell_box1);
        sell_box2 = findViewById(R.id.sell_box2);
        sell_box3 = findViewById(R.id.sell_box3);
        sell_box4 = findViewById(R.id.sell_box4);
        sell_box5 = findViewById(R.id.sell_box5);
        rectangle_7 = findViewById(R.id.rectangle_7);
        rectangle_8 = findViewById(R.id.rectangle_8);
        bozon_box1 = findViewById(R.id.bozon_box1);
        bozon_box2 = findViewById(R.id.bozon_box2);
        bozon_box3 = findViewById(R.id.bozon_box3);

        bozon_pen = findViewById(R.id.bozon_pen);
        bozon_boll = findViewById(R.id.bozon_boll);
        bozon_clean = findViewById(R.id.bozon_clean);

        sell_name_ed = findViewById(R.id.sell_name_ed);
        sell_author_ed = findViewById(R.id.sell_author_ed);
        sell_publisher_ed = findViewById(R.id.sell_publisher_ed);
        sell_publishd_ed = findViewById(R.id.sell_publishd_ed);
        sell_price_ed = findViewById(R.id.sell_price_ed);

        sell_next_btn = findViewById(R.id.sell_next_btn);
        bozon_next_btn = findViewById(R.id.bozon_next_btn);
        back_btn_used = findViewById(R.id.back_btn_used);

        text_sudan = findViewById(R.id.text_sudan);
        off_box = findViewById(R.id.off_box);
        on_box = findViewById(R.id.on_box);
        both_box = findViewById(R.id.both_box);
        radio_group_sudan = findViewById(R.id.radio_group_sudan);
        r_button_1_sudan = findViewById(R.id.r_button_1_sudan);
        r_button_2_sudan = findViewById(R.id.r_button_2_sudan);
        r_button_3_sudan = findViewById(R.id.r_button_3_sudan);
        radio1_sudan = findViewById(R.id.radio1_sudan);
        radio2_sudan = findViewById(R.id.radio2_sudan);
        radio3_sudan = findViewById(R.id.radio3_sudan);
        sudan_next_btn = findViewById(R.id.sudan_next_btn);
        off = findViewById(R.id.off);
        on = findViewById(R.id.on);
        both = findViewById(R.id.both);
        text_area = findViewById(R.id.text_area);
        ed_area = findViewById(R.id.ed_area);
        rectangle_9 = findViewById(R.id.rectangle_9);
        area_next_btn = findViewById(R.id.area_next_btn);
        area_btn = findViewById(R.id.area_btn);

        text_explain = findViewById(R.id.text_explain);
        ed_plain = findViewById(R.id.ed_plain);
        exp_next_btn = findViewById(R.id.exp_next_btn);
        exp_btn = findViewById(R.id.exp_btn);

        rectangle_10 = findViewById(R.id.rectangle_10);
        text_last = findViewById(R.id.text_last);
        pdf_ = findViewById(R.id.pdf_);
        pdf_1 = findViewById(R.id.pdf_1);
        pdf_2 = findViewById(R.id.pdf_2);
        pdf_3 = findViewById(R.id.pdf_3);
        last_btn = findViewById(R.id.last_btn);

        sell_next_btn.setEnabled(false);
        bozon_next_btn.setEnabled(false);
        camera_next_btn.setEnabled(false);
        sudan_next_btn.setEnabled(false);
        area_next_btn.setEnabled(false);
        exp_next_btn.setEnabled(false);

        back_btn_used.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        textWatcher = new TextWatcher() {       //정보를 입력하지않으면 넘어가지않음
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(sell_name_ed.length() >= 1 &&
                        sell_author_ed.length() >= 1 &&
                        sell_publisher_ed.length() >= 1 &&
                        sell_publishd_ed.length() >= 1 &&
                        sell_price_ed.length() >= 1){
                    sell_next_btn.setEnabled(true);
                    sell_next_btn.setBackgroundResource(R.drawable.sell_next_b_b);
                    sell_next_btn.setTextColor(Color.WHITE);

                }else{
                    sell_next_btn.setEnabled(false);
                    sell_next_btn.setBackgroundResource(R.drawable.sell_next_b);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        camera_textWatcher = new TextWatcher() {        //사진이 업로드 되지않았다면 넘어가지지 않음
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(camera_t1 || camera_t2){
                    camera_next_btn.setBackgroundResource(R.drawable.sell_next_b_b);
                    camera_next_btn.setTextColor(Color.WHITE);
                    camera_next_btn.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        area_textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(ed_area.length() >= 1){
                    area_next_btn.setEnabled(true);
                    area_next_btn.setBackgroundResource(R.drawable.sell_next_b_b);
                    area_next_btn.setTextColor(Color.WHITE);
                }else{
                    area_next_btn.setEnabled(false);
                    area_next_btn.setBackgroundResource(R.drawable.sell_next_b);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        exp_textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(ed_plain.length() >= 1){
                    exp_next_btn.setEnabled(true);
                    exp_next_btn.setBackgroundResource(R.drawable.sell_next_b_b);
                    exp_next_btn.setTextColor(Color.WHITE);
                }else{
                    exp_next_btn.setEnabled(false);
                    exp_next_btn.setBackgroundResource(R.drawable.sell_next_b);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        ed_area.addTextChangedListener(area_textWatcher);
        ed_plain.addTextChangedListener(exp_textWatcher);
        sell_name_ed.addTextChangedListener(textWatcher);
        sell_author_ed.addTextChangedListener(textWatcher);
        sell_publisher_ed.addTextChangedListener(textWatcher);
        sell_publishd_ed.addTextChangedListener(textWatcher);
        sell_price_ed.addTextChangedListener(textWatcher);

        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radio1.isChecked() || radio2.isChecked() || radio3.isChecked()) {
                    bozon_next_btn.setEnabled(true);
                    bozon_next_btn.setBackgroundResource(R.drawable.sell_next_b_b);

                } else {
                    bozon_next_btn.setEnabled(false);
                    bozon_next_btn.setBackgroundResource(R.drawable.sell_next_b);
                }
            }
        });
        radio_group_sudan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radio1_sudan.isChecked() || radio2_sudan.isChecked() || radio3_sudan.isChecked()) {
                    sudan_next_btn.setEnabled(true);
                    sudan_next_btn.setBackgroundResource(R.drawable.sell_next_b_b);

                } else {
                    sudan_next_btn.setEnabled(false);
                    sudan_next_btn.setBackgroundResource(R.drawable.sell_next_b);
                }
            }
        });
        sell_next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_book_info.setVisibility(View.INVISIBLE);
                sell_box1.setVisibility(View.INVISIBLE);
                sell_box2.setVisibility(View.INVISIBLE);
                sell_box3.setVisibility(View.INVISIBLE);
                sell_box4.setVisibility(View.INVISIBLE);
                sell_box5.setVisibility(View.INVISIBLE);
                sell_next_btn.setVisibility(View.INVISIBLE);
                sell_name_ed.setVisibility(View.INVISIBLE);
                sell_author_ed.setVisibility(View.INVISIBLE);
                sell_publisher_ed.setVisibility(View.INVISIBLE);
                sell_publishd_ed.setVisibility(View.INVISIBLE);
                sell_price_ed.setVisibility(View.INVISIBLE);
                rectangle_7.setVisibility(View.INVISIBLE);

                text_book_bozon.setVisibility(View.VISIBLE);
                bozon_box1.setVisibility(View.VISIBLE);
                bozon_box2.setVisibility(View.VISIBLE);
                bozon_box3.setVisibility(View.VISIBLE);
                radio_group.setVisibility(View.VISIBLE);
                r_button_1.setVisibility(View.VISIBLE);
                r_button_2.setVisibility(View.VISIBLE);
                r_button_3.setVisibility(View.VISIBLE);
                bozon_pen.setVisibility(View.VISIBLE);
                bozon_boll.setVisibility(View.VISIBLE);
                bozon_clean.setVisibility(View.VISIBLE);
                bozon_next_btn.setVisibility(View.VISIBLE);
                rectangle_8.setVisibility(View.VISIBLE);

            }
        });
        bozon_next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_book_bozon.setVisibility(View.INVISIBLE);
                bozon_box1.setVisibility(View.INVISIBLE);
                bozon_box2.setVisibility(View.INVISIBLE);
                bozon_box3.setVisibility(View.INVISIBLE);
                r_button_1.setVisibility(View.INVISIBLE);
                r_button_2.setVisibility(View.INVISIBLE);
                r_button_3.setVisibility(View.INVISIBLE);
                radio_group.setVisibility(View.INVISIBLE);
                bozon_pen.setVisibility(View.INVISIBLE);
                bozon_boll.setVisibility(View.INVISIBLE);
                bozon_clean.setVisibility(View.INVISIBLE);
                bozon_next_btn.setVisibility(View.INVISIBLE);
                rectangle_8.setVisibility(View.INVISIBLE);

                text_camera.setVisibility(View.VISIBLE);
                camera_upload1.setVisibility(View.VISIBLE);
                camera_upload2.setVisibility(View.VISIBLE);
                camera_logo1.setVisibility(View.VISIBLE);
                camera_logo2.setVisibility(View.VISIBLE);
                camera_btn.setVisibility(View.VISIBLE);
                camera_next_btn.setVisibility(View.VISIBLE);
                upload_btn1.setVisibility(View.VISIBLE);
                upload_btn2.setVisibility(View.VISIBLE);
            }
        });
        camera_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_camera.setVisibility(View.INVISIBLE);
                camera_upload1.setVisibility(View.INVISIBLE);
                camera_upload2.setVisibility(View.INVISIBLE);
                camera_logo1.setVisibility(View.INVISIBLE);
                camera_logo2.setVisibility(View.INVISIBLE);
                camera_btn.setVisibility(View.INVISIBLE);
                camera_next_btn.setVisibility(View.INVISIBLE);
                upload_btn1.setVisibility(View.INVISIBLE);
                upload_btn2.setVisibility(View.INVISIBLE);

                text_sudan.setVisibility(View.VISIBLE);
                off_box.setVisibility(View.VISIBLE);
                on_box.setVisibility(View.VISIBLE);
                both_box.setVisibility(View.VISIBLE);
                radio_group_sudan.setVisibility(View.VISIBLE);
                r_button_1_sudan.setVisibility(View.VISIBLE);
                r_button_2_sudan.setVisibility(View.VISIBLE);
                r_button_3_sudan.setVisibility(View.VISIBLE);
                sudan_next_btn.setVisibility(View.VISIBLE);
                off.setVisibility(View.VISIBLE);
                on.setVisibility(View.VISIBLE);
                both.setVisibility(View.VISIBLE);
                rectangle_8.setVisibility(View.VISIBLE);
            }
        });
        camera_next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_camera.setVisibility(View.INVISIBLE);
                camera_upload1.setVisibility(View.INVISIBLE);
                camera_upload2.setVisibility(View.INVISIBLE);
                camera_logo1.setVisibility(View.INVISIBLE);
                camera_logo2.setVisibility(View.INVISIBLE);
                camera_btn.setVisibility(View.INVISIBLE);
                camera_next_btn.setVisibility(View.INVISIBLE);
                upload_btn1.setVisibility(View.INVISIBLE);
                upload_btn2.setVisibility(View.INVISIBLE);

                text_sudan.setVisibility(View.VISIBLE);
                off_box.setVisibility(View.VISIBLE);
                on_box.setVisibility(View.VISIBLE);
                both_box.setVisibility(View.VISIBLE);
                radio_group_sudan.setVisibility(View.VISIBLE);
                r_button_1_sudan.setVisibility(View.VISIBLE);
                r_button_2_sudan.setVisibility(View.VISIBLE);
                r_button_3_sudan.setVisibility(View.VISIBLE);
                sudan_next_btn.setVisibility(View.VISIBLE);
                off.setVisibility(View.VISIBLE);
                on.setVisibility(View.VISIBLE);
                both.setVisibility(View.VISIBLE);
                rectangle_8.setVisibility(View.VISIBLE);
            }
        });
        sudan_next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_sudan.setVisibility(View.INVISIBLE);
                off_box.setVisibility(View.INVISIBLE);
                on_box.setVisibility(View.INVISIBLE);
                both_box.setVisibility(View.INVISIBLE);
                radio_group_sudan.setVisibility(View.INVISIBLE);
                r_button_1_sudan.setVisibility(View.INVISIBLE);
                r_button_2_sudan.setVisibility(View.INVISIBLE);
                r_button_3_sudan.setVisibility(View.INVISIBLE);
                sudan_next_btn.setVisibility(View.INVISIBLE);
                off.setVisibility(View.INVISIBLE);
                on.setVisibility(View.INVISIBLE);
                both.setVisibility(View.INVISIBLE);
                rectangle_8.setVisibility(View.INVISIBLE);

                if(radio1_sudan.isChecked() || radio3_sudan.isChecked()){
                    text_area.setVisibility(View.VISIBLE);
                    ed_area.setVisibility(View.VISIBLE);
                    rectangle_9.setVisibility(View.VISIBLE);
                    area_next_btn.setVisibility(View.VISIBLE);
                    area_btn.setVisibility(View.VISIBLE);
                }else if(radio2_sudan.isChecked()){
                    text_explain.setVisibility(View.VISIBLE);
                    ed_plain.setVisibility(View.VISIBLE);
                    exp_next_btn.setVisibility(View.VISIBLE);
                    exp_btn.setVisibility(View.VISIBLE);
                    text_explain.setVisibility(View.VISIBLE);
                }

            }
        });
        exp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_area.setVisibility(View.INVISIBLE);
                ed_area.setVisibility(View.INVISIBLE);
                rectangle_9.setVisibility(View.INVISIBLE);
                area_next_btn.setVisibility(View.INVISIBLE);
                area_btn.setVisibility(View.INVISIBLE);
                text_explain.setVisibility(View.INVISIBLE);
                ed_plain.setVisibility(View.INVISIBLE);
                exp_next_btn.setVisibility(View.INVISIBLE);
                exp_btn.setVisibility(View.INVISIBLE);
                text_explain.setVisibility(View.INVISIBLE);

                rectangle_10.setVisibility(View.VISIBLE);
                text_last.setVisibility(View.VISIBLE);
                pdf_.setVisibility(View.VISIBLE);
                pdf_1.setVisibility(View.VISIBLE);
                pdf_2.setVisibility(View.VISIBLE);
                pdf_3.setVisibility(View.VISIBLE);
                last_btn.setVisibility(View.VISIBLE);
            }
        });
        area_next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_area.setVisibility(View.INVISIBLE);
                ed_area.setVisibility(View.INVISIBLE);
                rectangle_9.setVisibility(View.INVISIBLE);
                area_next_btn.setVisibility(View.INVISIBLE);
                area_btn.setVisibility(View.INVISIBLE);
                text_explain.setVisibility(View.INVISIBLE);
                ed_plain.setVisibility(View.INVISIBLE);
                exp_next_btn.setVisibility(View.INVISIBLE);
                exp_btn.setVisibility(View.INVISIBLE);
                text_explain.setVisibility(View.INVISIBLE);

                text_explain.setVisibility(View.VISIBLE);
                ed_plain.setVisibility(View.VISIBLE);
                exp_next_btn.setVisibility(View.VISIBLE);
                exp_btn.setVisibility(View.VISIBLE);
                text_explain.setVisibility(View.VISIBLE);
            }
        });
        area_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_area.setVisibility(View.INVISIBLE);
                ed_area.setVisibility(View.INVISIBLE);
                rectangle_9.setVisibility(View.INVISIBLE);
                area_next_btn.setVisibility(View.INVISIBLE);
                area_btn.setVisibility(View.INVISIBLE);
                text_explain.setVisibility(View.INVISIBLE);
                ed_plain.setVisibility(View.INVISIBLE);
                exp_next_btn.setVisibility(View.INVISIBLE);
                exp_btn.setVisibility(View.INVISIBLE);
                text_explain.setVisibility(View.INVISIBLE);

                text_explain.setVisibility(View.VISIBLE);
                ed_plain.setVisibility(View.VISIBLE);
                exp_next_btn.setVisibility(View.VISIBLE);
                exp_btn.setVisibility(View.VISIBLE);
                text_explain.setVisibility(View.VISIBLE);
            }
        });
        exp_next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_area.setVisibility(View.INVISIBLE);
                ed_area.setVisibility(View.INVISIBLE);
                rectangle_9.setVisibility(View.INVISIBLE);
                area_next_btn.setVisibility(View.INVISIBLE);
                area_btn.setVisibility(View.INVISIBLE);
                text_explain.setVisibility(View.INVISIBLE);
                ed_plain.setVisibility(View.INVISIBLE);
                exp_next_btn.setVisibility(View.INVISIBLE);
                exp_btn.setVisibility(View.INVISIBLE);
                text_explain.setVisibility(View.INVISIBLE);

                rectangle_10.setVisibility(View.VISIBLE);
                text_last.setVisibility(View.VISIBLE);
                pdf_.setVisibility(View.VISIBLE);
                pdf_1.setVisibility(View.VISIBLE);
                pdf_2.setVisibility(View.VISIBLE);
                pdf_3.setVisibility(View.VISIBLE);
                last_btn.setVisibility(View.VISIBLE);
            }
        });
        last_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //넣었던 데이터 값 저장해야함
                String sell_name_ed_fb = sell_name_ed.getText().toString();
                String sell_author_ed_fb = sell_author_ed.getText().toString();
                String sell_publisher_ed_fb = sell_publisher_ed.getText().toString();
                String sell_publishd_ed_fb = sell_publishd_ed.getText().toString();
                String sell_price_ed_fb = sell_price_ed.getText().toString();
                String bozon_fb="";
                String area = "";
                if(radio1.isChecked()){
                    bozon_fb = "상";
                }else if(radio2.isChecked()){
                    bozon_fb = "중";
                }else if(radio3.isChecked()){
                    bozon_fb = "하";
                }
                String imageUrl_fb = "";
                String imageUrl_fb2 = "";
                if(imageUrl_1 == "" && imageUrl_2 != ""){
                    imageUrl_fb = "";
                    imageUrl_fb2 = imageUrl_2;
                }else if(imageUrl_1 != "" && imageUrl_2 == ""){
                    imageUrl_fb = imageUrl_1;
                    imageUrl_fb2 = "";
                }else if(imageUrl_1 != "" && imageUrl_2 != ""){
                    imageUrl_fb = imageUrl_1;
                    imageUrl_fb2 = imageUrl_2;
                }

                String sudan_fb="";
                if(radio1_sudan.isChecked()){
                    sudan_fb = "직거래";
                    area = ed_area.getText().toString();
                }else if(radio2_sudan.isChecked()){
                    sudan_fb = "택배";
                }else if(radio3_sudan.isChecked()){
                    sudan_fb = "둘다";
                    area = ed_area.getText().toString();
                }
                String plain_fb = ed_plain.getText().toString();
                String name_nums = MainActivity.name_num;

                DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference(MainActivity.name_num + "books");
                DatabaseReference databaseRef_b = FirebaseDatabase.getInstance().getReference("books");
                String key = databaseRef.push().getKey();
                Book_info book_info = new Book_info(sell_name_ed_fb, sell_author_ed_fb, sell_publisher_ed_fb, sell_publishd_ed_fb, sell_price_ed_fb, bozon_fb, imageUrl_fb,
                        imageUrl_fb2, sudan_fb, plain_fb, area, name_nums, key);
                databaseRef.child(key).setValue(book_info);
                databaseRef_b.child(key).setValue(book_info);

                Intent intent = new Intent(getApplicationContext(), Book_mine.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        //라디오버튼 처리------------------------------------------------------------------------------
        r_button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio1.setChecked(true);
                radio2.setChecked(false);
                radio3.setChecked(false);
                if(radio1.isChecked()){
                    bozon_box1.setBackgroundResource(R.drawable.sell_box_d_check);
                    bozon_box2.setBackgroundResource(R.drawable.sell_box_d);
                    bozon_box3.setBackgroundResource(R.drawable.sell_box_d);
                    bozon_pen.setTextColor(Color.BLACK);
                    bozon_boll.setTextColor(Color.GRAY);
                    bozon_clean.setTextColor(Color.GRAY);
                }
            }
        });
        r_button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio1.setChecked(false);
                radio2.setChecked(true);
                radio3.setChecked(false);
                if(radio2.isChecked()){
                    bozon_box2.setBackgroundResource(R.drawable.sell_box_d_check);
                    bozon_box1.setBackgroundResource(R.drawable.sell_box_d);
                    bozon_box3.setBackgroundResource(R.drawable.sell_box_d);
                    bozon_pen.setTextColor(Color.GRAY);
                    bozon_boll.setTextColor(Color.BLACK);
                    bozon_clean.setTextColor(Color.GRAY);
                }
            }
        });
        r_button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio1.setChecked(false);
                radio2.setChecked(false);
                radio3.setChecked(true);
                if(radio3.isChecked()){
                    bozon_box3.setBackgroundResource(R.drawable.sell_box_d_check);
                    bozon_box1.setBackgroundResource(R.drawable.sell_box_d);
                    bozon_box2.setBackgroundResource(R.drawable.sell_box_d);
                    bozon_pen.setTextColor(Color.GRAY);
                    bozon_boll.setTextColor(Color.GRAY);
                    bozon_clean.setTextColor(Color.BLACK);
                }
            }
        });

        r_button_1_sudan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio1_sudan.setChecked(true);
                radio2_sudan.setChecked(false);
                radio3_sudan.setChecked(false);
                if(radio1_sudan.isChecked()){
                    off_box.setBackgroundResource(R.drawable.sell_box_d_check);
                    on_box.setBackgroundResource(R.drawable.sell_box_d);
                    both_box.setBackgroundResource(R.drawable.sell_box_d);
                    off.setTextColor(Color.BLACK);
                    on.setTextColor(Color.GRAY);
                    both.setTextColor(Color.GRAY);
                }
            }
        });
        r_button_2_sudan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio1_sudan.setChecked(false);
                radio2_sudan.setChecked(true);
                radio3_sudan.setChecked(false);
                if(radio2_sudan.isChecked()){
                    off_box.setBackgroundResource(R.drawable.sell_box_d);
                    on_box.setBackgroundResource(R.drawable.sell_box_d_check);
                    both_box.setBackgroundResource(R.drawable.sell_box_d);
                    off.setTextColor(Color.GRAY);
                    on.setTextColor(Color.BLACK);
                    both.setTextColor(Color.GRAY);
                }
            }
        });
        r_button_3_sudan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio1_sudan.setChecked(false);
                radio2_sudan.setChecked(false);
                radio3_sudan.setChecked(true);
                if(radio3_sudan.isChecked()){
                    off_box.setBackgroundResource(R.drawable.sell_box_d);
                    on_box.setBackgroundResource(R.drawable.sell_box_d);
                    both_box.setBackgroundResource(R.drawable.sell_box_d_check);
                    off.setTextColor(Color.GRAY);
                    on.setTextColor(Color.GRAY);
                    both.setTextColor(Color.BLACK);
                }
            }
        });
//--------------------------------------------------------------------------------------------------
        home_btn_used = findViewById(R.id.home_btn_used);
        sell_btn_used = findViewById(R.id.sell_btn_used);
        mine_btn_used = findViewById(R.id.mine_btn_used);

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

}
