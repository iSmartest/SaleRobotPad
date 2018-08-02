package com.freeintelligence.robotclient.ui.moudel;

import java.util.List;

/**
 * Created by za on 2018/6/30.
 */

public class ShowBean {

    /**
     * data : {"carProduct":[{"price":1110,"introduce":"保障安全","pic":"15301651919262589.png"},{"price":1006,"introduce":"真皮","pic":"15301647046372843.png"}]}
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
        private List<CarProductBean> carProduct;

        public List<CarProductBean> getCarProduct() {
            return carProduct;
        }

        public void setCarProduct(List<CarProductBean> carProduct) {
            this.carProduct = carProduct;
        }

        public static class CarProductBean {
            /**
             * price : 1110
             * introduce : 保障安全
             * pic : 15301651919262589.png
             */

            private int price;
            private String introduce;
            private String pic;

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
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
