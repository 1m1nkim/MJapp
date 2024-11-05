package com.example.firebase_test;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WebviewActivity8 extends AppCompatActivity {
    private WebView webview_web8;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web8_student);

        webview_web8 = findViewById(R.id.webview_web8);
        webview_web8.setWebViewClient(new WebViewClient());
        Button back_btn_web6 = findViewById(R.id.back_btn_web8);


        back_btn_web6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WebviewActivity8.this, WebsiteActivity.class);
                startActivity(intent);
            }
        });

        WebSettings webSettings = webview_web8.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webview_web8.clearCache(true);
        webview_web8.loadUrl("https://mpu.mjc.ac.kr/");

    }
}