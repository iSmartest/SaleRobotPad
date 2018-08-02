package com.freeintelligence.robotclient.ui.moudel;

/**
 * Created by za on 2018/7/11.
 */

public class UpkeepBean {

    /**
     * data : {}
     * resultCode : 1
     * msg : 车牌号非法
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
    }
}
