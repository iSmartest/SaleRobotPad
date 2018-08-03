package com.freeintelligence.robotclient.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.ui.activity.ConsultActivity;
import com.freeintelligence.robotclient.ui.activity.HotcarActivity;
import com.freeintelligence.robotclient.app.App;
import com.freeintelligence.robotclient.config.MyString;
import com.freeintelligence.robotclient.ui.moudel.HotcarBean;
import com.freeintelligence.robotclient.utils.GlideUtils;
import com.freeintelligence.robotclient.view.MyRecyclerView;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.freeintelligence.robotclient.config.Url.HTTP;

/**
 * Created by za on 2018/6/20.
 */

public class HotCarAdapter extends RecyclerView.Adapter<HotCarAdapter.HotCarViewHolder> {

    private Context context;
    private List<HotcarBean.DataBean.CarBean> mList;

    public HotCarAdapter(Context context, List<HotcarBean.DataBean.CarBean> mList) {
        this.context = context;
        this.mList = mList;
    }


    @NonNull
    @Override
    public HotCarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot, parent, false);
        HotCarViewHolder viewHolder = new HotCarViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final HotCarViewHolder viewHolder, int position) {
        final HotcarBean.DataBean.CarBean carListBean = mList.get(position);
        GlideUtils.imageLoader(context,carListBean.getPic(),viewHolder.ivNewCar);
        viewHolder.tvHotCarName.setText(carListBean.getName().concat(" ").concat(carListBean.getIdea()));
        viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        BrightPointAdapter brightPointAdapter = new BrightPointAdapter(context,carListBean.getBrightPoint());
        viewHolder.recyclerView.setAdapter(brightPointAdapter);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ConsultActivity.class);
                intent.putExtra(MyString.INTENTHOTCAR, carListBean.getId());
                intent.putExtra(MyString.CONSULT, 2);
                context.startActivity(intent);
            }
        });
        viewHolder.recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    viewHolder.itemView.performClick();
                }
                return false;
            }

        });
    }


    class HotCarViewHolder extends RecyclerView.ViewHolder {
        ImageView ivNewCar;
        TextView tvHotCarName;
        RecyclerView recyclerView;
        HotCarViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            ivNewCar = itemView.findViewById(R.id.iv_newcar);
            tvHotCarName = itemView.findViewById(R.id.tv_hotcarname);
            recyclerView = itemView.findViewById(R.id.rv_hotcaradapter);

        }
    }

}
