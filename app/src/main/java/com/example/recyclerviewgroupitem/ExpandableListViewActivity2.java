package com.example.recyclerviewgroupitem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.recyclerviewgroupitem.models.MyModel;

import java.util.ArrayList;

public class ExpandableListViewActivity2 extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view2);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_lst_items);
        initRecyclerView(loadHeaders());
    }

    private void initRecyclerView(ArrayList<String> arrayList) {
        mAdapter = new MyAdapter(arrayList);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @NonNull
    private ArrayList<MyModel> loadEvents() {
        ArrayList<MyModel> arrayList = new ArrayList<>();
        arrayList.add(new MyModel("Jan1", "2020-01-21 01:57:33.435"));
        arrayList.add(new MyModel("Feb1", "2020-02-01 01:57:33.435"));
        arrayList.add(new MyModel("Jan2", "2020-01-31 01:57:33.435"));
        arrayList.add(new MyModel("Dec3", "2020-12-21 01:57:33.435"));
        arrayList.add(new MyModel("Dec1", "2020-12-11 01:57:33.435"));
        arrayList.add(new MyModel("Dec2", "2020-12-12 01:57:33.435"));
        arrayList.add(new MyModel("Mar2", "2020-03-17 01:57:33.435"));
        arrayList.add(new MyModel("Mar1", "2020-03-01 01:57:33.435"));
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

    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private RecyclerItemListener mListener;
        private ArrayList<String> mItems;

        public interface RecyclerItemListener {
            void onItemShow(String header);
        }

        public MyAdapter(ArrayList<String> mItems) {
            this.mItems = mItems;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header_with_child, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
            holder.textView.setText(mItems.get(position));
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemShow(mItems.get(position));
                }
            });
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        private class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout layout;
            TextView textView;
            RecyclerView recyclerView;
            MyViewHolder(View itemView) {
                super(itemView);
                layout = (LinearLayout) itemView.findViewById(R.id.layout_id);
                textView = (TextView) itemView.findViewById(R.id.header_title);
                recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_view_lst_items_child);
            }
        }

    }

}