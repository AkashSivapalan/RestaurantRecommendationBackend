package com.example.Backend.api.controller;

import com.example.Backend.api.model.Restaurant;
import com.example.Backend.api.model.User;
import com.example.Backend.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@SpringBootApplication
public class RestaurantController {

    private RestaurantService rs;


    @Autowired
    public RestaurantController(RestaurantService rs){
        this.rs=rs;

    }
    @GetMapping("/restaurant")
    public Restaurant getRestaurantInfo(@RequestParam String id) {

        Optional restaurant = rs.getRestaurant(id);
        if (restaurant.isPresent()){
            return (Restaurant) restaurant.get();
        }
        return null;
    }

}
