package com.freeintelligence.robotclient.ui.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.VideoView;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.config.MyString;
import com.freeintelligence.robotclient.utils.SPUtil;


import static com.freeintelligence.robotclient.config.Url.IMAGE_HTTP;


public class FirstVideoActivity extends Activity {

    VideoView videoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_video);
        videoView = findViewById(R.id.videoView);
        initView();
    }


    protected void initView() {
        final String uri = IMAGE_HTTP + SPUtil.getString(FirstVideoActivity.this, "address");
        videoView.setVideoPath(uri);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);
            }
        });


        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.setVideoPath(uri);
                videoView.start();
            }
        });
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_UP:
                Intent intent = new Intent(this, ConsultActivity.class);
                intent.putExtra(MyString.CONSULT, 1);
                intent.putExtra(MyString.INTENTHOTCAR, SPUtil.getString(FirstVideoActivity.this, "carId"));
                startActivity(intent);
                finish();
                break;

        }
        return super.dispatchTouchEvent(ev);
    }

    @Override

    public boolean dispatchKeyEvent(KeyEvent event) {
        switch (event.getAction()) {
            case KeyEvent.ACTION_DOWN:
                break;
            case KeyEvent.ACTION_UP:
                break;

        }
        return super.dispatchKeyEvent(event);

    }


    @Override

    protected void onStop() {
        super.onStop();
        videoView.stopPlayback();

    }


    @Override

    protected void onDestroy() {
        super.onDestroy();
        videoView.stopPlayback();

    }
}