package com.example.firebase_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WebviewActivity2 extends AppCompatActivity {

    private WebView webview_web2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web3_eclass);

        webview_web2 = findViewById(R.id.webview_web3);
        webview_web2.setWebViewClient(new WebViewClient());
        Button back_btn_web3 = findViewById(R.id.back_btn_web3);


        back_btn_web3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WebviewActivity2.this, WebsiteActivity.class);
                startActivity(intent);
            }
        });

        WebSettings webSettings =  webview_web2.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webview_web2.clearCache(true);
        webview_web2.loadUrl("https://icampus.mjc.ac.kr/");

    }
}
