package com.freeintelligence.robotclient.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.config.MyString;
import com.freeintelligence.robotclient.config.Url;
import com.freeintelligence.robotclient.okhttp.MyOkhttp;
import com.freeintelligence.robotclient.ui.moudel.RobotconsultBean;
import com.freeintelligence.robotclient.utils.AppManager;
import com.freeintelligence.robotclient.utils.JsonParser;
import com.freeintelligence.robotclient.utils.SPUtil;
import com.google.gson.Gson;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.iflytek.sunflower.FlowerCollector;
import com.robot.performlib.action.AIUIAction;
import com.robot.performlib.action.SpeakAction;
import com.robot.performlib.action.WakeupAction;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import xfyun.setting.IatSettings;

public class RobotconsltActivity extends BaseActivity {
    private static String TAG = RobotconsltActivity.class.getSimpleName();
    @BindView(R.id.title_Back)
    ImageView mBack;
    @BindView(R.id.tv_this1)
    TextView tvThis1;
    @BindView(R.id.tv_robotan2)
    TextView tvRobotan2;
    @BindView(R.id.iv_mic)
    ImageView ivMic;
    @BindView(R.id.tv_rbcon3)
    TextView tvRbcon3;
    @BindView(R.id.iv_mic2)
    ImageView ivMic2;
    @BindView(R.id.iv_yinbo)
    ImageView ivYinbo;
    @BindView(R.id.tv_ifly)
    TextView tvIfly;
    // 语音听写对象
    private SpeechRecognizer mIat;
    // 语音听写UI
    private RecognizerDialog mIatDialog;
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();
    private SharedPreferences mSharedPreferences;
    private SpeechSynthesizer mTts;
    // 默认发音人
    private String voicer = "vinn";
    private String[] mCloudVoicersEntries;
    private String[] mCloudVoicersValue;

