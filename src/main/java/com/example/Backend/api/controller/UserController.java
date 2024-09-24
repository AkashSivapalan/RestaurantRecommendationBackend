package com.example.Backend.api.controller;

import com.example.Backend.api.model.Preference;
import com.example.Backend.api.model.User;
import com.example.Backend.api.model.ChangePasswordRequest;
import com.example.Backend.api.repository.UserRepository;
import com.example.Backend.service.FavouriteService;
import com.example.Backend.service.PreferenceService;
import com.example.Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;
    private final PreferenceService prefService;
    private final FavouriteService favService;


@Autowired
    public UserController(UserService userService, PreferenceService prefService, FavouriteService favService){
        this.userService = userService;
        this.prefService = prefService;
        this.favService = favService;
    }

    @PatchMapping("/change-pass")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request
    ) {
        boolean isPasswordChanged = userService.changePassword(request);

        if (isPasswordChanged) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(400).body("Password change failed. Check your current password and new password confirmation.");
        }
    }


    @GetMapping("/user/{email}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> getUser(@PathVariable String email) {
        Optional<User> user = userService.getUser(email);

        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/user/{email}")
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> deleteUser(@PathVariable String email){

        Optional<User> user = userService.deleteUser(email);
        ResponseEntity<?> pref = prefService.deletePreference(email);
        ResponseEntity<?> fav = favService.deleteFavourite(email);

        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/user/{email}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> putUser(@PathVariable String email,@RequestBody User updatedUser){
        Optional<User> user = userService.putUser(email, updatedUser);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
