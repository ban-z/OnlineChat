package com.example.onlinechat.fragment.UtilFragment;

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
import android.widget.EditText;
import android.widget.Toast;

import com.example.onlinechat.R;
import com.example.onlinechat.Utils.netAddress;

public class IPandPortFragment extends Fragment {

    private EditText et_ip;
    private EditText et_port;
    private Button btn_ipProt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ipand_port, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        /*测试：
         *管理toolbar问题*/
        AppBarLayout appBarLayout = getActivity().findViewById(R.id.appbar);
        appBarLayout.setVisibility(View.GONE);

        et_ip = view.findViewById(R.id.et_ip);
        et_port = view.findViewById(R.id.et_port);
        btn_ipProt = view.findViewById(R.id.btn_ipPort);

        btn_ipProt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                netAddress.setSocketIp(et_ip.getText().toString().trim());
                netAddress.setSocketPort(Integer.parseInt(et_port.getText().toString()));
                Log.d("IPandPortFragment: ", "onClick: netAddress update adress: " + netAddress.getSocketIp() +"::::"+ netAddress.getSocketPort());
                Toast.makeText(getActivity(), "网络配置成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
