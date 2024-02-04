package com.example.Backend.service;

import com.example.Backend.api.model.Business;
import com.example.Backend.api.model.ErrorResponse;
import com.example.Backend.api.model.RestaurantYelp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
@RequiredArgsConstructor
public class yelpApiService {
    private int lastSelectedIndex = -1;

    public Object getRestaurants(float latitude, float longitude, int radius, String token){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        String url = "https://api.yelp.com/v3/businesses/search?term=restaurant&latitude=" + latitude + "&longitude=" +
                longitude + "&radius=" + radius + "&sort_by=distance";
        RestTemplate rt = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        try{
            ResponseEntity<Business> restaurants = rt.exchange(url, HttpMethod.GET, entity, Business.class);
            return restaurants.getBody();

        } catch (HttpClientErrorException e){
            System.out.println(e.getMessage());
            switch (e.getStatusCode().value()){
                case 400:
                    return new ResponseEntity<>( new ErrorResponse("CLIENT ERROR", "Problem making request to Yelp API"), HttpStatus.BAD_REQUEST);
                case 401,403:
                    return new ResponseEntity<>( new ErrorResponse("CLIENT ERROR", "Authorization Failed - Yelp API"), HttpStatus.UNAUTHORIZED);
                case 404:
                    return new ResponseEntity<>(new ErrorResponse("CLIENT ERROR", "Could not access resource - Yelp API"), HttpStatus.NOT_FOUND);
                case 413:
                    return new ResponseEntity<>(new ErrorResponse("CLIENT ERROR", "Length of request exceeded maximum limit"), HttpStatus.PAYLOAD_TOO_LARGE);
                case 429:
                    return new ResponseEntity<>(new ErrorResponse("CLIENT ERROR", "Daily quota or queries per second limit exceeded"), HttpStatus.TOO_MANY_REQUESTS);
                default:
                    return new ResponseEntity<>(new ErrorResponse("CLIENT ERROR", "Client-Side Error making search request to Yelp API"), HttpStatus.BAD_REQUEST);
            }
        } catch (HttpServerErrorException e){
            System.out.println(e.getMessage());
            switch (e.getStatusCode().value()){
                case 500:
                    return new ResponseEntity<>(new ErrorResponse("EXTERNAL SERVER ERROR", "Internal Server Error - Yelp API"), HttpStatus.INTERNAL_SERVER_ERROR);
                case 503:
                    return new ResponseEntity<>(new ErrorResponse("EXTERNAL SERVER ERROR", "Service Unavailable - Yelp API"), HttpStatus.SERVICE_UNAVAILABLE);
                default:
                    return new ResponseEntity<>(new ErrorResponse("EXTERNAL SERVER ERROR", "Server-Side Error making search request to Yelp API"), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ErrorResponse("INTERNAL SERVER ERROR", "A problem occured"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public Object getRestaurantById(@PathVariable String id, @RequestHeader String token) {
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Bearer " + token);

        String url = "https://api.yelp.com/v3/businesses/" + id;
        RestTemplate rt = new RestTemplate();

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        try {
            ResponseEntity<RestaurantYelp> restaurant = rt.exchange(url, HttpMethod.GET, entity, RestaurantYelp.class);
            System.out.println("got here");
            return restaurant.getBody();

        } catch (HttpClientErrorException e) {
            System.out.println(e.getMessage());
            switch (e.getStatusCode().value()) {
                case 400:
                    return new ResponseEntity<>(new ErrorResponse("CLIENT ERROR", "Problem making request to Yelp API"), HttpStatus.BAD_REQUEST);
                case 401, 403:
                    return new ResponseEntity<>(new ErrorResponse("CLIENT ERROR", "Authorization failed - Yelp API"), HttpStatus.UNAUTHORIZED);
                case 404:
                    return new ResponseEntity<>(new ErrorResponse("CLIENT ERROR", "Could not access resource - Yelp API"), HttpStatus.NOT_FOUND);
                case 413:
                    return new ResponseEntity<>(new ErrorResponse("CLIENT ERROR", "Length of request exceeded maximum limit"), HttpStatus.PAYLOAD_TOO_LARGE);
                case 429:
                    return new ResponseEntity<>(new ErrorResponse("CLIENT ERROR", "Daily quota or queries per second limit exceeded"), HttpStatus.TOO_MANY_REQUESTS);
                default:
                    return new ResponseEntity<>(new ErrorResponse("CLIENT ERROR", "Client-Side Error making search request to Yelp API"), HttpStatus.BAD_REQUEST);
            }
        } catch (HttpServerErrorException e) {
            System.out.println(e.getMessage());
            switch (e.getStatusCode().value()) {
                case 500:
                    return new ResponseEntity<>(new ErrorResponse("EXTERNAL SERVER ERROR", "Internal Server Error - Yelp API"), HttpStatus.INTERNAL_SERVER_ERROR);
                case 503:
                    return new ResponseEntity<>(new ErrorResponse("EXTERNAL SERVER ERROR", "Service Unavailable - Yelp API"), HttpStatus.SERVICE_UNAVAILABLE);
                default:
                    return new ResponseEntity<>(new ErrorResponse("EXTERNAL SERVER ERROR", "Server-Side Error making search request to Yelp API"), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ErrorResponse("INTERNAL SERVER ERROR", "A problem occured"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Object getNextRestaurant(float latitude, float longitude, int radius, String token) {
        // Get a list of all restaurants
        Business business = (Business) getRestaurants(latitude, longitude, radius, token);

        // Access the array of restaurants
        RestaurantYelp[] restaurants = business.getBusinesses();

        lastSelectedIndex++;
        if(lastSelectedIndex >= restaurants.length){
            lastSelectedIndex=0;
        }
        return (restaurants[lastSelectedIndex]);
    }

    public Object swipeLeft(float latitude, float longitude, int radius, String token) {
        try {
            return getNextRestaurant(latitude, longitude, radius, token);

        } catch (Exception e) {
            System.out.println("Error swiping left.");
            e.printStackTrace();
        }
        return null;
    }


}
