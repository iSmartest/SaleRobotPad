package com.freeintelligence.robotclient.ui.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.config.MyString;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class DetailsActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.jz_videodetails)
    JZVideoPlayerStandard jzVideodetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        String uri = getIntent().getStringExtra(MyString.INTENTIBRIEFADRESS);
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;  //横向
        JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;  //横向

        jzVideodetails.backButton.setVisibility(View.VISIBLE);
        jzVideodetails.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        jzVideodetails.fullscreenButton.setVisibility(View.GONE);
        jzVideodetails.tinyBackImageView.setVisibility(View.GONE);
        jzVideodetails.titleTextView.setVisibility(View.GONE);
        jzVideodetails.setUp("http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4"
                , JZVideoPlayerStandard.SCREEN_WINDOW_FULLSCREEN);
        //设置播放器封面
      /* Glide.with(this)
                .load("http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png")
                .into(jzVideodetails.thumbImageView);*/
       jzVideodetails.startVideo();

    }
    @Override
    public void onBackPressed() {
        if(JZVideoPlayerStandard.backPress()){
            return;
        }
        super.onBackPressed();
    }

    /**
     * 暂停 /失去焦点
     * Activity或者按Home键之后会视频就会releas(释放)
     */
    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayerStandard.releaseAllVideos();
    }
    
}
