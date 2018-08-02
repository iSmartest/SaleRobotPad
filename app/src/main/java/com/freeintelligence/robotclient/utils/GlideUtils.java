package com.freeintelligence.robotclient.utils;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.freeintelligence.robotclient.R;

import static com.freeintelligence.robotclient.config.Url.HTTP;


/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/7/19.
 * Description:
 */
public class GlideUtils {
    public static void imageLoader(Context context, String image, ImageView imageView){
        String pic = HTTP + image;
        Glide.with(context).load(pic).error(R.drawable.car).into(imageView);
    }
}
