package com.example.Backend.service;

import com.example.Backend.api.model.User;
import com.example.Backend.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

//    private List<User> userList;
    private final UserRepository userrepo;


    @Autowired
    public UserService(UserRepository userrepo){

        this.userrepo=userrepo;

    }


    public List<User> getAllUsers() {
        return userrepo.findAll();
    }

    public Optional<User> getUser(String id){
        Optional <User> user=this.userrepo.findById(id);

        return user;

    }
    public Optional<User> putUser(String id,User updatedUser){
        Optional<User> existingUser = userrepo.findById(id);
        if (existingUser.isPresent()) {
            // Update the fields
            User userToUpdate = existingUser.get();

            userToUpdate.setEmail(updatedUser.getEmail());
            userToUpdate.setAddress(updatedUser.getAddress());
            userToUpdate.setPassword(updatedUser.getPassword());
            userToUpdate.setFname(updatedUser.getFname());
            userToUpdate.setLname(updatedUser.getLname());

            userrepo.save(userToUpdate);
        }

        return existingUser;

    }
    public Optional<User> deleteUser(String id){

        Optional <User> user=this.userrepo.findById(id);

        if(user.isPresent()){
            this.userrepo.deleteById(id);
        }
        return user;

    }
}
