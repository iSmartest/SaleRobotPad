package com.freeintelligence.robotclient.app;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDex;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.bulong.rudeness.RudenessScreenHelper;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.zhy.autolayout.config.AutoLayoutConifg;

import java.util.UUID;

/**
 * Created by za on 2018/6/12.
 */

public class App extends Application {

    public static BaseActivity activity = null;
    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        SpeechUtility.createUtility(App.this, SpeechConstant.APPID + "=5b34af5c");
        AutoLayoutConifg.getInstance().useDeviceSize().init(this);
        getPhoneSign();
        int designWidth = 1920;
        new RudenessScreenHelper(this, designWidth).activate();
    }

    public static Context getContext() {
        return activity;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    //获取手机的唯一标识
    public String getPhoneSign() {
        StringBuilder deviceId = new StringBuilder();
        // 渠道标志
        deviceId.append("a");
        try {
            //IMEI（imei）
            TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return "";
            }
            String imei = tm.getDeviceId();
                if(!TextUtils.isEmpty(imei)){
                    deviceId.append("imei");
                    deviceId.append(imei);
                  return deviceId.toString();
                    }
                //序列号（sn）
                String sn = tm.getSimSerialNumber();
              if(!TextUtils.isEmpty(sn)){
                   deviceId.append("sn");
                    deviceId.append(sn);
                    return deviceId.toString();
                 }
                //如果上面都没有， 则生成一个id：随机码
                String uuid = getUUID();
                if(!TextUtils.isEmpty(uuid)){
                     deviceId.append("id");
                     deviceId.append(uuid);
                     return deviceId.toString();
                     }
               } catch (Exception e) {
                e.printStackTrace();
                deviceId.append("id").append(getUUID());
               }
            return deviceId.toString();
          }
 /**
 36   * 得到全局唯一UUID
 37   */
          private String uuid;
  public String getUUID(){
          SharedPreferences mShare = getSharedPreferences("uuid",MODE_PRIVATE);
          if(mShare != null){
              uuid = mShare.getString("uuid", "");
             }
           if(TextUtils.isEmpty(uuid)){
              uuid = UUID.randomUUID().toString();
               mShare.edit().putString("uuid",uuid).commit();
             }
           return uuid;
         }
}
