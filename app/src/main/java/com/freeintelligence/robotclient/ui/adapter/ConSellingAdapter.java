package com.freeintelligence.robotclient.ui.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.ui.activity.SellingshowActivity;
import com.freeintelligence.robotclient.ui.activity.VideoActivity;
import com.freeintelligence.robotclient.app.App;
import com.freeintelligence.robotclient.config.MyString;
import com.freeintelligence.robotclient.ui.moudel.ConsultBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;


/**
 * Created by za on 2018/7/25.
 */

public class ConSellingAdapter extends RecyclerView.Adapter<ConSellingAdapter.ConSellingViewHolder> {

    private List<ConsultBean.DataBean.BrightPointBean> brightPoint;

    public ConSellingAdapter(List<ConsultBean.DataBean.BrightPointBean> brightPoint) {
        this.brightPoint = brightPoint;
    }

    @NonNull
    @Override
    public ConSellingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_conselling, parent, false);
        ConSellingViewHolder viewHolder = new ConSellingViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ConSellingViewHolder viewHolder, int position) {
        final ConsultBean.DataBean.BrightPointBean brightPointBean = brightPoint.get(position);
        viewHolder.tvConselling.setText(brightPointBean.getSellPoint());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int type = brightPointBean.getType();
                switch (type){
                    case 1:
                        Intent intent = new Intent(App.activity, SellingshowActivity.class);
                        intent.putExtra("selling",brightPointBean);
                        App.activity.startActivity(intent);
                        break;
                    case 2:
                        Intent intent1 = new Intent(App.activity, SellingshowActivity.class);
                        intent1.putExtra("selling",brightPointBean);
                        App.activity.startActivity(intent1);
                        break;

                    case 3:
                        Intent intent2 = new Intent(App.activity, VideoActivity.class);
                        intent2.putExtra(MyString.VIDEO,brightPointBean.getSellPointvid());
                        App.activity.startActivity(intent2);
                        break;

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (brightPoint != null && brightPoint.size() > 0) {
            return brightPoint.size();
        }
        return 0;
    }

    class ConSellingViewHolder extends RecyclerView.ViewHolder {
        TextView tvConselling;
        public ConSellingViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            tvConselling = itemView.findViewById(R.id.tv_conselling);
        }
    }
}
