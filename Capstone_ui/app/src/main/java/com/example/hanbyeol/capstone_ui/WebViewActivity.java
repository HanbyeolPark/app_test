package com.example.hanbyeol.capstone_ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.webview_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //WebViewActivity.this.finish();
                overridePendingTransition(R.anim.anim_hold, R.anim.anim_slide_out_to_right);
            }
        });

        WebView mWebView = (WebView) findViewById(R.id.webview_webview);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        //mWebView.addJavascriptInterface(new AndroidBridge(), "androidBridge");
        mWebView.loadUrl("http://www.naver.com");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        WebViewActivity.this.finish();
        overridePendingTransition(R.anim.anim_hold, R.anim.anim_slide_out_to_right);

        return false;
    }

}
