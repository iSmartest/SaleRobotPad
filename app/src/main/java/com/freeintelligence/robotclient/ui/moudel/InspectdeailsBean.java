package com.freeintelligence.robotclient.ui.moudel;

import java.util.List;

/**
 * Created by za on 2018/6/30.
 */

public class InspectdeailsBean {

    /**
     * data : {"common":{"carName":"奥迪A4","carPhone":"000001","mileage":1000},"itemList":["全险","强险"]}
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
        /**
         * common : {"carName":"奥迪A4","carPhone":"000001","mileage":1000}
         * itemList : ["全险","强险"]
         */

        private CommonBean common;
        private List<String> itemList;

        public CommonBean getCommon() {
            return common;
        }

        public void setCommon(CommonBean common) {
            this.common = common;
        }

        public List<String> getItemList() {
            return itemList;
        }

        public void setItemList(List<String> itemList) {
            this.itemList = itemList;
        }

        public static class CommonBean {
            /**
             * carName : 奥迪A4
             * carPhone : 000001
             * mileage : 1000
             */

            private String carName;
            private String carPhone;
            private int mileage;

            public String getCarName() {
                return carName;
            }

            public void setCarName(String carName) {
                this.carName = carName;
            }

            public String getCarPhone() {
                return carPhone;
            }

            public void setCarPhone(String carPhone) {
                this.carPhone = carPhone;
            }

            public int getMileage() {
                return mileage;
            }

            public void setMileage(int mileage) {
                this.mileage = mileage;
            }
        }
    }
}
