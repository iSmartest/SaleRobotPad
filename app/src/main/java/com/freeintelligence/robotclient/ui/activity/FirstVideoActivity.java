package com.freeintelligence.robotclient.ui.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.VideoView;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.config.MyString;

public class FirstVideoActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_video);
        videoView = findViewById(R.id.videoView);
        final String uri = "http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4";
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
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                switch (i){
                    case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                        Log.e("text","发生未知错误");

                        break;
                    case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                        Log.e("text","媒体服务器死机");
                        break;
                    default:
                        Log.e("text","onError+"+i);
                        break;
                }
                switch (i1){
                    case MediaPlayer.MEDIA_ERROR_IO:
                        //io读写错误
                        Log.e("text","文件或网络相关的IO操作错误");
                        break;
                    case MediaPlayer.MEDIA_ERROR_MALFORMED:
                        //文件格式不支持
                        Log.e("text","比特流编码标准或文件不符合相关规范");
                        break;
                    case MediaPlayer.MEDIA_ERROR_TIMED_OUT:
                        //一些操作需要太长时间来完成,通常超过3 - 5秒。
                        Log.e("text","操作超时");
                        break;
                    case MediaPlayer.MEDIA_ERROR_UNSUPPORTED:
                        //比特流编码标准或文件符合相关规范,但媒体框架不支持该功能
                        Log.e("text","比特流编码标准或文件符合相关规范,但媒体框架不支持该功能");
                        break;
                    default:
                        Log.e("text","onError+"+i1
                        );
                        break;
                }
                return false;
            }
        });
        //设置为全屏模式播放
       // setVideoViewLayoutParams(1);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_CANCEL:

                break;
            case MotionEvent.ACTION_UP:
                Intent intent = new Intent(this, ConsultActivity.class);
                intent.putExtra(MyString.CONSULT,1);
                startActivity(intent);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        switch (event.getAction()){
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
}
