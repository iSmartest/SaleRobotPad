package com.freeintelligence.robotclient.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.ui.activity.InspectdetailsActivity;
import com.freeintelligence.robotclient.config.MyString;
import com.freeintelligence.robotclient.ui.moudel.InspectBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by za on 2018/6/20.
 */

public class MaintainAdapter extends RecyclerView.Adapter<MaintainAdapter.MaintainViewHolder> {
    private Context context;
    private List<InspectBean.DataBean.MaintainListBean> mList;
    private int type;
    private int id;

    public MaintainAdapter(Context context, List<InspectBean.DataBean.MaintainListBean> mList, int id, int type) {
        this.context = context;
        this.mList = mList;
        this.type = type;
        this.id = id;
    }

    @NonNull
    @Override
    public MaintainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_maintain, parent, false);
        MaintainViewHolder viewHolder = new MaintainViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MaintainViewHolder viewHolder, int position) {

        final InspectBean.DataBean.MaintainListBean repairListBean = mList.get(position);
        viewHolder.tvContent.setText(repairListBean.getContent());
        viewHolder.tvDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InspectdetailsActivity.class);
                intent.putExtra(MyString.INTENTINSPECTID, repairListBean.getId());
                intent.putExtra(MyString.INTENTINSPECTTYPE, type);
                intent.putExtra(MyString.CUSTOMERID, id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    class MaintainViewHolder extends RecyclerView.ViewHolder {
        TextView tvContent;
        TextView tvDes;

        MaintainViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            tvContent = itemView.findViewById(R.id.tv_mtcontent);
            tvDes = itemView.findViewById(R.id.tv_mtdetails);
        }
    }
}
