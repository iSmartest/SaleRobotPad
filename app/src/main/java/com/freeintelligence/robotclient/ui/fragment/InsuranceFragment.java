package com.freeintelligence.robotclient.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.config.Url;
import com.freeintelligence.robotclient.okhttp.MyOkhttp;
import com.freeintelligence.robotclient.ui.adapter.InsuranceAdapter;
import com.freeintelligence.robotclient.app.App;
import com.freeintelligence.robotclient.base.BaseFragment;
import com.freeintelligence.robotclient.config.MyString;
import com.freeintelligence.robotclient.ui.moudel.InsuranceBean;
import com.freeintelligence.robotclient.ui.moudel.LoadBean;
import com.freeintelligence.robotclient.utils.ToastUtils;
import com.freeintelligence.robotclient.view.MyDialog;
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

public class InsuranceFragment extends BaseFragment{
    @BindView(R.id.rv_insurance)
    RecyclerView rvInsurance;
    Unbinder unbinder;
    private InsuranceAdapter insuranceAdapter;
    private int id=1;
    List<InsuranceBean.DataBean.RepairListBean> myList;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_insurance;
    }


    @Override
    protected void initView(View view) {
        LoadBean.DataBean data = (LoadBean.DataBean)bundle.getSerializable(MyString.INSPECTDATA);
        id = data.getId();
        myList=new ArrayList<>();
        rvInsurance.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        insuranceAdapter = new InsuranceAdapter(context,myList);
        rvInsurance.setAdapter(insuranceAdapter);
        starintnet();
    }

    @Override
    protected void loadData() {

    }
    private void starintnet() {
        Map<String,String> params = new HashMap<>();
        params.put("storeId","1");
        params.put("customerId",id+"");
        params.put("type","3");
        MyOkhttp.Okhttp(context, Url.INSPRCT, "加载中...", params, new MyOkhttp.CallBack() {
            @Override
            public void onRequestComplete(String response, String result, String resultNote) {
                Gson gson = new Gson();
                InsuranceBean insuranceBean = gson.fromJson(response,InsuranceBean.class);
                if (result.equals("1")){
                    ToastUtils.makeText(getActivity(),resultNote);
                    return;
                }
                List<InsuranceBean.DataBean.RepairListBean> repairList = insuranceBean.getData().getRepairList();
                if(repairList != null && !repairList.isEmpty() && repairList.size() > 0){
                    myList.addAll(repairList);
                    insuranceAdapter.notifyDataSetChanged();
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
