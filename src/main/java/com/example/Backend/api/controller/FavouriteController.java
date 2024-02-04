package com.example.Backend.api.controller;

import com.example.Backend.api.model.Favourite;
import com.example.Backend.api.repository.FavouriteRepository;
import com.example.Backend.service.FavouriteRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FavouriteController {

    private FavouriteRepository favouritesRepository;

    public FavouriteController(FavouriteRepository favouritesRepository) {
        this.favouritesRepository = favouritesRepository;
    }


    @PostMapping("/favourite")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Favourite> createFavourite(@RequestBody FavouriteRequest favouriteRequest){

        if (favouritesRepository.existsByUserIdAndRestaurantId(favouriteRequest.getUserId(), favouriteRequest.getRestaurantId())) {
            return ResponseEntity.status(409).body(null);
        }
        Favourite favourite = new Favourite();
        favourite.setUserId(favouriteRequest.getUserId());
        favourite.setRestaurantId(favouriteRequest.getRestaurantId());

        return ResponseEntity.status(201).body(this.favouritesRepository.save(favourite));
    }

    @GetMapping("/favourite/{UserId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity getAllFavouritebyUserId(@PathVariable String UserId) {
        List<Favourite> favourites = this.favouritesRepository.findByUserId(UserId);

        if (!favourites.isEmpty()) {
            return ResponseEntity.ok(favourites);
        } else {
            return ResponseEntity.ok("Favourites List with User ID " + UserId + " was not found.");
        }

    }

    @DeleteMapping("/favourite/{UserId}/{RestaurantId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity deleteFavourite(@PathVariable String UserId, @PathVariable String RestaurantId) {
        Optional<Favourite> optionalFavourite = favouritesRepository.findByUserIdAndRestaurantId(UserId, RestaurantId);

        if (optionalFavourite.isPresent()) {
            favouritesRepository.delete(optionalFavourite.get());
            return ResponseEntity.ok("Favourite deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("Favourite not found for User ID: " + UserId + " and Restaurant ID: " + RestaurantId);
        }
    }

    @DeleteMapping("/favourite/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity deleteAllFavouritesByUserId(@PathVariable String userId) {
        List<Favourite> favourites = favouritesRepository.findByUserId(userId);

        if (!favourites.isEmpty()) {
            favouritesRepository.deleteAll(favourites);
            return ResponseEntity.ok("All favorites for User ID " + userId + " deleted successfully.");
        } else {
            // If no favorites found for the userId, return a not found response
            return ResponseEntity.status(404).body("No favorites found for User ID " + userId);
        }
    }

}
