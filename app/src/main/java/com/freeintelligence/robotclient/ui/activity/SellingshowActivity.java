package com.freeintelligence.robotclient.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.app.App;
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

public class SellingshowActivity extends Activity {

    @BindView(R.id.slidbanner)
    Banner banner;
    @BindView(R.id.iv_slid)
    ImageView tvSlid;
    @BindView(R.id.title_Back)
    ImageView mBack;
    private List<String> list_path;
    private ArrayList<String> list_title;
    private String videoAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);
        ButterKnife.bind(this);
        ConsultBean.DataBean.BrightPointBean answersBean = (ConsultBean.DataBean.BrightPointBean) getIntent().getSerializableExtra("selling");
        int consultint = answersBean.getType();
        switch (consultint){
            case 1:
                tvSlid.setVisibility(View.VISIBLE);
                break;
            case 2:
                tvSlid.setVisibility(View.GONE);
                break;
        }
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
          loaddata(answersBean);
          initview(answersBean);
    }

    private void loaddata(final ConsultBean.DataBean.BrightPointBean answersBean) {
            tvSlid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   if(answersBean!=null){
                       videoAddress = answersBean.getSellPointvid();
                   }
                    Intent intent = new Intent(App.activity, VideoActivity.class);
                    intent.putExtra(MyString.VIDEO,HTTP+ videoAddress);
                    startActivity(intent);
                }
            });
    }


    private void initview(ConsultBean.DataBean.BrightPointBean answersBean) {

        list_path = new ArrayList<>();
        list_path.clear();
        list_title = new ArrayList<>();
        list_title.clear();
        if (answersBean == null) {
            return;
        }
        for (int i = 0; i < answersBean.getSellPointdsc().size(); i++) {
            ConsultBean.DataBean.BrightPointBean.SellPointdscBean picsBean = answersBean.getSellPointdsc().get(i);
            String des = picsBean.getDsc();
            list_title.add(des);
            String pic = picsBean.getImg();
            list_path.add(pic);
        }
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        banner.setImageLoader(new MyLoader());
        banner.setImages(list_path);
        banner.setBannerAnimation(Transformer.Default);
        banner.setBannerTitles(list_title);
        banner.isAutoPlay(false);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.start();
    }
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            GlideUtils.imageLoader(context,(String)path,imageView);
        }
    }
}
