package com.example.Backend.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * A data transfer object (DTO) representing a request to change a user's password.
 */
@Getter
@Setter
@Builder
public class ChangePasswordRequest {

    private String email;

    // The user's current password
    private String currentPassword;

    // The new password to be set
    private String newPassword;

    // Confirmation of the new password to ensure it matches
    private String confirmationPassword;
}
