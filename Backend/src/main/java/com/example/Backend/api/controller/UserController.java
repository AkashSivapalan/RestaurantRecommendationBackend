package com.example.Backend.api.controller;

import com.example.Backend.api.model.User;
import com.example.Backend.api.repository.UserRepository;
import com.example.Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;



@Autowired
    public UserController(UserService userService){
        this.userService=userService;

    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService. getAllUsers());

    }
    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam String id) {
        Optional<User> user = userService.getUser(id);

        return user.map(u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/user")
    public ResponseEntity<User> deleteUser(@RequestParam String id){

        Optional<User> user = userService.deleteUser(id);


        return user.map(u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.notFound().build());


    }

    @PutMapping("/user")
    public ResponseEntity<User> putUser(@RequestParam String id,@RequestBody User updatedUser){
        Optional<User> user = userService.putUser(id,updatedUser);
        return user.map(u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.notFound().build());
    }


}
