package com.freeintelligence.robotclient.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.ui.moudel.VipBean;
import com.freeintelligence.robotclient.utils.DateUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by za on 2018/6/20.
 */

public class IntegralAdapter extends RecyclerView.Adapter<IntegralAdapter.IntegralViewHolder> {

    private List<VipBean.DataBean.ScoreListBean> mList;
    private Context context;
    public IntegralAdapter(Context context, List<VipBean.DataBean.ScoreListBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public IntegralViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_integral, parent, false);
        IntegralViewHolder viewHolder = new IntegralViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IntegralViewHolder viewHolder, int position) {
        VipBean.DataBean.ScoreListBean scoreListBean = mList.get(position);
        Log.i("",scoreListBean.toString());
        long date = scoreListBean.getDate();
        String time = DateUtils.getDateToString(date,"yyyy-MM-dd");
        viewHolder.tvIntegral1.setText(time);
        viewHolder.tvIntegral2.setText(scoreListBean.getContent());
    }

    @Override
    public int getItemCount() {

        return mList == null ? 0 : mList.size();
    }

    class IntegralViewHolder extends RecyclerView.ViewHolder {
        TextView tvIntegral1;
        TextView tvIntegral2;
        IntegralViewHolder(View itemView) {
            super(itemView);
            tvIntegral1 = itemView.findViewById(R.id.tv_integral1);
            tvIntegral2 = itemView.findViewById(R.id.tv_integral2);
        }
    }
}
