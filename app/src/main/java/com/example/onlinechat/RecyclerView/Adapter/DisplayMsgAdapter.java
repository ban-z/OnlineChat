package com.example.onlinechat.RecyclerView.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.onlinechat.R;
import com.example.onlinechat.RecyclerView.Model.DisplayMsg;

import java.util.List;

public class DisplayMsgAdapter extends RecyclerView.Adapter<DisplayMsgAdapter.ViewHolder> {

    private List<DisplayMsg> displayMsgList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout receiverLayout;
        LinearLayout senderLayout;
        TextView tv_receiver;
        TextView tv_sender;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            receiverLayout = itemView.findViewById(R.id.linearlayout_receiver);
            senderLayout = itemView.findViewById(R.id.linearlayout_sender);
            tv_receiver = itemView.findViewById(R.id.receive_msg);
            tv_sender = itemView.findViewById(R.id.send_msg);
        }
    }

    public DisplayMsgAdapter(List<DisplayMsg> msgList){
        displayMsgList = msgList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        DisplayMsg displayMsg = displayMsgList.get(i);
        if (displayMsg.getType() == DisplayMsg.TYPE_RECEIVED){
            viewHolder.receiverLayout.setVisibility(View.VISIBLE);
            viewHolder.senderLayout.setVisibility(View.GONE);
            viewHolder.tv_receiver.setText(displayMsg.getContent());
        }else if (displayMsg.getType() == DisplayMsg.TYPE_SENT){
            viewHolder.receiverLayout.setVisibility(View.GONE);
            viewHolder.senderLayout.setVisibility(View.VISIBLE);
            viewHolder.tv_sender.setText(displayMsg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return displayMsgList.size();
    }
}
