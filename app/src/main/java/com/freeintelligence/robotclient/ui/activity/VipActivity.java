package com.freeintelligence.robotclient.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.config.MyString;
import com.freeintelligence.robotclient.ui.fragment.ConsumeFragment;
import com.freeintelligence.robotclient.ui.fragment.IntegralFragment;
import com.freeintelligence.robotclient.ui.moudel.LoadBean;
import com.freeintelligence.robotclient.utils.AppManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VipActivity extends BaseActivity {
    @BindView(R.id.title_Back)
    ImageView mBack;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.rb_vip1)
    RadioButton rbVip1;
    @BindView(R.id.rb_vip2)
    RadioButton rbVip2;
    @BindView(R.id.rg_vip)
    RadioGroup rgVip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        //默认选中第一个按键

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_vip;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        final Bundle bundle = new Bundle();
        LoadBean.DataBean data = (LoadBean.DataBean) getIntent().getSerializableExtra(MyString.LOADDATA);
        bundle.putSerializable(MyString.VIPDATA, data);
        changeFragment(IntegralFragment.class, R.id.fl_vip, false, bundle, false);
        //设置选中事件
        rgVip.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rb_vip1:
                        changeFragment(IntegralFragment.class, R.id.fl_vip, false, bundle, false);
                        break;
                    case R.id.rb_vip2:
                        changeFragment(ConsumeFragment.class, R.id.fl_vip, false, bundle, false);
                        break;
                }
            }
        });
    }

    @OnClick({R.id.title_Back})
    public void onViewClicked() {
        AppManager.finishActivity();
    }
}
