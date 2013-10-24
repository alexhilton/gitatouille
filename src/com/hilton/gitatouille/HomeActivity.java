package com.hilton.gitatouille;

import android.os.Bundle;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.devspark.sidenavigation.ISideNavigationCallback;
import com.devspark.sidenavigation.SideNavigationView;

public class HomeActivity extends SherlockActivity {

    private SideNavigationView mNavigationView;
    private TextView mContent;
    private ActionBar mActionBar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        mContent = (TextView) findViewById(R.id.content);
        mNavigationView = (SideNavigationView) findViewById(R.id.side_navigation_view);
        mNavigationView.setMenuItems(R.menu.main);
        mNavigationView.setMenuClickCallback(new ISideNavigationCallback() {
            @Override
            public void onSideNavigationItemClick(int itemId) {
                switch (itemId) {
                case R.id.menu_git_manual:
                    mContent.setText(R.string.menu_git_manual);
                    mActionBar.setTitle(R.string.menu_git_manual);
                    break;
                case R.id.menu_git_tutorial:
                    mContent.setText(R.string.menu_git_tutorial);
                    mActionBar.setTitle(R.string.menu_git_tutorial);
                    break;
                case R.id.menu_git_tricks:
                    mContent.setText(R.string.menu_git_tricks);
                    mActionBar.setTitle(R.string.menu_git_tricks);
                    break;
                case R.id.menu_git_articles:
                    mContent.setText(R.string.menu_git_articles);
                    mActionBar.setTitle(R.string.menu_git_articles);
                    break;
                case R.id.menu_favorites:
                    mContent.setText(R.string.menu_favorites);
                    mActionBar.setTitle(R.string.menu_favorites);
                    break;
                case R.id.menu_about_us:
                    mContent.setText(R.string.menu_about_us);
                    mActionBar.setTitle(R.string.menu_about_us);
                    break;
                case R.id.menu_exit:
                    finish();
                    break;
                }
            }
            
        });
        mNavigationView.setMode(SideNavigationView.Mode.LEFT);
        
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            mNavigationView.toggleMenu();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
