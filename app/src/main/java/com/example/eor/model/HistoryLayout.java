package com.example.eor.model;

import java.util.Date;

public class HistoryLayout {

    private String title,username,location, postId;
    String toDate,fromDate;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public HistoryLayout(String title, String username, String location, String postId, String toDate, String fromDate) {
        this.title = title;
        this.username = username;
        this.location = location;
        this.postId = this.postId;
        this.toDate = toDate;
        this.fromDate = fromDate;
    }

    public HistoryLayout() {

    }

    public HistoryLayout(String title, String username, String location, String toDate, String fromDate) {
        this.title = title;
        this.username = username;
        this.location = location;
        this.toDate = toDate;
        this.fromDate = fromDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "HistoryLayout{" +
                "title='" + title + '\'' +
                ", username='" + username + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
