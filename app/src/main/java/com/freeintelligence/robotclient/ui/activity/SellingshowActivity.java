package com.freeintelligence.robotclient.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.app.App;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.config.MyString;
import com.freeintelligence.robotclient.config.Url;
import com.freeintelligence.robotclient.ui.moudel.ConsultBean;
import com.freeintelligence.robotclient.utils.AppManager;
import com.freeintelligence.robotclient.view.PhotoView;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;


public class SellingshowActivity extends BaseActivity {
    @BindView(R.id.title_Back)
    ImageView mBack;
    @BindView(R.id.toolbar)
    RelativeLayout titleLayout;
    @BindView(R.id.see_pager)
    ViewPager mPager;
    @BindView(R.id.ll_see_picture)
    LinearLayout mLlPicture;
    @BindView(R.id.text_total_item)
    TextView mTotalItem;
    @BindView(R.id.text_pic_info)
    TextView mPicInfo;
    @BindView(R.id.iv_play_video)
    ImageView mPlay;
    private ImageAdapter mImageAdapter;
    private boolean isVISIBLE = true;
    private String videoAddress;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_slideshow;
    }

    @Override
    protected void initView() {
        titleLayout.setBackgroundColor(context.getResources().getColor(R.color.black_50_transparent));
        final ConsultBean.DataBean.BrightPointBean answersBean = (ConsultBean.DataBean.BrightPointBean) getIntent().getSerializableExtra("selling");
        int consultint = answersBean.getType();
        switch (consultint){
            case 1:
                mPlay.setVisibility(View.VISIBLE);
                break;
            case 2:
                mPlay.setVisibility(View.GONE);
                break;
        }

        mTotalItem.setText(1 + "/" + answersBean.getSellPointdsc().size());
        mPicInfo.setText(answersBean.getSellPointdsc().get(0).getDsc());
        mPicInfo.setMaxHeight(600);
        mPicInfo.setMovementMethod(ScrollingMovementMethod.getInstance());
        mPager.setPageMargin((int) (context.getResources().getDisplayMetrics().density * 15));
        mImageAdapter = new ImageAdapter(context,answersBean.getSellPointdsc());
        mPager.setAdapter(mImageAdapter);
        mPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mTotalItem.setText((position + 1)+"/" + answersBean.getSellPointdsc().size());
                mPicInfo.setText(answersBean.getSellPointdsc().get(position).getDsc());
            }
        });

        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answersBean!=null){
                    videoAddress = answersBean.getSellPointvid();
                }
                Intent intent = new Intent(App.activity, VideoActivity.class);
                intent.putExtra(MyString.VIDEO, videoAddress);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void loadData() {

    }


    public class ImageAdapter extends PagerAdapter {
        private PhotoView photo_view;
        private Context mContext;
        private List<ConsultBean.DataBean.BrightPointBean.SellPointdscBean> mShopImgList;

        public ImageAdapter(Context mContext, List<ConsultBean.DataBean.BrightPointBean.SellPointdscBean> mShopImgList) {
            this.mContext = mContext;
            this.mShopImgList = mShopImgList;
        }

        @Override
        public int getCount() {
            return mShopImgList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.item_see_picture, null);
            photo_view = view.findViewById(R.id.iv_see_picture);
            photo_view.enable();
            photo_view.setScaleType(ImageView.ScaleType.FIT_CENTER);
            Glide.with(mContext).load(Url.IMAGE_HTTP + mShopImgList.get(position).getImg()).into(photo_view);
            photo_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isVISIBLE){
                        mLlPicture.setVisibility(View.GONE);
                        titleLayout.setVisibility(View.GONE);
                        isVISIBLE = false;
                    }else {
                        mLlPicture.setVisibility(View.VISIBLE);
                        titleLayout.setVisibility(View.VISIBLE);
                        isVISIBLE = true;
                    }
                }
            });
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
    @OnClick({R.id.title_Back})
    public void onViewClicked() {
        AppManager.finishActivity();
    }
}
