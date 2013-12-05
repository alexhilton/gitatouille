package com.hilton.gitatouille;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.devspark.sidenavigation.ISideNavigationCallback;
import com.devspark.sidenavigation.SideNavigationView;

public class HomeActivity extends SherlockFragmentActivity {

    private SideNavigationView mNavigationView;
    private ActionBar mActionBar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        mNavigationView = (SideNavigationView) findViewById(R.id.side_navigation_view);
        mNavigationView.setMenuItems(R.menu.main);
        mNavigationView.setMenuClickCallback(new ISideNavigationCallback() {
            @Override
            public void onSideNavigationItemClick(int itemId) {
                switch (itemId) {
                case R.id.menu_git_tutorial:
                    mActionBar.setTitle(R.string.menu_git_tutorial);
                    showProGitList();
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
        
        showProGitList();
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
        Fragment progit = getSupportFragmentManager().findFragmentByTag(ProGitFragment.TAG);
        if (progit == null) {
            progit = new ProGitFragment();
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_stub, progit, ProGitFragment.TAG);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
}