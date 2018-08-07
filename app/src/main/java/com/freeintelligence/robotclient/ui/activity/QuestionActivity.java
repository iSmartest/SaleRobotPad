package com.freeintelligence.robotclient.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.xrecyclerview.XRecyclerView;
import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.config.Url;
import com.freeintelligence.robotclient.okhttp.MyOkhttp;
import com.freeintelligence.robotclient.ui.adapter.QuestionAdapter;
import com.freeintelligence.robotclient.app.App;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.ui.moudel.QuestionBean;
import com.freeintelligence.robotclient.utils.AppManager;
import com.freeintelligence.robotclient.utils.SPUtil;
import com.freeintelligence.robotclient.view.MyDialog;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class QuestionActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.title_Back)
    ImageView mBack;
    XRecyclerView rvQuestion;
    EditText etRename;
    RadioGroup rgResex;
    EditText etReAge;
    EditText etRephone;
    RadioGroup rgQuestionNine,rgSex;
    TextView tvQusecommit;
    private View headView,footView;
    private List<QuestionBean> mList = new ArrayList<>();
    private String mSex;
    private String mNineYes = "0",mNineNo = "0";
    private String[] question = new String[]{"Q1：您主要通过什么渠道了解车型信息？", "Q2：您会采用哪种途径购车？", "Q3：您购车时主要关注哪些方面？", "Q4：您现在拥有的车是？", "Q5：若购车的话，您接受的价格区间？", "Q6：您哪类车感兴趣？", "Q7：您购车主要用途？", "Q8：请问您打算什么时间购车？"};
    private String[][] answer = new String[][]{{"4S 店咨询", "汽车门户网站", "汽车电商平台（如：京东、天猫等）", "车展", "汽车官方网站 ", "身边的朋友、同事", "其他"}, {"4S店购买", "网络订购", "托熟人购买", "汽车交易市场", "车展订购", " 其他"}, {"车型（SUV、轿车、MPV等）", "外观", "价格", "品牌", "整车性能（动力、安全、舒适、操控等）", "配置", "油耗", "多功能配置（如倒车雷达、导航、定速巡航）", "售后服务 ", "内部空间"}, {"小型", "中型", "中大型", "豪华型", "MPV", "SUV", "跑车", "面包车", "皮卡", "无车"}, {"10万以下", "10～20万", "20～30万", "30～50万", "50～80万", "80万以上"}, {"国产", "合资", "进口", "德系", "日系", "韩系", "美系", "欧系"}, {"自驾代步", "休闲旅游", "商务用车", "单位购买", "换车", "其他"}, {"3个月以内", "6个月", "1年以内", "1年以上"}};
    private QuestionAdapter questionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_question;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        rvQuestion = findViewById(R.id.rv_question);
        headView = LayoutInflater.from(QuestionActivity.this).inflate(R.layout.question_head,null);
        footView = LayoutInflater.from(QuestionActivity.this).inflate(R.layout.question_foot,null);
        etRename = footView.findViewById(R.id.et_rename);
        etRename = footView.findViewById(R.id.et_rename);
        rgResex = footView.findViewById(R.id.rg_resex);
        etReAge = footView.findViewById(R.id.et_re_age);
        etRephone = footView.findViewById(R.id.et_rephone);
        tvQusecommit = footView.findViewById(R.id.tv_qusecommit);
        rgQuestionNine = footView.findViewById(R.id.rg_answer_nine);
        rgSex = footView.findViewById(R.id.rg_resex);
        tvQusecommit.setOnClickListener(this);
        rvQuestion.setLayoutManager(new LinearLayoutManager(this));
        rvQuestion.setPullRefreshEnabled(false);
        if (headView != null){
            rvQuestion.addHeaderView(headView);
        }
        if (footView != null){
            rvQuestion.addFootView(footView,true);
        }
        rvQuestion.noMoreLoading();

        rgQuestionNine.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_answer_yes){
                    mNineYes = "1";
                    mNineNo = "0";
                }else if (checkedId == R.id.rb_answer_no){
                    mNineYes = "0";
                    mNineNo = "1";
                }
            }
        });
        rgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_sex_man){
                    mSex = "0";
                }else if (checkedId == R.id.rb_sex_woman){
                    mSex = "1";
                }
            }
        });
        initDate();
    }

    private void initDate() {
        for (int i = 0; i < question.length; i++) {
            QuestionBean questionBean = new QuestionBean();
            questionBean.setQuestion(question[i]);
            List<QuestionBean.AnswerBean> answerList = new ArrayList<>();
            for (int j = 0; j < answer[i].length; j++) {
                QuestionBean.AnswerBean answerBean = new QuestionBean.AnswerBean();
                answerBean.setAnswer(answer[i][j]);
                answerBean.setPositon(0);
                answerList.add(answerBean);
            }
            questionBean.setAnswerList(answerList);
            mList.add(questionBean);
        }
        Log.i("TAG", "initDate: " + mList.size());
        questionAdapter = new QuestionAdapter(this, mList);
        rvQuestion.setAdapter(questionAdapter);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_qusecommit:
                List<List<String>> answerList =new ArrayList<>();
                for (int i = 0; i <mList.size() ; i++) {
                    List<String> mAnswer = new ArrayList<>();
                    for (int j = 0; j < mList.get(i).getAnswerList().size(); j++) {
                        mAnswer.add(mList.get(i).getAnswerList().get(j).getPositon()+"");
                    }
                    answerList.add(mAnswer);
                }
                List<String> mAnswer = new ArrayList<>();
                mAnswer.add(mNineYes);
                mAnswer.add(mNineNo);
                answerList.add(mAnswer);
                Log.i("TAg", "onClick: "+ answerList.toString());
                String name = etRename.getText().toString().trim();
                String phone = etRephone.getText().toString().trim();
                String age = etReAge.getText().toString().trim();
                if (name.isEmpty()){
                    showToast(App.activity, "请输入您的姓名！");
                    return;
                }
                if (mSex.isEmpty()){
                    showToast(App.activity, "请选择性别！");
                    return;
                }
                if (age.isEmpty()){
                    showToast(App.activity, "请输入您的年龄！");
                    return;
                }
                if (phone.isEmpty()){
                    showToast(App.activity, "请输入您的手机号！");
                    return;
                }

                submitAnswer(answerList,name,age,phone);
                break;
        }

    }

    private void submitAnswer(List<List<String>> answerList, String name, String age, String phone) {
        Map<String,String>map = new HashMap<>();
        map.put("question1", String.valueOf(answerList.get(0)));
        map.put("question2", String.valueOf(answerList.get(1)));
        map.put("question3", String.valueOf(answerList.get(2)));
        map.put("question4", String.valueOf(answerList.get(3)));
        map.put("question5", String.valueOf(answerList.get(4)));
        map.put("question6", String.valueOf(answerList.get(5)));
        map.put("question7", String.valueOf(answerList.get(6)));
        map.put("question8", String.valueOf(answerList.get(7)));
        map.put("question9", String.valueOf(answerList.get(8)));
        map.put("storeId", SPUtil.getString(context,"storeId"));
        map.put("name",name);
        map.put("age",age);
        map.put("sex",mSex);
        map.put("phone",phone);
        map.put("index","1");
        MyOkhttp.Okhttp(context, Url.EXAMTION, "提交中...", map, new MyOkhttp.CallBack() {
            @Override
            public void onRequestComplete(String response, String result, String resultNote) {
                if (result.equals("1")){
                    showToast(QuestionActivity.this,resultNote);
                }else {
                    showToast(QuestionActivity.this,resultNote);
                    finish();
                }
            }
        });
    }
    @OnClick({R.id.title_Back})
    public void onViewClicked() {
        AppManager.finishActivity();
    }
}
