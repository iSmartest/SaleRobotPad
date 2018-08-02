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

import com.bumptech.glide.Glide;
import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.ui.activity.DetailsActivity;
import com.freeintelligence.robotclient.config.MyString;
import com.freeintelligence.robotclient.ui.moudel.BriefBean;
import com.freeintelligence.robotclient.utils.GlideUtils;

import java.util.List;

/**
 * Created by za on 2018/6/19.
 */

public class BriefAdapter extends RecyclerView.Adapter<BriefAdapter.BriefViewHolder> {
    private Context context;
    private List<BriefBean.DataBean.CarVideoBean> mList;

    public BriefAdapter(Context context, List<BriefBean.DataBean.CarVideoBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public BriefViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_explain, parent, false);
        BriefViewHolder viewHolder = new BriefViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull BriefViewHolder holder, int position) {
        final BriefBean.DataBean.CarVideoBean carVideoBean = mList.get(position);
        GlideUtils.imageLoader(context,carVideoBean.getImg(),holder.ivBrief);
        holder.tvBrief.setText(carVideoBean.getName()+carVideoBean.getIdea());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra(MyString.INTENTIBRIEFADRESS,carVideoBean.getAddress());
                context.startActivity(intent);
            }
        });
    }

    class BriefViewHolder extends RecyclerView.ViewHolder {
        ImageView ivBrief;
        TextView tvBrief;
        BriefViewHolder(View itemView) {
            super(itemView);
            ivBrief = itemView.findViewById(R.id.iv_brief);
            tvBrief = itemView.findViewById(R.id.tv_brief);
        }
    }
}
