package com.freeintelligence.robotclient.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.example.xrecyclerview.XRecyclerView;
import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.config.Url;
import com.freeintelligence.robotclient.okhttp.MyOkhttp;
import com.freeintelligence.robotclient.ui.adapter.BriefAdapter;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.ui.moudel.BriefBean;
import com.freeintelligence.robotclient.utils.AppManager;
import com.freeintelligence.robotclient.utils.SPUtil;
import com.freeintelligence.robotclient.utils.ToastUtils;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BriefActivity extends BaseActivity {
    @BindView(R.id.title_Back)
    ImageView mBack;
    @BindView(R.id.rl_brief)
    XRecyclerView xRecyclerView;
    private int nowPage = 1;
    private List<BriefBean.DataBean.CarVideoBean> mList = new ArrayList<>();
    private BriefAdapter briefAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_brief;
    }

    @Override
    protected void loadData() {
        Map<String, String> params = new HashMap<>();
        params.put("storeId", SPUtil.getString(context,"storeId"));
        params.put("page", nowPage + "");
        MyOkhttp.Okhttp(context, Url.BRIEF, "加载中...", params, new MyOkhttp.CallBack() {
            @Override
            public void onRequestComplete(String response, String result, String resultNote) {
                Gson gson = new Gson();
                BriefBean briefBean = gson.fromJson(response, BriefBean.class);
                if (result.equals("1")) {
                    ToastUtils.makeText(context, resultNote);
                    return;
                }

                List<BriefBean.DataBean.CarVideoBean> carVideoBeans = briefBean.getData().getCarVideo();
                if (carVideoBeans != null && !carVideoBeans.isEmpty() && carVideoBeans.size() > 0) {
                    mList.addAll(carVideoBeans);
                    briefAdapter.notifyDataSetChanged();
                    xRecyclerView.refreshComplete();
                }
                if (carVideoBeans.size() < 10) {
                    xRecyclerView.noMoreLoading();
                }
            }
        });
    }

    @Override
    protected void initView() {
        xRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        briefAdapter = new BriefAdapter(context, mList);
        xRecyclerView.setAdapter(briefAdapter);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                nowPage = 1;
                mList.clear();
                briefAdapter.notifyDataSetChanged();
                loadData();
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                nowPage++;
                loadData();
                xRecyclerView.loadMoreComplete();
            }
        });
    }

    @OnClick({R.id.title_Back})
    public void onViewClicked(View view) {
        AppManager.finishActivity();
    }
}
