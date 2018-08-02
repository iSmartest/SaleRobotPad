package com.freeintelligence.robotclient.ui.moudel;

import java.util.List;

/**
 * Created by za on 2018/7/11.
 */

public class QuestionBean {
    private String question;
    private List<AnswerBean> answerList;
    public static class AnswerBean{
        private String  answer;
        public boolean isChoosed;
        private int positon;

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public boolean isChoosed() {
            return isChoosed;
        }

        public void setChoosed(boolean choosed) {
            isChoosed = choosed;
        }

        public int getPositon() {
            return positon;
        }

        public void setPositon(int positon) {
            this.positon = positon;
        }
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<AnswerBean> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnswerBean> answerList) {
        this.answerList = answerList;
    }
}
