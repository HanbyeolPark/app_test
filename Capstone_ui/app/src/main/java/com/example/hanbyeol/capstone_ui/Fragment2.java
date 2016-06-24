package com.example.hanbyeol.capstone_ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Fragment2 extends Fragment {

    private String curURL;
    private WebView mWebView;
    private WebSettings mWebSettings;
    private InputMethodManager mInputMethodManager;
    private ProgressBar mProgressBar;

    //private Fragment2(){ }
    //public Fragment2(String url){
        //curURL = url;
   // }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_fragment2, container, false);
        // CookieManager.createInstance(this);
        //CookieManager.getInstance().sync();

        //mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mWebView = (WebView) view.findViewById(R.id.frag2_webview);
        curURL = "file:///android_asset/webView1.html";

        mWebSettings = mWebView.getSettings();
        mWebSettings.setBuiltInZoomControls(true);
        mWebSettings.setSupportZoom(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setSaveFormData(false);
        //mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        //mWebView.setWebChromeClient(new webViewChrome());

        //if(savedInstanceState == null){
            mWebView.setWebViewClient(new MyWebClient());
            mWebSettings.setJavaScriptEnabled(true);
       // } else {
           // mWebView.restoreState(savedInstanceState);
        //}
        mWebView.addJavascriptInterface(new AndroidBridge(), "Fragment2_androidBridge");
        mWebView.loadUrl(curURL);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        mWebView.saveState(outState);
    }

    public class MyWebClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            mProgressBar.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            Log.d("URL_CATCH", url);

            return super.shouldOverrideUrlLoading(view, url);
            //return true;
        }
    }

    /* class webViewChrome extends WebChromeClient {
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

    private class AndroidBridge {
        @JavascriptInterface
        public void changeView(final String url) {
            //set new fragment.
            Log.d("in activity", "test() is working");

            WebViewFragment change = new WebViewFragment(url);
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.addToBackStack(null);
            ft.replace(R.id.frag2_replace, change);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();

            //change in current mode- not working
            //curURL = "file:///android_asset/webView2.html";
           // mWebView.loadUrl(curURL);

            //new activity
            //Intent intent = new Intent(getActivity(), FragTestActivity.class);
            //startActivity(intent);

        }
    }
}
