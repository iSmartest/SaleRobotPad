package com.freeintelligence.robotclient.utils;

import android.os.CountDownTimer;
import android.widget.Button;

/**
 * //                   _ooOoo_
 * //                  o8888888o
 * //                  88" . "88
 * //                  (| -_- |)
 * //                  O\  =  /O
 * //               ____/`---'\____
 * //             .'  \\|     |//  `.
 * //            /  \\|||  :  |||//  \
 * //           /  _||||| -:- |||||-  \
 * //           |   | \\\  -  /// |   |
 * //           | \_|  ''\---/''  |   |
 * //           \  .-\__  `-`  ___/-. /
 * //         ___`. .'  /--.--\  `. . ___
 * //      ."" '<  `.___\_<|>_/___.'  >' "".
 * //     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * //     \  \ `-.   \_ __\ /__ _/   .-` /  /
 * //======`-.____`-.___\_____/___.-`____.-'======
 * //                   `=---='
 * //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 * //         佛祖保佑     永不宕机      永无BUG
 * //                     大姨夫 2018/1/2
 * //               站在顶峰,看世界
 * //               跌落谷底,思人生
 */
public class TimeCount extends CountDownTimer {

    private Button button;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public TimeCount(Button button,long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.button=button;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        button.setClickable(false);
        button.setText(millisUntilFinished / 1000 +"s");
    }

    @Override
    public void onFinish() {
        button.setText("重新发送");
        button.setClickable(true);

    }
}
