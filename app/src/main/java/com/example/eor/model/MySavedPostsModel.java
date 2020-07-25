package com.example.eor.model;

public class MySavedPostsModel
{

    String title,description,rent,location;
    String image_url;

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public MySavedPostsModel(String title, String description, String rent, String location, String image_url) {
        this.title = title;
        this.description = description;
        this.rent = rent;
        this.location = location;
        this.image_url = image_url;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public MySavedPostsModel(String title, String description, String rent, String location) {
        this.title = title;
        this.description = description;
        this.rent = rent;
        this.location = location;
    }

    public MySavedPostsModel() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public MySavedPostsModel(String title, String description, String rent) {
        this.title = title;
        this.description = description;
        this.rent = rent;
    }
}

