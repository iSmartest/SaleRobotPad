package com.freeintelligence.robotclient.ui.moudel;

/**
 * Created by za on 2018/7/13.
 */

public class LoadcodeBean {

    /**
     * data : {"sessionId":"7P7sJiRLQ539XFUexey3js"}
     * resultCode : 0
     * msg : 发送成功
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
        /**
         * sessionId : 7P7sJiRLQ539XFUexey3js
         */

        private String sessionId;

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }
    }
}
