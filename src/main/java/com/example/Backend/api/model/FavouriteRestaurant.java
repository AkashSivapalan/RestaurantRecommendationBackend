package com.example.Backend.api.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof FavouriteRestaurant res)) {
            return false;
        }

        return this.getResturantId().equals(res.getResturantId());
    }


}
