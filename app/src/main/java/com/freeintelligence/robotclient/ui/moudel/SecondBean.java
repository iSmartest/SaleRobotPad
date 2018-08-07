package com.freeintelligence.robotclient.ui.moudel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by za on 2018/6/29.
 */

public class SecondBean implements Serializable{


    /**
     * data : {"mpv":[{"money":1000,"price":60000,"brightPoint":[],"name":"奥迪A6","mold":2,"id":2,"time":656784000000,"isNew":0,"mileage":1000},{"money":0,"price":185,"brightPoint":["和顺,和顺1,平稳"],"name":"奥迪A9L","mold":1,"id":18,"time":1531929600000,"isNew":0,"mileage":11000},{"money":111,"price":185,"brightPoint":["和顺,平稳,平稳"],"name":"TT","mold":2,"id":19,"time":1531929600000,"isNew":0,"mileage":11}],"suv":[{"money":0,"price":10000,"brightPoint":[],"name":"奥迪A9","mold":1,"id":1,"time":659894400000,"isNew":1,"mileage":1000},{"money":2,"price":185,"brightPoint":[],"name":"Q7","mold":2,"id":3,"time":817660800000,"isNew":0,"mileage":11000},{"money":11000,"price":185,"brightPoint":[],"name":"奥迪A8","mold":2,"id":8,"time":657993600000,"isNew":0,"mileage":11},{"money":11000,"price":185,"brightPoint":["安全,平稳,和顺"],"name":"q8","mold":2,"id":14,"time":1531929600000,"isNew":0,"mileage":11000},{"money":0,"price":185,"brightPoint":["和顺,,"],"name":"奥迪A8","mold":1,"id":17,"time":1531929600000,"isNew":0,"mileage":11},{"money":11,"price":185,"brightPoint":["和顺1,111,111"],"name":"TT1","mold":2,"id":20,"time":1531929600000,"isNew":0,"mileage":11000},{"money":0,"price":185,"brightPoint":["和顺,和顺,和顺"],"name":"奥迪A8q","mold":1,"id":21,"time":1530633600000,"isNew":0,"mileage":11},{"money":1,"price":23,"brightPoint":["和顺,和顺1,和顺1"],"name":"A11","mold":2,"id":24,"time":1532275200000,"isNew":0,"mileage":11000}],"jiaoChe":[{"money":1000,"price":18,"brightPoint":[],"name":"A4L","mold":2,"id":5,"time":659980800000,"isNew":0,"mileage":1000},{"money":555,"price":185,"brightPoint":[],"name":"奥迪A7","mold":2,"id":7,"time":659894400000,"isNew":0,"mileage":11},{"money":1222,"price":185,"brightPoint":["和顺1,2,"],"name":"奥迪A8L","mold":2,"id":15,"time":1531929600000,"isNew":0,"mileage":11000},{"money":0,"price":185,"brightPoint":["和顺1,和顺,"],"name":"A9L","mold":1,"id":22,"time":1532016000000,"isNew":0,"mileage":111}],"paoChe":[{"money":100,"price":23,"brightPoint":["和顺,安全,便宜"],"name":"a1L","mold":2,"id":23,"time":1532275200000,"isNew":0,"mileage":1}]}
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

    public static class DataBean implements Serializable {
        private List<CarBean> mpv;
        private List<CarBean> suv;
        private List<CarBean> jiaoChe;
        private List<CarBean> paoChe;

        public List<CarBean> getMpv() {
            return mpv;
        }

        public void setMpv(List<CarBean> mpv) {
            this.mpv = mpv;
        }

        public List<CarBean> getSuv() {
            return suv;
        }

        public void setSuv(List<CarBean> suv) {
            this.suv = suv;
        }

        public List<CarBean> getJiaoChe() {
            return jiaoChe;
        }

        public void setJiaoChe(List<CarBean> jiaoChe) {
            this.jiaoChe = jiaoChe;
        }

        public List<CarBean> getPaoChe() {
            return paoChe;
        }

        public void setPaoChe(List<CarBean> paoChe) {
            this.paoChe = paoChe;
        }

        public static class CarBean implements Serializable{
            /**
             * money : 1000.0
             * price : 60000.0
             * brightPoint : []
             * name : 奥迪A6
             * mold : 2
             * id : 2
             * time : 656784000000
             * isNew : 0
             * mileage : 1000.0
             */

            private double money;
            private double price;
            private String name;
            private String images;
            private String image;
            private int mold;
            private int id;
            private long time;
            private int isNew;
            private double mileage;
            private List<String> brightPoint;

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getMold() {
                return mold;
            }

            public void setMold(int mold) {
                this.mold = mold;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getIsNew() {
                return isNew;
            }

            public void setIsNew(int isNew) {
                this.isNew = isNew;
            }

            public double getMileage() {
                return mileage;
            }

            public void setMileage(double mileage) {
                this.mileage = mileage;
            }

            public List<String> getBrightPoint() {
                return brightPoint;
            }

            public void setBrightPoint(List<String> brightPoint) {
                this.brightPoint = brightPoint;
            }
        }
    }
}
