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


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService. getAllUsers());

    }
    @GetMapping("/user")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> getUser(@RequestParam String id) {
        Optional<User> user = userService.getUser(id);

        return user.map(u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/user")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> deleteUser(@RequestParam String id){

        Optional<User> user = userService.deleteUser(id);


        return user.map(u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.notFound().build());


    }

    @PutMapping("/user")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> putUser(@RequestParam String id,@RequestBody User updatedUser){
        Optional<User> user = userService.putUser(id,updatedUser);
        return user.map(u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.notFound().build());
    }


}
