package com.example.recyclerviewgroupitem.adapters.activity2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewgroupitem.R;
import com.example.recyclerviewgroupitem.models.MyModel;

import java.util.ArrayList;

public class MyItemAdapter extends RecyclerView.Adapter<MyItemAdapter.MyViewHolder2> {

    private ArrayList<MyModel> mItems;

    public MyItemAdapter(ArrayList<MyModel> items) {
        this.mItems = items;
    }

    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new MyViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, final int position) {
        holder.textView.setText(mItems.get(position).getDate() + "");
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class MyViewHolder2 extends RecyclerView.ViewHolder {
        TextView textView;
        MyViewHolder2(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.even_title);
        }
    }
}
