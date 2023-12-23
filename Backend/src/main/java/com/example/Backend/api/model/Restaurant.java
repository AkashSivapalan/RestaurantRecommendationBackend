package com.example.Backend.api.model;

public class Restaurant {
    private String id ;
    private String name ;
    private String imgUrl ;
    private String phone ;
    private String address ;
    private int rating ;
    private String category ;

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

    private String price ;
    private Boolean is_open ;
    public Restaurant(String id, String name, String imgUrl, String phone, String address, int rating, String category, String price, Boolean isOpen) {
        this.id=id;
        this.name=name;
        this.imgUrl=imgUrl;
        this.phone=phone;
        this.address=address;
        this.rating=rating;
        this.category=category;
        this.price  =price;
        this.is_open=isOpen;

    }
}
