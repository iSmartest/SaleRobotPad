package com.freeintelligence.robotclient.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.freeintelligence.robotclient.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by za on 2018/6/26.
 */

public class TableAdapter extends BaseAdapter {
    private Context context;
    private List<List<String>> listList;
    private String[] tanlename = new String[]{"基本参数", "发动机参数", "地盘及制动", "安全配置", "外部配置", "内部配置"};
    private String[][] table = new String[][]{{"证件品牌型号", "厂商", "品牌", "发动机", "变速箱", "车身结构", "长*宽*高(mm)", "轴距", "行李箱容积(L)", "整车重量(kg)"}, {"排量(L)", "进气形式", "气缸", "最大马力(ps)", "最大扭矩(N*m)", "燃料类型", "燃油标号", "供油方式", "排放标准", ""}, {"驱动方式", "助力类型", "前悬挂类型", "后悬挂类型", "前制动类型", "后制动类型", "驻车制动类型", "前轮胎规格", "后轮胎规格", ""}, {"主副驾驶安全气囊", "前后排侧气囊", "前后排头部气囊", "胎压检测", "车内中控锁", "儿童座椅接口", "无钥匙启动", "防抱死系统(ABS)", "车身稳定控制(ESP)", ""}, {"电动天窗", "全景天窗", "电动吸合门", "感应后备箱", "感应雨刷", "后雨刷", "前后电动车窗", "后视镜电动调节", "后视镜加热", ""}, {"多功能方向盘", "定速巡航", "空调", "自动空调", "GPS导航", "倒车雷达", "倒车影像系统", "真皮座椅", "前后排座椅加热", ""}};

    public TableAdapter(Context context, List<List<String>> listList) {
        this.context = context;
        this.listList = listList;
    }

    @Override
    public int getCount() {
        return listList == null ? 0 : listList.size();
    }

    @Override
    public Object getItem(int position) {

        return listList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TableViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_tabler, parent, false);
            viewHolder = new TableViewHolder();
            viewHolder.tvtb = convertView.findViewById(R.id.tv_table);
            viewHolder.listView = convertView.findViewById(R.id.lv_table1);
            convertView.setTag(viewHolder);
            AutoUtils.autoSize(convertView);
        }else {
            viewHolder = (TableViewHolder) convertView.getTag();
        }
        viewHolder.tvtb.setText(tanlename[position]);
        List<String> strings = listList.get(position);
        TableParamsAdapter tableParamsAdapter = new TableParamsAdapter(context, strings, table[position]);
        viewHolder.listView.setAdapter(tableParamsAdapter);
        return convertView;
    }


    class TableViewHolder{
        TextView tvtb;
        ListView listView;
    }

}
