package com.example.Backend.service;


import com.example.Backend.api.model.Restaurant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class RestaurantService {
    public RestaurantService() {

    }

    public Optional<Restaurant> getRestaurant(String rst_id){
        Optional optional = Optional.empty();

        try{
            String url = "/businesses/"+rst_id;
            WebClient webClient = WebClient.builder()
                    .baseUrl("https://api.yelp.com/v3")
                    .defaultHeader("Authorization", "Bearer 8P4gCUEQ-RUWmoNdiJJoB4gwupzwULScE6X1_taPcOYDM6iGDuKY1aedFqvwIY18Ty2byGzUJrNA2iFQonGHhy0Rt6Nq0IqR3-sKfsQ1zE7_ceTNWvpEwiMRwZ1zZXYx")
                    .build();

            String response =  webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();


            String name = getName(response);
            String imgUrl = getImageUrl(response);
            String phone = getPhone(response);
            String address = getAddress(response);
            int rating = getRating(response);
            String category = getCategory(response);
            String price = getPrice(response);
            Boolean is_open = getOpen(response);


            Restaurant rst = new Restaurant(rst_id,name,imgUrl,phone,address,rating,category,price,is_open);

            optional = Optional.of(rst);

            System.out.println(optional);
        }catch(Exception e){
            System.out.println("error, invalid id");
        }

        return optional;

    }

    private Boolean getOpen(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response);
            JsonNode open = node.at("/price");
            return open.asBoolean();
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return false;
    }

    private String getPrice(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response);
            JsonNode price = node.at("/price");
            return price.asText();
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return "";
    }

    private String getCategory(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response);
            JsonNode category = node.at("/categories/0/title");
            return category.asText();
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return "";
    }

    private int getRating(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response);
            JsonNode rating = node.at("/rating");
            return rating.asInt();
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return -1;


    }


    public String getName(String response){
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response);
            JsonNode name = node.at("/name");
            return name.asText();
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return "";
    }


    public String  getImageUrl(String response){
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response);
            JsonNode imgUrl = node.at("/image_url");
            return imgUrl.asText();
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return "";
    }


    public String  getPhone(String response){
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response);
            JsonNode phone = node.at("/phone");
            return phone.asText();
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return "";
    }


    public String  getAddress(String response){
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response);
            JsonNode address = node.at("/location/address1");
            JsonNode city = node.at("/location/city");
            JsonNode zip_code = node.at("/location/zip_code");
            return address.asText() + ", " + city.asText() + ", " + zip_code.asText();

        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return "";
    }
}
