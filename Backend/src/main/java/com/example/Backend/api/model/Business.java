package com.example.Backend.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Business {

    @JsonProperty("businesses")
    private RestaurantYelp[] businesses;

    public RestaurantYelp[] getBusinesses() {
        return businesses;
    }

    public void setBusinesses(RestaurantYelp[] businesses) {
        this.businesses = businesses;
    }
}
