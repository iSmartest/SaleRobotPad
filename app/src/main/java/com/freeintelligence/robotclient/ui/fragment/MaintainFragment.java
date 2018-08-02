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
import com.freeintelligence.robotclient.ui.adapter.MaintainAdapter;
import com.freeintelligence.robotclient.base.BaseFragment;
import com.freeintelligence.robotclient.config.MyString;
import com.freeintelligence.robotclient.ui.moudel.InspectBean;
import com.freeintelligence.robotclient.ui.moudel.LoadBean;
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

public class MaintainFragment extends BaseFragment {
    @BindView(R.id.rl_maintian)
    RecyclerView rlMaintian;
    Unbinder unbinder;
    @BindView(R.id.tv_namemaintain)
    TextView tvNamemaintain;
    private List<InspectBean.DataBean.MaintainListBean> mList = new ArrayList<>();
    private MaintainAdapter maintainAdapter;
    private int id = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_maintain;
    }

    @Override
    protected void initView(View view) {
        tvNamemaintain.setVisibility(View.GONE);
    }

    @Override
    protected void loadData() {
        LoadBean.DataBean data = (LoadBean.DataBean)bundle.getSerializable(MyString.INSPECTDATA);
        id = data.getId();
        rlMaintian.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        maintainAdapter = new MaintainAdapter(context, mList,id,1);
        rlMaintian.setAdapter(maintainAdapter);
        starintnet();
    }

    private void starintnet() {
        Map<String, String> params = new HashMap<>();
        params.put("storeId", "1");
        params.put("customerId", id+"");
        params.put("type", "1");
        MyOkhttp.Okhttp(context, Url.INSPRCT, "加载中...", params, new MyOkhttp.CallBack() {
            @Override
            public void onRequestComplete(String response, String result, String resultNote) {
                Gson gson = new Gson();
                InspectBean inspectBean = gson.fromJson(response,InspectBean.class);
                if (result.equals("1")){
                    ToastUtils.makeText(getActivity(),resultNote);
                    tvNamemaintain.setVisibility(View.GONE);
                    return;
                }
                List<InspectBean.DataBean.MaintainListBean> maintainlist = inspectBean.getData().getMaintainList();
                if(maintainlist!=null && !maintainlist.isEmpty() && maintainlist.size() > 0){
                    mList.addAll(maintainlist);
                    tvNamemaintain.setVisibility(View.VISIBLE);
                    tvNamemaintain.setText(maintainlist.get(0).getName().concat("保养记录"));
                    maintainAdapter.notifyDataSetChanged();
                }else {
                    tvNamemaintain.setVisibility(View.GONE);
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
