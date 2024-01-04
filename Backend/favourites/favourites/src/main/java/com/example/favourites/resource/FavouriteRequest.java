package com.example.favourites.resource;

public class FavouriteRequest {

        private String userId;
        private String restaurantId;

    public FavouriteRequest() {

    }

    public FavouriteRequest(String userId, String restaurantId) {
        this.userId = userId;
        this.restaurantId = restaurantId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }
}
