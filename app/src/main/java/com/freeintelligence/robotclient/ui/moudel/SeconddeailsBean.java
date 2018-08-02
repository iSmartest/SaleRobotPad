package com.freeintelligence.robotclient.ui.moudel;

import java.util.List;

/**
 * Created by za on 2018/6/29.
 */

public class SeconddeailsBean {

    /**
     * data : {"common":{"date":1530066349000,"istransfer":0,"isAccident":"无重大事故","isFire":"无火烧事故","carName":"奥迪A9","city":"北京","pic":"/upload/24343543.jpg","isWater":"无水淹事故","outPut":2,"phone":"18332212560","price":10000,"name":"黄蓉","guideprice":15000,"pics":["/upload/24343543.jpg","/upload/24343543.jpg"],"mileage":1000,"ismaintain":1},"outer":["38","39","40","41","42","43","44","45","46"],"underPan":["20","21","22","23","24","25","26","27","28"],"montor":["11","12","13","14","15","16","17","18","19"],"basic":["1","2","微型车","4","5","6","7","8","9","10"],"secure":["29","30","31","32","33","34","35","36","37"],"inner":["47","48","49","50","51","52","53","54","55"]}
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
         * common : {"date":1530066349000,"istransfer":0,"isAccident":"无重大事故","isFire":"无火烧事故","carName":"奥迪A9","city":"北京","pic":"/upload/24343543.jpg","isWater":"无水淹事故","outPut":2,"phone":"18332212560","price":10000,"name":"黄蓉","guideprice":15000,"pics":["/upload/24343543.jpg","/upload/24343543.jpg"],"mileage":1000,"ismaintain":1}
         * outer : ["38","39","40","41","42","43","44","45","46"]
         * underPan : ["20","21","22","23","24","25","26","27","28"]
         * montor : ["11","12","13","14","15","16","17","18","19"]
         * basic : ["1","2","微型车","4","5","6","7","8","9","10"]
         * secure : ["29","30","31","32","33","34","35","36","37"]
         * inner : ["47","48","49","50","51","52","53","54","55"]
         */

        private CommonBean common;
        private List<String> outer;
        private List<String> underPan;
        private List<String> montor;
        private List<String> basic;
        private List<String> secure;
        private List<String> inner;

        public CommonBean getCommon() {
            return common;
        }

        public void setCommon(CommonBean common) {
            this.common = common;
        }

        public List<String> getOuter() {
            return outer;
        }

        public void setOuter(List<String> outer) {
            this.outer = outer;
        }

        public List<String> getUnderPan() {
            return underPan;
        }

        public void setUnderPan(List<String> underPan) {
            this.underPan = underPan;
        }

        public List<String> getMontor() {
            return montor;
        }

        public void setMontor(List<String> montor) {
            this.montor = montor;
        }

        public List<String> getBasic() {
            return basic;
        }

        public void setBasic(List<String> basic) {
            this.basic = basic;
        }

        public List<String> getSecure() {
            return secure;
        }

        public void setSecure(List<String> secure) {
            this.secure = secure;
        }

        public List<String> getInner() {
            return inner;
        }

        public void setInner(List<String> inner) {
            this.inner = inner;
        }

        public static class CommonBean {
            /**
             * date : 1530066349000
             * istransfer : 0
             * isAccident : 无重大事故
             * isFire : 无火烧事故
             * carName : 奥迪A9
             * city : 北京
             * pic : /upload/24343543.jpg
             * isWater : 无水淹事故
             * outPut : 2
             * phone : 18332212560
             * price : 10000
             * name : 黄蓉
             * guideprice : 15000
             * pics : ["/upload/24343543.jpg","/upload/24343543.jpg"]
             * mileage : 1000
             * ismaintain : 1
             */

            private long date;
            private int istransfer;
            private String isAccident;
            private String isFire;
            private String carName;
            private String city;
            private String pic;
            private String isWater;
            private int outPut;
            private String phone;
            private int price;
            private String name;
            private int guideprice;
            private int mileage;
            private int ismaintain;
            private List<String> pics;

            public long getDate() {
                return date;
            }

            public void setDate(long date) {
                this.date = date;
            }

            public int getIstransfer() {
                return istransfer;
            }

            public void setIstransfer(int istransfer) {
                this.istransfer = istransfer;
            }

            public String getIsAccident() {
                return isAccident;
            }

            public void setIsAccident(String isAccident) {
                this.isAccident = isAccident;
            }

            public String getIsFire() {
                return isFire;
            }

            public void setIsFire(String isFire) {
                this.isFire = isFire;
            }

            public String getCarName() {
                return carName;
            }

            public void setCarName(String carName) {
                this.carName = carName;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getIsWater() {
                return isWater;
            }

            public void setIsWater(String isWater) {
                this.isWater = isWater;
            }

            public int getOutPut() {
                return outPut;
            }

            public void setOutPut(int outPut) {
                this.outPut = outPut;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getGuideprice() {
                return guideprice;
            }

            public void setGuideprice(int guideprice) {
                this.guideprice = guideprice;
            }

            public int getMileage() {
                return mileage;
            }

            public void setMileage(int mileage) {
                this.mileage = mileage;
            }

            public int getIsmaintain() {
                return ismaintain;
            }

            public void setIsmaintain(int ismaintain) {
                this.ismaintain = ismaintain;
            }

            public List<String> getPics() {
                return pics;
            }

            public void setPics(List<String> pics) {
                this.pics = pics;
            }
        }
    }
}
