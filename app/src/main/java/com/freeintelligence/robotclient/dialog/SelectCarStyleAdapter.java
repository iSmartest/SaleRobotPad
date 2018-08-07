package com.freeintelligence.robotclient.dialog;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.ui.moudel.CarpyteBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/7.
 * Description:
 */
public class SelectCarStyleAdapter extends BaseAdapter {
    private Context context;
    private List<CarpyteBean.DataBean.CarTypeListBean> mList;
    public SelectCarStyleAdapter(Context context, List<CarpyteBean.DataBean.CarTypeListBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SelectCarStyleViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_select_car_atyle,parent,false);
            viewHolder = new SelectCarStyleViewHolder();
            viewHolder.tvCarName = convertView.findViewById(R.id.tv_car_style_name);
            convertView.setTag(viewHolder);
            AutoUtils.autoSize(convertView);
        }else {
            viewHolder = (SelectCarStyleViewHolder) convertView.getTag();
        }
        viewHolder.tvCarName.setText(mList.get(position).getName());
        return convertView;
    }

    class SelectCarStyleViewHolder{
        TextView tvCarName;
    }
}
