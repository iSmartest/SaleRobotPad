package com.freeintelligence.robotclient.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freeintelligence.robotclient.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by za on 2018/7/25.
 */

public class RedsellingAdapter extends RecyclerView.Adapter<RedsellingAdapter.RedSellingViewHolder> {
    private Context context;
    private List<String> brightPoint;

    public RedsellingAdapter(Context context,List<String> brightPoint) {
        this.context = context;
        this.brightPoint = brightPoint;
    }

    @NonNull
    @Override
    public RedSellingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_redselling, parent, false);
        RedSellingViewHolder viewHolder = new RedSellingViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RedSellingViewHolder viewHolder, int position) {
        viewHolder.tvSelling.setText((position+1) + "、"+brightPoint.get(position));
    }

    @Override
    public int getItemCount() {

        return brightPoint == null ? 0 : brightPoint.size();
    }

    class RedSellingViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_adapterselling)
        TextView tvSelling;
        public RedSellingViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            tvSelling = itemView.findViewById(R.id.tv_adapterselling);
        }
    }
}
