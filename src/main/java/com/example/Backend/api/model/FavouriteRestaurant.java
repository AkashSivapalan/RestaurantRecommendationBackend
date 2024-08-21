package com.example.Backend.api.model;

public class FavouriteRestaurant {
    private String restaurantId;
    private String name;
    private String imgUrl;

    public FavouriteRestaurant(String restaurantId, String name, String imgUrl) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public String getResturantId() {
        return restaurantId;
    }

    public void setResturantId(String resturantId) {
        this.restaurantId = resturantId;
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
