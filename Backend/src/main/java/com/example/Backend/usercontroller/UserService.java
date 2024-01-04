package com.example.Backend.usercontroller;


import com.example.Backend.resource.UserRequest;
import com.example.Backend.user.User;
import com.example.Backend.userrepo.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserService {

    private final UserRepository UserRepository;

    public UserService(UserRepository userRepository) {
        this.UserRepository = userRepository;
    }


    @GetMapping("/User")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(this.UserRepository.findAll());
    }

    @PostMapping("/User")
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setCity(userRequest.getCity());

        return ResponseEntity.status(201).body(this.UserRepository.save(user));
    }

    @GetMapping("/User/{id}")
    public ResponseEntity getUserbyId(@PathVariable String id) {

        Optional<User> user = this.UserRepository.findById(id);

        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.ok("the user with id:" + id + "not found");
        }
    }

    @DeleteMapping("/User/{id}")
    public ResponseEntity deleteUserbyId(@PathVariable String id) {

        Optional<User> user = this.UserRepository.findById(id);

        if(user.isPresent()){
            this.UserRepository.deleteById(id);
            return ResponseEntity.ok("User deleted Successfully");
        } else {
            return ResponseEntity.ok("the user with id:" + id + "not found");
        }
    }



}
