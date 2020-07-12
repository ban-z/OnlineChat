package com.example.onlinechat.RecyclerView.Adapter;

import android.content.BroadcastReceiver;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.onlinechat.R;
import com.example.onlinechat.RecyclerView.Model.LiveBroads;

import java.util.ArrayList;
import java.util.List;

public class LiveBroadsAdapter  extends RecyclerView.Adapter<LiveBroadsAdapter.ViewHolder> {

    private List<String> BroadList;

    public LiveBroadsAdapter(List<String> list){
        BroadList = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_live_broadcast;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_live_broadcast = itemView.findViewById(R.id.tv_live_broadcast);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.broad_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String broad = BroadList.get(i);
        viewHolder.tv_live_broadcast.setText(broad);
    }

    @Override
    public int getItemCount() {
        return BroadList.size();
    }
}
