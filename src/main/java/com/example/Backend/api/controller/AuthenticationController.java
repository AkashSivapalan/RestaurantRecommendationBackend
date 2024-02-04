package com.example.Backend.api.controller;

import com.example.Backend.api.model.AuthenticationRequest;
import com.example.Backend.api.model.AuthenticationResponse;
import com.example.Backend.api.model.RegisterRequest;
import com.example.Backend.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Controller class responsible for handling authentication-related endpoints.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    /**
     * Endpoint for user registration.
     *
     * @param request The registration request containing user details.
     * @return ResponseEntity with the authentication response.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    /**
     * Endpoint for user authentication.
     *
     * @param request The authentication request containing user credentials.
     * @return ResponseEntity with the authentication response.
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    /**
     * Endpoint for refreshing an authentication token.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @throws IOException If an I/O error occurs.
     */
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
}
