package com.freeintelligence.robotclient.ui.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.config.Url;
import com.freeintelligence.robotclient.okhttp.MyOkhttp;
import com.freeintelligence.robotclient.ui.adapter.HotCarColorAdapter;
import com.freeintelligence.robotclient.ui.adapter.HotcardeailsAdapter;
import com.freeintelligence.robotclient.ui.adapter.PicHotCarDesAdapter;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.config.MyString;
import com.freeintelligence.robotclient.ui.moudel.HotcarBean;
import com.freeintelligence.robotclient.ui.moudel.HotcardeailsBean;
import com.freeintelligence.robotclient.utils.ToastUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class HotcardeailsActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.ll_carcolor)
    LinearLayout llCarcolor;
    @BindView(R.id.tv_displacement)
    TextView tvDisplacement;
    @BindView(R.id.tv_carsturcture)
    TextView tvCarsturcture;
    @BindView(R.id.tv_grade)
    TextView tvGrade;
    @BindView(R.id.rv_hotacardeails)
    RecyclerView rvHotacardeails;
    @BindView(R.id.tv_price1)
    TextView tvPrice1;
    @BindView(R.id.tv_price2)
    TextView tvPrice2;
    @BindView(R.id.rl_hotcardeails)
    RelativeLayout rlHotcardeails;
    @BindView(R.id.jz_hotcar)
    JZVideoPlayerStandard jzHotcar;
    @BindView(R.id.hoatcarbox)
    TextView hoatcarbox;
    @BindView(R.id.rv_hotcarcolor)
    RecyclerView rvHotcarcolor;
    @BindView(R.id.rv_pichotacardeails)
    RecyclerView rvPichotacardeails;
    private HotcardeailsAdapter hotcardeailsAdapter;
    private List<String> list = new ArrayList<>();
    private List<String> colorlist = new ArrayList<>();
    private int carid = 0;
    private HotCarColorAdapter hotCarColorAdapter;
    private PicHotCarDesAdapter picHotCarDesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hotcardeails;
    }

    @Override
    protected void loadData() {
        int hotcar = getIntent().getIntExtra(MyString.INTENTHOTCAR, 0);
        carid = hotcar;
        HotcarBean.DataBean dataBean = (HotcarBean.DataBean) getIntent().getSerializableExtra(MyString.HOTCATLIST);
        Log.i("", dataBean.toString());
        jzHotcar.setVisibility(View.GONE);
        rvHotacardeails.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        if (dataBean != null) {
        }
        hotcardeailsAdapter = new HotcardeailsAdapter();
        rvHotacardeails.setAdapter(hotcardeailsAdapter);
        rvPichotacardeails.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        picHotCarDesAdapter = new PicHotCarDesAdapter(context,list);
        rvPichotacardeails.setAdapter(picHotCarDesAdapter);
        rvHotcarcolor.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        hotCarColorAdapter = new HotCarColorAdapter(context,colorlist);
        rvHotcarcolor.setAdapter(hotCarColorAdapter);

        starintnet();
    }

    @Override
    protected void initView() {


    }

    private void starintnet() {
        Map<String,String> params = new HashMap<>();
        params.put("storeId", "1");
        params.put("carId", carid + "");
        MyOkhttp.Okhttp(context, Url.HOTCARDEAILS, "加载中...", params, new MyOkhttp.CallBack() {
            @Override
            public void onRequestComplete(String response, String result, String resultNote) {
                Gson gson = new Gson();
                HotcardeailsBean hotcardeailsBean = gson.fromJson(response, HotcardeailsBean.class);
                if (result.equals("1")) {
                    ToastUtils.makeText(context, resultNote);
                    return;
                }
                HotcardeailsBean.DataBean.CarBean car = hotcardeailsBean.getData().getCar();

                /**
                 * motor : dd
                 * score : 100
                 * colour : ["1","2"]
                 * address :
                 * preprice : 10000
                 * guideprice : 155
                 * pic : upload/15296550812188634.png
                 * gearbox : cvt变速箱
                 * pics : ["/upload/24343543.jpg","/upload/24343543.jpg"]
                 * structure : 1
                 */
                tvPrice1.setText(car.getPreprice());
                tvPrice2.setText(car.getGuideprice());
                tvCarsturcture.setText(car.getStructure());
                tvDisplacement.setText(car.getGearbox());
                tvGrade.setText(car.getScore() + "分");
                tvDisplacement.setText(car.getMotor());
                List<String> pics = car.getPics();
                list.clear();
                list.addAll(pics);
                picHotCarDesAdapter.notifyDataSetChanged();
                List<String> colour = car.getColour();
                colorlist.clear();
                colorlist.addAll(colour);
                hotCarColorAdapter.notifyDataSetChanged();
                jzHotcar.setVisibility(View.VISIBLE);
                JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;  //横向
                JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;  //纵向
                jzHotcar.setUp("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4"
                        , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "饺子闭眼睛");
                jzHotcar.thumbImageView.setImageResource(R.mipmap.bg);
                boolean b = JZVideoPlayer.backPress();

            }
        });

    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    public void refresh(int id) {
        carid = id;
        starintnet();
    }
}
