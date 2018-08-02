package com.freeintelligence.robotclient.ui.moudel;

import java.util.List;

/**
 * Created by za on 2018/6/29.
 */

public class HotcarBean {


    /**
     * data : {"mpv":[],"suv":[{"idea":"科技","brightPoint":[{"sellPoint":"呼呼","sellPointdsc":[{"img":"/upload/car/carImg/1532434575708.jpg","dsc":"威威"},{"img":"/upload/car/carImg/1532434570960.jpg","dsc":"11"},{"img":"/upload/car/carImg/1532434579123.jpg","dsc":"333"}],"sellPointvid":"/upload/2018/7/doc20180724201739460.mp4","type":1}],"name":"奥迪Q3","id":7,"pic":null},{"idea":"轻巧","brightPoint":[],"name":"奥迪Q3","id":5,"pic":"15324266012571767.png"}],"jiaoChe":[{"idea":"轻巧","brightPoint":[{"sellPoint":"总描述","sellPointdsc":[{"img":"/upload/car/carImg/1532434843962.jpg","dsc":"22"},{"img":"/upload/car/carImg/1532434849501.jpg","dsc":"舒适"},{"img":"/upload/car/carImg/1532434849101.jpg","dsc":"22"},{"img":"/upload/car/carImg/1532434845657.jpg","dsc":"让人"}],"sellPointvid":"/upload/2018/7/doc20180724202114901.mp4","type":1}],"name":"奥迪A1","id":8,"pic":null},{"idea":"轻巧","brightPoint":[{"sellPoint":"运动","sellPointdsc":[{"img":"/upload/car/carImg/1532433410423.jpg","dsc":"uu的方法的"},{"img":"/upload/car/carImg/1532433411703.jpg","dsc":"sdasdasd"}],"sellPointvid":"/upload/2018/7/doc20180724195725392.mp4","type":1}],"name":"奥迪A1","id":6,"pic":null},{"idea":"轻巧","brightPoint":[],"name":"q7","id":4,"pic":"15324238483312368.png"},{"idea":"非一般的感觉！！","brightPoint":[{"sellPoint":"理性","sellPointdsc":[{"img":"/upload/car/carImg/1531968487769.jpg","dsc":"哭哭哭"},{"img":"/upload/car/carImg/1531968489320.jpg","dsc":"笑笑笑"},{"img":"/upload/car/carImg/1531968490875.jpg","dsc":"呱呱呱"}],"sellPointvid":"/upload/car/carImg/1531968490875.MP4","type":1},{"sellPoint":"任性","sellPointdsc":[{"img":"/upload/car/carImg/1531968487769.jpg","dsc":"mmm"},{"img":"/upload/car/carImg/1531968489320.jpg","dsc":"nnn"},{"img":"/upload/car/carImg/1531968490875.jpg","dsc":"zzz"}],"sellPointvid":"/upload/car/carImg/1531968490875.MP4","type":1}],"name":"奥迪Rs3","id":3,"pic":"15319688416966456.png"},{"idea":"非一般的感觉！！","brightPoint":[{"sellPoint":"感性","sellPointdsc":[{"img":"/upload/car/carImg/1531968487769.jpg","dsc":"哦哦哦"},{"img":"/upload/car/carImg/1531968489320.jpg","dsc":"嘎嘎嘎"},{"img":"/upload/car/carImg/1531968490875.jpg","dsc":"呱呱呱"}],"sellPointvid":"/upload/car/carImg/1531968490875.MP4","type":1}],"name":"奥迪a4","id":2,"pic":"15319687902554828.png"},{"idea":"自由我主张","brightPoint":[{"sellPoint":"这车超性感","sellPointdsc":[],"sellPointvid":"/upload/car/carImg/1531968490875.MP4","type":3},{"sellPoint":"这车超骨干","sellPointdsc":[{"img":"/upload/car/carImg/1531968487769.jpg","dsc":"啊啊啊"},{"img":"/upload/car/carImg/1531968489320.jpg","dsc":"嗯嗯嗯"},{"img":"/upload/car/carImg/1531968490875.jpg","dsc":"呵呵呵"}],"sellPointvid":"/upload/car/carImg/1531968490875.MP4","type":1},{"sellPoint":"这车牛","sellPointdsc":[{"img":"/upload/car/carImg/1531968487769.jpg","dsc":"ccc"},{"img":"/upload/car/carImg/1531968489320.jpg","dsc":"bbb"},{"img":"/upload/car/carImg/1531968490875.jpg","dsc":"eee"}],"sellPointvid":" ","type":2}],"name":"奥迪a1","id":1,"pic":"15319685291092115.png"}],"paoChe":[]}
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

        public static class CarBean {
            /**
             * idea : 科技
             * brightPoint : [{"sellPoint":"呼呼","sellPointdsc":[{"img":"/upload/car/carImg/1532434575708.jpg","dsc":"威威"},{"img":"/upload/car/carImg/1532434570960.jpg","dsc":"11"},{"img":"/upload/car/carImg/1532434579123.jpg","dsc":"333"}],"sellPointvid":"/upload/2018/7/doc20180724201739460.mp4","type":1}]
             * name : 奥迪Q3
             * id : 7
             * pic : null
             */

