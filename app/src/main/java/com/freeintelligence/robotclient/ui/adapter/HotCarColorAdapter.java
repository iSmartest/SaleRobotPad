package com.freeintelligence.robotclient.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.app.App;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by za on 2018/7/13.
 */

public class HotCarColorAdapter extends RecyclerView.Adapter<HotCarColorAdapter.HotCarColorViewHolder> {
    private List<String> colorlist;
    private Context context;
    public HotCarColorAdapter(Context context, List<String> colorlist) {
        this.context = context;
        this.colorlist = colorlist;
    }

    @NonNull
    @Override
    public HotCarColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_color, parent, false);
        HotCarColorViewHolder viewHolder = new HotCarColorViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HotCarColorViewHolder viewHolder, int position) {
        String s = colorlist.get(position);
        switch (s) {
            case "1":
                viewHolder.ivColor.setBackgroundResource(R.color.carcolor1);
                break;
            case "2":
                viewHolder.ivColor.setBackgroundResource(R.drawable.shape_sideline);
                break;
            case "3":
                viewHolder.ivColor.setBackgroundResource(R.color.carcolor3);
                break;
            case "4":
                viewHolder.ivColor.setBackgroundResource(R.color.carcolor5);
                break;
        }
    }


    @Override
    public int getItemCount() {
        return colorlist == null ? 0 : colorlist.size();
    }

    class HotCarColorViewHolder extends RecyclerView.ViewHolder {
        ImageView ivColor;
        HotCarColorViewHolder(View itemView) {
            super(itemView);
            ivColor = itemView.findViewById(R.id.iv_color);
        }
    }
}
