package com.freeintelligence.robotclient.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xrecyclerview.XRecyclerView;
import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.config.Url;
import com.freeintelligence.robotclient.okhttp.MyOkhttp;
import com.freeintelligence.robotclient.ui.adapter.SecondAdapter;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.ui.moudel.SecondBean;
import com.freeintelligence.robotclient.utils.AppManager;
import com.freeintelligence.robotclient.utils.SPUtil;
import com.freeintelligence.robotclient.utils.ToastUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends BaseActivity {
    @BindView(R.id.title_Back)
    ImageView mBack;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.rl_second)
    XRecyclerView rlSecond;
    @BindView(R.id.sv_toolbar)
    EditText svToolbar;
    @BindView(R.id.tv_search)
    ImageView tvSearch;
    @BindView(R.id.rb_tbl1se)
    RadioButton rbTbl1se;
    @BindView(R.id.rb_tbl2se)
    RadioButton rbTbl2se;
    @BindView(R.id.rb_tbl3se)
    RadioButton rbTbl3se;
    @BindView(R.id.rb_tbl4se)
    RadioButton rbTbl4se;
    @BindView(R.id.rg_hotonese)
    RadioGroup rgHotonese;
    @BindView(R.id.rb_tblt1se)
    RadioButton rbTblt1se;
    @BindView(R.id.rb_tblt2se)
    RadioButton rbTblt2se;
    @BindView(R.id.rb_tblt3se)
    RadioButton rbTblt3se;
    @BindView(R.id.rb_tblt4se)
    RadioButton rbTblt4se;
    @BindView(R.id.rb_tblt5se)
    RadioButton rbTblt5se;
    @BindView(R.id.rb_tblt6se)
    RadioButton rbTblt6se;
    @BindView(R.id.rb_tblt7se)
    RadioButton rbTblt7se;
    @BindView(R.id.rg_hottwose)
    RadioGroup rgHottwose;
    @BindView(R.id.tablegridlayout)
    LinearLayout tablegridlayout;
    @BindView(R.id.tv_toolbartitle)
    TextView tvToolbartitle;
    @BindView(R.id.tv_toolbartitleright)
    TextView tvToolbartitleright;
    @BindView(R.id.ll_second_car)
    LinearLayout mSecondCar;
    @BindView(R.id.tv_jiaochevariety)
    TextView tvJiaochevariety;
    @BindView(R.id.tv_suvvariety)
    TextView tvSuvvariety;
    @BindView(R.id.tv_mpvvariety)
    TextView tvMpvvariety;
    @BindView(R.id.tv_paochevariety)
    TextView tvPaochevariety;
    private List<SecondBean.DataBean.CarBean> mList = new ArrayList<>();
    private int cartype = 0;
    private int pricetype = 0;
    private final String CARTYPE = "carType";
    private final String PRICETYPE = "priceType";
    private final String TYPE = "type";
    private final String STOREID = "storeId";
    private final String PAGE = "page";
    private final String ROWS = "rows";
    private final String CARNAME = "carName";
    private SecondAdapter secondAdapter;
    private String search;
    private List<SecondBean.DataBean.CarBean> jiaoChe = new ArrayList<>();
    private List<SecondBean.DataBean.CarBean> suv = new ArrayList<>();
    private List<SecondBean.DataBean.CarBean> mpv = new ArrayList<>();
    private List<SecondBean.DataBean.CarBean> paoChe = new ArrayList<>();
    private int chage=1;
    private int nowPage = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_second;
    }
    @Override
    protected void loadData() {
        search = svToolbar.getText().toString().trim();
        Map<String, String> map = new HashMap<>();
        map.put(CARTYPE, cartype + "");
        map.put(PRICETYPE, pricetype + "");
        map.put(TYPE, "2");
        map.put(STOREID,  SPUtil.getString(context,"storeId"));
        map.put(PAGE, nowPage+"");
        map.put(ROWS, "10");
        map.put(CARNAME, search);
        MyOkhttp.Okhttp(context, Url.HOTCAR, "加载中...", map, new MyOkhttp.CallBack() {
            @Override
            public void onRequestComplete(String response, String result, String resultNote) {
                Gson gson = new Gson();
                SecondBean secondBean = gson.fromJson(response, SecondBean.class);
                if (result.equals("1")) {
                    ToastUtils.makeText(context, resultNote);
                    tvJiaochevariety.setVisibility(View.GONE);
                    tvSuvvariety.setVisibility(View.GONE);
                    tvMpvvariety.setVisibility(View.GONE);
                    tvPaochevariety.setVisibility(View.GONE);
                    return;
                }
                SecondBean.DataBean data = secondBean.getData();
                if(data!=null){
                    jiaoChe = data.getJiaoChe();
                    if(jiaoChe !=null&& jiaoChe.size()>0){
                        tvJiaochevariety.setVisibility(View.VISIBLE);
                        tvJiaochevariety.setText("轿车（共"+ jiaoChe.size()+"款）");
                    }else {
                        tvJiaochevariety.setVisibility(View.GONE);
                    }
                    suv =data.getSuv();
                    if(suv !=null&& suv.size()>0){
                        tvSuvvariety.setVisibility(View.VISIBLE);
                        tvSuvvariety.setText("SUV（共"+ suv.size()+"款）");
                    }else {
                        tvSuvvariety.setVisibility(View.GONE);
                    }
                    mpv = data.getMpv();
                    if(mpv !=null&& mpv.size()>0){
                        tvMpvvariety.setVisibility(View.VISIBLE);
                        tvMpvvariety.setText("MPV（共"+ mpv.size()+"款）");
                    }
                    else {
                        tvMpvvariety.setVisibility(View.GONE);
                    }
                    paoChe = data.getPaoChe();
                    if(paoChe !=null&& paoChe.size()>0){
                        tvPaochevariety.setVisibility(View.VISIBLE);
                        tvPaochevariety.setText("跑车（共"+ paoChe.size() +"款）");
                    }else {
                        tvPaochevariety.setVisibility(View.GONE);
                    }
                }
                mList.clear();

                if(jiaoChe!=null&&jiaoChe.size()>0){
                    mList.addAll(jiaoChe);
                    chage=1;
                    tvJiaochevariety.setTextColor(Color.BLUE);
                }else if(suv!=null&&suv.size()>0){
                    mList.addAll(suv);
                    chage=2;
                    tvSuvvariety.setTextColor(Color.BLUE);
                }else if(mpv!=null&&mpv.size()>0){
                    mList.addAll(mpv);
                    chage=3;
                    tvMpvvariety.setTextColor(Color.BLUE);
                    tvJiaochevariety.setTextColor(Color.BLUE);
                }else if(paoChe!=null&&paoChe.size()>0){
                    mList.addAll(paoChe);
                    chage=4;
                    tvPaochevariety.setTextColor(Color.BLUE);
                }
                switch (chage){
                    case 1:
                        tvJiaochevariety.setTextColor(Color.BLUE);
                        tvSuvvariety.setTextColor(Color.GRAY);
                        tvMpvvariety.setTextColor(Color.GRAY);
                        tvPaochevariety.setTextColor(Color.GRAY);
                        break;
                    case 2:
                        tvJiaochevariety.setTextColor(Color.GRAY);
                        tvSuvvariety.setTextColor(Color.BLUE);
                        tvMpvvariety.setTextColor(Color.GRAY);
                        tvPaochevariety.setTextColor(Color.GRAY);
                        break;
                    case 3:
                        tvJiaochevariety.setTextColor(Color.GRAY);
                        tvSuvvariety.setTextColor(Color.GRAY);
                        tvMpvvariety.setTextColor(Color.BLUE);
                        tvPaochevariety.setTextColor(Color.GRAY);
                        break;
                    case 4:
                        tvJiaochevariety.setTextColor(Color.GRAY);
                        tvSuvvariety.setTextColor(Color.GRAY);
                        tvMpvvariety.setTextColor(Color.GRAY);
                        tvPaochevariety.setTextColor(Color.BLUE);
                        break;
                }
                secondAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    protected void initView() {
        llSearch.setVisibility(View.VISIBLE);
        rlSecond.setLayoutManager(new GridLayoutManager(context, 2));
        secondAdapter = new SecondAdapter(context, mList);
        rlSecond.setAdapter(secondAdapter);
        loadData();
        rlSecond.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                nowPage = 1;
                mList.clear();
                secondAdapter.notifyDataSetChanged();
                loadData();
                rlSecond.refreshComplete();
            }
            @Override
            public void onLoadMore() {
                rlSecond.loadMoreComplete();
            }
        });
        rgHotonese.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_tbl1se:
                        cartype = 0;
                        break;
                    case R.id.rb_tbl2se:
                        cartype = 1;
                        break;
                    case R.id.rb_tbl3se:
                        cartype = 2;
                        break;
                    case R.id.rb_tbl4se:
                        cartype = 3;
                        break;
                }
                mList.clear();
                secondAdapter.notifyDataSetChanged();
                loadData();
            }

        });
        rgHottwose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_tblt1se:
                        pricetype = 0;
                        break;
                    case R.id.rb_tblt2se:
                        pricetype = 1;
                        break;
                    case R.id.rb_tblt3se:
                        pricetype = 2;
                        break;
                    case R.id.rb_tblt4se:
                        pricetype = 3;
                        break;
                    case R.id.rb_tblt5se:
                        pricetype = 4;
                        break;
                    case R.id.rb_tblt6se:
                        pricetype = 5;
                        break;
                    case R.id.rb_tblt7se:
                        pricetype = 6;
                        break;
                }
                mList.clear();
                secondAdapter.notifyDataSetChanged();
                loadData();
            }
        });
    }

    @OnClick({R.id.title_Back,R.id.tv_search,R.id.tv_jiaochevariety, R.id.tv_suvvariety, R.id.tv_mpvvariety, R.id.tv_paochevariety})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.tv_search:
                String trim = svToolbar.getText().toString().trim();
                if(!TextUtils.isEmpty(trim)){
                    search=trim;
                    loadData();
                }
                break;
            case R.id.tv_jiaochevariety:
               if(chage!=1){
                   chage=1;
                  tvJiaochevariety.setTextColor(Color.BLUE);
                  tvSuvvariety.setTextColor(Color.GRAY);
                  tvMpvvariety.setTextColor(Color.GRAY);
                  tvPaochevariety.setTextColor(Color.GRAY);
                  mList.clear();
                   mList.addAll(jiaoChe);
                  secondAdapter.notifyDataSetChanged();
               }
                break;
            case R.id.tv_suvvariety:
                if(chage!=2){
                    chage=2;
                    tvJiaochevariety.setTextColor(Color.GRAY);
                    tvSuvvariety.setTextColor(Color.BLUE);
                    tvMpvvariety.setTextColor(Color.GRAY);
                    tvPaochevariety.setTextColor(Color.GRAY);
                    mList.clear();
                    mList.addAll(suv);
                    secondAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.tv_mpvvariety:
                if(chage!=3){
                    chage=3;
                    tvJiaochevariety.setTextColor(Color.GRAY);
                    tvSuvvariety.setTextColor(Color.GRAY);
                    tvMpvvariety.setTextColor(Color.BLUE);
                    tvPaochevariety.setTextColor(Color.GRAY);
                    mList.clear();
                    mList.addAll(mpv);
                    secondAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.tv_paochevariety:
                if(chage!=4){
                    chage=4;
                    tvJiaochevariety.setTextColor(Color.GRAY);
                    tvSuvvariety.setTextColor(Color.GRAY);
                    tvMpvvariety.setTextColor(Color.GRAY);
                    tvPaochevariety.setTextColor(Color.BLUE);
                    mList.clear();
                    mList.addAll(paoChe);
                    secondAdapter.notifyDataSetChanged();
                }
                break;

            case R.id.title_Back:
                AppManager.finishActivity();
                break;
        }
    }
}
