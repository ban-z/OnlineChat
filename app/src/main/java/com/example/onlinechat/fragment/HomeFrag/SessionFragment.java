package com.example.onlinechat.fragment.HomeFrag;

import android.os.Bundle;
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

import com.example.onlinechat.R;
import com.example.onlinechat.RecyclerView.Adapter.SessionAdapter;
import com.example.onlinechat.RecyclerView.Model.SessionPeople;

import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.List;

public class SessionFragment extends Fragment {

    private List<SessionPeople> peopleList = new ArrayList<SessionPeople>();
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        /*
         * 测试RecyclerView
         * */
        /*initSession();*/

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_session, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Session");

        recyclerView = view.findViewById(R.id.rv_session);
        /*
        * RecyclerView有多种排列方式，此实例中获取到的LinearLayoutManager为线性排序的Manager
        * */
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        /*
        * 实例化此SeesionAdapter，为RecyclerView添加适配器
        * */
        SessionAdapter adapter = new SessionAdapter(peopleList);
        recyclerView.setAdapter(adapter);
    }

    /*private void initSession() {
        SessionPeople people1 = new SessionPeople(1111111);
        peopleList.add(people1);
        SessionPeople people2 = new SessionPeople(2222222);
        peopleList.add(people2);
        SessionPeople people3 = new SessionPeople(3333333);
        peopleList.add(people3);
        SessionPeople people4 = new SessionPeople(4444444);
        peopleList.add(people4);
        SessionPeople people5 = new SessionPeople(5555555);
        peopleList.add(people5);
        SessionPeople people6 = new SessionPeople(6666666);
        peopleList.add(people6);
        SessionPeople people7 = new SessionPeople(7777777);
        peopleList.add(people7);
        SessionPeople people8 = new SessionPeople(8888888);
        peopleList.add(people8);
        Log.d("SeesionFragment: ", "initSession: " + peopleList.toString());
    }*/
}
