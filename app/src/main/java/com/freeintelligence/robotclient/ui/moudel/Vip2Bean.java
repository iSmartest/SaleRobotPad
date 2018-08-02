package com.freeintelligence.robotclient.ui.moudel;

import java.util.List;

/**
 * Created by za on 2018/7/3.
 */

public class Vip2Bean {


    /**
     * data : {"consumptionList":[{"date":1530201600000,"money":1000,"content":"张先生清洗发动机花了1000元"},{"date":1530260200000,"money":1000,"content":"张先生清洗发动机花了1000元"}]}
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
        private List<ConsumptionListBean> consumptionList;

        public List<ConsumptionListBean> getConsumptionList() {
            return consumptionList;
        }

        public void setConsumptionList(List<ConsumptionListBean> consumptionList) {
            this.consumptionList = consumptionList;
        }

        public static class ConsumptionListBean {
            /**
             * date : 1530201600000
             * money : 1000.0
             * content : 张先生清洗发动机花了1000元
             */

            private long date;
            private double money;
            private String content;

            public long getDate() {
                return date;
            }

            public void setDate(long date) {
                this.date = date;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
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
