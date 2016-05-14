package com.example.hanbyeol.capstone_ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Fragment1_ extends Fragment {

    private String curURL;

    public Fragment1_() {}
    public Fragment1_(String url){
        curURL = url;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_fragment1_, container, false);

        WebView mWebView = (WebView) view.findViewById(R.id.frag1_webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl(curURL);

        //mWebView.addJavascriptInterface(new AndroidBridge(), "androidBridge");

        return view;
    }

}
