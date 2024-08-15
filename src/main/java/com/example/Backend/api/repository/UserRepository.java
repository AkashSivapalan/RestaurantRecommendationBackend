package com.example.Backend.api.repository;

import com.example.Backend.api.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    /**
     * Find a user by their email address.
     *
     * @param email The email address of the user.
     * @return An Optional containing the user if found, or an empty Optional if not found.
     */
    Optional<User> findByEmail(String email);

    /**
     * Delete a user by their email address.
     *
     * @param email The email address of the user.
     * @return An Optional containing the user if found, or an empty Optional if not found.
     */
    Optional<User> deleteByEmail(String email);
}
