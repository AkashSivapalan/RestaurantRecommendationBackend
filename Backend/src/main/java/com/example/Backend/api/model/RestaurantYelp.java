package com.example.Backend.api.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.springframework.data.web.JsonPath;

import java.util.List;
import java.util.Map;

public class RestaurantYelp {
    private String id ;
    private String name ;
    @JsonAlias("image_url")
    private String imageUrl ;
    private String phone ;
    private String address ;
    private double rating ;
    private String category ;
    private String price ;
    @JsonAlias("is_closed")
    private Boolean isClosed ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imgUrl) {
        this.imageUrl = imgUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

//    @JsonSetter("address")
    @JsonAlias("location")
    public void setAddress(Map<String, Object> location){
        this.address = "";
        List<String> display = (List<String>) location.get("display_address");

        for (String s : display){
            this.address += s + ", ";
        }

        if (!this.address.isEmpty()){
            this.address = this.address.substring(0, this.address.length() - 2);
        }

    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    @JsonProperty("categories")
    public void setCategory(Object[] categories) {
        this.category =  "";

        for (Object category: categories){
            Map<String, String> info = (Map<String, String>) category;
            this.category += (String) info.get("title") + ", ";
        }

        if (!this.category.isEmpty())
            this.category = this.category.substring(0, this.category.length()-2);

    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Boolean getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(Boolean isClosed) {
        this.isClosed = isClosed;
    }
}
