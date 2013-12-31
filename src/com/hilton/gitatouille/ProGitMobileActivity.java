package com.hilton.gitatouille;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class ProGitMobileActivity extends SherlockActivity {
    public static final String ACTION_VIEW_CONTENT = "com.hilton.gitatouille.VIEW_CONTENT";
    private static final int MENU_EXIT = 100;
    private static final String CURRENT_URL = "current_url";
    private WebView mWebView;
    
    @SuppressLint({ "SetJavaScriptEnabled", "NewApi", "JavascriptInterface" })
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mobile);
        Log.e("fuck", "on create instance " + savedInstanceState + ", intent "+getIntent());
        mWebView = (WebView) findViewById(R.id.webview);
        final WebSettings ws = mWebView.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setAllowFileAccess(true);
        ws.setJavaScriptCanOpenWindowsAutomatically(true);
        ws.setAllowFileAccessFromFileURLs(true);
        
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
        
        String currentUrl = null;
        if (savedInstanceState != null) {
            currentUrl = savedInstanceState.getString(CURRENT_URL);
        }
        mWebView.loadUrl(TextUtils.isEmpty(currentUrl) ? ProGit.getHomepageUrl() : currentUrl);
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(CURRENT_URL, mWebView.getUrl());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (mWebView != null && mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    /* (non-Javadoc)
     * @see com.actionbarsherlock.app.SherlockActivity#onCreateOptionsMenu(com.actionbarsherlock.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_EXIT, 0, "Exit");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case MENU_EXIT:
            finish();
            break;
        default:
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}