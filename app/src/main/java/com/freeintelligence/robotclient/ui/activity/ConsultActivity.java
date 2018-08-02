package com.freeintelligence.robotclient.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.config.Url;
import com.freeintelligence.robotclient.okhttp.MyOkhttp;
import com.freeintelligence.robotclient.ui.adapter.ConSellingAdapter;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.config.MyString;
import com.freeintelligence.robotclient.ui.moudel.CarpyteBean;
import com.freeintelligence.robotclient.ui.moudel.ConsultBean;
import com.freeintelligence.robotclient.utils.PriceUtils;
import com.freeintelligence.robotclient.utils.ToastUtils;
import com.freeintelligence.robotclient.view.FlowGroupView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.freeintelligence.robotclient.config.Url.HTTP;

public class ConsultActivity extends BaseActivity {

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
    @BindView(R.id.title_Back)
    ImageView mBack;
    @BindView(R.id.iv_consultqus)
    ImageView ivConsultqus;
    @BindView(R.id.rv_consultselling)
    RecyclerView rvConsultselling;
    @BindView(R.id.ll_consult)
    LinearLayout llConsult;
    private List<String> carlist;
    private ArrayList<ConsultBean.DataBean.AnswersBean> datas;
    private OptionsPickerView pvCustomOptions;
    private int id = 1;
    private List<CarpyteBean.DataBean.CarTypeListBean> carTypeList;
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
    protected void loadData() {
        Map<String, String> params = new HashMap<>();
        params.put("carId", id + "");
        params.put("storeId","1");
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
                tvConprice1.setText(getprice);
                tvConcarsturcture.setText(data.getStructure());
                tvCongearbox.setText(data.getGearbox());
                tvToolbartitle.setText(data.getName());
                tvDondisplacement.setText(data.getMotor());
                tvConprice2.setText(PriceUtils.getprice(data.getPrice()));
                List<ConsultBean.DataBean.BrightPointBean> dataBrightPoint = data.getBrightPoint();
                if (dataBrightPoint != null && !dataBrightPoint.isEmpty() && dataBrightPoint.size() > 0){
                    brightPoint.clear();
                    brightPoint.addAll(dataBrightPoint);
                    conSellingAdapter.notifyDataSetChanged();
                }

                List<ConsultBean.DataBean.AnswersBean> answers = data.getAnswers();
                if (answers != null && !answers.isEmpty() && answers.size() > 0){
                    datas.clear();
                    datas.addAll(answers);
                }
                setTwoFlowLayout();
            }
        });
    }



    @Override
    protected void initView() {
        datas = new ArrayList<>();
        brightPoint=new ArrayList<>();
        tvToolbartitle.setVisibility(View.VISIBLE);
        tvToolbartitleright.setVisibility(View.VISIBLE);
        int intExtra = getIntent().getIntExtra(MyString.CONSULT, 2);
        final Dialog dialog = new Dialog(this, R.style.Transparent);
        View view = LayoutInflater.from(this).inflate(R.layout.intodialog, null, false);
        final ImageView imageView = view.findViewById(R.id.iv_condialog);
        carlist = new ArrayList<>();
        rvConsultselling.setLayoutManager(new LinearLayoutManager(this));
        conSellingAdapter = new ConSellingAdapter(brightPoint);
        rvConsultselling.setAdapter(conSellingAdapter);
        starinternets();
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        switch (intExtra){
            case 1:
                dialog.show();
                mBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ConsultActivity.this, MainActivity.class);
                        startActivity(intent);
                        ConsultActivity.this.finish();
                    }
                });
                break;
            case 2:
                int intExtra1 = getIntent().getIntExtra(MyString.INTENTHOTCAR, 1);
                id=intExtra1;
                break;
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });
    }

    private void starinternets() {
        Map<String, String> params = new HashMap<>();
        params.put("storeId","1");
        MyOkhttp.Okhttp(context, Url.CARTAPY, "加载中...", params, new MyOkhttp.CallBack() {
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
                if(carTypeList !=null&& carTypeList.size()>0){
                    for (int i = 0; i < carTypeList.size(); i++) {
                        CarpyteBean.DataBean.CarTypeListBean carTypeListBean = carTypeList.get(i);
                        String name = carTypeListBean.getName();
                        carlist.add(name);
                    }
                }
                if(carlist==null||carlist.size()==0){
                    carlist.add("暂无车辆");
                }
                initCustomOptionPicker();
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
        child.setBackgroundResource(R.drawable.shape_consult);
        child.setText(s);
        child.setTextColor(Color.WHITE);
        child.setTextSize(20);
        child.setPadding(25, 15, 25, 15);
        initEvents(child, i);
        flvConsult.addView(child);
    }

    private void initEvents(TextView child, final int i) {
      child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConsultBean.DataBean.AnswersBean answersBean = datas.get(i);
                if(answersBean==null){
                }
                else {
                int type = answersBean.getType();
                switch (type){
                    case 1:
                        Intent intent = new Intent(ConsultActivity.this,SlideshowActivity.class);
                        intent.putExtra("consult", answersBean);
                      //  intent.putExtra("consultint",1);
                        startActivity(intent);
                        break;
                    case 2:
                        Intent intent2 = new Intent(ConsultActivity.this,SlideshowActivity.class);
                        intent2.putExtra("consult", answersBean);
                      //  intent2.putExtra("consultint",2);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent1 = new Intent(ConsultActivity.this,VideoActivity.class);
                        intent1.putExtra(MyString.VIDEO,HTTP+answersBean.getVideoAddress());
                        startActivity(intent1);
                        break;
                }
              }
            }
        });
    }


    @OnClick({R.id.tv_toolbartitle, R.id.tv_toolbartitleright, R.id.iv_consultqus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_toolbartitle:
                break;
            case R.id.tv_toolbartitleright:
                pvCustomOptions.show();
                break;
            case R.id.iv_consultqus:
                Intent intent = new Intent(this, QuestionActivity.class);
                startActivity(intent);
                break;
        }
    }
    private void initCustomOptionPicker() {
        pvCustomOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                if(carTypeList!=null&&carTypeList.size()>0){
                    id=carTypeList.get(options1).getId();
                    loadData();
                }
            }
        })
                .isDialog(true)
                .build();
        pvCustomOptions.setPicker(carlist);//添加数据
    }
}
