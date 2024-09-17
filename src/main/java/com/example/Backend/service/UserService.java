package com.example.Backend.service;

import com.example.Backend.api.model.User;
import com.example.Backend.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.Backend.api.model.ChangePasswordRequest;

import java.security.Principal;
@Service
public class UserService {

    //    private List<User> userList;
    private final UserRepository userrepo;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userrepo, PasswordEncoder passwordEncoder){

        this.userrepo=userrepo;

        this.passwordEncoder = passwordEncoder;
    }


    public boolean changePassword(ChangePasswordRequest request) {
        Optional<User> userOptional = userrepo.findByEmail(request.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Check if the current password is correct
            if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
                return false; // Current password is incorrect
            }

            // Check if the two new passwords are the same
            if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
                return false; // New passwords do not match
            }

            // Update the password with the encoded new password
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userrepo.save(user); // Save the updated user

            return true; // Password was successfully changed
        }
        return false; // User not found
    }




    public List<User> getAllUsers() {
        return userrepo.findAll();
    }

    public Optional<User> getUser(String email) {
        return this.userrepo.findByEmail(email);
    }

    public Optional<User> putUser(String email, User updatedUser) {
        Optional<User> existingUser = userrepo.findByEmail(email);

        if (existingUser.isPresent()) {
            // Update the fields
            User userToUpdate = existingUser.get();

            userToUpdate.setEmail(updatedUser.getUsername());
            //userToUpdate.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            userToUpdate.setFname(updatedUser.getFname());
            userToUpdate.setLname(updatedUser.getLname());

            User savedUser = userrepo.save(userToUpdate);
            return Optional.of(savedUser);
        }

        return Optional.empty();
    }

    public Optional<User> deleteUser(String email) {
        Optional <User> user=this.userrepo.findByEmail(email);

        if (user.isPresent()) {
            this.userrepo.deleteByEmail(email);
        }

        return user;
    }
}
