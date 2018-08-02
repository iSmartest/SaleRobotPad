package com.freeintelligence.robotclient.ui.moudel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by za on 2018/7/9.
 */

public class ConsultBean implements Serializable{

    /**
     * data : {"motor":"tsi","image":"15319687902554828.png","guiPrice":200000,"price":200000,"brightPoint":[{"sellPoint":"感性","sellPointdsc":[{"img":"/upload/car/carImg/1531968487769.jpg","dsc":"哦哦哦"},{"img":"/upload/car/carImg/1531968489320.jpg","dsc":"嘎嘎嘎"},{"img":"/upload/car/carImg/1531968490875.jpg","dsc":"呱呱呱"}],"sellPointvid":"/upload/car/carImg/1531968490875.MP4","type":1}],"name":220000,"answers":[{"question":"条子","videoAddress":" ","id":37,"type":"3","pics":[{"des":"呃呃呃呃呃呃呃","pic":"/upload/2018/7/doc20180724181710499.jpg"}]}],"gearbox":"tsi变速箱","structure":"1","cid":1}
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

    public static class DataBean implements Serializable{
        /**
         * motor : tsi
         * image : 15319687902554828.png
         * guiPrice : 200000
         * price : 200000
         * brightPoint : [{"sellPoint":"感性","sellPointdsc":[{"img":"/upload/car/carImg/1531968487769.jpg","dsc":"哦哦哦"},{"img":"/upload/car/carImg/1531968489320.jpg","dsc":"嘎嘎嘎"},{"img":"/upload/car/carImg/1531968490875.jpg","dsc":"呱呱呱"}],"sellPointvid":"/upload/car/carImg/1531968490875.MP4","type":1}]
         * name : 220000
         * answers : [{"question":"条子","videoAddress":" ","id":37,"type":"3","pics":[{"des":"呃呃呃呃呃呃呃","pic":"/upload/2018/7/doc20180724181710499.jpg"}]}]
         * gearbox : tsi变速箱
         * structure : 1
         * cid : 1
         */

        private String motor;
        private String image;
        private int guiPrice;
        private int price;
        private String name;
        private String gearbox;
        private String structure;
        private int cid;
        private List<BrightPointBean> brightPoint;
        private List<AnswersBean> answers;

        public String getMotor() {
            return motor;
        }

        public void setMotor(String motor) {
            this.motor = motor;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getGuiPrice() {
            return guiPrice;
        }

        public void setGuiPrice(int guiPrice) {
            this.guiPrice = guiPrice;
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

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public List<BrightPointBean> getBrightPoint() {
            return brightPoint;
        }

        public void setBrightPoint(List<BrightPointBean> brightPoint) {
            this.brightPoint = brightPoint;
        }

        public List<AnswersBean> getAnswers() {
            return answers;
        }

        public void setAnswers(List<AnswersBean> answers) {
            this.answers = answers;
        }

        public static class BrightPointBean implements Serializable{
            /**
             * sellPoint : 感性
             * sellPointdsc : [{"img":"/upload/car/carImg/1531968487769.jpg","dsc":"哦哦哦"},{"img":"/upload/car/carImg/1531968489320.jpg","dsc":"嘎嘎嘎"},{"img":"/upload/car/carImg/1531968490875.jpg","dsc":"呱呱呱"}]
             * sellPointvid : /upload/car/carImg/1531968490875.MP4
             * type : 1
             */

            private String sellPoint;
            private String sellPointvid;
            private int type;
            private List<SellPointdscBean> sellPointdsc;

            public String getSellPoint() {
                return sellPoint;
            }

            public void setSellPoint(String sellPoint) {
                this.sellPoint = sellPoint;
            }

            public String getSellPointvid() {
                return sellPointvid;
            }

            public void setSellPointvid(String sellPointvid) {
                this.sellPointvid = sellPointvid;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public List<SellPointdscBean> getSellPointdsc() {
                return sellPointdsc;
            }

            public void setSellPointdsc(List<SellPointdscBean> sellPointdsc) {
                this.sellPointdsc = sellPointdsc;
            }

            public static class SellPointdscBean implements Serializable{
                /**
                 * img : /upload/car/carImg/1531968487769.jpg
                 * dsc : 哦哦哦
                 */

                private String img;
                private String dsc;

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getDsc() {
                    return dsc;
                }

                public void setDsc(String dsc) {
                    this.dsc = dsc;
                }
            }
        }

        public static class AnswersBean implements Serializable{
            /**
             * question : 条子
             * videoAddress :
             * id : 37
             * type : 3
             * pics : [{"des":"呃呃呃呃呃呃呃","pic":"/upload/2018/7/doc20180724181710499.jpg"}]
             */

            private String question;
            private String videoAddress;
            private int id;
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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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
                 * des : 呃呃呃呃呃呃呃
                 * pic : /upload/2018/7/doc20180724181710499.jpg
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
