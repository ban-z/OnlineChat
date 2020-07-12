package com.example.onlinechat.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.navigation.Navigation;

import com.example.onlinechat.Control.Client;
import com.example.onlinechat.Data.User;
import com.example.onlinechat.MainActivity;
import com.example.onlinechat.R;
import com.example.onlinechat.Utils.SharedPreference;

public class loginFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private SharedPreference preference;

    private Button btn_login;
    private Button btn_register;
    private CheckBox checkBoxUser;
    private EditText et_userAccount;
    private EditText et_password;

    private static boolean flag_login = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        preference = new SharedPreference(getActivity());
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        /*测试：
         *管理toolbar问题*/
        AppBarLayout appBarLayout = getActivity().findViewById(R.id.appbar);
        appBarLayout.setVisibility(View.INVISIBLE);

        btn_login = view.findViewById(R.id.btn_login);
        btn_register = view.findViewById(R.id.btn_register);
        checkBoxUser = view.findViewById(R.id.checkBoxForUser);
        et_userAccount = view.findViewById(R.id.et_userAccount);
        et_password = view.findViewById(R.id.et_password);

        et_userAccount.setText(preference.getSharedUserAccount() + "");
        et_password.setText(preference.getSharedUserPassword());

        /*InputUserInformation();*/

        checkBoxUser.setOnCheckedChangeListener(this);
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    /*private void InputUserInformation() {
        int account = preference.getSharedUserAccount();
        String password = preference.getSharedUserPassword();
        if (password != null) {
            et_userAccount.setText(account);
            et_password.setText(password);
            Toast.makeText(getActivity(), "自动填充用户信息", Toast.LENGTH_SHORT).show();
        }
    }*/

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                /*Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_homeFragment);*/

                preference.setSharedPreferences(Integer.parseInt(et_userAccount.getText().toString()), et_password.getText().toString());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        boolean check = login(Integer.parseInt(et_userAccount.getText().toString()), et_password.getText().toString());
                        if (check){
                            flag_login = true;
                            Log.d("LoginFragment: ", "run: ##############################flag_login = " + flag_login);
                        }
                    }
                }).start();

                        if (flag_login == true){
                            Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_homeFragment);
                        }else {
                            Toast.makeText(getActivity(), "请确认再次确认", Toast.LENGTH_SHORT).show();
                        }
                break;
                /**********************************************************************************************************************************/
            case R.id.btn_register:
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_registerFragment);
                break;
        }
    }

    private boolean login(int account, String password) {
        User user = new User("login", account, password);
        saveUserInformation(account, password);
        boolean flag = new Client(getContext()).sendLoginInfo(user);
        if (flag){
            return true;
        }
        return false;
    }

    /*
     * 监听CheckBox的改变，保存用户信息
     * */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            int account = Integer.parseInt(et_userAccount.getText().toString());
            String password = et_password.getText().toString().trim();
            saveUserInformation(account, password);
            Toast.makeText(getActivity(), "用户信息已保存", Toast.LENGTH_SHORT).show();
        }
    }
    public void saveUserInformation(int account, String password){
        preference.setSharedPreferences(account, password);
    }
}
