package com.freeintelligence.robotclient.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.config.MyString;
import com.freeintelligence.robotclient.ui.fragment.InsuranceFragment;
import com.freeintelligence.robotclient.ui.fragment.MaintainFragment;
import com.freeintelligence.robotclient.ui.fragment.RepairFragment;
import com.freeintelligence.robotclient.ui.moudel.LoadBean;
import com.freeintelligence.robotclient.utils.AppManager;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InspectActivity extends BaseActivity {
    @BindView(R.id.title_Back)
    ImageView mBack;
    @BindView(R.id.rg_inspect)
    RadioGroup rgInspect;
    @BindView(R.id.rb_in1)
    RadioButton rbIn1;
    @BindView(R.id.rb_in2)
    RadioButton rbIn2;
    @BindView(R.id.rb_in3)
    RadioButton rbIn3;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.fl_inspect)
    FrameLayout flInspect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inspect;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        final Bundle bundle = new Bundle();
        LoadBean.DataBean data = (LoadBean.DataBean)getIntent().getSerializableExtra(MyString.LOADDATA);
        bundle.putSerializable(MyString.INSPECTDATA,data);
        changeFragment(RepairFragment.class, R.id.fl_inspect, false, bundle, false);
        rgInspect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rb_in1:
                        changeFragment(RepairFragment.class, R.id.fl_inspect, false, bundle, false);
                        break;
                    case R.id.rb_in2:
                        changeFragment(MaintainFragment.class, R.id.fl_inspect, false, bundle, false);
                        break;
                    case R.id.rb_in3:
                        changeFragment(InsuranceFragment.class, R.id.fl_inspect, false, bundle, false);
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
