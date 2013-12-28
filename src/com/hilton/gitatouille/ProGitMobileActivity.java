package com.hilton.gitatouille;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;

public class ProGitMobileActivity extends SherlockActivity {
    public static final String ACTION_VIEW_CONTENT = "com.hilton.gitatouille.VIEW_CONTENT";
    private static final int MENU_PREV = 10;
    private static final int MENU_NEXT = 11;
    private WebView mWebView;
    
    @SuppressLint({ "SetJavaScriptEnabled", "NewApi", "JavascriptInterface" })
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mobile);
        mWebView = (WebView) findViewById(R.id.webview);
        final WebSettings ws = mWebView.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setAllowFileAccess(true);
        ws.setJavaScriptCanOpenWindowsAutomatically(true);
        ws.setAllowFileAccessFromFileURLs(true);
        
        NavigationHelper nh = new NavigationHelper();
        mWebView.addJavascriptInterface(nh, NavigationHelper.NAME);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.e("console", String.format("%s-%s", consoleMessage.messageLevel().name(), consoleMessage.message()));
                return true;
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Log.e("jsalert", "url -" + url + ", msg -" + message);
                return super.onJsAlert(view, url, message, result);
            }
        });
        
        mWebView.loadUrl(ProGit.getHomepageUrl());
    }
    
    private class NavigationHelper {
        public static final String NAME = "NavigationHelper";
        public String nextSection() {
            final String url = ProGit.getNextSectionUrl();
            return url;
        }
        
        public String prevSection() {
            return ProGit.getPreviousSectionUrl();
        }
    }
}