package com.hilton.gitatouille;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ProGitListFragment extends Fragment {
    public static final String TAG = "list_in_list";
    private static final String LOG_TAG = ProGitListFragment.class.getName();
    private LayoutInflater mFactory;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        mFactory = LayoutInflater.from(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.progit_list, null, false);
        ListView list = (ListView) root;
        ProGitGroupListAdapter adapter = new ProGitGroupListAdapter(getActivity());
        list.setAdapter(adapter);
        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
                Log.e("list", "clicked list item v is " + v);
                LinearLayout itemView = (LinearLayout) v;
                ListView subList = (ListView) mFactory.inflate(R.layout.progit_list, null, false);
                subList.setAdapter(new ProGitChildListAdapter(getActivity(), pos));
                itemView.addView(subList);
            }
        });
        return root;
    }
    
    private class ProGitGroupListAdapter extends BaseAdapter {
        private List<ProGitChapter> mProGitChapters;
        private Context mContext;
        private LayoutInflater mViewFactory;
        
        public ProGitGroupListAdapter(Context ctx) {
            mContext = ctx;
            mProGitChapters = ProGit.getChapters();
            mViewFactory = LayoutInflater.from(ctx);
        }
        
        @Override
        public int getCount() {
            return mProGitChapters.size();
        }

        @Override
        public ProGitChapter getItem(int pos) {
            return mProGitChapters.get(pos);
        }

        @Override
        public long getItemId(int pos) {
            return pos;
        }

        @Override
        public View getView(int pos, View convertView, ViewGroup parent) {
            View root = null;
            if (convertView == null) {
                root = mViewFactory.inflate(R.layout.progit_listitem, null, false);
            } else {
                root = convertView;
            }
            TextView textView = (TextView) root.findViewById(R.id.text);
            textView.setText(getItem(pos).mName);
            ImageView indicator = (ImageView) root.findViewById(R.id.indicator);
            indicator.setImageResource(R.drawable.button_tab_to_open);
            return root;
        }
    }
    
    private class ProGitChildListAdapter extends BaseAdapter {
        private List<ProGitChapter> mProGitChapters;
        private Context mContext;
        private LayoutInflater mViewFactory;
        private int mGroupIndex;
        private List<ProGitSection> mProGitSections;
        private ProGitChapter mChapter;
        
        public ProGitChildListAdapter(Context ctx, int groupIndex) {
            mContext = ctx;
            mProGitChapters = ProGit.getChapters();
            mViewFactory = LayoutInflater.from(ctx);
            mGroupIndex = groupIndex;
            mChapter = mProGitChapters.get(groupIndex);
        }
        
        @Override
        public int getCount() {
            return mChapter.getSectionCount();
        }

        @Override
        public ProGitSection getItem(int pos) {
            return mChapter.getSection(pos);
        }

        @Override
        public long getItemId(int pos) {
            return pos;
        }

        @Override
        public View getView(int pos, View convertView, ViewGroup parent) {
            View root = null;
            if (convertView == null) {
                root = mViewFactory.inflate(R.layout.progit_child_item, null, false);
            } else {
                root = convertView;
            }
            TextView textView = (TextView) root.findViewById(R.id.name);
            textView.setText(getItem(pos).mName);
            return root;
        }
    }
}
