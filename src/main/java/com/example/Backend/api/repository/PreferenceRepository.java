package com.example.Backend.api.repository;

import com.example.Backend.api.model.Preference;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PreferenceRepository extends MongoRepository<Preference, String> {
    Optional<Preference> findByEmail(String email);

    void deleteByEmail(String email);
}
