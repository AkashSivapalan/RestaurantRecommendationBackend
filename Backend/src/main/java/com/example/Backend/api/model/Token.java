package com.example.Backend.api.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing an authentication token associated with a user.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tokens")
public class Token {

    // Unique identifier for the token
    @Id
    private String id;

    // The actual token value
    private String token;

    // The type of the token (e.g., Bearer)
    private TokenType tokenType = TokenType.BEARER;

    // Flag indicating whether the token has been revoked
    private boolean revoked;

    // Flag indicating whether the token has expired
    private boolean expired;

    // Reference to the user associated with the token
    @DBRef
    private User user;
}
