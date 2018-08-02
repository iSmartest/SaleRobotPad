package com.freeintelligence.robotclient.ui.moudel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by za on 2018/7/10.
 */

public class RobotconsultBean {

    /**
     * data : {"carList":[{"image":"upload/15296550812188634.png","name":"奥迪a4","answers":[{"question":"整个车的介绍","videoAddress":"/upload/1111.mp4","type":"2","pics":[{"des":"这是第一句话","pic":"upload/1111.jpg"},{"des":"这是第二句话","pic":"upload/2222.jpg"},{"des":"这是第三句话","pic":"upload/3333.jpg"}]}]}]}
     * resultCode : 0
     * msg : 我为你找到了以下答案
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
        private List<CarListBean> carList;

        public List<CarListBean> getCarList() {
            return carList;
        }

        public void setCarList(List<CarListBean> carList) {
            this.carList = carList;
        }

        public static class CarListBean {
            /**
             * image : upload/15296550812188634.png
             * name : 奥迪a4
             * answers : [{"question":"整个车的介绍","videoAddress":"/upload/1111.mp4","type":"2","pics":[{"des":"这是第一句话","pic":"upload/1111.jpg"},{"des":"这是第二句话","pic":"upload/2222.jpg"},{"des":"这是第三句话","pic":"upload/3333.jpg"}]}]
             */

            private String image;
            private String name;
            private List<AnswersBean> answers;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<AnswersBean> getAnswers() {
                return answers;
            }

            public void setAnswers(List<AnswersBean> answers) {
                this.answers = answers;
            }

            public static class AnswersBean implements Serializable{
                /**
                 * question : 整个车的介绍
                 * videoAddress : /upload/1111.mp4
                 * type : 2
                 * pics : [{"des":"这是第一句话","pic":"upload/1111.jpg"},{"des":"这是第二句话","pic":"upload/2222.jpg"},{"des":"这是第三句话","pic":"upload/3333.jpg"}]
                 */

                private String question;
                private String videoAddress;
                private int type;
                private List<PicsBean> pics;

                public String getQuestion() {
                    return question;
                }

                public void setQuestion(String question) {
                    this.question = question;
                }

                public String getVideoAddress() {
                    return videoAddress;
                }

                public void setVideoAddress(String videoAddress) {
                    this.videoAddress = videoAddress;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public List<PicsBean> getPics() {
                    return pics;
                }

                public void setPics(List<PicsBean> pics) {
                    this.pics = pics;
                }

                public static class PicsBean implements Serializable{
                    /**
                     * des : 这是第一句话
                     * pic : upload/1111.jpg
                     */

                    private String des;
                    private String pic;

                    public String getDes() {
                        return des;
                    }

                    public void setDes(String des) {
                        this.des = des;
                    }

                    public String getPic() {
                        return pic;
                    }

                    public void setPic(String pic) {
                        this.pic = pic;
                    }
                }
            }
        }
    }
}
