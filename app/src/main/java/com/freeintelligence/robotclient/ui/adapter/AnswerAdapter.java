package com.freeintelligence.robotclient.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.ui.moudel.QuestionBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/7/31.
 * Description:
 */
public class AnswerAdapter extends BaseAdapter {
    private Context context;
    private List<QuestionBean.AnswerBean> mList;
    public AnswerAdapter(Context context, List<QuestionBean.AnswerBean> mList) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final AnswerViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_answer,null);
            viewHolder = new AnswerViewHolder();
            viewHolder.checkBox = convertView.findViewById(R.id.ck_answer);
            convertView.setTag(viewHolder);
            AutoUtils.autoSize(convertView);
        }else {
            viewHolder = (AnswerViewHolder) convertView.getTag();
        }
        final QuestionBean.AnswerBean answerList = mList.get(position);
        viewHolder.checkBox.setChecked(answerList.isChoosed());
        viewHolder.checkBox.setText(answerList.getAnswer());
        viewHolder.checkBox.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        answerList.setChoosed(((CheckBox) v).isChecked());
                        if (viewHolder.checkBox.isChecked()){
                            answerList.setPositon(1);
                        }else {
                            answerList.setPositon(0);
                        }
                    }
                }
        );
        return convertView;
    }

    class AnswerViewHolder {
        CheckBox checkBox;
    }
}
