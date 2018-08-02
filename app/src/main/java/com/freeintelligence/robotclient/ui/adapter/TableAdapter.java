package com.freeintelligence.robotclient.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.ui.moudel.SeconddeailsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by za on 2018/6/26.
 */

public class TableAdapter extends android.widget.BaseAdapter {
    private Context context;
    private SeconddeailsBean.DataBean dataBean;
    private List<List<String>> listList;
    private String[] tanlename = new String[]{"基本参数", "发动机参数", "地盘及制动", "安全配置", "外部配置", "内部配置"};
    private String[][] table = new String[][]{{"证件品牌型号", "厂商", "级别", "发动机", "变速箱", "车身结构", "长*宽*高(mm)", "轴距", "行李箱容积(L)", "整车重量(kg)"}, {"排量(L)", "进气形式", "气缸", "最大马力(ps)", "最大扭矩(N*m)", "燃料类型", "燃油标号", "供油方式", "排放标准", ""}, {"驱动方式", "助力类型", "前悬挂类型", "后悬挂类型", "前制动类型", "后制动类型", "驻车制动类型", "前轮胎规格", "后轮胎规格", ""}, {"主副驾驶安全气囊", "前后排侧气囊", "前后排头部气囊", "胎压检测", "车内中控锁", "儿童座椅接口", "无钥匙启动", "防抱死系统(ABS)", "车身稳定控制(ESP)", ""}, {"电动天窗", "全景天窗", "电动吸合门", "感应后备箱", "感应雨刷", "后雨刷", "前后电动车窗", "后视镜电动调节", "后视镜加热", ""}, {"多功能方向盘", "定速巡航", "空调", "自动空调", "GPS导航", "倒车雷达", "倒车影像系统", "真皮座椅", "前后排座椅加热", ""}};

    public TableAdapter(Context context, SeconddeailsBean.DataBean dataBean) {
        this.context = context;
        this.dataBean = dataBean;
        this.listList = new ArrayList<>();
        listList.clear();
        listList.add(dataBean.getBasic());
        listList.add(dataBean.getMontor());
        listList.add(dataBean.getUnderPan());
        listList.add(dataBean.getSecure());
        listList.add(dataBean.getOuter());
        listList.add(dataBean.getInner());
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public Object getItem(int i) {

        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View thisview = LayoutInflater.from(context).inflate(R.layout.item_tabler, null, false);
        TextView tvtb = (TextView) thisview.findViewById(R.id.tv_table);
        tvtb.setText(tanlename[i]);
        ListView lvtb1 = (ListView) thisview.findViewById(R.id.lv_table1);
        List<String> strings = listList.get(i);
        TtableAdapter ttableAdapter = new TtableAdapter(context, strings, table[i]);
        lvtb1.setAdapter(ttableAdapter);
        return thisview;
    }
}
