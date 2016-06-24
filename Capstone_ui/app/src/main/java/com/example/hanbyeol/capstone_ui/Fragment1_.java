package com.example.hanbyeol.capstone_ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.net.CookieManager;

public class Fragment1_ extends Fragment {

    private String curURL;
    private WebView mWebView;
    private WebSettings mWebSettings;
    private InputMethodManager mInputMethodManager;
    private ProgressBar mProgressBar;

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

       //mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mWebView = (WebView) view.findViewById(R.id.frag1_webview);
        mWebSettings = mWebView.getSettings();
        mWebSettings.setBuiltInZoomControls(true);
        mWebSettings.setSupportZoom(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setSaveFormData(false);
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        //mWebView.setWebChromeClient(new webViewChrome());

        if(savedInstanceState == null){
            mWebView.setWebViewClient(new MyWebClient());
            mWebSettings.setJavaScriptEnabled(true);
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
            //mProgressBar.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            Log.d("URL_CATCH", url);

            //return super.shouldOverrideUrlLoading(view, url);
            return true;
        }
    }

   /* public class webViewChrome extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);

            if(newProgress < 100) {
                mProgressBar.setProgress(newProgress);
            }else{
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        }
    }*/

    public static synchronized Fragment1_ getInstance(){
        if(UniqueFragment1_ == null)
            UniqueFragment1_ = new Fragment1_();
        return UniqueFragment1_;
    }

}
