package com.example.Backend.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("Favourites")
public class Favourite {

    @Id
    private String id;
    private String email;
    private List<FavouriteRestaurant> restaurants;

    public Favourite(String email) {
        this.email = email;
        this.restaurants = new ArrayList<FavouriteRestaurant>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<FavouriteRestaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<FavouriteRestaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
