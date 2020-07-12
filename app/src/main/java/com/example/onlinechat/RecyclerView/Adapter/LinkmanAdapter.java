package com.example.onlinechat.RecyclerView.Adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.navigation.Navigation;

import com.example.onlinechat.R;
import com.example.onlinechat.RecyclerView.Model.LinkmanPeople;

import java.util.List;

public class LinkmanAdapter extends RecyclerView.Adapter<LinkmanAdapter.ViewHolder> {

    private List<LinkmanPeople> LinkmanList;

    public LinkmanAdapter(List<LinkmanPeople> list){
        LinkmanList = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View ItemView;
        TextView tv_linkman_people_account;
        TextView tv_linkman_people_rcmd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ItemView = itemView;
            tv_linkman_people_account = itemView.findViewById(R.id.tv_linkman_people_account);
            tv_linkman_people_rcmd = itemView.findViewById(R.id.tv_linkman_people_rcmd);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.linkman_people_item, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.ItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Receiver = Integer.parseInt(holder.tv_linkman_people_account.getText().toString());
                Bundle bundle_account = new Bundle();
                bundle_account.putInt("item_account", Receiver);
                Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_chatFragment, bundle_account);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        LinkmanPeople people = LinkmanList.get(i);
        viewHolder.tv_linkman_people_account.setText(people.getAccount() + "");
        viewHolder.tv_linkman_people_rcmd.setText(people.getRcmdOneself());
    }

    @Override
    public int getItemCount() {
        return LinkmanList.size();
    }
}
