package com.freeintelligence.robotclient.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.xrecyclerview.XRecyclerView;
import com.freeintelligence.robotclient.R;;
import com.freeintelligence.robotclient.config.Url;
import com.freeintelligence.robotclient.okhttp.MyOkhttp;
import com.freeintelligence.robotclient.ui.adapter.HotCarAdapter;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.ui.moudel.HotcarBean;
import com.freeintelligence.robotclient.utils.AppManager;
import com.freeintelligence.robotclient.utils.SPUtil;
import com.freeintelligence.robotclient.utils.ToastUtils;
import com.freeintelligence.robotclient.view.MyDialog;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HotcarActivity extends BaseActivity {
    @BindView(R.id.title_Back)
    ImageView mBack;
    @BindView(R.id.rb_tbl1)
    RadioButton rbTbl1;
    @BindView(R.id.rb_tbl2)
    RadioButton rbTbl2;
    @BindView(R.id.rb_tbl3)
    RadioButton rbTbl3;
    @BindView(R.id.rb_tbl4)
    RadioButton rbTbl4;
    @BindView(R.id.rg_hotone)
    RadioGroup rgHotone;
    @BindView(R.id.rb_tblt1)
    RadioButton rbTblt1;
    @BindView(R.id.rb_tblt2)
    RadioButton rbTblt2;
    @BindView(R.id.rb_tblt3)
    RadioButton rbTblt3;
    @BindView(R.id.rb_tblt4)
    RadioButton rbTblt4;
    @BindView(R.id.rg_hottwo)
    RadioGroup rgHottwo;
    @BindView(R.id.tablegridlayout)
    LinearLayout tablegridlayoutt;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.rl_hotcar)
    XRecyclerView rlHotcar;
    @BindView(R.id.sv_toolbar)
    EditText svToolbar;
    @BindView(tv_search)
    ImageView tvSearch;
    @BindView(R.id.tv_hjiaochevariety)
    TextView tvHjiaochevariety;
    @BindView(R.id.tv_hsuvvariety)
    TextView tvHsuvvariety;
    @BindView(R.id.tv_hmpvvariety)
    TextView tvHmpvvariety;
    @BindView(R.id.tv_hpaochevariety)
    TextView tvHpaochevariety;
    private List<HotcarBean.DataBean.CarBean> data = new ArrayList<>();
    private int cartype = 0;
    private int pricetype = 0;
    private final String CARTYPE = "carType";
    private final String PRICETYPE = "priceType";
    private final String TYPE = "type";
    private final String STOREID = "storeId";
    private final String PAGE = "page";
    private final String ROWS = "rows";
    private final String CARNAME = "carName";
    private HotCarAdapter hotcarAdapter;
    private int page = 1;
    private String search;
    private List<HotcarBean.DataBean.CarBean> sedan;
    private List<HotcarBean.DataBean.CarBean> suv;
    private List<HotcarBean.DataBean.CarBean> mpv;
    private List<HotcarBean.DataBean.CarBean> paoChe;
    private List<HotcarBean.DataBean.CarBean> mList = new ArrayList<>();
    private int chage = 1;
    private final int tv_search = R.id.tv_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hot_car;
    }


    @Override
    protected void initView() {
        llSearch.setVisibility(View.VISIBLE);
        rlHotcar.setLayoutManager(new GridLayoutManager(context, 2));
        hotcarAdapter = new HotCarAdapter(context, mList);
        rlHotcar.setAdapter(hotcarAdapter);
        rlHotcar.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mList.clear();
                hotcarAdapter.notifyDataSetChanged();
                loadData();
                rlHotcar.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                rlHotcar.loadMoreComplete();
            }
        });
        //两行条件选择器的动态监听
        rgHotone.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_tbl1:
                        cartype = 0;
                        break;
                    case R.id.rb_tbl2:
                        cartype = 1;
                        break;
                    case R.id.rb_tbl3:
                        cartype = 2;
                        break;
                    case R.id.rb_tbl4:
                        cartype = 3;
                        break;
                }
                mList.clear();
                hotcarAdapter.notifyDataSetChanged();
                loadData();
            }

        });
        rgHottwo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_tblt1:
                        pricetype = 0;
                        break;
                    case R.id.rb_tblt2:
                        pricetype = 7;
                        break;
                    case R.id.rb_tblt3:
                        pricetype = 8;
                        break;
                    case R.id.rb_tblt4:
                        pricetype = 9;
                        break;
                }
                mList.clear();
                hotcarAdapter.notifyDataSetChanged();
                loadData();
            }
        });
    }


    @Override
    protected void loadData() {
        //开启网络请求
        search = svToolbar.getText().toString().trim();
        Map<String, String> params = new HashMap<>();
        params.put(CARTYPE, cartype + "");
        params.put(PRICETYPE, pricetype + "");
        params.put(TYPE, "1");
        params.put(STOREID,  SPUtil.getString(context,"storeId"));
        params.put(PAGE, page + "");
        params.put(ROWS, "10");
        params.put(CARNAME, search);
        MyOkhttp.Okhttp(context, Url.HOTCAR, "加载中...", params, new MyOkhttp.CallBack() {
            @Override
            public void onRequestComplete(String response, String result, String resultNote) {
                Gson gson = new Gson();
                HotcarBean hotcarBean = gson.fromJson(response, HotcarBean.class);
                if (result.equals("1")) {
                    tvHjiaochevariety.setVisibility(View.GONE);
                    tvHsuvvariety.setVisibility(View.GONE);
                    tvHmpvvariety.setVisibility(View.GONE);
                    tvHpaochevariety.setVisibility(View.GONE);
                    ToastUtils.makeText(context, resultNote);
                    return;
                }
                HotcarBean.DataBean data = hotcarBean.getData();
                if (data != null) {
                    sedan = data.getJiaoChe();
                    if (sedan != null && !sedan.isEmpty() && sedan.size() > 0) {
                        tvHjiaochevariety.setVisibility(View.VISIBLE);
                        tvHjiaochevariety.setText("轿车（共" + sedan.size() + "款）");
                    } else {
                        tvHjiaochevariety.setVisibility(View.GONE);
                    }
                    suv = data.getSuv();
                    if (suv != null && !suv.isEmpty() && suv.size() > 0) {
                        tvHsuvvariety.setVisibility(View.VISIBLE);
                        tvHsuvvariety.setText("SUV（共" + suv.size() + "款）");
                    } else {
                        tvHsuvvariety.setVisibility(View.GONE);
                    }
                    mpv = data.getMpv();
                    if (mpv != null && !mpv.isEmpty() && mpv.size() > 0) {
                        tvHmpvvariety.setVisibility(View.VISIBLE);
                        tvHmpvvariety.setText("MPV（共" + mpv.size() + "款）");
                    } else {
                        tvHmpvvariety.setVisibility(View.GONE);
                    }
                    paoChe = data.getPaoChe();
                    if (paoChe != null && !paoChe.isEmpty() && paoChe.size() > 0) {
                        tvHpaochevariety.setVisibility(View.VISIBLE);
                        tvHpaochevariety.setText("跑车（共" + paoChe.size() + "款）");
                    } else {
                        tvHpaochevariety.setVisibility(View.GONE);
                    }
                }
                mList.clear();
                if (sedan != null && sedan.size() > 0) {
                    mList.addAll(sedan);
                    chage = 1;
                    tvHjiaochevariety.setTextColor(Color.BLUE);
                } else if (suv != null && suv.size() > 0) {
                    mList.addAll(suv);
                    chage = 2;
                    tvHsuvvariety.setTextColor(Color.BLUE);
                } else if (mpv != null && mpv.size() > 0) {
                    mList.addAll(mpv);
                    chage = 3;
                    tvHmpvvariety.setTextColor(Color.BLUE);
                    tvHjiaochevariety.setTextColor(Color.BLUE);
                } else if (paoChe != null && paoChe.size() > 0) {
                    mList.addAll(paoChe);
                    chage = 4;
                    tvHpaochevariety.setTextColor(Color.BLUE);
                }
                switch (chage) {
                    case 1:
                        tvHjiaochevariety.setTextColor(Color.BLUE);
                        tvHsuvvariety.setTextColor(Color.GRAY);
                        tvHmpvvariety.setTextColor(Color.GRAY);
                        tvHpaochevariety.setTextColor(Color.GRAY);
                        break;
                    case 2:
                        tvHjiaochevariety.setTextColor(Color.GRAY);
                        tvHsuvvariety.setTextColor(Color.BLUE);
                        tvHmpvvariety.setTextColor(Color.GRAY);
                        tvHpaochevariety.setTextColor(Color.GRAY);
                        break;
                    case 3:
                        tvHjiaochevariety.setTextColor(Color.GRAY);
                        tvHsuvvariety.setTextColor(Color.GRAY);
                        tvHmpvvariety.setTextColor(Color.BLUE);
                        tvHpaochevariety.setTextColor(Color.GRAY);
                        break;
                    case 4:
                        tvHjiaochevariety.setTextColor(Color.GRAY);
                        tvHsuvvariety.setTextColor(Color.GRAY);
                        tvHmpvvariety.setTextColor(Color.GRAY);
                        tvHpaochevariety.setTextColor(Color.BLUE);
                        break;
                }
                hotcarAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick({R.id.tv_search,R.id.title_Back,R.id.tv_hjiaochevariety, R.id.tv_hsuvvariety, R.id.tv_hmpvvariety, R.id.tv_hpaochevariety})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                String trim = svToolbar.getText().toString().trim();
                if (trim != null && trim != "") {
                    loadData();
                }
                break;
            case R.id.title_Back:
                AppManager.finishActivity();
                break;
            case R.id.tv_hjiaochevariety:
                if (chage != 1) {
                    chage = 1;
                    tvHjiaochevariety.setTextColor(Color.BLUE);
                    tvHsuvvariety.setTextColor(Color.GRAY);
                    tvHmpvvariety.setTextColor(Color.GRAY);
                    tvHpaochevariety.setTextColor(Color.GRAY);
                    mList.clear();
                    mList.addAll(sedan);
                    hotcarAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.tv_hsuvvariety:
                if (chage != 2) {
                    chage = 2;
                    tvHjiaochevariety.setTextColor(Color.GRAY);
                    tvHsuvvariety.setTextColor(Color.BLUE);
                    tvHmpvvariety.setTextColor(Color.GRAY);
                    tvHpaochevariety.setTextColor(Color.GRAY);
                    mList.clear();
                    mList.addAll(suv);
                    hotcarAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.tv_hmpvvariety:
                if (chage != 3) {
                    chage = 3;
                    tvHjiaochevariety.setTextColor(Color.GRAY);
                    tvHsuvvariety.setTextColor(Color.GRAY);
                    tvHmpvvariety.setTextColor(Color.BLUE);
                    tvHpaochevariety.setTextColor(Color.GRAY);
                    mList.clear();
                    mList.addAll(mpv);
                    hotcarAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.tv_hpaochevariety:
                if (chage != 4) {
                    chage = 4;
                    tvHjiaochevariety.setTextColor(Color.GRAY);
                    tvHsuvvariety.setTextColor(Color.GRAY);
                    tvHmpvvariety.setTextColor(Color.GRAY);
                    tvHpaochevariety.setTextColor(Color.BLUE);
                    mList.clear();
                    mList.addAll(paoChe);
                    hotcarAdapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