            private String idea;
            private String name;
            private int id;
            private String pic;
            private List<BrightPointBean> brightPoint;

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

            public List<BrightPointBean> getBrightPoint() {
                return brightPoint;
            }

            public void setBrightPoint(List<BrightPointBean> brightPoint) {
                this.brightPoint = brightPoint;
            }

            public static class BrightPointBean {
                /**
                 * sellPoint : 呼呼
                 * sellPointdsc : [{"img":"/upload/car/carImg/1532434575708.jpg","dsc":"威威"},{"img":"/upload/car/carImg/1532434570960.jpg","dsc":"11"},{"img":"/upload/car/carImg/1532434579123.jpg","dsc":"333"}]
                 * sellPointvid : /upload/2018/7/doc20180724201739460.mp4
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

                public static class SellPointdscBean {
                    /**
                     * img : /upload/car/carImg/1532434575708.jpg
                     * dsc : 威威
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
        }

        public static class JiaoCheBean {
            /**
             * idea : 轻巧
             * brightPoint : [{"sellPoint":"总描述","sellPointdsc":[{"img":"/upload/car/carImg/1532434843962.jpg","dsc":"22"},{"img":"/upload/car/carImg/1532434849501.jpg","dsc":"舒适"},{"img":"/upload/car/carImg/1532434849101.jpg","dsc":"22"},{"img":"/upload/car/carImg/1532434845657.jpg","dsc":"让人"}],"sellPointvid":"/upload/2018/7/doc20180724202114901.mp4","type":1}]
             * name : 奥迪A1
             * id : 8
             * pic : null
             */

            private String idea;
            private String name;
            private int id;
            private Object pic;
            private List<BrightPointBeanX> brightPoint;

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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getPic() {
                return pic;
            }

            public void setPic(Object pic) {
                this.pic = pic;
            }

            public List<BrightPointBeanX> getBrightPoint() {
                return brightPoint;
            }

            public void setBrightPoint(List<BrightPointBeanX> brightPoint) {
                this.brightPoint = brightPoint;
            }

            public static class BrightPointBeanX {
                /**
                 * sellPoint : 总描述
                 * sellPointdsc : [{"img":"/upload/car/carImg/1532434843962.jpg","dsc":"22"},{"img":"/upload/car/carImg/1532434849501.jpg","dsc":"舒适"},{"img":"/upload/car/carImg/1532434849101.jpg","dsc":"22"},{"img":"/upload/car/carImg/1532434845657.jpg","dsc":"让人"}]
                 * sellPointvid : /upload/2018/7/doc20180724202114901.mp4
                 * type : 1
                 */

                private String sellPoint;
                private String sellPointvid;
                private int type;
                private List<SellPointdscBeanX> sellPointdsc;

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

                public List<SellPointdscBeanX> getSellPointdsc() {
                    return sellPointdsc;
                }

                public void setSellPointdsc(List<SellPointdscBeanX> sellPointdsc) {
                    this.sellPointdsc = sellPointdsc;
                }

                public static class SellPointdscBeanX {
                    /**
                     * img : /upload/car/carImg/1532434843962.jpg
                     * dsc : 22
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
        }
    }
}
