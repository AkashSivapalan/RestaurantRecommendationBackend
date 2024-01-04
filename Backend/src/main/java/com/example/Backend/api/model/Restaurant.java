package com.example.Backend.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Restaurant {
    @JsonProperty("id")
    private String id ;
    @JsonProperty("name")
    private String name ;
    @JsonProperty("imgUrl")
    private String imgUrl ;
    @JsonProperty("phone")
    private String phone ;
    @JsonProperty("addrress")
    private String address ;
    @JsonProperty("rating")
    private int rating ;
    @JsonProperty("category")
    private String category ;
    @JsonProperty("price")
    private String price ;
    @JsonProperty("is_open")
    private Boolean is_open ;
    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", rating=" + rating +
                ", category='" + category + '\'' +
                ", price='" + price + '\'' +
                ", is_open=" + is_open +
                '}';
    }


    public Restaurant(String id, String name, String imgUrl, String phone, String address, int rating, String category, String price, Boolean is_open) {
        this.id=id;
        this.name=name;
        this.imgUrl=imgUrl;
        this.phone=phone;
        this.address=address;
        this.rating=rating;
        this.category=category;
        this.price  =price;
        this.is_open=is_open;

    }
}
