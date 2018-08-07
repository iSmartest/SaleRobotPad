package com.freeintelligence.robotclient.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.config.Url;
import com.freeintelligence.robotclient.dialog.SelectCarStyleDialog;
import com.freeintelligence.robotclient.okhttp.MyOkhttp;
import com.freeintelligence.robotclient.ui.moudel.CarpyteBean;
import com.freeintelligence.robotclient.utils.AppManager;
import com.freeintelligence.robotclient.utils.SPUtil;
import com.freeintelligence.robotclient.utils.ToastUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/6.
 * Description:
 */
public class HotCarSelectActivity extends BaseActivity {
    @BindView(R.id.title_Back)
    ImageView mBack;
    @BindView(R.id.tv_select_hot_car)
    TextView tvSelectCar;
    @BindView(R.id.tv_complete_setting)
    TextView tvComplete;
    private List<CarpyteBean.DataBean.CarTypeListBean> mList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hot_cae_select;
    }

    @Override
    protected void loadData() {
        Map<String, String> params = new HashMap<>();
        params.put("storeId", SPUtil.getString(context, "storeId"));
        MyOkhttp.Okhttp(context, Url.CARTAPY, "加载中...", params, new MyOkhttp.CallBack() {
            @Override
            public void onRequestComplete(String response, String result, String resultNote) {
                Gson gson = new Gson();
                CarpyteBean carpyteBean = gson.fromJson(response, CarpyteBean.class);
                if (result.equals("1")) {
                    ToastUtils.makeText(context, resultNote);
                    return;
                }
                List<CarpyteBean.DataBean.CarTypeListBean> carTypeList = carpyteBean.getData().getCarTypeList();
                if (carTypeList != null && !carTypeList.isEmpty() && carTypeList.size() > 0) {
                    mList.addAll(carTypeList);
                }

            }
        });
    }


    @Override
    protected void initView() {

    }

    @OnClick({R.id.title_Back,R.id.tv_complete_setting, R.id.tv_select_hot_car})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_select_hot_car:
                SelectCarStyleDialog selectCarStyleDialog = new SelectCarStyleDialog(context, mList, new SelectCarStyleDialog.OnSureBtnClickListener() {
                    @Override
                    public void sure(int position) {
                        SPUtil.putString(context, "carId", mList.get(position).getId() + "");
                        tvSelectCar.setText(mList.get(position).getName());
                        SPUtil.putString(context,"address",mList.get(position).getAddress());
                    }
                });
                selectCarStyleDialog.setCanceledOnTouchOutside(false);
                selectCarStyleDialog.show();
                break;
            case R.id.tv_complete_setting:
                if (tvSelectCar.getText().toString().trim().isEmpty()){
                    ToastUtils.makeText(context,"请选择车型");
                    return;
                }
                Intent intent = new Intent(HotCarSelectActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.title_Back:
                AppManager.finishActivity();
                break;
        }
    }

}
