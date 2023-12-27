package com.example.Backend.api.controller;

import com.example.Backend.api.model.User;
import com.example.Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {
    private UserService userService;


    @Autowired
    public UserController(UserService userService){
        this.userService=userService;

    }
    @GetMapping("/user")
    public User getUser(@RequestParam Integer id){
        Optional user = userService.getUser(id);
        if (user.isPresent()){
            return (User) user.get();
        }
        return null;


    }
    @DeleteMapping("/user")
    public User deleteUser(@RequestParam Integer id){
        Optional user = userService.deleteUser(id);
        if (user.isPresent()){
            return (User) user.get();
        }
        return null;


    }


    @PutMapping("/user")
    public User putUser(@RequestParam Integer id,String fieldName, String val){
        Optional user = userService.putUser(id,fieldName,val);
        if (user.isPresent()){
            return (User) user.get();
        }
        return null;
    }


}
