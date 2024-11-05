package com.example.firebase_test;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WebviewActivity3 extends AppCompatActivity {

    private WebView webview_web3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web3_eclass);

        webview_web3 = findViewById(R.id.webview_web3);
        webview_web3.setWebViewClient(new WebViewClient());
        Button back_btn_web3 = findViewById(R.id.back_btn_web3);


        back_btn_web3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WebviewActivity3.this, WebsiteActivity.class);
                startActivity(intent);
            }
        });

        WebSettings webSettings =  webview_web3.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webview_web3.clearCache(true);
        webview_web3.loadUrl("https://cyber.mjc.ac.kr/");

    }
}