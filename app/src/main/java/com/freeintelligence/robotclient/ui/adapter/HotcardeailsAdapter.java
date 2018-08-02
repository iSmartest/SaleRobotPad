package com.freeintelligence.robotclient.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.ui.activity.HotcardeailsActivity;
import com.freeintelligence.robotclient.ui.moudel.HotcarBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by za on 2018/6/30.
 */

public class HotcardeailsAdapter extends RecyclerView.Adapter {


    private Context context;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotcardeatil, parent, false);
        ViewHodler viewHodler = new ViewHodler(view);
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {

        return 0;
    }

    class ViewHodler extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_ahotcardeatials)
        ImageView ivAhotcardeatials;
        @BindView(R.id.tv_ahotcardeatials)
        TextView tvAhotcardeatials;
        public ViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
