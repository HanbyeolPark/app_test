package com.example.hanbyeol.capstone_ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Handler;

public class UrlActivity extends AppCompatActivity {

    WebView mWebView;
    TextView mTextView;
    EditText mEditText;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);

        mWebView = (WebView) findViewById(R.id.url_webView);
        mTextView = (TextView) findViewById(R.id.url_textview);
        mButton = (Button) findViewById(R.id.url_button);
        mEditText = (EditText) findViewById(R.id.url_edittext);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new AndroidBridge(), "androidBridge");
        mWebView.loadUrl("file:///android_asset/index.html");

        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mWebView.loadUrl("javascript:setMessage('" + mEditText.getText() + "')");
            }
        });

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.webview_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //WebViewActivity.this.finish();
                overridePendingTransition(R.anim.anim_hold, R.anim.anim_slide_out_to_right);
            }
        });*/
    }

    private class AndroidBridge {
        @JavascriptInterface
        public void setMessage(final String arg) {
            Handler handler = null;
            handler.post(new Runnable() {
                public void run() {
                    Log.d("tag", "arg:" + arg);
                    mTextView.setText("received msg : \n" + arg);
                }
            });
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        UrlActivity.this.finish();
        overridePendingTransition(R.anim.anim_hold, R.anim.anim_slide_out_to_right);

        return false;
    }
}
