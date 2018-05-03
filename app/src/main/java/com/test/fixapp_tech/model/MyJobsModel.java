package com.test.fixapp_tech.model;

public class MyJobsModel {
    public final String title;
    public final String detail;
    public final String date;
    public final int pictureProduct;
    public final int pictureStatus;

    public MyJobsModel(String title, String detail, String date, int pictureProduct,int pictureStatus) {
        this.title = title;
        this.detail = detail;
        this.date = date;
        this.pictureProduct = pictureProduct;
        this.pictureStatus = pictureStatus;
    }
}
