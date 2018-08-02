package com.freeintelligence.robotclient.okhttp.budiler;
import com.freeintelligence.robotclient.okhttp.budiler.budiler.OkHttpRequestBuilder;

import java.util.Map;

/**
 * Created by 小火
 * Create time on  2017/3/22
 * My mailbox is 1403241630@qq.com
 */
public interface HasParamsable
{
    OkHttpRequestBuilder params(Map<String, String> params);
    OkHttpRequestBuilder addParams(String key, String val);
}
