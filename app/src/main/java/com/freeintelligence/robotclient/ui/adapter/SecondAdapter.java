package com.freeintelligence.robotclient.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.ui.activity.SecondInfoActivity;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.config.MyString;
import com.freeintelligence.robotclient.ui.moudel.SecondBean;
import com.freeintelligence.robotclient.utils.DateUtils;
import com.freeintelligence.robotclient.utils.GlideUtils;
import com.freeintelligence.robotclient.utils.PriceUtils;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by za on 2018/6/20.
 */

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.SecondViewHolder> {
    private Context context;
    private List<SecondBean.DataBean.CarBean> data;

    public SecondAdapter(Context context, List<SecondBean.DataBean.CarBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public SecondViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_second, parent, false);
        SecondViewHolder viewHolder = new SecondViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SecondViewHolder viewHolder, int position) {
        final SecondBean.DataBean.CarBean carListBean = data.get(position);
        GlideUtils.imageLoader(context,carListBean.getImages(),viewHolder.ivTseond);
        viewHolder.tvSecond1.setText(carListBean.getName());
        long time = carListBean.getTime();
        String dateToString = DateUtils.getDateToString(time, "yyy-MM-dd");
        viewHolder.tvSecond2.setText("上牌时间    "+dateToString);
        viewHolder.tvSecond3.setText("公里表显里程  "+ PriceUtils.getprice(carListBean.getMileage()));
        viewHolder.tvSecond4.setText("车主报价： "+PriceUtils.getprice(carListBean.getPrice()));
        int isNew = carListBean.getIsNew();
        if(isNew==1){
            viewHolder.tvSecond5.setVisibility(View.VISIBLE);
        }else {
            viewHolder.tvSecond5.setVisibility(View.GONE);
       }
      int mold = carListBean.getMold();
        switch (mold){
           case 2:
               viewHolder.ivSecondright.setImageResource(R.drawable.label2);
               viewHolder.tvItemDown.setText("急降");
               viewHolder.tvItemMoney.setVisibility(View.VISIBLE);
               String getprice = PriceUtils.getprice(carListBean.getMoney());
               viewHolder.tvItemMoney.setText(getprice);
               break;
           case 1:
               viewHolder.ivSecondright.setImageResource(R.drawable.label1);
               viewHolder.tvItemDown.setText("新上架");
               viewHolder.tvItemMoney.setVisibility(View.GONE);
               break;
       }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SecondInfoActivity.class);
                intent.putExtra(MyString.INTENTINSPECTID,carListBean);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (data != null && data.size() > 0) {
            return data.size();
        }
        return 0;
    }

    class SecondViewHolder extends RecyclerView.ViewHolder {
        ImageView ivTseond;
        TextView tvSecond1;
        TextView tvSecond2;
        TextView tvSecond3;
        TextView tvSecond4;
        ImageView ivSecondright;
        TextView tvItemDown;
        TextView tvItemMoney;
        TextView tvSecond5;
        SecondViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            ivTseond = itemView.findViewById(R.id.iv_tseond);
            tvSecond1 = itemView.findViewById(R.id.tv_second1);
            tvSecond2 = itemView.findViewById(R.id.tv_second2);
            tvSecond3 = itemView.findViewById(R.id.tv_second3);
            tvSecond4 = itemView.findViewById(R.id.tv_second4);
            tvSecond5 = itemView.findViewById(R.id.tv_second5);
            ivSecondright = itemView.findViewById(R.id.iv_secondright);
            tvItemDown = itemView.findViewById(R.id.tv_item_down);
            tvItemMoney = itemView.findViewById(R.id.tv_item_money);
        }
    }
}
