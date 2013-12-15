package com.hilton.gitatouille;

import android.os.Bundle;
import android.text.TextUtils;
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
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_viewer);
        final int chap = getIntent().getIntExtra(ProGit.EXTRA_INDEX_CHAPTER, 0);
        final int sec = getIntent().getIntExtra(ProGit.EXTRA_INDEX_SECTION, 0);
        mWebView = (CustomizedWebView) findViewById(R.id.webview);
        mWebView.loadUrl(ProGit.getCurrentSectionUrl(chap, sec));
        
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
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
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
}
