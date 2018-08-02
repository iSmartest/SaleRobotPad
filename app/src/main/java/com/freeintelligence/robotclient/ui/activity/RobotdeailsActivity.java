package com.freeintelligence.robotclient.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.app.App;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.ui.moudel.RobotconsultBean;
import com.freeintelligence.robotclient.utils.GlideUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RobotdeailsActivity extends BaseActivity {


    @BindView(R.id.robotbanner)
    Banner banner;
    @BindView(R.id.iv_rslid)
    ImageView ivSlid;
    private List<String> list_path;
    private ArrayList<String> list_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_robotdeails;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        RobotconsultBean.DataBean.CarListBean.AnswersBean robot = (RobotconsultBean.DataBean.CarListBean.AnswersBean) getIntent().getSerializableExtra("robot");
        List<RobotconsultBean.DataBean.CarListBean.AnswersBean.PicsBean> pics = robot.getPics();
        int type = robot.getType();
        switch (type){
            case 1:
                ivSlid.setVisibility(View.VISIBLE);
                break;
            case 2:
                ivSlid.setVisibility(View.GONE);
                break;
        }
        String videoAddress = robot.getVideoAddress();
        ivSlid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RobotdeailsActivity.this, RobotVideoActivity.class);
                RobotdeailsActivity.this.startActivity(intent);
            }
        });
        initview(pics);

    }

    private void initview(List<RobotconsultBean.DataBean.CarListBean.AnswersBean.PicsBean> pics) {
        RobotdeailsActivity robotdeailsActivity = new RobotdeailsActivity();
        list_path = new ArrayList<>();
        list_path.clear();
        list_title = new ArrayList<>();
        list_title.clear();
        if (pics == null) {
            return;
        }
        for (int i = 0; i < pics.size(); i++) {
            RobotconsultBean.DataBean.CarListBean.AnswersBean.PicsBean picsBean = pics.get(i);
            String des = picsBean.getDes();
            list_title.add(des);
            String pic = picsBean.getPic();
            list_path.add(pic);
        }
        // list_path.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531127016303&di=a5030ac040163ec5df99d4712a02e68e&imgtype=0&src=http%3A%2F%2Fpic10.photophoto.cn%2F20090323%2F0038038047023560_b.jpg");
        //list_path.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531127016304&di=31fd95c1d1ea62604b08175fd60cadd3&imgtype=0&src=http%3A%2F%2Fpic11.nipic.com%2F20101214%2F6244130_191026049138_2.jpg");
        // list_path.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531127016304&di=b9843f56a7bb278e1f2ad2ec0da9b868&imgtype=0&src=http%3A%2F%2Fpic35.photophoto.cn%2F20150601%2F0038038082361163_b.jpg");
        //list_path.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531127016304&di=8caf5ee41df353187db7038943f0257d&imgtype=0&src=http%3A%2F%2Fpic4.nipic.com%2F20091116%2F3625984_005010553304_2.jpg");

        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
        banner.setBannerTitles(list_title);
        //设置轮播间隔时间
        //  banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(false);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
        // .setOnBannerListener(this)
        //必须最后调用的方法，启动轮播图。
        banner.start();
    }

    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            GlideUtils.imageLoader(context,(String)path,imageView);
        }
    }

}
