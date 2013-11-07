package com.hilton.gitatouille;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.devspark.sidenavigation.ISideNavigationCallback;
import com.devspark.sidenavigation.SideNavigationView;

public class HomeActivity extends SherlockFragmentActivity {

    private SideNavigationView mNavigationView;
    private TextView mStubContent;
    private ActionBar mActionBar;
    
    private ExpandableListView mProgitListView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        mStubContent = (TextView) findViewById(R.id.content);
        mNavigationView = (SideNavigationView) findViewById(R.id.side_navigation_view);
        mNavigationView.setMenuItems(R.menu.main);
        mNavigationView.setMenuClickCallback(new ISideNavigationCallback() {
            @Override
            public void onSideNavigationItemClick(int itemId) {
                switch (itemId) {
                case R.id.menu_git_manual:
                    mStubContent.setText(R.string.menu_git_manual);
                    mActionBar.setTitle(R.string.menu_git_manual);
                    break;
                case R.id.menu_git_tutorial:
                    mStubContent.setText(R.string.menu_git_tutorial);
                    mActionBar.setTitle(R.string.menu_git_tutorial);
                    showProGitList();
                    mStubContent.setVisibility(View.GONE);
                    break;
                case R.id.menu_git_tricks:
                    mStubContent.setText(R.string.menu_git_tricks);
                    mActionBar.setTitle(R.string.menu_git_tricks);
                    break;
                case R.id.menu_git_articles:
                    mStubContent.setText(R.string.menu_git_articles);
                    mActionBar.setTitle(R.string.menu_git_articles);
                    break;
                case R.id.menu_favorites:
                    mStubContent.setText(R.string.menu_favorites);
                    mActionBar.setTitle(R.string.menu_favorites);
                    break;
                case R.id.menu_about_us:
                    mStubContent.setText(R.string.menu_about_us);
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
        
        mProgitListView = (ExpandableListView) findViewById(R.id.progit_list);
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

    @Override
    public void onBackPressed() {
        if (mNavigationView.isShown()) {
            mNavigationView.hideMenu();
        } else {
            super.onBackPressed();
        }
    }

    private void showProGitList() {
        mProgitListView.setVisibility(View.VISIBLE);
        final ProgitListAdapter adapter = new ProgitListAdapter(getBaseContext());
        mProgitListView.setAdapter(adapter);
        mProgitListView.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
                final ProGitSection section = adapter.getChild(groupPosition, childPosition);
                final Intent i = new Intent(ContentViewerActivity.ACTION_VIEW_CONTENT);
                i.putExtra(ProGit.EXTRA_URL, section.getUrl());
                startActivity(i);
                return true;
            }
        });
    }
}
