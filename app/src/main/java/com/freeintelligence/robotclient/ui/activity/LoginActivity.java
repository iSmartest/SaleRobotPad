package com.freeintelligence.robotclient.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.config.Url;
import com.freeintelligence.robotclient.okhttp.MyOkhttp;
import com.freeintelligence.robotclient.ui.moudel.LoadcodeBean;
import com.freeintelligence.robotclient.ui.moudel.LoginBean;
import com.freeintelligence.robotclient.utils.SPUtil;
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
    EditText etLoginPassword;
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


        tvLoginLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mAccount = etLoginAccount.getText().toString().trim();
                final String mPassword = etLoginPassword.getText().toString().trim();
                if (mAccount.isEmpty()){
                    ToastUtils.makeText(context,"请输入账号");
                    return;
                }
                if (mPassword.isEmpty()){
                    ToastUtils.makeText(context,"请输入密码");
                    return;
                }
                starinternet(mAccount,mPassword);
            }
        });
    }

    private void starinternet(String mAccount,String mPassword) {
        Map<String, String> map = new HashMap<>();
        map.put("phone", mAccount);
        map.put("password", mPassword);
        map.put("index", "18337125295");
        MyOkhttp.Okhttp(context, Url.LOGIN, "正在获取...", map, new MyOkhttp.CallBack() {
            @Override
            public void onRequestComplete(String response, String result, String resultNote) {
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(response,LoginBean.class);
                if (result.equals("1")) {
                    ToastUtils.makeText(context, resultNote);
                    return;
                }
                SPUtil.putString(context,"storeId",loginBean.getData().getStoreId()+"");

                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
