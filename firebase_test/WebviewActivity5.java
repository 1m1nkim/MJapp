package com.example.firebase_test;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WebviewActivity5 extends AppCompatActivity {
    private WebView webview_web5;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web5_system);

        webview_web5 = findViewById(R.id.webview_web5);
        webview_web5.setWebViewClient(new WebViewClient());
        Button back_btn_web5 = findViewById(R.id.back_btn_web5);


        back_btn_web5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WebviewActivity5.this, WebsiteActivity.class);
                startActivity(intent);
            }
        });

        WebSettings webSettings =  webview_web5.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webview_web5.clearCache(true);
        webview_web5.loadUrl("https://ncsi.mjc.ac.kr/");

    }
}