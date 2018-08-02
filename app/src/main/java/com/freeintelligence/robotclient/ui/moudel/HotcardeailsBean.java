package com.freeintelligence.robotclient.ui.moudel;

import java.util.List;

/**
 * Created by za on 2018/6/29.
 */

public class HotcardeailsBean {

    /**
     * data : {"car":{"motor":"dd","score":100,"colour":["1","2"],"address":"","preprice":10000,"guideprice":155,"pic":"upload/15296550812188634.png","gearbox":"cvt变速箱","pics":["/upload/24343543.jpg","/upload/24343543.jpg"],"structure":"1"}}
     * resultCode : 0
     * msg : 获取成功！
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
         * car : {"motor":"dd","score":100,"colour":["1","2"],"address":"","preprice":10000,"guideprice":155,"pic":"upload/15296550812188634.png","gearbox":"cvt变速箱","pics":["/upload/24343543.jpg","/upload/24343543.jpg"],"structure":"1"}
         */

        private CarBean car;

        public CarBean getCar() {
            return car;
        }

        public void setCar(CarBean car) {
            this.car = car;
        }

        public static class CarBean {
            /**
             * motor : dd
             * score : 100
             * colour : ["1","2"]
             * address :
             * preprice : 10000
             * guideprice : 155
             * pic : upload/15296550812188634.png
             * gearbox : cvt变速箱
             * pics : ["/upload/24343543.jpg","/upload/24343543.jpg"]
             * structure : 1
             */
            private String motor;
            private int score;
            private String address;
            private String preprice;
            private String guideprice;
            private String pic;
            private String gearbox;
            private String structure;
            private List<String> colour;
            private List<String> pics;

            public String getMotor() {
                return motor;
            }

            public void setMotor(String motor) {
                this.motor = motor;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getPreprice() {
                return preprice;
            }

            public void setPreprice(String preprice) {
                this.preprice = preprice;
            }

            public String getGuideprice() {
                return guideprice;
            }

            public void setGuideprice(String guideprice) {
                this.guideprice = guideprice;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getGearbox() {
                return gearbox;
            }

            public void setGearbox(String gearbox) {
                this.gearbox = gearbox;
            }

            public String getStructure() {
                return structure;
            }

            public void setStructure(String structure) {
                this.structure = structure;
            }

            public List<String> getColour() {
                return colour;
            }

            public void setColour(List<String> colour) {
                this.colour = colour;
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
