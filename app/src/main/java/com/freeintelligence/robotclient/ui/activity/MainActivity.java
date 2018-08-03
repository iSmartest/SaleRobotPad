package com.freeintelligence.robotclient.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.config.MyString;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity{
    @BindView(R.id.iv_hot)
    ImageView ivHot;
    @BindView(R.id.iv_second)
    ImageView ivSecond;
    @BindView(R.id.iv_service)
    ImageView ivService;
    @BindView(R.id.iv_vip)
    ImageView ivVip;
    @BindView(R.id.iv_inspect)
    ImageView ivInspect;
    @BindView(R.id.iv_subscribe)
    ImageView ivSubscribe;
    @BindView(R.id.iv_set)
    ImageView ivSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermissions();
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {

    }

    //8个控件的点击事件跳转页面
    @OnClick({R.id.iv_hot, R.id.iv_second, R.id.iv_service, R.id.iv_vip, R.id.iv_inspect, R.id.iv_subscribe, R.id.iv_set,R.id.tv_linshi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_hot://跳转到热车销售页面
                Intent hotintent = new Intent(this, HotcarActivity.class);
                startActivity(hotintent);
                break;
            case R.id.iv_second:
                //跳转到二手车页面
                Intent secondintent = new Intent(this, SecondActivity.class);
                startActivity(secondintent);
                break;
            case R.id.iv_service:
                //跳转到客服接待页面
                Intent serintent = new Intent(this, ServiceActivity.class);
                startActivity(serintent);
                break;
            case R.id.iv_vip:
                //跳转到VIP页面
                Intent vipintent = new Intent(this, LoadActivity.class);
                vipintent.putExtra(MyString.LOADTAG,1);
                startActivity(vipintent);
                break;
            case R.id.iv_inspect:
                //跳转到车间服务页面
                Intent inspectintent = new Intent(this, LoadActivity.class);
                inspectintent.putExtra(MyString.LOADTAG,2);
                startActivity(inspectintent);
                break;
            case R.id.iv_subscribe:
                //跳转到预约页面
                Intent subscribeintent = new Intent(this, SubscribeActivity.class);
                startActivity(subscribeintent);
                break;
            case R.id.iv_set:
                //跳转到咨询销售页面
               Intent setintent = new Intent(this, RobotconsltActivity.class);
                startActivity(setintent);
                break;
            case R.id.tv_linshi:
                Intent zixunintent = new Intent(this, ORCActivity888.class);
                startActivity(zixunintent);
                break;
        }
    }
    private void requestPermissions(){
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int permission = ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if(permission!= PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,new String[]
                            {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.LOCATION_HARDWARE,Manifest.permission.READ_PHONE_STATE,
                                    Manifest.permission.WRITE_SETTINGS,Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_CONTACTS},0x0010);
                }
                if(permission != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,new String[] {
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},0x0010);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
