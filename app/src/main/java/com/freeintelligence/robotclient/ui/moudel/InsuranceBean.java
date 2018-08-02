package com.freeintelligence.robotclient.ui.moudel;

import java.util.List;

/**
 * Created by za on 2018/7/2.
 */

public class InsuranceBean {

    /**
     * data : {"repairList":[{"date":1530062169000,"records":["张献忠进行了保险！！！"],"name":"中国人寿","items":"全险 强险 局部险 "},{"date":1529545290000,"records":[],"name":"中国人寿","items":"全险 强险 局部险 强险 "}]}
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
        private List<RepairListBean> repairList;

        public List<RepairListBean> getRepairList() {
            return repairList;
        }

        public void setRepairList(List<RepairListBean> repairList) {
            this.repairList = repairList;
        }

        public static class RepairListBean {
            /**
             * date : 1530062169000
             * records : ["张献忠进行了保险！！！"]
             * name : 中国人寿
             * items : 全险 强险 局部险
             */

            private long date;
            private String name;
            private String items;
            private List<String> records;

            public long getDate() {
                return date;
            }

            public void setDate(long date) {
                this.date = date;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getItems() {
                return items;
            }

            public void setItems(String items) {
                this.items = items;
            }

            public List<String> getRecords() {
                return records;
            }

            public void setRecords(List<String> records) {
                this.records = records;
            }
        }
    }
}
