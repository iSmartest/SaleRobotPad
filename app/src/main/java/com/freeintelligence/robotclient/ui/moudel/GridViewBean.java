package com.freeintelligence.robotclient.ui.moudel;


public class GridViewBean {

    private int img;
    private String tv;

    public GridViewBean(int img, String tv) {
        this.img = img;
        this.tv = tv;
    }
    public GridViewBean() {

    }
    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }
}
