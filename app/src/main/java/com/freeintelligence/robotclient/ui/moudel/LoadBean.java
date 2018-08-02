package com.freeintelligence.robotclient.ui.moudel;

import java.io.Serializable;

/**
 * Created by za on 2018/7/10.
 */

public class LoadBean {

    /**
     * data : {"score":1850,"phone":"18332212560","name":"李亚辉","id":1,"pic":"15311847218602166.png"}
     * resultCode : 0
     * msg : 登录成功
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

    public static class DataBean implements Serializable{
        /**
         * score : 1850
         * phone : 18332212560
         * name : 李亚辉
         * id : 1
         * pic : 15311847218602166.png
         */

        private int score;
        private String phone;
        private String name;
        private int id;
        private String pic;

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
