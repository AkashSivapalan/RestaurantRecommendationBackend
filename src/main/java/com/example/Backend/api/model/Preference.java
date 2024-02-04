package com.example.Backend.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("preferences")
public class Preference {

    @Id
    private String id;

    private int distance;
    private int price;
    private boolean halal;
    private boolean vegetarian;
    private boolean vegan;
    private boolean glutenFree;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isHalal() {
        return halal;
    }

    public void setHalal(boolean halal) {
        this.halal = halal;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public boolean isGlutenFree() {
        return glutenFree;
    }

    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    //public Preference() {}

    public Preference(String id, int distance, int price, boolean halal, boolean vegetarian, boolean vegan, boolean glutenFree) {
        this.id = id;
        this.distance = distance;
        this.price = price;
        this.halal = halal;
        this.vegetarian = vegetarian;
        this.vegan = vegan;
        this.glutenFree = glutenFree;
    }
}
