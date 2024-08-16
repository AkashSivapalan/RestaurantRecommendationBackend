package com.example.Backend.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Favourites")
public class Favourite {

    @Id
    private String id;
    private String userId;
    private Resturant[] favourites;

    public Favourite(String id, String userId, Resturant[] favourites) {
        this.id = id;
        this.userId = userId;
        this.favourites = favourites;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Resturant[] getFavourites() {
        return favourites;
    }

    public void setFavourites(Resturant[] favourites) {
        this.favourites = favourites;
    }
}
