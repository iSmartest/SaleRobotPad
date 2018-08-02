package com.freeintelligence.robotclient.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.config.MyString;
import com.freeintelligence.robotclient.config.Url;
import com.freeintelligence.robotclient.okhttp.MyOkhttp;
import com.freeintelligence.robotclient.ui.moudel.LoadcodeBean;
import com.freeintelligence.robotclient.utils.RegexpUtils;
import com.freeintelligence.robotclient.utils.ToastUtils;
import com.freeintelligence.robotclient.view.MyDialog;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoadActivity extends BaseActivity{

    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_vcode)
    EditText etVcode;
    @BindView(R.id.ll_load)
    LinearLayout llLoad;
    @BindView(R.id.tv_vcode)
    TextView tvVcode;
    @BindView(R.id.tv_load)
    TextView tvLoad;
    private String sessionId="";
    private TimeCount time;
    private int loadtag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_load;
    }

    @Override
    protected void loadData() {

    }
    @Override
    protected void initView() {
        int intExtra = getIntent().getIntExtra(MyString.LOADTAG, 0);
        loadtag=intExtra;
        time = new TimeCount(60000, 1000);
    }
    @OnClick({R.id.tv_vcode, R.id.tv_load})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.tv_vcode:
                String phone = etPhone.getText().toString().trim();
                boolean mobileNO = RegexpUtils.isMobileNO(phone);
                if(mobileNO==true){
                   time.start();
                   starinternet(phone);
                }else {
                    showToast(this,"请输入正确的电话号码");
                }
                break;
            case R.id.tv_load:
                String phone1 = etPhone.getText().toString().trim();
                boolean mobileNO1 = RegexpUtils.isMobileNO(phone1);
                String vcode = etVcode.getText().toString().trim();
                if(mobileNO1==true){
                   if(!TextUtils.isEmpty(vcode)){
                        starinternets(phone1,vcode);
                      /* switch (loadtag){
                           case 1:
                               Intent vipintent = new Intent(this, VipActivity.class);
                               startActivity(vipintent);
                               finish();
                               break;
                           case 2:
                               Intent inspectintent = new Intent(this, InspectActivity.class);
                               startActivity(inspectintent);
                               finish();
                               break;
                       }*/
                   }
                   else {
                       Toast.makeText(this,"请输入验证码",Toast.LENGTH_SHORT);
                   }

                }else {
                    showToast(this,"请输入正确的电话号码");
                }
                break;
        }
    }

    private void starinternets(String phone1, String vcode) {

        // login  vip登录    phoneNumber code   sessionId  storeId
        Map<String,String> map =new HashMap<>();
        map.put("phoneNumber",phone1);
        map.put("code",vcode);
        map.put("sessionId",sessionId);
        map.put("storeId","1");
        MyOkhttp.Okhttp(context, Url.LOADLOGIN, "正在登录...", map, new MyOkhttp.CallBack() {
            @Override
            public void onRequestComplete(String response, String result, String resultNote) {
                Gson gson = new Gson();
                LoadcodeBean loadcodeBean = gson.fromJson(response, LoadcodeBean.class);
                if (result.equals("1")) {
                    ToastUtils.makeText(context, resultNote);
                    return;
                }
                ToastUtils.makeText(context, resultNote);
            }
        });

    }

    private void starinternet(String phone) {
       // sendCode   参数  mobile   storeId
        Map<String,String> map =new HashMap<>();
        map.put("mobile",phone);
        map.put("storeId","1");
        MyOkhttp.Okhttp(context, Url.LOADVCODE, "正在获取...", map, new MyOkhttp.CallBack() {
            @Override
            public void onRequestComplete(String response, String result, String resultNote) {
                Gson gson = new Gson();
                LoadcodeBean loadcodeBean = gson.fromJson(response, LoadcodeBean.class);
                if (result.equals("1")) {
                    sessionId = loadcodeBean.getData().getSessionId();
                    ToastUtils.makeText(context, resultNote);
                    return;
                }
                sessionId = loadcodeBean.getData().getSessionId();
            }
        });
    }
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tvVcode.setBackgroundColor(Color.parseColor("#B6B6D8"));
            tvVcode.setClickable(false);
            tvVcode.setText("("+millisUntilFinished / 1000 +") 秒后可重新发送");
        }

        @Override
        public void onFinish() {
            tvVcode.setText("重新获取验证码");
            tvVcode.setClickable(true);
            tvVcode.setBackgroundColor(Color.parseColor("#FF167CD5"));

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
