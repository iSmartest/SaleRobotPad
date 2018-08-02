package com.freeintelligence.robotclient.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.config.Url;
import com.freeintelligence.robotclient.okhttp.MyOkhttp;
import com.freeintelligence.robotclient.ui.adapter.IntegralAdapter;
import com.freeintelligence.robotclient.app.App;
import com.freeintelligence.robotclient.base.BaseFragment;
import com.freeintelligence.robotclient.config.MyString;
import com.freeintelligence.robotclient.ui.moudel.LoadBean;
import com.freeintelligence.robotclient.ui.moudel.VipBean;
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

public class IntegralFragment extends BaseFragment {

    @BindView(R.id.rl_integral)
    RecyclerView rlIntegral;
    Unbinder unbinder;
    @BindView(R.id.tv_interphone)
    TextView tvInterphone;
    @BindView(R.id.tv_intersor)
    TextView tvIntersor;
    private List<VipBean.DataBean.ScoreListBean> list = new ArrayList<>();
    private IntegralAdapter integralAdapter;
    private int id;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_integral;
    }

    @Override
    protected void initView(View view) {
        LoadBean.DataBean data = (LoadBean.DataBean) bundle.getSerializable(MyString.VIPDATA);
        id = data.getId();
        String name = data.getName();
        String phone = data.getPhone();
        int score = data.getScore();
        tvInterphone.setText(phone+"");
        tvIntersor.setText(score+"");
        rlIntegral.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        integralAdapter = new IntegralAdapter(context,list);
        rlIntegral.setAdapter(integralAdapter);
        integralAdapter.notifyDataSetChanged();
    }

    @Override
    protected void loadData() {

        starinternet();
    }

    private void starinternet() {
         /* vip
    @param storeId 4s店id
    @param customerId 用户id
    @param type 类型  1积分  2消费
    @param page 分页*/
        Map<String, String> params = new HashMap<>();
        params.put("storeId", "1");
        params.put("customerId", id + "");
        params.put("type", "1");
        params.put("page", "1");
        MyOkhttp.Okhttp(getActivity(), Url.VIP, "加载中...", params, new MyOkhttp.CallBack() {
            @Override
            public void onRequestComplete(String response, String result, String resultNote) {
                Gson gson = new Gson();
                VipBean vipBean = gson.fromJson(response,VipBean.class);
                if (result.equals("1")){
                    ToastUtils.makeText(getActivity(),resultNote);
                    return;
                }
                List<VipBean.DataBean.ScoreListBean> scoreList = vipBean.getData().getScoreList();
                if (scoreList != null && !scoreList.isEmpty() && scoreList.size()>0){
                    list.addAll(scoreList);
                    integralAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
