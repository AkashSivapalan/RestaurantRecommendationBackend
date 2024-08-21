package com.example.Backend.service;

import com.example.Backend.api.model.Favourite;
import com.example.Backend.api.model.FavouriteRestaurant;
import com.example.Backend.api.repository.FavouriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;

@Service
public class FavouriteService {
    private final FavouriteRepository favRepo;

    @Autowired
    public FavouriteService(FavouriteRepository favRepo) {
        this.favRepo = favRepo;
    }

    public ResponseEntity<?> getFavourites(String email) {
        Optional<Favourite> favourites = this.favRepo.findByEmail(email);

        if (favourites.isPresent()) {
            return ResponseEntity.ok(favourites);
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> postFavourite(String email) {
        if (this.favRepo.existsByEmail(email)) {
            return ResponseEntity.status(404).body("User Favourites Already Exist");
        }

        Favourite newFav = new Favourite(email);

        return ResponseEntity.status(201).body(this.favRepo.save(newFav));
    }

    public ResponseEntity<?> putFavourite(String email, FavouriteRestaurant favouriteRestaurant) {
        Optional<Favourite> optionalFavourite = this.favRepo.findByEmail(email);

        if (optionalFavourite.isPresent()) {
            Favourite favourite = optionalFavourite.get();
            if (!favourite.getRestaurants().contains(favouriteRestaurant))
                favourite.getRestaurants().add(favouriteRestaurant);

            return ResponseEntity.status(204).body(this.favRepo.save(favourite));
        }

        return ResponseEntity.status(404).body("User Does Not Exist");
    }

    public ResponseEntity<?> deleteFavourite(String email, String restaurantId) {
        Optional<Favourite> optionalFavourite = this.favRepo.findByEmail(email);
        if (optionalFavourite.isPresent()) {
            Favourite favourite = optionalFavourite.get();
            Iterator<FavouriteRestaurant> iter = favourite.getRestaurants().iterator();
            while (iter.hasNext()) {
                FavouriteRestaurant restaurant = iter.next();
                if (restaurant.getResturantId().equals(restaurantId)) {
                    iter.remove();
                    return ResponseEntity.status(204).body(this.favRepo.save(favourite));
                }
            }

            return ResponseEntity.status(404).body("Restaurant Not Found");
        }

        return ResponseEntity.status(404).body("User Not Found");
    }

    public ResponseEntity<?> deleteFavourite(String email) {
        Optional<Favourite> favourite = this.favRepo.findByEmail(email);

        if (favourite.isPresent()) {
            this.favRepo.deleteById(favourite.get().getId());
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(404).body("User Not Found");
    }
}
