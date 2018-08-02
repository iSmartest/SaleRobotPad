package com.freeintelligence.robotclient.ui.moudel;

import java.util.List;

/**
 * Created by za on 2018/6/30.
 */

public class BriefBean {

    /**
     * data : {"carVideo":[{"img":"upload/aaaa.jpg","address":"/upload/aaaa.mp4","idea":"自由我主张","name":"奥迪A4","type":"安全性","carId":1}]}
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
        private List<CarVideoBean> carVideo;

        public List<CarVideoBean> getCarVideo() {
            return carVideo;
        }

        public void setCarVideo(List<CarVideoBean> carVideo) {
            this.carVideo = carVideo;
        }

        public static class CarVideoBean {
            /**
             * img : upload/aaaa.jpg
             * address : /upload/aaaa.mp4
             * idea : 自由我主张
             * name : 奥迪A4
             * type : 安全性
             * carId : 1
             */
            private String img;
            private String address;
            private String idea;
            private String name;
            private String type;
            private int carId;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getIdea() {
                return idea;
            }

            public void setIdea(String idea) {
                this.idea = idea;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getCarId() {
                return carId;
            }

            public void setCarId(int carId) {
                this.carId = carId;
            }
        }
    }
}
