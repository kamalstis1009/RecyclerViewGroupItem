package com.example.recyclerviewgroupitem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.recyclerviewgroupitem.adapters.activity2.HeaderAdapter;
import com.example.recyclerviewgroupitem.adapters.activity2.MyItemAdapter;
import com.example.recyclerviewgroupitem.models.MyModel;

import java.util.ArrayList;

public class ExpandableListViewActivity2 extends AppCompatActivity implements HeaderAdapter.RecyclerItemListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view2);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_lst_items);
        initRecyclerView(loadHeaders(), mRecyclerView);
    }

    private void initRecyclerView(ArrayList<String> headers, RecyclerView mRecyclerView) {
        HeaderAdapter mAdapter = new HeaderAdapter(headers, this);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemShow(String header, RecyclerView recyclerView) {
        ArrayList<MyModel> list = loadEvents();
        ArrayList<MyModel> arrayList = new ArrayList<>();
        for (MyModel model : list) {
            if (model.getTitle().equals(header)) {
                arrayList.add(model);
            }
        }
        MyItemAdapter adapter = new MyItemAdapter(arrayList);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void clear(ArrayList<MyModel> arrayList, MyItemAdapter adapter) {
        arrayList.clear();
        adapter.notifyDataSetChanged();
    }


    @NonNull
    private ArrayList<MyModel> loadEvents() {
        ArrayList<MyModel> arrayList = new ArrayList<>();
        arrayList.add(new MyModel("January", "2020-01-21 01:57:33.435"));
        arrayList.add(new MyModel("February", "2020-02-01 01:57:33.435"));
        arrayList.add(new MyModel("January", "2020-01-31 01:57:33.435"));
        arrayList.add(new MyModel("December", "2020-12-21 01:57:33.435"));
        arrayList.add(new MyModel("December", "2020-12-11 01:57:33.435"));
        arrayList.add(new MyModel("December", "2020-12-12 01:57:33.435"));
        arrayList.add(new MyModel("March", "2020-03-17 01:57:33.435"));
        arrayList.add(new MyModel("March", "2020-03-01 01:57:33.435"));
        return arrayList;
    }

    @NonNull
    private ArrayList<String> loadHeaders() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("January");
        arrayList.add("February");
        arrayList.add("March");
        arrayList.add("April");
        arrayList.add("May");
        arrayList.add("Jun");
        arrayList.add("July");
        arrayList.add("August");
        arrayList.add("September");
        arrayList.add("October");
        arrayList.add("November");
        arrayList.add("December");
        return arrayList;
    }

}