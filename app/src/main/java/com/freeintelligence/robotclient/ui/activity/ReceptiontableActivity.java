package com.freeintelligence.robotclient.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import com.freeintelligence.robotclient.R;;
import com.freeintelligence.robotclient.base.BaseActivity;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ReceptiontableActivity extends BaseActivity {

    @BindView(R.id.sv_toolbar)
    SearchView svToolbar;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.ll_allpsq)
    LinearLayout llpsqall;
    private String[] qunestion=new String[] {"Q1：您主要通过什么渠道了解车型信息？", "Q2：您会采用哪种途径购车？", "Q3：您购车时主要关注哪些方面？","Q4：您现在拥有的车是？", "Q5：若购车的话，您接受的价格区间？", "Q6：您哪类车感兴趣？","Q7：您购车主要用途？","Q8：请问您打算什么时间购车？","Q9：您是否尝试过线上预约试乘/试驾？"};
    private String[][] answer=new String[][]{{"4S 店咨询", "汽车门户网站", "汽车电商平台（如：京东、天猫等）", "车展", "汽车官方网站 ","身边的朋友、同事","其他"}, {"4S店购买", "网络订购", "托熟人购买", "汽车交易市场", "车展订购", " 其他"}, {"车型（SUV、轿车、MPV等）", "外观", "价格", "品牌", "整车性能（动力、安全、舒适、操控等）","配置","油耗","多功能配置（如倒车雷达、导航、定速巡航）","售后服务 ","内部空间"},{"小型", "中型", "中大型", "豪华型", "MPV","SUV","跑车","面包车","皮卡","无车"}, {"10万以下", "10～20万", "20～30万", "30～50万", "50～80万", "80万以上"}, {"国产", "合资", "进口", "德系", "日系","韩系","美系","欧系"},{"自驾代步","休闲旅游","商务用车","单位购买","换车","其他"},{"3个月以内","6个月","1年以内","1年以上"},{"尝试过","没尝试过"}};
    private List<List<String>> listList;
    private List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_receptiontable;

    }

    @Override
    protected void loadData() {

    }


    @Override
    protected void initView() {
          listList=new ArrayList<>();
          list=new ArrayList<>();
        for (int i = 0; i <59 ; i++) {
            list.add("0,");
        }
        for (int i = 0; i <qunestion.length ; i++) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            TextView textView = new TextView(this);
            textView.setTextColor(Color.BLACK);
            textView.setText(qunestion[i]);
            linearLayout.addView(textView);
           list=new ArrayList<>();
            for (int j = 0; j <answer[i].length; j++) {
                CheckBox checkBox = new CheckBox(this);
                boolean checked = checkBox.isChecked();
                checkBox.setTextColor(Color.BLACK);
                checkBox.setText(answer[i][j]);
             final int finalJ = j;
             final int finalI = i;
               checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                       if(checked){
                         list.set(finalJ,"1,");
                       }else {
                          list.set(finalJ,"0,");
                       }
                    }
                });
                linearLayout.addView(checkBox);
            }
              /*  listList.set(i,list);*/
            llpsqall.addView(linearLayout);
        }
         /*parse();*/
    }
    private void parse() {
        Log.i("",listList.toString());
    }
}
