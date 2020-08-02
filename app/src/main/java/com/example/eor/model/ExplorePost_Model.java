package com.example.eor.model;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import java.util.Objects;

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
    double price,latitude,longitude;


    public ExplorePost_Model(String id, String title, String username, String location, String image) {
        this.id = id;
        this.title= title;
        this.username = username;
        this.location = location;
        this.image_path = image;
    }

    @NonNull
    @Override
    public String toString() {
        return title + " By " + username;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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

    public ExplorePost_Model(String id, String title,String username, String location, String image_path, double price, double latitude, double longitude) {
        this.id = id;
        this.title=title;
        this.username = username;
        this.location = location;
        this.image_path =image_path;
        this.price = price;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public ExplorePost_Model(String id, String title,String username, String location, String image_path, double price) {
        this.id = id;
        this.title=title;
        this.username = username;
        this.location = location;
        this.image_path =image_path;
        this.price = price;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExplorePost_Model that = (ExplorePost_Model) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
