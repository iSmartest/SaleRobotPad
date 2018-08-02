package com.freeintelligence.robotclient.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.freeintelligence.robotclient.R;

/**
 * Created by za on 2018/7/15.
 */

public class MyDialog extends Dialog {
    public ImageView mImageView;
    public TextView mTextView;

    public MyDialog(@NonNull Context context, int themeResId,String content) {
        super(context, themeResId);
        setCanceledOnTouchOutside(true);
        setCancelable(false);
        setContentView(R.layout.mydialog);
        mTextView =  findViewById(R.id.loadingTv);
        mImageView =  findViewById(R.id.loadingIv);
        Glide.with(context).load(R.drawable.loading).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mImageView);
        mTextView.setText(content);
    }

    public void setText(String text){
        mTextView.setText(text);
    }
}