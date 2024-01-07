package com.alibou.security.user;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for managing User entities in MongoDB.
 * Extends the MongoRepository interface provided by Spring Data MongoDB.
 */
public interface UserRepository extends MongoRepository<User, String> {

  /**
   * Find a user by their email address.
   *
   * @param email The email address of the user.
   * @return An Optional containing the user if found, or an empty Optional if not found.
   */
  Optional<User> findByEmail(String email);

}

