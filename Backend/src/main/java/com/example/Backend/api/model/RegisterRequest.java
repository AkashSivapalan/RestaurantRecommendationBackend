package com.example.Backend.api.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A data class representing the request for user registration.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    /**
     * The first name of the user.
     */
    private String fname;

    /**
     * The last name of the user.
     */
    private String lname;

    /**
     * The address of the user.
     */
    private Address address;

    /**
     * The email address of the user, serving as a unique identifier.
     */
    private String email;

    /**
     * The password associated with the user account.
     */
    private String password;


}