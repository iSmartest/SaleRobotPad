package com.freeintelligence.robotclient.ui.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.config.MyString;
import com.freeintelligence.robotclient.utils.AppManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

import static com.freeintelligence.robotclient.config.Url.IMAGE_HTTP;

public class DetailsActivity extends BaseActivity {
    @BindView(R.id.title_Back)
    ImageView mBack;
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
        String uri = IMAGE_HTTP+getIntent().getStringExtra(MyString.INTENTIBRIEFADRESS);
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
        jzVideodetails.setUp(uri,JZVideoPlayerStandard.SCREEN_WINDOW_FULLSCREEN);
        //设置播放器封面
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
    @OnClick({R.id.title_Back})
    public void onViewClicked(View view) {
        AppManager.finishActivity();
    }
}
