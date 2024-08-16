package com.example.Backend.api.model;

public class Resturant {
    private String resturantId;
    private String name;
    private String imgUrl;

    public Resturant(String resturantId, String name, String imgUrl) {
        this.resturantId = resturantId;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public String getResturantId() {
        return resturantId;
    }

    public void setResturantId(String resturantId) {
        this.resturantId = resturantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
