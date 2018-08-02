package com.freeintelligence.robotclient.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freeintelligence.robotclient.R;

/**
 * Created by za on 2018/6/19.
 */

public class NewCarInfoRecyclerViewAdapter extends RecyclerView.Adapter<NewCarInfoRecyclerViewAdapter.NewCarInfoViewHolder>{
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private View mHeaderView;

    public NewCarInfoRecyclerViewAdapter() {

    }

    @Override
    public int getItemViewType(int position) {
        if(mHeaderView == null) {
            return TYPE_NORMAL;
        }
        if(position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_NORMAL;
    }
    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }


    @NonNull
    @Override
    public NewCarInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mHeaderView != null && viewType == TYPE_HEADER){
            return new NewCarInfoViewHolder(mHeaderView);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot, parent, false);
        NewCarInfoViewHolder viewHolder = new NewCarInfoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewCarInfoViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_HEADER) return;
    }

    @Override
    public int getItemCount() {
        return 18;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    class NewCarInfoViewHolder extends RecyclerView.ViewHolder {
        NewCarInfoViewHolder(View itemView) {
            super(itemView);

        }
    }
}