    // 引擎类型
    private String mEngineType = SpeechConstant.TYPE_CLOUD;
    private Toast mToast;
    private boolean mTranslateEnable = false;
    int ret = 0; // 函数调用返回值
    List<RobotconsultBean.DataBean.CarListBean.AnswersBean> myanswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_robotconslt;
    }

    @Override
    protected void loadData() {
    }

    @Override
    protected void initView() {
//        WakeupAction.AIUIWakeUp(this, 0);
//        AIUIAction.changeScene(context, AIUIAction.Scene.asr);
        tvThis1.setVisibility(View.GONE);
        tvRobotan2.setVisibility(View.GONE);
        ivMic2.setVisibility(View.GONE);
        ivYinbo.setVisibility(View.GONE);
        Glide.with(RobotconsltActivity.this).load(R.drawable.yinbo).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(ivYinbo);
        myanswers = new ArrayList<>();
        // 初始化识别无UI识别对象
        // 使用SpeechRecognizer对象，可根据回调消息自定义界面；
        mIat = SpeechRecognizer.createRecognizer(RobotconsltActivity.this, mInitListener);
        mTts = SpeechSynthesizer.createSynthesizer(this, mTtsInitListener);
        // 云端发音人名称列表
        mCloudVoicersEntries = getResources().getStringArray(R.array.voicer_cloud_entries);
        mCloudVoicersValue = getResources().getStringArray(R.array.voicer_cloud_values);
        // 初始化听写Dialog，如果只使用有UI听写功能，无需创建SpeechRecognizer
        // 使用UI听写功能，请根据sdk文件目录下的notice.txt,放置布局文件和图片资源
        mSharedPreferences = getSharedPreferences(IatSettings.PREFER_NAME,
                Activity.MODE_PRIVATE);
        mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
    }

    @OnClick({R.id.title_Back, R.id.iv_mic, R.id.iv_mic2, R.id.tv_robotan2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mic:
                ivMic.setVisibility(View.GONE);
                tvRbcon3.setVisibility(View.GONE);
                ivYinbo.setVisibility(View.VISIBLE);
                onclick();
                break;
            case R.id.iv_mic2:
                ivMic2.setVisibility(View.GONE);
                ivYinbo.setVisibility(View.VISIBLE);
                onclick();
                break;
            case R.id.tv_robotan2:
                if (myanswers != null && myanswers.size() > 0) {
                    RobotconsultBean.DataBean.CarListBean.AnswersBean answersBean = myanswers.get(0);
                    int type = answersBean.getType();
                    switch (type) {
                        case 1:
                            //都有
                            Intent intent = new Intent(this, RobotdeailsActivity.class);
                            intent.putExtra("robot", answersBean);
                            // intent.putExtra("robotint",1);
                            startActivity(intent);
                            break;
                        case 2:
                            //图文
                            Intent intent1 = new Intent(this, RobotdeailsActivity.class);
                            intent1.putExtra("robot", answersBean);
                            // intent1.putExtra("robotint",2);
                            startActivity(intent1);
                            break;
                        case 3:
                            //视频
                            Intent intent2 = new Intent(this, VideoActivity.class);
                            intent2.putExtra(MyString.VIDEO, answersBean.getVideoAddress());
                            startActivity(intent2);
                            break;

                    }
                }
                break;
            case R.id.title_Back:
                AppManager.finishActivity();
                break;
        }
    }

    private void onclick() {
//        SpeakAction.getInstance().speak(context, "您好，很高兴为您服务", "wakeUp");

        tvRobotan2.setText("");
        FlowerCollector.onEvent(RobotconsltActivity.this, "iat_recognize");
        mIatResults.clear();
        // 设置参数
        setParam();
        ret = mIat.startListening(mRecognizerListener);
        if (ret != ErrorCode.SUCCESS) {
            showTip("听写失败,错误码：" + ret);
        } else {
            // showTip("开始");
        }


    }

    private void setParam() {
        // 清空参数
        mIat.setParameter(SpeechConstant.PARAMS, null);
        // 设置听写引擎
        mIat.setParameter(SpeechConstant.ENGINE_TYPE, mEngineType);
        // 设置返回结果格式
        mIat.setParameter(SpeechConstant.RESULT_TYPE, "json");
        this.mTranslateEnable = mSharedPreferences.getBoolean("translate", false);
        if (mTranslateEnable) {
            Log.i(TAG, "translate enable");
            mIat.setParameter(SpeechConstant.ASR_SCH, "1");
            mIat.setParameter(SpeechConstant.ADD_CAP, "translate");
            mIat.setParameter(SpeechConstant.TRS_SRC, "its");
        }

        String lag = mSharedPreferences.getString("iat_language_preference",
                "mandarin");
        if (lag.equals("en_us")) {
            // 设置语言
            mIat.setParameter(SpeechConstant.LANGUAGE, "en_us");
            mIat.setParameter(SpeechConstant.ACCENT, null);

            if (mTranslateEnable) {
                mIat.setParameter(SpeechConstant.ORI_LANG, "en");
                mIat.setParameter(SpeechConstant.TRANS_LANG, "cn");
            }
        } else {
            // 设置语言
            mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
            // 设置语言区域
            mIat.setParameter(SpeechConstant.ACCENT, lag);

            if (mTranslateEnable) {
                mIat.setParameter(SpeechConstant.ORI_LANG, "cn");
                mIat.setParameter(SpeechConstant.TRANS_LANG, "en");
            }
        }
        //此处用于设置dialog中不显示错误码信息
        //mIat.setParameter("view_tips_plain","false");

        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        mIat.setParameter(SpeechConstant.VAD_BOS, mSharedPreferences.getString("iat_vadbos_preference", "4000"));

        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mIat.setParameter(SpeechConstant.VAD_EOS, mSharedPreferences.getString("iat_vadeos_preference", "1000"));

        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mIat.setParameter(SpeechConstant.ASR_PTT, mSharedPreferences.getString("iat_punc_preference", "0"));

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mIat.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/iat.wav");
    }

    private void printTransResult(RecognizerResult results) {
        String result = results.getResultString(); //为解析的
        String text = JsonParser.parseIatResult(result);//解析过后的
        String sn = null;
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mIatResults.put(sn, text);//没有得到一句，添加到
        StringBuffer resultBuffer = new StringBuffer();
        for (String key : mIatResults.keySet()) {
            resultBuffer.append(mIatResults.get(key));
        }
        mIat.cancel();
        String resul = resultBuffer.toString();
        tvThis1.setText(resul);
        starinternet(resul);
    }

    private void starinternet(String s) {
        Map<String, String> map = new HashMap<>();
        //  keyWord  storeId
        map.put("keyWord", s);
        map.put("storeId", SPUtil.getString(context, "storeId"));
        map.put("cId", "1");
        MyOkhttp.Okhttp(context, Url.ROBOTCONSULT, "", map, new MyOkhttp.CallBack() {
            @Override
            public void onRequestComplete(String response, String result, String resultNote) {
                Gson gson = new Gson();
                RobotconsultBean robotconsultBean = gson.fromJson(response, RobotconsultBean.class);
                if (robotconsultBean == null) {
                    tvThis1.setVisibility(View.VISIBLE);
                    tvRobotan2.setVisibility(View.VISIBLE);
                    ivMic2.setVisibility(View.VISIBLE);
                    startts("我没有找到答案，请您换个问题");
                    tvRobotan2.setText("我没有找到答案，请您换个问题");
                }
                switch (robotconsultBean.getResultCode()) {
                    case 0:
                        tvThis1.setVisibility(View.VISIBLE);
                        tvRobotan2.setVisibility(View.VISIBLE);
                        ivYinbo.setVisibility(View.GONE);
                        ivMic2.setVisibility(View.VISIBLE);
                        startts(robotconsultBean.getMsg());
                        RobotconsultBean.DataBean data = robotconsultBean.getData();
                        List<RobotconsultBean.DataBean.CarListBean> carList = data.getCarList();
                        if (carList == null || carList.size() == 0) {
                            tvRobotan2.setText("null");
                        } else {
                            RobotconsultBean.DataBean.CarListBean carListBean = carList.get(0);

                            if (!TextUtils.isEmpty(carListBean.getName()) && carListBean.getAnswers() != null && carListBean.getAnswers().size() > 0) {
                                if (!TextUtils.isEmpty(carListBean.getAnswers().get(0).getQuestion())) {
                                    tvRobotan2.setText(carListBean.getName() + carListBean.getAnswers().get(0).getQuestion());
                                    List<RobotconsultBean.DataBean.CarListBean.AnswersBean> answers = carListBean.getAnswers();
                                    myanswers.clear();
                                    myanswers.addAll(answers);
                                }
                            }
                        }
                        break;
                    case 1:
                        ivYinbo.setVisibility(View.GONE);
                        ivMic2.setVisibility(View.VISIBLE);
                        tvRobotan2.setVisibility(View.VISIBLE);
                        tvThis1.setVisibility(View.VISIBLE);
                        startts(robotconsultBean.getMsg());
                        myanswers.clear();
                        tvRobotan2.setText(robotconsultBean.getMsg());
                        break;
                }

                Log.i("", robotconsultBean.toString());

            }
        });
    }

    private void startts(String s) {
        FlowerCollector.onEvent(this, "tts_play");
        // 设置参数
        setttsParam();
        int code = mTts.startSpeaking(s, mTtsListener);
        if (code != ErrorCode.SUCCESS) {
            showTip("语音合成失败,错误码: " + code);
        }
    }


    /**
     * 初始化监听器。
     */
    private InitListener mInitListener = new InitListener() {

        @Override
        public void onInit(int code) {
            Log.d(TAG, "SpeechRecognizer init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                showTip("初始化失败，错误码：" + code);
            }
        }
    };

    private void showTip(final String str) {
        mToast.setText(str);
        mToast.show();
    }

    /**
     * 听写监听器。
     */
    private RecognizerListener mRecognizerListener = new RecognizerListener() {

        @Override
        public void onBeginOfSpeech() {
            // 此回调表示：sdk内部录音机已经准备好了，用户可以开始语音输入
        }

        @Override
        public void onError(SpeechError error) {
            if (mTranslateEnable && error.getErrorCode() == 14002) {
                showTip(error.getPlainDescription(true) + "\n请确认是否已开通翻译功能");
            } else {
                startts("您好像没有说话");
            }
            ivMic2.setVisibility(View.VISIBLE);
            tvThis1.setVisibility(View.VISIBLE);
            tvThis1.setText("您好像没有说话");
            ivYinbo.setVisibility(View.GONE);
        }

        @Override
        public void onEndOfSpeech() {
            ivYinbo.setVisibility(View.GONE);
        }

        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            Log.d(TAG, results.getResultString());
            if (mTranslateEnable) {
                printTransResult(results);
            } else {
                printResult(results);
            }
            printTransResult(results);
            if (isLast) {

            }
        }

        @Override
        public void onVolumeChanged(int volume, byte[] data) {
          /*  showTip("当前正在说话，音量大小：" + volume);
            Log.d(TAG, "返回音频数据：" + data.length);*/
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            //	if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //		String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //		Log.d(TAG, "session id =" + sid);
            //	}
        }
    };
    /*  听写UI监听器
     */
    private RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
        public void onResult(RecognizerResult results, boolean isLast) {
            if (mTranslateEnable) {
                printTransResult(results);
            } else {
                printResult(results);
            }
        }
        //* 识别回调错误.*/

        public void onError(SpeechError error) {
            if (mTranslateEnable && error.getErrorCode() == 14002) {
                showTip(error.getPlainDescription(true) + "\n请确认是否已开通翻译功能");
            } else {
                showTip(error.getPlainDescription(true));
            }
        }

    };

    private void printResult(RecognizerResult results) {
        String text = JsonParser.parseIatResult(results.getResultString());
        String sn = null;
        // 读取json结果中的sn字段
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mIatResults.put(sn, text);
        StringBuffer resultBuffer = new StringBuffer();
        for (String key : mIatResults.keySet()) {
            resultBuffer.append(mIatResults.get(key));
        }
    }

    /**
     * 初始化监听。
     */
    private InitListener mTtsInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
            Log.d(TAG, "InitListener init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                showTip("初始化失败,错误码：" + code);
            } else {
                // 初始化成功，之后可以调用startSpeaking方法
                // 注：有的开发者在onCreate方法中创建完合成对象之后马上就调用startSpeaking进行合成，
                // 正确的做法是将onCreate中的startSpeaking调用移至这里
            }
        }
    };

    /**
     * 合成回调监听。
     */
    private SynthesizerListener mTtsListener = new SynthesizerListener() {

        @Override
        public void onSpeakBegin() {
            // showTip("开始播放");
        }

        @Override
        public void onSpeakPaused() {
            //showTip("暂停播放");
        }

        @Override
        public void onSpeakResumed() {
            //showTip("继续播放");
        }

        @Override
        public void onBufferProgress(int percent, int beginPos, int endPos,
                                     String info) {
            // 合成进度
          /*  mPercentForBuffering = percent;
            showTip(String.format(getString(R.string.tts_toast_format),
                    mPercentForBuffering, mPercentForPlaying));*/
        }

        @Override
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
            // 播放进度
         /*   mPercentForPlaying = percent;
            showTip(String.format(getString(R.string.tts_toast_format),
                    mPercentForBuffering, mPercentForPlaying));*/
        }

        @Override
        public void onCompleted(SpeechError error) {
            if (error == null) {
                // showTip("播放完成");
            } else if (error != null) {
                showTip(error.getPlainDescription(true));
            }
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            Log.e(TAG, "TTS Demo onEvent >>>" + eventType);
            if (SpeechEvent.EVENT_SESSION_ID == eventType) {
                String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
                Log.d(TAG, "session id =" + sid);
            }
        }
    };

    /**
     * 参数设置
     *
     * @return
     */
    private void setttsParam() {
        // 清空参数
        mTts.setParameter(SpeechConstant.PARAMS, null);
        // 根据合成引擎设置相应参数
        if (mEngineType.equals(SpeechConstant.TYPE_CLOUD)) {
            mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
            // 设置在线合成发音人
            mTts.setParameter(SpeechConstant.VOICE_NAME, voicer);
            //设置合成语速
            mTts.setParameter(SpeechConstant.SPEED, mSharedPreferences.getString("speed_preference", "85"));
            //设置合成音调
            mTts.setParameter(SpeechConstant.PITCH, mSharedPreferences.getString("pitch_preference", "50"));
            //设置合成音量
            mTts.setParameter(SpeechConstant.VOLUME, mSharedPreferences.getString("volume_preference", "80"));
        } else {
            mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_LOCAL);
            // 设置本地合成发音人 voicer为空，默认通过语记界面指定发音人。
            mTts.setParameter(SpeechConstant.VOICE_NAME, "");
            /**
             * TODO 本地合成不设置语速、音调、音量，默认使用语记设置
             * 开发者如需自定义参数，请参考在线合成参数设置
             */
        }
        //设置播放器音频流类型
        mTts.setParameter(SpeechConstant.STREAM_TYPE, mSharedPreferences.getString("stream_preference", "3"));
        // 设置播放合成音频打断音乐播放，默认为true
        mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mTts.setParameter(SpeechConstant.AUDIO_FORMAT, "pcm");
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/tts.pcm");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WakeupAction.AIUISleep(this);

        if (null != mTts) {
            mTts.stopSpeaking();
            // 退出时释放连接
            mTts.destroy();
        }
        if (mIat != null) {
            mIat.stopListening();
        }
        mIat.destroy();

    }

    @Override
    protected void onResume() {
        // 开放统计 移动数据统计分析
        FlowerCollector.onResume(this);
        FlowerCollector.onPageStart(TAG);
        super.onResume();
    }

    @Override
    protected void onPause() {
        // 开放统计 移动数据统计分析
        FlowerCollector.onPageEnd(TAG);
        FlowerCollector.onPause(this);
        super.onPause();
    }
}
