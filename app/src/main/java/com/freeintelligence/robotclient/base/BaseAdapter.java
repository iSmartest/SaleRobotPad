package com.freeintelligence.robotclient.base;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private LayoutInflater mInflater;

    public BaseAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public LayoutInflater getInflater() {
        return mInflater;
    }


}