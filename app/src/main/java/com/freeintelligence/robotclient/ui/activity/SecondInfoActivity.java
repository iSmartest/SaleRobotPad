package com.freeintelligence.robotclient.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
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
import com.freeintelligence.robotclient.utils.AppManager;
import com.freeintelligence.robotclient.utils.DateUtils;
import com.freeintelligence.robotclient.utils.GlideUtils;
import com.freeintelligence.robotclient.utils.PriceUtils;
import com.freeintelligence.robotclient.utils.SPUtil;
import com.freeintelligence.robotclient.utils.ToastUtils;
import com.freeintelligence.robotclient.view.MyRecyclerView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondInfoActivity extends BaseActivity {
    @BindView(R.id.title_Back)
    ImageView mBack;
    @BindView(R.id.iv_seconddeatial)
    ImageView carPic;
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
    private List<List<String>> listList = new ArrayList<>();
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
        SecondBean.DataBean.CarBean carBean = (SecondBean.DataBean.CarBean) getIntent().getSerializableExtra(MyString.INTENTINSPECTID);
        id = carBean.getId();
        List<String> brightPoint = carBean.getBrightPoint();
        list = new ArrayList<>();
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
        map.put("storeId",  SPUtil.getString(context,"storeId"));
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
                GlideUtils.imageLoader(context,common.getPic(),carPic);
                List<String> pics = common.getPics();
                list.clear();
                list.addAll(pics);
                picSecondAdapter.notifyDataSetChanged();
                if (data.getBasic() != null && !data.getBasic().isEmpty() && data.getBasic().size() > 0){
                    listList.add(data.getBasic());
                }
                if (data.getMontor() != null && !data.getMontor().isEmpty() && data.getMontor().size() > 0){
                    listList.add(data.getMontor());
                }
                if (data.getUnderPan() != null && !data.getUnderPan().isEmpty() && data.getUnderPan().size() > 0){
                    listList.add(data.getUnderPan());
                }
                if (data.getSecure() != null && !data.getSecure().isEmpty() && data.getSecure().size() > 0){
                    listList.add(data.getSecure());
                }
                if (data.getOuter() != null && !data.getOuter().isEmpty() && data.getOuter().size() > 0){
                    listList.add(data.getOuter());
                }
                if (data.getInner() != null && !data.getInner().isEmpty() && data.getInner().size() > 0){
                    listList.add(data.getInner());
                }
                tableAdapter = new TableAdapter(context, listList);
                rvSecond.setAdapter(tableAdapter);
            }
        });
    }

    @Override
    protected void initView() {

    }
    @OnClick({R.id.title_Back})
    public void onViewClicked() {
        AppManager.finishActivity();
    }
}
