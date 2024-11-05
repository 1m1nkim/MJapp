package com.example.firebase_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.example.firebase_test.MainActivity;

import java.util.ArrayList;

public class BarcodeActivity extends AppCompatActivity {
    private ImageView barCode;
    TextView barId, barHak, barName, barDep, barJob, barSit;
    MainActivity myObject = new MainActivity();
    String id = myObject.login_hak;
    String name = myObject.name_num;
    String dep;
    Button back_btn_chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barcode);
        barId = (TextView)findViewById(R.id.bar_id); // 바코드 코드
        barHak = (TextView)findViewById(R.id.bar_hak); // 학번
        barName = (TextView)findViewById(R.id.bar_name); // 이름
        barDep = (TextView)findViewById(R.id.bar_dep); // 학과
        barJob = (TextView)findViewById(R.id.bar_job); // 신분
        barSit = (TextView)findViewById(R.id.bar_sit); // 상태
        back_btn_chat = findViewById(R.id.back_btn_chat); // 뒤로가기 버튼
        init();
        back_btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }


    private void init(){
        barCode = findViewById(R.id.bar_code);
        getCode();
        getDep(id);
        setTextView();
    }

    private void getCode(){
        try{
            barcode();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // create function for the barcode
    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
    private void barcode() throws WriterException {
        BitMatrix bitMatrix = multiFormatWriter.encode(id.toString(), BarcodeFormat.CODE_128, 400, 170, null);
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
        // set the generated bar code to out imageview
        barCode.setImageBitmap(bitmap);
    }

    public void getDep(String yourString) {
        try {
            if (yourString != null && yourString.contains("081")) {
                // 문자열이 null이 아니고 081을 포함하고 있을 때
                dep = "컴퓨터공학과";
            } else {
                // 문자열이 null이거나 081을 포함하고 있지 않을 때
                dep = "";
            }
        } catch (NullPointerException e) {
            e.printStackTrace(); // 예외가 발생했을 때의 처리 (로그 기록 등)
        }
    }

    public void setTextView(){
        barId.setText(id);
        barHak.setText("학번 : " + id);
        barName.setText("이름 : " + name);
        barDep.setText("학과 : " + dep);
        barJob.setText("신분 : 학생");
        barSit.setText("상태 : 재학/재직");
    }
}