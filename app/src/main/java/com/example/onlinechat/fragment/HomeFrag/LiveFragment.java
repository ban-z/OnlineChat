package com.example.onlinechat.fragment.HomeFrag;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.onlinechat.Notifaction.NoteReceive;
import com.example.onlinechat.R;
import com.example.onlinechat.RecyclerView.Adapter.LiveBroadsAdapter;
import com.example.onlinechat.RecyclerView.Model.DisplayMsg;

import java.util.ArrayList;
import java.util.List;

public class LiveFragment extends Fragment {

    private TextView tv_live_broadcast;
    private RecyclerView rv_live_broadcast;
    private LiveBroadsAdapter adapter;

    public static List<String> BroadList = new ArrayList<String>();

    public static Handler handler;

    public static Handler getHandler() {
        return handler;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_live, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Broadcast");

        initView(view);
        initBroadCast();
    }

    private void initView(View view) {
        tv_live_broadcast = view.findViewById(R.id.tv_live_broadcast);
        rv_live_broadcast = view.findViewById(R.id.rv_live_broadcast);

        adapter = new LiveBroadsAdapter(BroadList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv_live_broadcast.setLayoutManager(layoutManager);
        rv_live_broadcast.setAdapter(adapter);
    }

    private void initBroadCast() {
        //hander
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what ==  999){
                    String information = (String) msg.obj;
                    Log.d("ChatFragment: ", "handle BroadCast: &&&&&&&&&&&&&&&&&&&---------->>>>" + information);
                    BroadList.add(information);
                    adapter.notifyItemChanged(BroadList.size() - 1);
                    rv_live_broadcast.scrollToPosition(BroadList.size() - 1);
                    new NoteReceive(getContext(), 0000000, information).displayNotificaiton();
                }
            }
        };
    }
}
