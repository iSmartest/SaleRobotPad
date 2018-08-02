package com.freeintelligence.robotclient.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.ui.activity.PichotcarActivity;
import com.freeintelligence.robotclient.app.App;
import com.freeintelligence.robotclient.utils.GlideUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by za on 2018/7/16.
 */

public class PicHotCarDesAdapter extends RecyclerView.Adapter<PicHotCarDesAdapter.PicHotCarDesViewHodler> {

    private List<String> list;
    private Context context;

    public PicHotCarDesAdapter(Context context, List<String> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public PicHotCarDesViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pichotcardeatial, parent, false);
        PicHotCarDesViewHodler viewHodler = new PicHotCarDesViewHodler(view);
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull PicHotCarDesViewHodler viewHodler, final int position) {
        String uri = list.get(position);
        GlideUtils.imageLoader(context,uri,viewHodler.ivPic);
        viewHodler.ivPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PichotcarActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("pic", (Serializable) list);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        }
        return 0;
    }

    class PicHotCarDesViewHodler extends RecyclerView.ViewHolder {
        ImageView ivPic;

        public PicHotCarDesViewHodler(View itemView) {
            super(itemView);
            ivPic = itemView.findViewById(R.id.iv_pichotcardeatial);
        }
    }
}
