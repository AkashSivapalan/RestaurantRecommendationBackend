package com.example.Backend.api.controller;

import com.example.Backend.api.model.Favourite;
import com.example.Backend.api.model.FavouriteRestaurant;
import com.example.Backend.service.FavouriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FavouriteController {
    private final FavouriteService favService;

    public FavouriteController(FavouriteService favService) {
        this.favService = favService;
    }

    @GetMapping("/favourite/{email}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getFavourites(@PathVariable String email) {
        return favService.getFavourites(email);
    }

    @PostMapping("/favourite/{email}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> postFavourite(@PathVariable String email){
        return favService.postFavourite(email);
    }

    @PutMapping("/favourite/{email}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> putFavourite(@PathVariable String email, @RequestBody FavouriteRestaurant favouriteRestaurant) {
        return favService.putFavourite(email, favouriteRestaurant);
    }

    @DeleteMapping("/favourite/{email}/{restaurantId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> deleteFavourite(@PathVariable String email, @PathVariable String restaurantId) {
        return favService.deleteFavourite(email, restaurantId);
    }

    @DeleteMapping("/favourite/{email}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> deleteFavourite(@PathVariable String email) {
        return favService.deleteFavourite(email);
    }
//
//    @DeleteMapping("/favourite/{userId}")
//    @PreAuthorize("isAuthenticated()")
//    public ResponseEntity deleteAllFavouritesByUserId(@PathVariable String userId) {
//        List<Favourite> favourites = favouritesRepository.findByUserId(userId);
//
//        if (!favourites.isEmpty()) {
//            favouritesRepository.deleteAll(favourites);
//            return ResponseEntity.ok("All favorites for User ID " + userId + " deleted successfully.");
//        } else {
//            // If no favorites found for the userId, return a not found response
//            return ResponseEntity.status(404).body("No favorites found for User ID " + userId);
//        }
//    }

}
