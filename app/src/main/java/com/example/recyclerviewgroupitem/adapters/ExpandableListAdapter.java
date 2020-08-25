package com.example.recyclerviewgroupitem.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;


import com.example.recyclerviewgroupitem.R;
import com.example.recyclerviewgroupitem.models.MyModel;

import java.util.ArrayList;
import java.util.Map;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context mActivity;
    private ArrayList<String> mHeaders;
    private Map<String, ArrayList<MyModel>> mMap;

    public ExpandableListAdapter(Context activity, ArrayList<String> headers, Map<String, ArrayList<MyModel>> map) {
        this.mActivity = activity;
        this.mHeaders = headers;
        this.mMap = map;
    }

    @Override
    public Object getChild(int position, int expandedPosition) {
        return this.mMap.get(this.mHeaders.get(position)).get(expandedPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int position, int expandedPosition, boolean isLastChild, View view, ViewGroup parent) {
        final MyModel model = (MyModel) getChild(position, expandedPosition);
        if (view == null) {
            view = LayoutInflater.from(mActivity).inflate(R.layout.item_event, parent, false);
        }
        ((TextView) view.findViewById(R.id.even_title)).setText(model.getDate() + "");
        return view;
    }

    @Override
    public int getChildrenCount(int position) {
        return this.mMap.get(this.mHeaders.get(position)).size();
    }

    @Override
    public Object getGroup(int position) {
        return this.mHeaders.get(position);
    }

    @Override
    public int getGroupCount() {
        return this.mHeaders.size();
    }

    @Override
    public long getGroupId(int position) {
        return position;
    }

    @Override
    public View getGroupView(int position, boolean isExpanded, View view, ViewGroup parent) {
        String header = (String) getGroup(position);
        if (view == null) {
            view = LayoutInflater.from(mActivity).inflate(R.layout.item_header, parent, false);
            //Always expand group items
            //ExpandableListView eLV = (ExpandableListView) parent;
            //eLV.expandGroup(position);
        }
        ((TextView) view.findViewById(R.id.header_title)).setText(header + "");
        return view;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}