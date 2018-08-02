package com.freeintelligence.robotclient.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.widget.RelativeLayout;

import com.example.xrecyclerview.XRecyclerView;
import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.config.Url;
import com.freeintelligence.robotclient.okhttp.MyOkhttp;
import com.freeintelligence.robotclient.ui.adapter.ShowAdapter;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.ui.moudel.ShowBean;
import com.freeintelligence.robotclient.utils.ToastUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.rl_show)
    XRecyclerView rlShow;
    private  int nowPage = 1;
    private List<ShowBean.DataBean.CarProductBean> showlist = new ArrayList<>();
    private ShowAdapter showAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show;
    }

    @Override
    protected void initView() {
        rlShow.setLayoutManager(new GridLayoutManager(this, 4));
        showAdapter = new ShowAdapter(context,showlist);
        rlShow.setAdapter(showAdapter);
        rlShow.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                nowPage = 1;
                showlist.clear();
                showAdapter.notifyDataSetChanged();
                loadData();
                rlShow.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                nowPage ++ ;
                loadData();
                rlShow.loadMoreComplete();
            }
        });

    }

    @Override
    protected void loadData() {
        Map<String,String> map = new HashMap<>();
        map.put("storeId", "1");
        map.put("page", nowPage+"");
        MyOkhttp.Okhttp(context, Url.SHOW, "加载中", map, new MyOkhttp.CallBack() {
            @Override
            public void onRequestComplete(String response, String result, String resultNote) {
                Gson gson = new Gson();
                ShowBean showBean = gson.fromJson(response,ShowBean.class);
                if (result.equals("1")){
                    ToastUtils.makeText(context,resultNote);
                    return;
                }
                List<ShowBean.DataBean.CarProductBean> carProduct = showBean.getData().getCarProduct();
                if (carProduct != null && !showlist.isEmpty() && showlist.size() > 0){
                    showlist.addAll(carProduct);
                    showAdapter.notifyDataSetChanged();
                }
            }
        });
    }

}
