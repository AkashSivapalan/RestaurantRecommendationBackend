package com.example.Backend.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document("User")
public class User {

    @Id
    private String id;

    private String Username;
    private String Password;
    private String Email;
    private String City;

    public User() {
    }

    public User(String username, String password, String email, String city) {
        this.Username = username;
        this.Password = password;
        this.Email = email;
        this.City = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }
}
