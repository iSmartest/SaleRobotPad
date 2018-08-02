package com.freeintelligence.robotclient.ui.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    RelativeLayout toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {

    }
}
