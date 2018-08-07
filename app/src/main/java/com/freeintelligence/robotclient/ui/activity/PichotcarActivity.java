package com.freeintelligence.robotclient.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.utils.AppManager;
import com.freeintelligence.robotclient.utils.GlideUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.OnClick;

public class PichotcarActivity extends BaseActivity {
    @BindView(R.id.title_Back)
    ImageView mBack;
    @BindView(R.id.banner_hotcar)
    Banner bannerHotcar;
    private ArrayList<? extends String> list_path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pichotcar;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        final int pic = getIntent().getIntExtra("position", 0);
        list_path = getIntent().getParcelableArrayListExtra("pic");
        bannerHotcar.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
        .setImageLoader(new MyLoader())
        .setImages(list_path)
        .setBannerAnimation(Transformer.Default)
        .setDelayTime(1)
        .isAutoPlay(true);
        bannerHotcar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                if (position==pic+1){
                    bannerHotcar.isAutoPlay(false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bannerHotcar.setIndicatorGravity(BannerConfig.CENTER);
        bannerHotcar.start();
    }
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            GlideUtils.imageLoader(context,(String)path,imageView);
        }
    }
    @OnClick({R.id.title_Back})
    public void onViewClicked() {
        AppManager.finishActivity();
    }
}
