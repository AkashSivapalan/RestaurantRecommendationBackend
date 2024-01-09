package com.example.Backend.api.repository;

import com.example.Backend.api.model.Preference;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PreferenceRepository extends MongoRepository<Preference, String> {}
