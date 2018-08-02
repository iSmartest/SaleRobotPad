package com.freeintelligence.robotclient.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.freeintelligence.robotclient.R;

import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.config.Url;
import com.freeintelligence.robotclient.okhttp.MyOkhttp;
import com.freeintelligence.robotclient.ui.moudel.CarpyteBean;
import com.freeintelligence.robotclient.utils.DateUtils;
import com.freeintelligence.robotclient.utils.RegexpUtils;
import com.freeintelligence.robotclient.utils.ToastUtils;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestdriveActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.tv_choosecar)
    TextView tvChoosecar;
    @BindView(R.id.tv_choosetime)
    TextView tvChoosetime;
    @BindView(R.id.tv_testsub)
    TextView tvTestsub;
    @BindView(R.id.tv_testdrivephone)
    EditText tvTestdrivephone;

    private TimePickerView pvTime;
    private OptionsPickerView pvCustomOptions;
    private List<String> carlist = new ArrayList<>();
    private int id;
    private List<CarpyteBean.DataBean.CarTypeListBean> carTypeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_testdrive;
    }

    @Override
    protected void loadData() {
        Map<String, String> maps = new HashMap<>();
        maps.put("storeId", "1");
        MyOkhttp.Okhttp(context, Url.CARTAPY, "获取车型...", maps, new MyOkhttp.CallBack() {
            @Override
            public void onRequestComplete(String response, String result, String resultNote) {
                Gson gson = new Gson();
                CarpyteBean carpyteBean = gson.fromJson(response, CarpyteBean.class);
                if (result.equals("1")) {
                    ToastUtils.makeText(context, resultNote);
                    return;
                }
                CarpyteBean.DataBean data = carpyteBean.getData();
                carTypeList = data.getCarTypeList();
                carlist.clear();
                if (carTypeList != null && carTypeList.size() > 0) {
                    for (int i = 0; i < carTypeList.size(); i++) {
                        CarpyteBean.DataBean.CarTypeListBean carTypeListBean = carTypeList.get(i);
                        String name = carTypeListBean.getName();
                        carlist.add(name);
                    }
                } else {
                    carlist.add("暂无车辆");
                }
                initCustomOptionPicker();
            }
        });
    }


    private void starinternet(String phone, String time) {
        Map<String, String> params = new HashMap<>();
        params.put("storeId", "1");
        params.put("date", time);
        params.put("phoneNumber", phone);
        params.put("carId", id + "");
        MyOkhttp.Okhttp(context, Url.TESTDRIVE, "提交中...", params, new MyOkhttp.CallBack() {
            @Override
            public void onRequestComplete(String response, String result, String resultNote) {
                if (result.equals("1")) {
                    ToastUtils.makeText(context, resultNote);
                    return;
                }
                ToastUtils.makeText(context, resultNote);
            }
        });
    }

    @Override
    protected void initView() {
        initTimePicker();
    }

    @OnClick({R.id.tv_choosecar, R.id.tv_choosetime, R.id.tv_testsub})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_choosecar:
                pvCustomOptions.show();
                break;
            case R.id.tv_choosetime:
                pvTime.show();
                break;
            case R.id.tv_testsub:
                String phone = tvTestdrivephone.getText().toString().trim();
                String time = tvChoosetime.getText().toString();
                boolean mobileNO = RegexpUtils.isMobileNO(phone);
                if (mobileNO == false) {
                    showToast(this, "请输入正确的电话号码");
                    return;
                }
                if (time.isEmpty()) {
                    ToastUtils.makeText(context, "请输入试乘时间");
                    return;
                }
                starinternet(phone, time);
                break;

        }
    }

    private void initCustomOptionPicker() {
        pvCustomOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = carlist.get(options1);
                id = carTypeList.get(options1).getId();
                tvChoosecar.setText(tx);
            }
        })
                .isDialog(true)
                .build();

        pvCustomOptions.setPicker(carlist);//添加数据
    }

    private void initTimePicker() {
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        SimpleDateFormat formatter_year = new SimpleDateFormat("yyyy ");
        String year_str = formatter_year.format(curDate);
        int year_int = (int) Double.parseDouble(year_str);
        SimpleDateFormat formatter_mouth = new SimpleDateFormat("MM ");
        String mouth_str = formatter_mouth.format(curDate);
        int mouth_int = (int) Double.parseDouble(mouth_str);
        SimpleDateFormat formatter_day = new SimpleDateFormat("dd ");
        String day_str = formatter_day.format(curDate);
        int day_int = (int) Double.parseDouble(day_str);
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year_int, mouth_int - 1, day_int);
        Calendar endDate = Calendar.getInstance();
        endDate.set(year_int + 50, mouth_int - 1, day_int);
        //选中事件回调
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                tvChoosetime.setText(DateUtils.dateToString(date, "yyy-MM-dd"));
            }
        }).setType(new boolean[]{true, true, true, false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false)
                .setDividerColor(Color.RED)
                .setTextColorCenter(Color.RED)//设置选中项的颜色
                .setTextColorOut(Color.BLUE)//设置没有被选中项的颜色
                .setContentSize(21)
                .setDate(selectedDate)
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(-10, 0, 10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
                .setRangDate(startDate, endDate)
                .setDecorView(null)
                .build();

    }
}
