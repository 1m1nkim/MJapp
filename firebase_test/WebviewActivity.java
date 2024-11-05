package com.example.firebase_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WebviewActivity extends AppCompatActivity {
    private WebView webview_web;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web3_eclass);

        webview_web = findViewById(R.id.webview_web3);
        webview_web.setWebViewClient(new WebViewClient());
        Button back_btn_web3 = findViewById(R.id.back_btn_web3);


        back_btn_web3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WebviewActivity.this, WebsiteActivity.class);
                startActivity(intent);
            }
        });

        WebSettings webSettings =  webview_web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webview_web.clearCache(true);
        webview_web.loadUrl("https://mjc.ac.kr/");

    }
}
