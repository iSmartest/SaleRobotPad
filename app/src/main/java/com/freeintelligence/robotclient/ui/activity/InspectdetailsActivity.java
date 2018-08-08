package com.freeintelligence.robotclient.ui.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.config.MyString;
import com.freeintelligence.robotclient.config.Url;
import com.freeintelligence.robotclient.okhttp.MyOkhttp;
import com.freeintelligence.robotclient.ui.moudel.InspectdeailsBean;
import com.freeintelligence.robotclient.utils.AppManager;
import com.freeintelligence.robotclient.utils.SPUtil;
import com.freeintelligence.robotclient.utils.ToastUtils;
import com.freeintelligence.robotclient.view.FlowGroupView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InspectdetailsActivity extends BaseActivity {
    @BindView(R.id.title_Back)
    ImageView mBack;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.flow_view_group)
    FlowGroupView flowViewGroup;
    @BindView(R.id.lldeailsall)
    LinearLayout lldeailsall;
    @BindView(R.id.tv_indealis1)
    TextView tvIndealis1;
    @BindView(R.id.tv_indealis2)
    TextView tvIndealis2;
    @BindView(R.id.tv_indealis3)
    TextView tvIndealis3;
    private ArrayList<String> names;
    private int id=0;
    private int type=1;
    private int cid=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inspectdetails;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void loadData() {
        names = new ArrayList<String>();
        int intExtra = getIntent().getIntExtra(MyString.INTENTINSPECTID, 1);
        id=intExtra;
        int intExtra1 = getIntent().getIntExtra(MyString.INTENTINSPECTTYPE, 1);
        type=intExtra1;
        int intExtra2 = getIntent().getIntExtra(MyString.CUSTOMERID, 1);
        cid=intExtra2;
        starintnet();
    }
    private void starintnet() {
        /* 维修保养详情
    @param storeId 4s店id
    @param customerId 用户id
    @param type 类型 1维修 2保养
    @param detailId 详情id*/
        Map<String,String> params = new HashMap<>();
        params.put("storeId",  SPUtil.getString(context,"storeId"));
        params.put("customerId", cid+"");
        params.put("type", type+"");
        params.put("detailId", id+"");

        MyOkhttp.Okhttp(context, Url.DETAIL, "加载中...", params, new MyOkhttp.CallBack() {
            @Override
            public void onRequestComplete(String response, String result, String resultNote) {
                Gson gson = new Gson();
                InspectdeailsBean inspectdeailsBean = gson.fromJson(response, InspectdeailsBean.class);
                if (result.equals("1")) {
                    lldeailsall.setVisibility(View.GONE);
                    ToastUtils.makeText(context, resultNote);
                    return;
                }
                InspectdeailsBean.DataBean data = inspectdeailsBean.getData();
                InspectdeailsBean.DataBean.CommonBean common = data.getCommon();
                lldeailsall.setVisibility(View.VISIBLE);
                tvIndealis1.setText("车型：" + common.getCarName());
                tvIndealis2.setText("牌号：" + common.getCarPhone());
                tvIndealis3.setText("公里数：" + common.getCarName());
                List<String> itemList = data.getItemList();
                names.clear();
                names.addAll(itemList);
                setTwoFlowLayout();
            }
        });
    }
    private void setTwoFlowLayout() {
        for (int i = 0; i < names.size(); i++) {
            addTextView(names.get(i));
        }
    }
    private void addTextView(String s) {
        TextView child = new TextView(this);
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
        params.setMargins(5, 5, 5, 5);
        child.setLayoutParams(params);
        child.setBackgroundResource(R.drawable.shape_sideline);
        child.setText(s);
        child.setTextColor(Color.BLACK);
        child.setTextSize(18);
        child.setPadding(15, 15, 15, 15);
        flowViewGroup.addView(child);
    }

    @Override
    protected void initView() {
        lldeailsall.setVisibility(View.GONE);
    }

    @OnClick({R.id.title_Back})
    public void onViewClicked() {
        AppManager.finishActivity();
    }
}
