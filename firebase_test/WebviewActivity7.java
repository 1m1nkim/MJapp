package com.example.firebase_test;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WebviewActivity7 extends AppCompatActivity {
    private WebView webview_web7;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web7_professor);

        webview_web7 = findViewById(R.id.webview_web7);
        webview_web7.setWebViewClient(new WebViewClient());
        Button back_btn_web6 = findViewById(R.id.back_btn_web7);


        back_btn_web6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WebviewActivity7.this, WebsiteActivity.class);
                startActivity(intent);
            }
        });

        WebSettings webSettings = webview_web7.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webview_web7.clearCache(true);
        webview_web7.loadUrl("https://ctl.mjc.ac.kr/ctl/main/main_form.acl");

    }
}