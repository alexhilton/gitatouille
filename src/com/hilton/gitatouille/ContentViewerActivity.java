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
import android.widget.Toast;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.hilton.gitatouille.CustomizedWebView.OnScrollListener;

public class ContentViewerActivity extends SherlockActivity {
    public static final String ACTION_VIEW_CONTENT = "com.hilton.gitatouille.VIEW_CONTENT";
    private static final int MENU_PREV = 10;
    private static final int MENU_NEXT = 11;
    private ActionBar mActionBar;
    private CustomizedWebView mWebView;
    
    @SuppressLint({ "SetJavaScriptEnabled", "NewApi" })
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_viewer);
        final int chap = getIntent().getIntExtra(ProGit.EXTRA_INDEX_CHAPTER, 0);
        final int sec = getIntent().getIntExtra(ProGit.EXTRA_INDEX_SECTION, 0);
        mWebView = (CustomizedWebView) findViewById(R.id.webview);
        final WebSettings ws = mWebView.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setAllowFileAccess(true);
        ws.setJavaScriptCanOpenWindowsAutomatically(true);
        ws.setAllowFileAccessFromFileURLs(true);
        
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        
        mWebView.setScrollListener(new OnScrollListener() {
            @Override
            public void scrollDown() {
                if (mActionBar.isShowing()) {
                    mActionBar.hide();
                }
            }

            @Override
            public void scrollUp() {
                if (!mActionBar.isShowing()) {
                    mActionBar.show();
                }
            }

            @Override
            public void scrollLeft() {
                loadPrevSection();
            }

            @Override
            public void scrollRight() {
                loadNextSection();
            }
        });
        
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
        
        mWebView.loadUrl(ProGit.getCurrentSectionUrl(chap, sec));
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, MENU_PREV, 0, "Prev");
        menu.add(0, MENU_NEXT, 0, "Next");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            // TODO: use a better navigation strategy rather than simply finish.
            finish();
            return true;
        case MENU_PREV: {
            loadPrevSection();
            break;
        }
        case MENU_NEXT: {
            loadNextSection();
            break;
        }
        default:
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadNextSection() {
        final String url = ProGit.getNextSectionUrl();
        if (TextUtils.isEmpty(url)) {
            Toast.makeText(getApplication(), "This is the last section", Toast.LENGTH_SHORT).show();
            return;
        }
        changeUrl(url);
    }

    private void loadPrevSection() {
        final String url = ProGit.getPreviousSectionUrl();
        if (TextUtils.isEmpty(url)) {
            Toast.makeText(getApplication(), "This is the first section.", Toast.LENGTH_SHORT).show();
            return;
        }
        changeUrl(url);
    }

    private void changeUrl(final String url) {
        mWebView.stopLoading();
        mWebView.loadUrl(url);
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
