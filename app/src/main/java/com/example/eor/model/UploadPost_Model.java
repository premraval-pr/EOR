package com.example.eor.model;

import java.util.Date;

public class UploadPost_Model {

    private String[] uploadpost_imagepath;
    private String uploadpost_posttite;
    private String uploadpost_postdescription;
    private String uploadpost_postprice;
    private String uploadpost_postpolicy;
    private int uploadpost_postbid;
    private int uploadpost_postoffer;
    private Date uploadpost_postcreated;
    private Date uploadpost_postfrom;
    private Date uploadpost_postto;
    private String uploadpost_postCategory;

    public UploadPost_Model(String uploadpost_imagepath[], String uploadpost_posttite, String uploadpost_postdescription, String uploadpost_postprice) {
        this.uploadpost_imagepath = uploadpost_imagepath;
        this.uploadpost_posttite = uploadpost_posttite;
        this.uploadpost_postdescription = uploadpost_postdescription;
        this.uploadpost_postprice = uploadpost_postprice;
    }

    public String[] getUploadpost_imagepath() {
        return uploadpost_imagepath;
    }

    public void setUploadpost_imagepath(String uploadpost_imagepath[]) {
        this.uploadpost_imagepath = uploadpost_imagepath;
    }

    public String getUploadpost_posttite() {
        return uploadpost_posttite;
    }

    public void setUploadpost_posttite(String uploadpost_posttite) {
        this.uploadpost_posttite = uploadpost_posttite;
    }

    public String getUploadpost_postdescription() {
        return uploadpost_postdescription;
    }

    public void setUploadpost_postdescription(String uploadpost_postdescription) {
        this.uploadpost_postdescription = uploadpost_postdescription;
    }

    public String getUploadpost_postprice() {
        return uploadpost_postprice;
    }

    public void setUploadpost_postprice(String uploadpost_postprice) {
        this.uploadpost_postprice = uploadpost_postprice;
    }
}
