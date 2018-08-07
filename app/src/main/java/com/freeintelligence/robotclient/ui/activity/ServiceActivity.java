package com.freeintelligence.robotclient.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.utils.AppManager;
import com.freeintelligence.robotclient.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceActivity extends BaseActivity {
    @BindView(R.id.title_Back)
    ImageView mBack;
    @BindView(R.id.tv_service3)
    TextView tvService3;
    @BindView(R.id.tv_service2)
    TextView tvService2;
    @BindView(R.id.tv_service1)
    TextView tvService1;
    @BindView(R.id.tv_service5)
    TextView tvService5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_service;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.title_Back,R.id.tv_service3, R.id.tv_service2, R.id.tv_service1,  R.id.tv_service5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_service3://跳转呼叫服务页面
                ToastUtils.makeText(context,"暂未开通");
                break;
            case R.id.tv_service1:
                //跳转接待表页面
                Intent reptableintent = new Intent(this, QuestionActivity.class);
                startActivity(reptableintent);
                break;
            case R.id.tv_service2:
                //跳转产品简介页面
                Intent briefintent = new Intent(this, BriefActivity.class);
                startActivity(briefintent);
                break;
            case R.id.tv_service5:
                //跳转产品展示页面
                Intent showintent = new Intent(this, ShowActivity.class);
                startActivity(showintent);
                break;
            case R.id.title_Back:
                AppManager.finishActivity();
                break;
        }
    }
}
