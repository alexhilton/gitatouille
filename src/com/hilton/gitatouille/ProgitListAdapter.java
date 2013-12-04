package com.hilton.gitatouille;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

public class ProgitListAdapter extends BaseExpandableListAdapter {
    private static final int ANIMATION_DEFAULT_DURATION = 400;
    private static final int ANIMATION_DEFAULT_DELAY = 100;
    private static final String TAG = ProgitListAdapter.class.getName();
    private List<ProGitChapter> mProGitChapters;
    private Context mContext;
    private LayoutInflater mViewFactory;
    
    public ProgitListAdapter(Context ctx) {
        mContext = ctx;
        mProGitChapters = ProGit.getChapters();
        mViewFactory = LayoutInflater.from(ctx);
    }
    
    @Override
    public ProGitSection getChild(int groupPosition, int childPosition) {
        return mProGitChapters.get(groupPosition).getSection(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
        TextView name = null;
        if (convertView == null) {
            name = (TextView) mViewFactory.inflate(R.layout.progit_child_item, null, false);
        } else {
            name = (TextView) convertView;
        }
        name.setText(getChild(groupPosition, childPosition).mName);
        return name;
    }
    
    @Override
    public int getChildrenCount(int groupPosition) {
        return mProGitChapters.get(groupPosition).getSectionCount();
    }

    @Override
    public ProGitChapter getGroup(int groupPosition) {
        return mProGitChapters.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return mProGitChapters.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        View root = null;
        if (convertView == null) {
            root = mViewFactory.inflate(R.layout.progit_group_item, null, false);
            Animator flyIn = ObjectAnimator.ofFloat(root, "translationY", 500, 0);
            setupAnimation(groupPosition, root, parent, flyIn);
        } else {
            root = convertView;
        }
        TextView textView = (TextView) root.findViewById(R.id.name);
        textView.setText(getGroup(groupPosition).mName);
        ImageView indicator = (ImageView) root.findViewById(R.id.indicator);
        indicator.setImageResource(isExpanded ? R.drawable.button_tab_to_close : R.drawable.button_tab_to_open);
        return root;
    }

    private void setupAnimation(int position, View view, ViewGroup parent, Animator flyIn) {
        Log.e(TAG, "setupAnimation position " + position);
        ViewHelper.setAlpha(view, 0);
        Animator alpha = ObjectAnimator.ofFloat(view, "alpha", 0, 1);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(flyIn, alpha);
        int delay = ANIMATION_DEFAULT_DELAY;
        set.setStartDelay(position * delay);
        set.setDuration(ANIMATION_DEFAULT_DURATION);
        set.start();
    }
    
    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
