package com.alibou.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Controller class for handling user-related HTTP requests.
 */
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    /**
     * Endpoint for changing the password of the currently authenticated user.
     *
     * @param request         The request containing information about the password change.
     * @param connectedUser   The currently authenticated user.
     * @return ResponseEntity indicating the success of the password change.
     */
    @PatchMapping
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        // Delegate the password change operation to the UserService
        service.changePassword(request, connectedUser);

        // Return a ResponseEntity with a 200 OK status to indicate success
        return ResponseEntity.ok().build();
    }
}
