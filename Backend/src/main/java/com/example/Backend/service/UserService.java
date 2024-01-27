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


    /**
     * Change the password for the currently authenticated user.
     *
     * @param request        The request containing information about the password change.
     * @param connectedUser  The currently authenticated user.
     * @throws IllegalStateException If the current password is incorrect or the new passwords don't match.
     */
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        // Extract the User object from the authentication token
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // Check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }

        // Check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Passwords are not the same");
        }

        // Update the password with the encoded new password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // No need to explicitly save the updated user in MongoDB.
        // Spring Data MongoDB automatically updates the existing document based on the ID.
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
            userToUpdate.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
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
