package com.freeintelligence.robotclient.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.app.App;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.utils.AppManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SubscribeActivity extends BaseActivity {
    @BindView(R.id.title_Back)
    ImageView mBack;
    @BindView(R.id.tv_textdrive)
    TextView tvTextdrive;
    @BindView(R.id.tv_maintain)
    TextView tvMaintain;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_subscribe;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.title_Back,R.id.tv_textdrive, R.id.tv_maintain})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_textdrive:
                Intent testintent = new Intent(this, TestDriveActivity.class);
                startActivity(testintent);
                break;
            case R.id.tv_maintain:
                Intent maintainintent = new Intent(this, MaintainActivity.class);
                startActivity(maintainintent);
                break;
            case R.id.title_Back:
                AppManager.finishActivity();
                break;
        }
    }
}
