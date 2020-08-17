package com.example.recyclerviewgroupitem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewgroupitem.models.EventItem;
import com.example.recyclerviewgroupitem.models.HeaderItem;
import com.example.recyclerviewgroupitem.models.ListItem;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<ListItem> mItems;

    public MyAdapter(ArrayList<ListItem> mItems) {
        this.mItems = mItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ListItem.TYPE_HEADER) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false);
            return new HeaderViewHolder(itemView);
        } else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
            return new EventViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        int type = getItemViewType(position);
        if (type == ListItem.TYPE_HEADER) {
            HeaderItem header = (HeaderItem) mItems.get(position);
            HeaderViewHolder holder = (HeaderViewHolder) viewHolder;
            holder.txt_header.setText(header.getHeader());
        } else {
            EventItem event = (EventItem) mItems.get(position);
            EventViewHolder holder = (EventViewHolder) viewHolder;

            // your logic here
            holder.txt_title.setText(event.getEventModel().getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }

    private static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView txt_header;
        HeaderViewHolder(View itemView) {
            super(itemView);
            txt_header = (TextView) itemView.findViewById(R.id.header_title);
        }
    }

    private static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView txt_title;
        EventViewHolder(View itemView) {
            super(itemView);
            txt_title = (TextView) itemView.findViewById(R.id.even_title);
        }
    }

}
