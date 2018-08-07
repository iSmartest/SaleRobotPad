package com.freeintelligence.robotclient.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.ui.moudel.CarpyteBean;

import java.util.List;


/**
 * Created by 小火
 * Create time on  2017/10/13
 * My mailbox is 1403241630@qq.com
 */

public class SelectCarStyleDialog extends Dialog {
    private Context mContext;
    OnSureBtnClickListener mSureListener;
    GridView gridView;
    private SelectCarStyleAdapter mAdapter;
    private List<CarpyteBean.DataBean.CarTypeListBean> mList ;
    public SelectCarStyleDialog(Context context,List<CarpyteBean.DataBean.CarTypeListBean> mList, OnSureBtnClickListener sureListener) {
        super(context);
        this.mContext = context;
        this.mList = mList;
        this.mSureListener = sureListener;
        initView();
        try{
            int dividerID = mContext.getResources().getIdentifier("android:id/titleDivider", null, null);
            View divider=findViewById(dividerID);
            divider.setBackgroundColor(Color.TRANSPARENT);
        }catch(Exception e){
            //上面的代码，是用来去除Holo主题的蓝色线条
            e.printStackTrace();
        }
    }

    private void initView() {
        setContentView(R.layout.select_car_style_tips);
        gridView = findViewById(R.id.grid_view);
        mAdapter = new SelectCarStyleAdapter(mContext,mList);
        gridView.setAdapter(mAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mSureListener != null) {
                    mSureListener.sure(position);
                }
                dismiss();
            }
        });
    }

    @Override
    public void show() {
        windowDeploy(0, 0);
        super.show();
    }

    public void windowDeploy(int x, int y) {
        Window window = getWindow(); // 得到对话框
        window.setBackgroundDrawableResource(R.drawable.transpant_bg); // 设置对话框背景为透明

        WindowManager.LayoutParams wl = window.getAttributes();
        wl.width = (int) (mContext.getApplicationContext().getResources().getDisplayMetrics().widthPixels * 0.75);
        wl.height = (int) (mContext.getApplicationContext().getResources().getDisplayMetrics().heightPixels*0.6);
        wl.x = x;
        wl.y = y;
        wl.gravity = Gravity.CENTER;// 设置重力
        window.setAttributes(wl);
    }

    public interface OnSureBtnClickListener {
        void sure(int position);
    }
}
