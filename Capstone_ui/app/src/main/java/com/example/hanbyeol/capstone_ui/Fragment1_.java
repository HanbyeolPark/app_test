package com.example.hanbyeol.capstone_ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.CookieManager;

public class Fragment1_ extends Fragment {

    private String curURL;
    private WebView mWebView;

    private static Fragment1_ UniqueFragment1_;
    private Fragment1_(){ }

    //public Fragment1_() {}
    public Fragment1_(String url){
        curURL = url;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_fragment1_, container, false);

       // CookieManager.createInstance(this);
        //CookieManager.getInstance().sync();

        mWebView = (WebView) view.findViewById(R.id.frag1_webview);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);

        if(savedInstanceState == null){
            mWebView.setWebViewClient(new MyWebClient());
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.loadUrl(curURL);
            Log.d("temp:",curURL);
        } else {
            mWebView.restoreState(savedInstanceState);
            mWebView.loadUrl(curURL);
            Log.d("temp2:", curURL);
        }
        //mWebView.addJavascriptInterface(new AndroidBridge(), "androidBridge");

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        mWebView.saveState(outState);
    }

    public class MyWebClient extends WebViewClient{
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            Log.d("URL_CATCH", url);
            return true;
        }
    }

    public static synchronized Fragment1_ getInstance(){
        if(UniqueFragment1_ == null)
            UniqueFragment1_ = new Fragment1_();
        return UniqueFragment1_;
    }

}
