package com.freeintelligence.robotclient.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.config.Url;
import com.freeintelligence.robotclient.dialog.SelectCarStyleDialog;
import com.freeintelligence.robotclient.okhttp.MyOkhttp;
import com.freeintelligence.robotclient.ui.adapter.ConSellingAdapter;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.config.MyString;
import com.freeintelligence.robotclient.ui.moudel.CarpyteBean;
import com.freeintelligence.robotclient.ui.moudel.ConsultBean;
import com.freeintelligence.robotclient.utils.AppManager;
import com.freeintelligence.robotclient.utils.GlideUtils;
import com.freeintelligence.robotclient.utils.PriceUtils;
import com.freeintelligence.robotclient.utils.SPUtil;
import com.freeintelligence.robotclient.utils.ToastUtils;
import com.freeintelligence.robotclient.view.FlowGroupView;
import com.google.gson.Gson;
import com.robot.performlib.action.AIUIAction;
import com.robot.performlib.action.SpeakAction;
import com.robot.performlib.action.WakeupAction;
import com.robot.performlib.callback.PerformFaceDetectCallBack;
import com.robot.performlib.performs.CognizePerform;
import com.yunjichina.callback.FaceDetectCallback;
import com.yunjichina.constant.FaceDetectConstant;
import com.yunjichina.facedetect.demo.FaceDetectAction;
import com.yunjichina.vision.facedetect.FaceRect;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


