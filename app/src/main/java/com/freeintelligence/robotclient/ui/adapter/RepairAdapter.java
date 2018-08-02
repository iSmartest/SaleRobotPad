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
import com.freeintelligence.robotclient.ui.moudel.Inspect2Bean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by za on 2018/6/20.
 */

public class RepairAdapter extends RecyclerView.Adapter<RepairAdapter.RepairViewHolder> {
    private Context context;
    private List<Inspect2Bean.DataBean.RepairListBean> mList;
    private int type;
    private int cid;

    public RepairAdapter(Context context, List<Inspect2Bean.DataBean.RepairListBean> mList, int cid, int type) {
        this.context = context;
        this.mList = mList;
        this.type = type;
        this.cid = cid;
    }

    @NonNull
    @Override
    public RepairViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_maintain, parent, false);
        RepairViewHolder viewHolder = new RepairViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RepairViewHolder viewHolder, int position) {
        final Inspect2Bean.DataBean.RepairListBean maintainListBean = mList.get(position);
        viewHolder.tvContent.setText(maintainListBean.getContent());
        viewHolder.tvDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InspectdetailsActivity.class);
                intent.putExtra(MyString.INTENTINSPECTID, maintainListBean.getId());
                intent.putExtra(MyString.INTENTINSPECTTYPE, type);
                intent.putExtra(MyString.CUSTOMERID, cid);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    class RepairViewHolder extends RecyclerView.ViewHolder {
        TextView tvContent;
        TextView tvDes;

        RepairViewHolder(View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_mtcontent);
            tvDes = itemView.findViewById(R.id.tv_mtdetails);
        }
    }
}
