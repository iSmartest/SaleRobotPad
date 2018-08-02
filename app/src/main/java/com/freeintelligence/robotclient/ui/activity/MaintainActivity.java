package com.freeintelligence.robotclient.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
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
import com.freeintelligence.robotclient.ui.moudel.UpkeepBean;
import com.freeintelligence.robotclient.utils.DateUtils;
import com.freeintelligence.robotclient.utils.ToastUtils;
import com.freeintelligence.robotclient.view.MyDialog;
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

public class MaintainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.tv_project)
    TextView tvProject;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_maintainsub)
    TextView tvMaintainsub;
    @BindView(R.id.tv_number)
    EditText tvNumber;
    private TimePickerView pvTime;
    private OptionsPickerView pvCustomOptions;
    private List<String> mainlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_tain;
    }

    @Override
    protected void loadData() {
        mainlist=new ArrayList<>();
        mainlist.add("维修");
        mainlist.add("保养");
        initTimePicker();
        initCustomOptionPicker();
    }

    private void initCustomOptionPicker() {
        pvCustomOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx =mainlist.get(options1);

                tvProject.setText(tx);
            }
        })

                .isDialog(true)
                .build();

        pvCustomOptions.setPicker(mainlist);//添加数据
    }

    @Override
    protected void initView() {

    }
    @OnClick({R.id.tv_project, R.id.tv_number, R.id.tv_time, R.id.tv_maintainsub})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_project:
                pvCustomOptions.show();
                break;
            case R.id.tv_number:
                break;
            case R.id.tv_time:
               pvTime.show();
                break;
            case R.id.tv_maintainsub:
                starinternet();
                break;
        }
    }
    private void starinternet() {
       /* @param storeId 4s店id
        @param date 时间
        @param phoneNumber 手机号
        @param carNumber 车牌号
        @param type 类型 1维修 2保养*/
        Map<String,String> params =new HashMap<>();
        String s = tvNumber.getText().toString();
        params.put("storeId","1");
        params.put("data",s);
        params.put("phoneNumber","18332212560");
        params.put("carNumber","1");
        params.put("type","1");
        MyOkhttp.Okhttp(context, Url.MAINTAIN, "加载中...", params, new MyOkhttp.CallBack() {
            @Override
            public void onRequestComplete(String response, String result, String resultNote) {
                Gson gson = new Gson();
                UpkeepBean upkeepBean = gson.fromJson(response, UpkeepBean.class);
                if (result.equals("1")) {
                    ToastUtils.makeText(context, resultNote);
                    return;
                }
                ToastUtils.makeText(context,"预约成功");

            }
        });
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
        endDate.set(year_int+50, mouth_int - 1, day_int);
        //选中事件回调
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                tvTime.setText(DateUtils.dateToString(date,"yyy-MM-dd"));
            }
        })       .setType(new boolean[]{true, true, true, false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日","","","")//默认设置为年月日时分秒
                .isCenterLabel(false)
                .setDividerColor(Color.RED)
                .setTextColorCenter(Color.RED)//设置选中项的颜色
                .setTextColorOut(Color.BLUE)//设置没有被选中项的颜色
                .setContentSize(21)
                .setDate(selectedDate)
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(-10, 0,10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
                .setRangDate(startDate,endDate)
//                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .setDecorView(null)
                .build();

    }

}
