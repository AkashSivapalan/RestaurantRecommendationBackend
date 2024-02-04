package com.example.Backend.api.repository;

import com.example.Backend.api.model.Token;
import com.example.Backend.api.model.TokenType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository interface for managing Token entities in MongoDB.
 */
public interface TokenRepository extends MongoRepository<Token, String> {

    /**
     * Custom query method to find all valid tokens associated with a user.
     *
     * @param id The unique identifier of the user.
     * @return List of valid tokens associated with the user.
     */
    @Query(value = "{ 'user.id': :#{#id}, 'expired': false, 'revoked': false }")
    List<Token> findAllValidTokenByUser(@Param("id") String id);

    /**
     * Query method to find a token by its token value.
     *
     * @param token The token value.
     * @return Optional containing the token if found, otherwise empty.
     */
    Optional<Token> findByToken(String token);
}