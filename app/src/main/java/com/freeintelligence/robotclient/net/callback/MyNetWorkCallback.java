package com.freeintelligence.robotclient.net.callback;

/**
 * Created by xingge on 2017/9/27.
 */

public interface MyNetWorkCallback<T> {

    void onSuccess(T t);
    void onError(int errorCode, String errorMsg);

}
