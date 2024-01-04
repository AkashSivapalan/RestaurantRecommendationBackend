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
    public User getUser(@RequestParam String id){
        Optional user = userService.getUser(id);
        if (user.isPresent()){
            return (User) user.get();
        }
        return null;


    }
    @DeleteMapping("/user")
    public User deleteUser(@RequestParam String id){

    System.out.println(id);
        Optional user = userService.deleteUser(id);
        if (user.isPresent()){
            return (User) user.get();
        }
        return null;


    }

    @PutMapping("/user")
    public User putUser(@RequestParam String id,@RequestBody User updatedUser){
        Optional user = userService.putUser(id,updatedUser);
        if (user.isPresent()){
            return (User) user.get();
        }
        return null;
    }


}
