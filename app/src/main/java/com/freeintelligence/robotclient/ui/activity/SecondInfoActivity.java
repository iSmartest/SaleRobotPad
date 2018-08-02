package com.freeintelligence.robotclient.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.config.Url;
import com.freeintelligence.robotclient.okhttp.MyOkhttp;
import com.freeintelligence.robotclient.ui.adapter.PicSecondAdapter;
import com.freeintelligence.robotclient.ui.adapter.RedsellingAdapter;
import com.freeintelligence.robotclient.ui.adapter.TableAdapter;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.config.MyString;
import com.freeintelligence.robotclient.ui.moudel.SecondBean;
import com.freeintelligence.robotclient.ui.moudel.SeconddeailsBean;
import com.freeintelligence.robotclient.utils.DateUtils;
import com.freeintelligence.robotclient.utils.PriceUtils;
import com.freeintelligence.robotclient.utils.ToastUtils;
import com.freeintelligence.robotclient.view.MyRecyclerView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondInfoActivity extends BaseActivity {
    @BindView(R.id.rv_second)
    GridView rvSecond;
    @BindView(R.id.tv_sdname)
    TextView tvSdname;
    @BindView(R.id.tv_sd11)
    TextView tvSd11;
    @BindView(R.id.tv_sd12)
    TextView tvSd12;
    @BindView(R.id.tv_sd21)
    TextView tvSd21;
    @BindView(R.id.tv_sd22)
    TextView tvSd22;
    @BindView(R.id.tv_sd23)
    TextView tvSd23;
    @BindView(R.id.tv_sd24)
    TextView tvSd24;
    @BindView(R.id.tv_sd41)
    TextView tvSd41;
    @BindView(R.id.tv_sd42)
    TextView tvSd42;
    @BindView(R.id.tv_sd43)
    TextView tvSd43;
    @BindView(R.id.rv_picseconddea)
    MyRecyclerView rvPicseconddea;
    @BindView(R.id.rv_redselling)
    RecyclerView rvRedselling;
    @BindView(R.id.tv_sd31)
    TextView tvSd31;
    @BindView(R.id.tv_sd32)
    TextView tvSd32;
    private SeconddeailsBean.DataBean dataBean;
    private int id = 0;
    private TableAdapter tableAdapter;
    private List<String> list;
    private PicSecondAdapter picSecondAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_seconddeails;

    }

    @Override
    protected void loadData() {
        //int intExtra = getIntent().getIntExtra(MyString.INTENTSECONDCAR, 0);
        // id = intExtra;
        SecondBean.DataBean.CarBean carBean = (SecondBean.DataBean.CarBean) getIntent().getSerializableExtra(MyString.INTENTINSPECTID);
        id = carBean.getId();
        List<String> brightPoint = carBean.getBrightPoint();
        list = new ArrayList<>();
        dataBean = new SeconddeailsBean.DataBean();
        rvPicseconddea.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        picSecondAdapter = new PicSecondAdapter(context,list);
        rvPicseconddea.setAdapter(picSecondAdapter);
        rvRedselling.setLayoutManager(new LinearLayoutManager(context));
        RedsellingAdapter redsellingAdapter = new RedsellingAdapter(context,brightPoint);
        rvRedselling.setAdapter(redsellingAdapter);
        starintnet();
    }

    private void starintnet() {
        Map<String, String> map = new HashMap<>();
        map.put("storeId", "1");
        map.put("carId", id + "");
        MyOkhttp.Okhttp(context, Url.SECONDDEAILS, "加载中...", map, new MyOkhttp.CallBack() {
            @Override
            public void onRequestComplete(String response, String result, String resultNote) {
                Gson gson = new Gson();
                SeconddeailsBean seconddeailsBean = gson.fromJson(response,SeconddeailsBean.class);
                if (result.equals("1")){
                    ToastUtils.makeText(context,resultNote);
                    return;
                }
                SeconddeailsBean.DataBean data = seconddeailsBean.getData();
                SeconddeailsBean.DataBean.CommonBean common = data.getCommon();
                if (common == null) {
                    return;
                }
                tvSdname.setText(common.getCarName());
                if (common.getIsmaintain() == 0) {
                    tvSd11.setVisibility(View.VISIBLE);
                } else {
                    tvSd11.setVisibility(View.GONE);
                }
                if (common.getIstransfer() == 0) {
                    tvSd12.setVisibility(View.VISIBLE);
                } else {
                    tvSd12.setVisibility(View.GONE);
                }
                long date = common.getDate();
                String time = DateUtils.getDateToString(date, "yyyy-MM-dd");
                tvSd21.setText(time);
                tvSd22.setText(common.getMileage() + "万");
                tvSd23.setText(common.getCity());
                tvSd24.setText(common.getOutPut() + "T");
                tvSd31.setText(PriceUtils.getprice(common.getPrice()));
                tvSd32.setText(PriceUtils.getprice(common.getGuideprice()));
                tvSd41.setText(common.getIsAccident());
                tvSd42.setText(common.getIsFire());
                tvSd43.setText(common.getIsWater());
                List<String> pics = common.getPics();
                list.clear();
                list.addAll(pics);
                picSecondAdapter.notifyDataSetChanged();
                dataBean = data;
                tableAdapter = new TableAdapter(context, dataBean);
                rvSecond.setAdapter(tableAdapter);
            }
        });
    }

    @Override
    protected void initView() {

    }
}
