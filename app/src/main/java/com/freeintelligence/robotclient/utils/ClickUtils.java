package com.freeintelligence.robotclient.utils;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/9.
 * Description:
 */
public class ClickUtils {

    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
