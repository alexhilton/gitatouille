package com.hilton.gitatouille;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProgitListAdapter extends BaseExpandableListAdapter {
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
        } else {
            root = convertView;
        }
        TextView textView = (TextView) root.findViewById(R.id.name);
        textView.setText(getGroup(groupPosition).mName);
        ImageView indicator = (ImageView) root.findViewById(R.id.indicator);
        indicator.setImageResource(isExpanded ? R.drawable.button_tab_to_close : R.drawable.button_tab_to_open);
        return root;
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
