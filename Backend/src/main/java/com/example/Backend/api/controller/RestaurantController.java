package com.example.Backend.api.controller;

import com.example.Backend.api.model.ErrorResponse;
import com.example.Backend.api.model.Restaurant;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestaurantController {
    @GetMapping("/restaurants")
    @ResponseBody
    public Object getRestaurants(@RequestParam float latitude, @RequestParam float longitude, @RequestParam int radius, @RequestHeader String token){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        String url = "https://api.yelp.com/v3/businesses/search?term=restaurant&latitude=" + latitude + "&longitude=" +
                longitude + "&radius=" + radius + "&sort_by=distance";
        RestTemplate rt = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        try{
            ResponseEntity<Object> restaurants = rt.exchange(url, HttpMethod.GET, entity, Object.class);
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
        }


    }

    @GetMapping("/restaurant/{id}")
    @ResponseBody
    public Object getRestaurantById(@PathVariable String id, @RequestHeader String token){
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Bearer " + token);

        String url = "https://api.yelp.com/v3/businesses/" + id;
        RestTemplate rt = new RestTemplate();

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        try{
            ResponseEntity<Object> restaurant = rt.exchange(url, HttpMethod.GET, entity, Object.class);
            return restaurant.getBody();

        } catch (HttpClientErrorException e){
            System.out.println(e.getMessage());
            switch (e.getStatusCode().value()){
                case 400:
                    return new ResponseEntity<>(new ErrorResponse("CLIENT ERROR", "Problem making request to Yelp API"), HttpStatus.BAD_REQUEST);
                case 401,403:
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
        }


    }

}
