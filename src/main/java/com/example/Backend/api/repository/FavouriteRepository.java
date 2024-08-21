package com.example.Backend.api.repository;
import com.example.Backend.api.model.Favourite;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface FavouriteRepository extends MongoRepository<Favourite, String> {
    Optional<Favourite> findByEmail(String email);
    boolean existsByEmail(String email);
}

