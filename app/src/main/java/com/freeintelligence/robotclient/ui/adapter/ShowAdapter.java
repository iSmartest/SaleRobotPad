package com.freeintelligence.robotclient.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.ui.moudel.ShowBean;
import com.freeintelligence.robotclient.utils.GlideUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by za on 2018/6/19.
 */

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ShowViewHolder> {

    private Context context;
    private List<ShowBean.DataBean.CarProductBean> showlist;

    public ShowAdapter(Context context, List<ShowBean.DataBean.CarProductBean> showlist) {
        this.context = context;
        this.showlist = showlist;
    }

    @NonNull
    @Override
    public ShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show, parent, false);
        ShowViewHolder viewHolder = new ShowViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShowViewHolder viewHolder, int position) {
        ShowBean.DataBean.CarProductBean carProductBean = showlist.get(position);
        GlideUtils.imageLoader(context,carProductBean.getPic(),viewHolder.ivShow);
        viewHolder.tvShowmo.setText(carProductBean.getPrice()+"元");
        viewHolder.tvShowname.setText(carProductBean.getIntroduce());
    }

    @Override
    public int getItemCount() {
        return showlist == null ? 0 : showlist.size();
    }

    class ShowViewHolder extends RecyclerView.ViewHolder {
        ImageView ivShow;
        TextView tvShowmo;
        TextView tvShowname;
        ShowViewHolder(View itemView) {
            super(itemView);
            ivShow = itemView.findViewById(R.id.iv_show);
            tvShowmo = itemView.findViewById(R.id.tv_showmo);
            tvShowname = itemView.findViewById(R.id.tv_showname);
        }
    }

}
