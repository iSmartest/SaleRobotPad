package com.freeintelligence.robotclient.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.config.Url;
import com.freeintelligence.robotclient.okhttp.MyOkhttp;
import com.freeintelligence.robotclient.ui.adapter.RepairAdapter;
import com.freeintelligence.robotclient.base.BaseFragment;
import com.freeintelligence.robotclient.config.MyString;
import com.freeintelligence.robotclient.ui.moudel.Inspect2Bean;
import com.freeintelligence.robotclient.ui.moudel.LoadBean;
import com.freeintelligence.robotclient.utils.SPUtil;
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

public class RepairFragment extends BaseFragment {
    @BindView(R.id.rl_maintian)
    RecyclerView rlMaintian;
    Unbinder unbinder;
    @BindView(R.id.tv_namemaintain)
    TextView tvNamemaintain;
    List<Inspect2Bean.DataBean.RepairListBean> mList = new ArrayList<>();
    private RepairAdapter repairAdapter;
    private int id=1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_maintain;
    }

    @Override
    protected void initView(View view) {
        tvNamemaintain.setVisibility(View.GONE);
        LoadBean.DataBean data = (LoadBean.DataBean)bundle.getSerializable(MyString.INSPECTDATA);
        id = data.getId();
        rlMaintian.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        repairAdapter = new RepairAdapter(context, mList,id,2);
        rlMaintian.setAdapter(repairAdapter);
    }

    @Override
    protected void loadData() {
        Map<String, String> params = new HashMap<>();
        params.put("storeId",  SPUtil.getString(context,"storeId"));
        params.put("customerId", id+"");
        params.put("type", "2");
        MyOkhttp.Okhttp(context, Url.INSPRCT, "加载中...", params, new MyOkhttp.CallBack() {
            @Override
            public void onRequestComplete(String response, String result, String resultNote) {
                Log.i("TAG", "onRequestComplete: " + response);
                Gson gson = new Gson();
                Inspect2Bean inspect2Bean = gson.fromJson(response,Inspect2Bean.class);
                if (result.equals("1")){
                    tvNamemaintain.setVisibility(View.GONE);
                    ToastUtils.makeText(getActivity(),resultNote);
                    return;
                }
                List<Inspect2Bean.DataBean.RepairListBean> maintainlist = inspect2Bean.getData().getRepairList();
                if(maintainlist != null && !maintainlist.isEmpty() && maintainlist.size() > 0){
                    mList.addAll(maintainlist);
                    tvNamemaintain.setVisibility(View.VISIBLE);
                    tvNamemaintain.setText(maintainlist.get(0).getName().concat("维修记录"));
                    repairAdapter.notifyDataSetChanged();
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
