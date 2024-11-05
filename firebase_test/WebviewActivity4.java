package com.example.firebase_test;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WebviewActivity4 extends AppCompatActivity {
    private WebView webView_btn4;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web4_libarary);

        webView_btn4 = findViewById(R.id.webview_web4);
        webView_btn4.setWebViewClient(new WebViewClient());
        Button back_btn_web3 = findViewById(R.id.back_btn_web4);

        back_btn_web3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WebviewActivity4.this, WebsiteActivity.class);
                startActivity(intent);
            }
        });

        WebSettings webSettings = webView_btn4.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView_btn4.clearCache(true);
        webView_btn4.loadUrl("https://lib.mjc.ac.kr/");

    }


}
