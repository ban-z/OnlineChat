package com.example.onlinechat.RecyclerView.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.onlinechat.R;
import com.example.onlinechat.RecyclerView.Model.SessionPeople;

import java.util.ArrayList;
import java.util.List;

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.ViewHolder> {

    private List<SessionPeople> SessionList;

    public SessionAdapter(List<SessionPeople> list){
        SessionList = list;
    }

    /*
    * 通过ViewHolder实例化子项布局组件，减少了每次都通过findViewById寻找，提高性能
    * 参数传入子项顶层布局
    * */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_session_people_account;
        TextView tv_session_people_rcmd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_session_people_account = itemView.findViewById(R.id.tv_session_people_account);
            tv_session_people_rcmd = itemView.findViewById(R.id.tv_session_people_rcmd);
        }
    }


    /*
    * 从名字也可以看出，此方法是用来创建ViewHolder实例的
    * 所以参数传入子项布局顶层布局
    * */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.session_people_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    /*
    * 通过ViewHolder实例化的子项组件，通过此方法用来给子组件赋值
    * 会在每个子项被滚到屏幕内的时候执行
    * 通过position获取到当前项的实例，让后通过数据设置到ViewHolder的子组件中
    * */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
            SessionPeople people = SessionList.get(i);

            /*
            * TextView传入问题
            * android.content.res.Resources$NotFoundException: String resource ID #0x10f447
            * TextView接受的为字符串，传入int值会报此错误
            * 对于其他调用此方法的函数均需要同样处理
            * *//*
            viewHolder.tv_session_people_account.setText(people.getAccount());*/

            //改正之后的调用
            viewHolder.tv_session_people_account.setText(people.getAccount() + "");

            viewHolder.tv_session_people_rcmd.setText(people.getRcmdOneself());
    }

    /*
    * 通知RecyclerView共有多少子项，返回数据源的长度即可
    * */
    @Override
    public int getItemCount() {
        return SessionList.size();
    }
}
