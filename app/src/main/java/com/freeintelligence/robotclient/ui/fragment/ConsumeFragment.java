package com.freeintelligence.robotclient.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xrecyclerview.XRecyclerView;
import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.config.Url;
import com.freeintelligence.robotclient.okhttp.MyOkhttp;
import com.freeintelligence.robotclient.ui.adapter.ConsumeAdapter;
import com.freeintelligence.robotclient.app.App;
import com.freeintelligence.robotclient.base.BaseFragment;
import com.freeintelligence.robotclient.config.MyString;
import com.freeintelligence.robotclient.ui.moudel.LoadBean;
import com.freeintelligence.robotclient.ui.moudel.Vip2Bean;
import com.freeintelligence.robotclient.utils.ToastUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by za on 2018/6/20.
 */

public class ConsumeFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.rl_consume)
    XRecyclerView rlConsume;
    private ConsumeAdapter consumeAdapter;
    private List<Vip2Bean.DataBean.ConsumptionListBean> list;
    private int id;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_consume;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void loadData() {
        LoadBean.DataBean data = (LoadBean.DataBean) bundle.getSerializable(MyString.VIPDATA);
        id = data.getId();
        starinternet();
    }

    private void starinternet() {
        Map<String, String> params = new HashMap<>();
        params.put("storeId", "1");
        params.put("customerId", id+"");
        params.put("type", "2");
        params.put("page", "1");
        MyOkhttp.Okhttp(getActivity(), Url.VIP, "加载中...", params, new MyOkhttp.CallBack() {
            @Override
            public void onRequestComplete(String response, String result, String resultNote) {
                Gson gson = new Gson();
                Vip2Bean vip2Bean = gson.fromJson(response,Vip2Bean.class);
                if (result.equals("1")){
                    ToastUtils.makeText(getActivity(),resultNote);
                    return;
                }
                List<Vip2Bean.DataBean.ConsumptionListBean> consumptionList = vip2Bean.getData().getConsumptionList();
                if (consumptionList != null && !consumptionList.isEmpty() && consumptionList.size() > 0){
                    list.addAll(consumptionList);
                    consumeAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        list = new ArrayList<>();
        rlConsume.setLayoutManager(new LinearLayoutManager(App.activity, LinearLayoutManager.VERTICAL, false));
        consumeAdapter = new ConsumeAdapter(list);
        rlConsume.setAdapter(consumeAdapter);
        rlConsume.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                starinternet();
            }

            @Override
            public void onLoadMore() {
                starinternet();
            }
        });
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
