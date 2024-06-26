package com.example.Backend.api.controller;


import com.example.Backend.api.model.Business;
import com.example.Backend.api.model.ErrorResponse;
import com.example.Backend.api.model.Restaurant;
import com.example.Backend.api.model.RestaurantYelp;
import com.example.Backend.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import com.example.Backend.service.yelpApiService;


@RequiredArgsConstructor
@RestController
public class RestaurantController {


    private final yelpApiService YelpApiS;
    @GetMapping("/restaurants")
    @ResponseBody
    public Object getRestaurants(@RequestParam float latitude, @RequestParam float longitude, @RequestParam int radius, @RequestHeader String token){
        return YelpApiS.getRestaurants(latitude, longitude, radius, token);


    }


    @GetMapping("/restaurant/{id}")
    @ResponseBody
    public Object getRestaurantById(@PathVariable String id, @RequestHeader String token){
        return YelpApiS.getRestaurantById(id, token);
    }


    @GetMapping("/swipe-left")
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public Object swipeLeft(@RequestParam float latitude, @RequestParam float longitude, @RequestParam int radius, @RequestHeader String token) {
        return YelpApiS.swipeLeft(latitude, longitude, radius, token);
    }


}
