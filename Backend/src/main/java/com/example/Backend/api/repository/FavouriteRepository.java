package com.example.Backend.api.repository;
import com.example.Backend.api.model.Favourite;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface FavouriteRepository extends MongoRepository<Favourite, String> {
    boolean existsByUserIdAndRestaurantId(String UserId, String RestaurantId);
    Optional<Favourite> findByUserIdAndRestaurantId(String UserId, String RestaurantId);

    List<Favourite> findByUserId(String UserId);
}

