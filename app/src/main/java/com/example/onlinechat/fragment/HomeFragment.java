package com.example.onlinechat.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.onlinechat.R;
import com.example.onlinechat.fragment.HomeFrag.LinkmanFragment;
import com.example.onlinechat.fragment.HomeFrag.LiveFragment;
import com.example.onlinechat.fragment.HomeFrag.SessionFragment;

import static android.support.design.widget.BottomNavigationView.*;

public class HomeFragment extends Fragment {

    public static BottomNavigationView bottomNav;

    private Fragment SessionFragment;
    private Fragment LinkmanFragment;
    private Fragment LiveFragment;

    public static FragmentManager fragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SessionFragment = new SessionFragment();
        LinkmanFragment = new LinkmanFragment();
        LiveFragment = new LiveFragment();

        fragmentManager = getFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("OnlineChat");*/

        /*测试：
         *管理toolbar问题*/
        AppBarLayout appBarLayout = getActivity().findViewById(R.id.appbar);
        appBarLayout.setVisibility(View.VISIBLE);


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        replaceFragment(SessionFragment);
        bottomNav = view.findViewById(R.id.bottomNav);
        bottomNav.setVisibility(VISIBLE);

        bottomNav.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.btm_nav_seesion:
                        replaceFragment(SessionFragment);
                        return true;
                    case R.id.btm_nav_linkman:
                        replaceFragment(LinkmanFragment);
                        return true;
                    case R.id.btm_nav_live:
                        replaceFragment(LiveFragment);
                        return true;
                }
                return false;
            }
        });

        return view;
    }



    public static void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.HomeFragment, fragment);
        transaction.commit();
    }
}
