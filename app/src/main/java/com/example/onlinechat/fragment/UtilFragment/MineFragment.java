package com.example.onlinechat.fragment.UtilFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlinechat.R;
import com.example.onlinechat.Utils.SharedPreference;

public class MineFragment extends Fragment {

    private ImageView img_myself;
    private TextView tv_account;
    private TextView tv_password;

    private SharedPreference preference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        preference = new SharedPreference(getActivity());
        /*测试：
         *管理toolbar问题*/
        AppBarLayout appBarLayout = getActivity().findViewById(R.id.appbar);
        appBarLayout.setVisibility(View.GONE);

        img_myself = view.findViewById(R.id.img_myself);
        tv_account = view.findViewById(R.id.tv_account);
        tv_password = view.findViewById(R.id.tv_password);

        tv_account.setText(preference.getSharedUserAccount() + "");
        tv_password.setText(preference.getSharedUserPassword());
    }
}
