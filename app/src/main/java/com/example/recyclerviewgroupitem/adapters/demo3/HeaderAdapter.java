package com.example.recyclerviewgroupitem.adapters.demo3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewgroupitem.R;

import java.util.ArrayList;

public class HeaderAdapter extends RecyclerView.Adapter<HeaderAdapter.MyViewHolder> {

    private RecyclerItemListener mListener;
    private ArrayList<String> mHeaders;

    public interface RecyclerItemListener {
        void onItemShow(String header, RecyclerView recyclerView);
        void onItemRemove(String header, RecyclerView recyclerView);
    }

    public HeaderAdapter(ArrayList<String> headers, RecyclerItemListener listener) {
        this.mHeaders = headers;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header_with_child, parent, false);
        return new MyViewHolder(view);
    }

    private int drawerOption = 0;

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.textView.setText(mHeaders.get(position));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerOption == 0) {
                    drawerOption = 1;
                    onClickRotation(holder.imageButton);
                    mListener.onItemShow(mHeaders.get(position), holder.recyclerView);
                } else if (drawerOption == 1) {
                    drawerOption = 0;
                    onClickRotation(holder.imageButton);
                    mListener.onItemRemove(mHeaders.get(position), holder.recyclerView);
                }
            }
        });
    }

    private void onClickRotation(ImageButton imageButton) {
        float deg = (imageButton.getRotation() == 180F) ? 0F : 180F;
        imageButton.animate().rotation(deg).setInterpolator(new AccelerateDecelerateInterpolator());
    }

    @Override
    public int getItemCount() {
        return mHeaders.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView textView;
        ImageButton imageButton;
        RecyclerView recyclerView;
        MyViewHolder(View itemView) {
            super(itemView);
            layout = (LinearLayout) itemView.findViewById(R.id.layout_id);
            imageButton = (ImageButton) itemView.findViewById(R.id.header_icon);
            textView = (TextView) itemView.findViewById(R.id.header_title);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_view_lst_items_child);
        }
    }
}
