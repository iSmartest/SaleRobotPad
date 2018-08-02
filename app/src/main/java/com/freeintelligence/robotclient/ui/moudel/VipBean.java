package com.freeintelligence.robotclient.ui.moudel;

import java.util.List;

/**
 * Created by za on 2018/6/30.
 */

public class VipBean {


    /**
     * data : {"scoreList":[{"date":1530326803000,"content":"张先生洗车获得了100个积分。"}]}
     * resultCode : 0
     * msg : 查询成功
     */

    private DataBean data;
    private int resultCode;
    private String msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private List<ScoreListBean> scoreList;

        public List<ScoreListBean> getScoreList() {
            return scoreList;
        }

        public void setScoreList(List<ScoreListBean> scoreList) {
            this.scoreList = scoreList;
        }

        public static class ScoreListBean {
            /**
             * date : 1530326803000
             * content : 张先生洗车获得了100个积分。
             */

            private long date;
            private String content;

            public long getDate() {
                return date;
            }

            public void setDate(long date) {
                this.date = date;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
