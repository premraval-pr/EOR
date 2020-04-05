package com.example.eor;

public class HistoryLayout {

    private String title,username,location;

    public HistoryLayout() {

    }

    public HistoryLayout(String title, String username, String location) {
        this.title = title;
        this.username = username;
        this.location = location;
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
