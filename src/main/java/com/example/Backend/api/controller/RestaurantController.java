package com.example.Backend.api.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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


    @GetMapping("/yelpRestaurants")
    @ResponseBody
    public Object getYelpRestaurants(@RequestParam float latitude, @RequestParam float longitude, @RequestParam int radius,
                                     @RequestParam int price,@RequestParam String categories ,@RequestParam int offset ,@RequestHeader String token){
        return YelpApiS.getYelpRestaurants(latitude, longitude, radius,price,categories, offset, token);


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
