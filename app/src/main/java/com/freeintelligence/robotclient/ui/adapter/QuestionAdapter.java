package com.freeintelligence.robotclient.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freeintelligence.robotclient.R;
import com.freeintelligence.robotclient.ui.moudel.QuestionBean;
import com.freeintelligence.robotclient.view.MyListView;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by za on 2018/7/11.
 */

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>{
    private List<QuestionBean> alllsit;
    private List<String>datalist;
    private Context context;

    public QuestionAdapter(Context context, List<QuestionBean> alllsit) {
        this.alllsit=alllsit;
        this.context=context;
        datalist=new ArrayList<>();
        datalist.clear();
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_question,parent,false);
        QuestionViewHolder viewHolder = new QuestionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        QuestionBean questionBean = alllsit.get(position);
        holder.mQuestion.setText(questionBean.getQuestion());
        AnswerAdapter answerAdapter = new AnswerAdapter(context,questionBean.getAnswerList());
        holder.myListView.setAdapter(answerAdapter);
    }


    @Override
    public int getItemCount() {
        return alllsit == null ? 0 : alllsit.size();
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView mQuestion;
        MyListView myListView;
        QuestionViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            mQuestion = itemView.findViewById(R.id.tv_question);
            myListView = itemView.findViewById(R.id.listView);

        }
    }

}
