package com.example.onlinechat.fragment.HomeFrag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.onlinechat.R;
import com.example.onlinechat.RecyclerView.Adapter.LinkmanAdapter;
import com.example.onlinechat.RecyclerView.Model.LinkmanPeople;
import com.example.onlinechat.Utils.FriendList;
import com.example.onlinechat.Utils.RequestFriends;

import java.util.ArrayList;
import java.util.List;

public class LinkmanFragment extends Fragment implements View.OnClickListener {

    private List<LinkmanPeople> LinkmanList = new ArrayList<LinkmanPeople>();
    private RecyclerView recyclerView;

    private Button btn_addFriend;
    private Button btn_update_friends;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        * 测试RecyclerView
        * */
        /*initLinkman();*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_linkman, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Linkman");
        /*需要将此类的实例化放入到onCreate()
         * 测试RecyclerView
         * *//*
        initLinkman();*/
        recyclerView = view.findViewById(R.id.rv_linkman);
        btn_addFriend = view.findViewById(R.id.btn_addFriend);
        btn_update_friends = view.findViewById(R.id.btn_update_friends);
        btn_update_friends.setOnClickListener(this);
        btn_addFriend.setOnClickListener(this);
        /*
         * RecyclerView有多种排列方式，此实例中获取到的LinearLayoutManager为线性排序的Manager
         * */
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        /*
         * 实例化此SeesionAdapter，为RecyclerView添加适配器
         * */
        LinkmanAdapter linkmanAdapter = new LinkmanAdapter(LinkmanList);
        recyclerView.setAdapter(linkmanAdapter);
    }

   /* private void initLinkman() {
        LinkmanPeople people1 = new LinkmanPeople(11111);
        LinkmanList.add(people1);
        LinkmanPeople people2 = new LinkmanPeople(11111);
        LinkmanList.add(people2);
        LinkmanPeople people3 = new LinkmanPeople(11111);
        LinkmanList.add(people3);
        LinkmanPeople people4 = new LinkmanPeople(11111);
        LinkmanList.add(people4);
        LinkmanPeople people5 = new LinkmanPeople(11111);
        LinkmanList.add(people5);
        LinkmanPeople people6 = new LinkmanPeople(11111);
        LinkmanList.add(people6);
        LinkmanPeople people7 = new LinkmanPeople(11111);
        LinkmanList.add(people7);
    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_addFriend:
                new RequestFriends().start();
                break;
            case R.id.btn_update_friends:
                List<Integer> friends = FriendList.getFriends();
                for (int i = 0; i < friends.size(); i++){
                    LinkmanList.add(new LinkmanPeople(friends.get(i)));
                }
                recyclerView.setAdapter(new LinkmanAdapter(LinkmanList));
                break;
        }
    }
}
