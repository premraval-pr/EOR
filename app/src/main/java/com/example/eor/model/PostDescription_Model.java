package com.example.eor.model;

import java.util.Date;

public class PostDescription_Model {

    String postId,postTitle, postDescription, postPrice, imagePath, user_fname, user_city;
    Date postFrom, postTo, postCreated;

    public PostDescription_Model(String postId, String postTitle, String postDescription, String postPrice, Date postFrom, Date postTo, Date postCreated, String imagePath, String user_fname, String user_city) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.postPrice = postPrice;
        this.postFrom = postFrom;
        this.postTo = postTo;
        this.postCreated = postCreated;
        this.imagePath = imagePath;
        this.user_fname = user_fname;
        this.user_city = user_city;
    }

    public PostDescription_Model() {

    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getPostPrice() {
        return postPrice;
    }

    public void setPostPrice(String postPrice) {
        this.postPrice = postPrice;
    }

    public Date getPostFrom() {
        return postFrom;
    }

    public void setPostFrom(Date postFrom) {
        this.postFrom = postFrom;
    }

    public Date getPostTo() {
        return postTo;
    }

    public void setPostTo(Date postTo) {
        this.postTo = postTo;
    }

    public Date getPostCreated() {
        return postCreated;
    }

    public void setPostCreated(Date postCreated) {
        this.postCreated = postCreated;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getUser_fname() {
        return user_fname;
    }

    public void setUser_fname(String user_fname) {
        this.user_fname = user_fname;
    }

    public String getUser_city() {
        return user_city;
    }

    public void setUser_city(String user_city) {
        this.user_city = user_city;
    }

    @Override
    public String toString() {
        return "PostDescription_Model{" +
                "postId='" + postId + '\'' +
                ", postTitle='" + postTitle + '\'' +
                ", postDescription='" + postDescription + '\'' +
                ", postPrice='" + postPrice + '\'' +
                ", postFrom=" + postFrom +
                ", postTo=" + postTo +
                ", postCreated=" + postCreated +
                ",imagePath='" + imagePath + '\'' +
                '}';
    }
}
