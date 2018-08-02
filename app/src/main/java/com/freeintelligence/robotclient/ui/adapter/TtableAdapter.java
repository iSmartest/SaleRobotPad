package com.freeintelligence.robotclient.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.base.BaseActivity;
import com.freeintelligence.robotclient.base.BaseAdapter;

import java.util.List;

/**
 * Created by za on 2018/6/26.
 */

class TtableAdapter  extends android.widget.BaseAdapter{
    private Context context;
    private  List<String> listList;
    private String[] strings;
    public TtableAdapter(Context context, List<String> listList, String[] strings) {
        this.context=context;
        this.listList=listList;
        this.strings=strings;
    }

    @Override
    public int getCount() {
      if(listList!=null&&listList.size()>0){
         return  10;
      }
      return 0;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_textview, null);
            //查找控件，保存控件的引用
            TextView tv1 = ((TextView) convertView.findViewById(R.id.tv_ttable1));
            TextView tv2 = ((TextView) convertView.findViewById(R.id.tv_ttable2));

          if(listList.size()<10&&i==9){
               tv1.setText(strings[i]);
               tv2.setText("");
           }else {
              tv1.setText(strings[i]);
              tv2.setText(listList.get(i));
          }

         return convertView;
    }

}
