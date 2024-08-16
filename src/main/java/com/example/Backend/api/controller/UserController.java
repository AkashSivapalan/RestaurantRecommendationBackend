package com.example.Backend.api.controller;

import com.example.Backend.api.model.User;
import com.example.Backend.api.model.ChangePasswordRequest;
import com.example.Backend.api.repository.UserRepository;
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



@Autowired
    public UserController(UserService userService){
        this.userService=userService;

    }

    @PatchMapping("/change-pass")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        // Delegate the password change operation to the UserService
        userService.changePassword(request, connectedUser);

        // Return a ResponseEntity with a 200 OK status to indicate success
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{email}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> getUser(@PathVariable String email) {
        Optional<User> user = userService.getUser(email);

        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/user/{email}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> deleteUser(@PathVariable String email){

        Optional<User> user = userService.deleteUser(email);


        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());


    }

    @PutMapping("/user/{email}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> putUser(@PathVariable String email,@RequestBody User updatedUser){
        Optional<User> user = userService.putUser(email, updatedUser);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
