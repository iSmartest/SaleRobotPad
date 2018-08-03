package com.freeintelligence.robotclient.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.config.MyString;
import com.freeintelligence.robotclient.ui.moudel.ConsultBean;
import com.freeintelligence.robotclient.utils.GlideUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.freeintelligence.robotclient.config.Url.HTTP;

public class SlideshowActivity extends Activity {

    @BindView(R.id.slidbanner)
    Banner banner;
    @BindView(R.id.iv_slid)
    ImageView tvSlid;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    private List<String> list_path;
    private ArrayList<String> list_title;
    private String videoAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);
        ButterKnife.bind(this);
        ConsultBean.DataBean.AnswersBean answersBean = (ConsultBean.DataBean.AnswersBean) getIntent().getSerializableExtra("consult");
        int consultint =answersBean.getType();
        switch (consultint){
            case 1:
                tvSlid.setVisibility(View.VISIBLE);
                break;
            case 2:
                tvSlid.setVisibility(View.GONE);
                break;
        }
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
          loaddata(answersBean);
          initview(answersBean);
    }

    private void loaddata(final ConsultBean.DataBean.AnswersBean answersBean) {
            tvSlid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   if(answersBean!=null){
                       videoAddress = answersBean.getVideoAddress();
                   }
                    Intent intent = new Intent(SlideshowActivity.this, VideoActivity.class);
                    intent.putExtra(MyString.VIDEO, videoAddress);
                    startActivity(intent);
                }
            });
    }
    private void initview(ConsultBean.DataBean.AnswersBean answersBean) {

        list_path = new ArrayList<>();
        list_path.clear();
        list_title = new ArrayList<>();
        list_title.clear();
        if (answersBean == null) {
            return;
        }
        for (int i = 0; i < answersBean.getPics().size(); i++) {
            ConsultBean.DataBean.AnswersBean.PicsBean picsBean = answersBean.getPics().get(i);
            String des = picsBean.getDes();
            list_title.add(des);
            String pic = picsBean.getPic();
            list_path.add(pic);
        }
       // list_path.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531127016303&di=a5030ac040163ec5df99d4712a02e68e&imgtype=0&src=http%3A%2F%2Fpic10.photophoto.cn%2F20090323%2F0038038047023560_b.jpg");
       // list_path.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531127016304&di=31fd95c1d1ea62604b08175fd60cadd3&imgtype=0&src=http%3A%2F%2Fpic11.nipic.com%2F20101214%2F6244130_191026049138_2.jpg");
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
       /* banner.setDelayTime(1);
        banner.isAutoPlay(true);
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                if (position==2){
                    banner.isAutoPlay(false);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });*/
       banner.isAutoPlay(false);
        //设置是否为自动轮播，默认是“是”。
       //banner.setOffscreenPageLimit(1);
       // banner.startAutoPlay();
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
