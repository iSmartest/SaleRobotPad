package com.freeintelligence.robotclient.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import com.freeintelligence.robotclient.ui.adapter.NewCarInfoRecyclerViewAdapter;
import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewCarInformationActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.rl_newcarinfor)
    RecyclerView rlNewcarinfor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_newcarinformation;
    }

    @Override
    protected void loadData() {

    }
    @Override
    protected void initView() {
        //设置2列的流线布局
        rlNewcarinfor.setLayoutManager(new GridLayoutManager(context,2));
        NewCarInfoRecyclerViewAdapter newCarInfoRecyclerViewAdapter = new NewCarInfoRecyclerViewAdapter();
        //关联适配器
        rlNewcarinfor.setAdapter(newCarInfoRecyclerViewAdapter);
        newCarInfoRecyclerViewAdapter.setHeaderView(LayoutInflater.from(context).inflate(R.layout.item_newcarinfor,rlNewcarinfor,false));
    }

}
