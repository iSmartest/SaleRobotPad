package com.freeintelligence.robotclient.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.config.Url;
import com.freeintelligence.robotclient.okhttp.MyOkhttp;
import com.freeintelligence.robotclient.ui.moudel.LoadcodeBean;
import com.freeintelligence.robotclient.utils.ToastUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_login_account)
    EditText etLoginAccount;
    @BindView(R.id.et_login_possword)
    EditText etLoginPossword;
    @BindView(R.id.tv_login_load)
    TextView tvLoginLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void loadData() {
    }

    @Override
    protected void initView() {

//        final String serialNum = android.os.Build.SERIAL;
        tvLoginLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                starinternet(serialNum);
                starinternet("");
            }
        });
    }

    private void starinternet(String serialNum) {

        Map<String, String> map = new HashMap<>();
        map.put("phone", "18332212560");
        map.put("password", "1234");
        map.put("index", "1");
        MyOkhttp.Okhttp(context, Url.LOADLOGIN, "正在获取...", map, new MyOkhttp.CallBack() {
            @Override
            public void onRequestComplete(String response, String result, String resultNote) {
                Gson gson = new Gson();
                LoadcodeBean loadcodeBean = gson.fromJson(response, LoadcodeBean.class);
                if (result.equals("1")) {
                    ToastUtils.makeText(context, resultNote);
                    return;
                }
                ToastUtils.makeText(context, resultNote);
            }
        });
    }
}
