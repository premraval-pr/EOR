package com.example.eor.model;

public class ExplorePost_Model {
    String id;
    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String username;
    String location;
    String image_path;
    double price;

    @Override
    public String toString() {
        return "ExplorePost_Model{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", location='" + location + '\'' +
                ", image_path='" + image_path + '\'' +
                ", price=" + price +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ExplorePost_Model(String id, String title,String username, String location, String image_path, double price) {
        this.id = id;
        this.title=title;
        this.username = username;
        this.location = location;
        this.image_path =image_path;
        this.price = price;
    }
}