public class ConsultActivity extends BaseActivity {
    @BindView(R.id.title_Back)
    ImageView mBack;
    @BindView(R.id.iv_consult)
    ImageView ivConsult;
    @BindView(R.id.tv_conprice1)
    TextView tvConprice1;
    @BindView(R.id.tv_conprice2)
    TextView tvConprice2;
    @BindView(R.id.tv_dondisplacement)
    TextView tvDondisplacement;
    @BindView(R.id.tv_congearbox)
    TextView tvCongearbox;
    @BindView(R.id.tv_concarsturcture)
    TextView tvConcarsturcture;
    @BindView(R.id.rl_hotcardeails)
    RelativeLayout rlHotcardeails;
    @BindView(R.id.flv_consult)
    FlowGroupView flvConsult;
    @BindView(R.id.tv_toolbartitle)
    TextView tvToolbartitle;
    @BindView(R.id.tv_toolbartitleright)
    TextView tvToolbartitleright;
    @BindView(R.id.iv_consultqus)
    ImageView ivConsultqus;
    @BindView(R.id.rv_consultselling)
    RecyclerView rvConsultselling;
    @BindView(R.id.ll_consult)
    LinearLayout llConsult;
    private ArrayList<ConsultBean.DataBean.AnswersBean> datas;
    private String id;
    private int intExtra = 2;
    private List<CarpyteBean.DataBean.CarTypeListBean> mList = new ArrayList<>();
    private ConSellingAdapter conSellingAdapter;
    private List<ConsultBean.DataBean.BrightPointBean> brightPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_consult;

    }

    @Override
    protected void initView() {
        datas = new ArrayList<>();
        brightPoint = new ArrayList<>();
        tvToolbartitle.setVisibility(View.VISIBLE);
        tvToolbartitleright.setVisibility(View.VISIBLE);
        intExtra = getIntent().getIntExtra(MyString.CONSULT, 2);
        id = getIntent().getStringExtra(MyString.INTENTHOTCAR);
        final Dialog dialog = new Dialog(this, R.style.Transparent);
        View view = LayoutInflater.from(this).inflate(R.layout.intodialog, null, false);
        final ImageView imageView = view.findViewById(R.id.iv_condialog);
        rvConsultselling.setLayoutManager(new LinearLayoutManager(this));
        conSellingAdapter = new ConSellingAdapter(brightPoint);
        rvConsultselling.setAdapter(conSellingAdapter);
        starinternets();
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        switch (intExtra) {
            case 1:
                dialog.show();
                break;
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
//                yunji();
            }
        });
    }


    private void yunji() {
//        AIUIAction.changeVoicer(context, AIUIAction.Voicer.mengmeng, 50, 50);
//        CognizePerform.create(callback).start(context);
        FaceDetectAction.init(context).setTime(10000).setCallback(call);
    }

    private FaceDetectCallback call = new FaceDetectCallback() {
        @Override
        public void findFaceHandler(List<FaceRect> featureList, int imageWidth, int imageHeight, List<String> nameList) {
            super.findFaceHandler(featureList, imageWidth, imageHeight, nameList);
            if (featureList != null && featureList.size() > 0) {
                FaceRect faceRect = featureList.get(0);
                if (faceRect.confidence > 0.75) {
                    SpeakAction.getInstance().speak(context, faceRect.name + "您好");
                } else {
                    if (TextUtils.equals(FaceDetectConstant.Gender.Male, faceRect.gender)) {
                        SpeakAction.getInstance().speak(context, "先生您好");
                        Log.i("TAG", "FindFacesHandler: "  + featureList.get(0).image);
                    } else if (TextUtils.equals(FaceDetectConstant.Gender.Female, faceRect.gender)) {
                        SpeakAction.getInstance().speak(context, "女士您好");
                        Log.i("TAG", "FindFacesHandler: "  + featureList.get(0).image);
                    } else {
                        SpeakAction.getInstance().speak(context, "您好");
                    }
                }
            }
        }

        @Override
        public void nofindFaceHandler() {
            super.nofindFaceHandler();
        }
    };
    private PerformFaceDetectCallBack callback = new PerformFaceDetectCallBack() {
        @Override
        public void FindFacesHandler(final List<FaceRect> featureList) {
            super.FindFacesHandler(featureList);

        }

        @Override
        public void FindFacesNotMiddleHandler(final Context context, final float rad) {
            super.FindFacesNotMiddleHandler(context, rad);
            // 识别到人脸但人脸不在屏幕正中间，此处会自动调整机器人角度，使机器人正对着人。
            // 建议在此位置发送唤醒 0 波束麦克风，实现人脸识别唤醒功能
            WakeupAction.AIUIWakeUp(context, 0);
        }

        @Override
        public void NotFindFacesHandler() {
            super.NotFindFacesHandler();
            ConsultActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, "没有识别到人脸", Toast.LENGTH_SHORT).show();
                }
            });
        }
    };


    @Override
    protected void loadData() {
        Map<String, String> params = new HashMap<>();
        params.put("carId", id + "");
        params.put("storeId", SPUtil.getString(context, "storeId"));
        MyOkhttp.Okhttp(context, Url.SCREENCONSULT, "加载中...", params, new MyOkhttp.CallBack() {
            @Override
            public void onRequestComplete(String response, String result, String resultNote) {
                Gson gson = new Gson();
                ConsultBean consultBean = gson.fromJson(response, ConsultBean.class);
                if (result.equals("1")) {
                    ToastUtils.makeText(context, resultNote);
                    return;
                }
                ConsultBean.DataBean data = consultBean.getData();
                String getprice = PriceUtils.getprice(data.getGuiPrice());
                GlideUtils.imageLoader(context, consultBean.getData().getImage(), ivConsult);
                tvConprice1.setText(getprice);
                tvConcarsturcture.setText(data.getStructure());
                tvCongearbox.setText(data.getGearbox());
                tvToolbartitle.setText(data.getName());
                tvDondisplacement.setText(data.getMotor());
                tvConprice2.setText(PriceUtils.getprice(data.getPrice()));
                List<ConsultBean.DataBean.BrightPointBean> dataBrightPoint = data.getBrightPoint();
                if (dataBrightPoint != null && !dataBrightPoint.isEmpty() && dataBrightPoint.size() > 0) {
                    brightPoint.clear();
                    brightPoint.addAll(dataBrightPoint);
                    conSellingAdapter.notifyDataSetChanged();
                }

                List<ConsultBean.DataBean.AnswersBean> answers = data.getAnswers();
                if (answers != null && !answers.isEmpty() && answers.size() > 0) {
                    datas.clear();
                    datas.addAll(answers);
                }
                setTwoFlowLayout();
            }
        });
    }


    private void starinternets() {
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

    private void setTwoFlowLayout() {
        if (datas == null || datas.size() == 0) {
            return;
        }
        flvConsult.removeAllViews();
        for (int i = 0; i < datas.size(); i++) {
            addTextView(datas.get(i).getQuestion(), i);
        }
    }

    private void addTextView(String s, int i) {
        TextView child = new TextView(this);
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
        params.setMargins(20, 20, 20, 20);
        child.setLayoutParams(params);
        child.setBackgroundResource(R.drawable.hot_new_question);
        child.setText(s);
        child.setTextColor(Color.WHITE);
        child.setTextSize(16);
        child.setGravity(Gravity.CENTER);
        child.setPadding(25, 15, 25, 15);
        initEvents(child, i);
        AutoUtils.autoSize(child);
        flvConsult.addView(child);
    }

    private void initEvents(TextView child, final int i) {
        child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConsultBean.DataBean.AnswersBean answersBean = datas.get(i);
                if (answersBean == null) {
                } else {
                    int type = answersBean.getType();
                    switch (type) {
                        case 1:
                            Intent intent = new Intent(ConsultActivity.this, SlideshowActivity.class);
                            intent.putExtra("consult", answersBean);
                            startActivity(intent);
                            break;
                        case 2:
                            Intent intent2 = new Intent(ConsultActivity.this, SlideshowActivity.class);
                            intent2.putExtra("consult", answersBean);
                            startActivity(intent2);
                            break;
                        case 3:
                            Intent intent1 = new Intent(ConsultActivity.this, VideoActivity.class);
                            intent1.putExtra(MyString.VIDEO, answersBean.getVideoAddress());
                            startActivity(intent1);
                            break;
                    }
                }
            }
        });
    }


    @OnClick({R.id.tv_toolbartitle, R.id.tv_toolbartitleright, R.id.iv_consultqus, R.id.title_Back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_toolbartitle:

                break;
            case R.id.tv_toolbartitleright:
                SelectCarStyleDialog selectCarStyleDialog = new SelectCarStyleDialog(context, mList, new SelectCarStyleDialog.OnSureBtnClickListener() {
                    @Override
                    public void sure(int position) {
                        id = mList.get(position).getId() + "";
                        loadData();
                    }
                });
                selectCarStyleDialog.show();
                break;
            case R.id.iv_consultqus:
                Intent intent = new Intent(this, QuestionActivity.class);
                startActivity(intent);
                break;
            case R.id.title_Back:
                if (intExtra == 1) {
                    Intent intent1 = new Intent(context, MainActivity.class);
                    startActivity(intent1);
                    AppManager.finishActivity();
                } else {
                    AppManager.finishActivity();
                }
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (intExtra == 1) {
                Intent intent1 = new Intent(context, MainActivity.class);
                startActivity(intent1);
                AppManager.finishActivity();
            } else {
                AppManager.finishActivity();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
