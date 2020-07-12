package com.example.onlinechat.fragment.HomeFrag;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.example.onlinechat.Control.ManageClientToServer;
import com.example.onlinechat.Control.sendMessage;
import com.example.onlinechat.Data.MessageType;
import com.example.onlinechat.Data.Mine;
import com.example.onlinechat.MainActivity;
import com.example.onlinechat.Notifaction.NoteReceive;
import com.example.onlinechat.R;
import com.example.onlinechat.RecyclerView.Adapter.DisplayMsgAdapter;
import com.example.onlinechat.RecyclerView.Model.DisplayMsg;
import com.example.onlinechat.fragment.HomeFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment implements View.OnClickListener {


    private EditText et_input;
    private Button btn_send;
    private RecyclerView rv_chat_body;
    private DisplayMsgAdapter msgAdapter;

    private int Receiver;
    public static List<DisplayMsg> msgList = new ArrayList<DisplayMsg>();
    public static Handler handler;

    public static Handler getHandler() {
        return handler;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Receiver = getArguments().getInt("item_account");
        Log.d("ChatFragment receiver", " Bundle onCreate: >>>>>>>>>>>>>--------->>>>>>>>>>>>>>" + Receiver);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
    }

    private void initView(View view) {
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(String.valueOf(Receiver));
        Log.d("ChatFragment toolbar ", "initView:-=-=-=-=-=-=-=-=-=-=-=-=-=>>> " + toolbar);

        et_input = view.findViewById(R.id.et_input);
        btn_send = view.findViewById(R.id.btn_send);
        rv_chat_body = view.findViewById(R.id.rv_chat_body);
        msgAdapter = new DisplayMsgAdapter(msgList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv_chat_body.setLayoutManager(layoutManager);
        rv_chat_body.setAdapter(msgAdapter);

        btn_send.setOnClickListener(this);

        //hander
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what ==  1){
                    String information = (String) msg.obj;
                    String[] msgStr = information.split(",");
                    DisplayMsg Dmsg = new DisplayMsg(msgStr[1], DisplayMsg.TYPE_RECEIVED);
                    msgList.add(Dmsg);
                    msgAdapter.notifyItemChanged(msgList.size() - 1);
                    rv_chat_body.scrollToPosition(msgList.size() - 1);
                    Log.d("ChatFragment: ", "handleMessage: &&&&&&&&&&&&&&&&&&&---------->>>>" + information);
                    new NoteReceive(getContext(),Integer.parseInt(msgStr[0]), msgStr[1]).displayNotificaiton();
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        final String information = et_input.getText().toString();
        Log.d("ChatFragment", "onClick:++++++++++++++++++++++++++++++++++>>> " + information);
        if (!"".equals(information)){
            DisplayMsg msg = new DisplayMsg(information, DisplayMsg.TYPE_SENT);
            msgList.add(msg);
            msgAdapter.notifyItemChanged(msgList.size() - 1);
            rv_chat_body.scrollToPosition(msgList.size() - 1);
            et_input.setText("");
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                sendMessage.sendMsg(Receiver, information, MessageType.COM_MSG);
            }
        }).start();
    }
}
