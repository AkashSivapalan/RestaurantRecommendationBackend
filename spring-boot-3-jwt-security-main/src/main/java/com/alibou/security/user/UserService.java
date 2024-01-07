package com.alibou.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

/**
 * Service class for managing user-related operations, such as changing passwords.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    /**
     * Change the password for the currently authenticated user.
     *
     * @param request        The request containing information about the password change.
     * @param connectedUser  The currently authenticated user.
     * @throws IllegalStateException If the current password is incorrect or the new passwords don't match.
     */
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        // Extract the User object from the authentication token
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // Check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }

        // Check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Passwords are not the same");
        }

        // Update the password with the encoded new password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // No need to explicitly save the updated user in MongoDB.
        // Spring Data MongoDB automatically updates the existing document based on the ID.
    }
}
