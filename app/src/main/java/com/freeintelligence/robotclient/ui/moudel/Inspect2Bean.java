package com.freeintelligence.robotclient.ui.moudel;

import java.util.List;

/**
 * Created by za on 2018/6/30.
 */

public class Inspect2Bean  {
    /**
     * data : {"repairList":[{"date":1532689217,"pohone":"15313723329","name":"闫孝忱","id":8,"content":"欧克"},{"date":1532689215,"pohone":"15313723329","name":"闫孝忱","id":7,"content":"vip"}]}
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
             * date : 1532689217
             * pohone : 15313723329
             * name : 闫孝忱
             * id : 8
             * content : 欧克
             */

            private int date;
            private String pohone;
            private String name;
            private int id;
            private String content;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public String getPohone() {
                return pohone;
            }

            public void setPohone(String pohone) {
                this.pohone = pohone;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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
