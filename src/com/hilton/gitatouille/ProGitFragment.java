package com.hilton.gitatouille;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import com.actionbarsherlock.app.SherlockFragment;

public class ProGitFragment extends SherlockFragment {
    public static final int ID = 0x001;
    public static final String TAG = "progit";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.progit, null, false);
        final ExpandableListView list = (ExpandableListView) root.findViewById(R.id.progit_list);
        final ProgitListAdapter adapter = new ProgitListAdapter(getActivity());
        list.setAdapter(adapter);
        list.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
                final Intent i = new Intent(ContentViewerActivity.ACTION_VIEW_CONTENT);
                i.putExtra(ProGit.EXTRA_INDEX_CHAPTER, groupPosition);
                i.putExtra(ProGit.EXTRA_INDEX_SECTION, childPosition);
                startActivity(i);
                return true;
            }
        });
        return root;
    }
}
