package com.hilton.gitatouille;

import android.os.Bundle;
import android.webkit.WebView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.hilton.gitatouille.CustomizedWebView.OnScrollListener;

public class ContentViewerActivity extends SherlockActivity {
    public static final String ACTION_VIEW_CONTENT = "com.hilton.gitatouille.VIEW_CONTENT";
    private String mUrl;
    private ActionBar mActionBar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_viewer);
        mUrl = getIntent().getStringExtra(ProGit.EXTRA_URL);
        final CustomizedWebView wv = (CustomizedWebView) findViewById(R.id.webview);
        wv.loadUrl(mUrl);
        
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        
        wv.setScrollListener(new OnScrollListener() {
            @Override
            public void scrollDown() {
                mActionBar.hide();
            }

            @Override
            public void scrollUp() {
                mActionBar.show();
            }
        });
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            // TODO: use a better navigation strategy rather than simply finish.
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
