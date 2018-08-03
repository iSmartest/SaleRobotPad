package com.freeintelligence.robotclient.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.ui.activity.PicSecondActivity;
import com.freeintelligence.robotclient.ui.activity.SecondInfoActivity;
import com.freeintelligence.robotclient.utils.GlideUtils;
import com.zhy.autolayout.utils.AutoUtils;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by za on 2018/7/16.
 */

public class PicSecondAdapter extends RecyclerView.Adapter<PicSecondAdapter.PicSecondViewHodler> {

    private List<String> list;
    private Context context;

    public PicSecondAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PicSecondViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_picsecond, parent, false);
        PicSecondViewHodler viewHodler = new PicSecondViewHodler(view);
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull PicSecondViewHodler viewHodler, final int position) {
        String uri = list.get(position);
        GlideUtils.imageLoader(context,uri,viewHodler.ivPicsd);
        viewHodler.ivPicsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PicSecondActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("pic", (Serializable) list);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class PicSecondViewHodler extends RecyclerView.ViewHolder {
        ImageView ivPicsd;

        public PicSecondViewHodler(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            ivPicsd = itemView.findViewById(R.id.iv_picsd);
        }
    }
}
