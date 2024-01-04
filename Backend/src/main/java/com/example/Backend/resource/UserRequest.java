package com.example.Backend.resource;

public class UserRequest {
    private String Username;

    private String Email;

    private String Password;

    private String City;


    public UserRequest () {

    }

    public UserRequest(String username, String email, String password, String city) {
        this.Username = username;
        this.Email = email;
        this.Password = password;
        this.City = city;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String name) {
        this.Username = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }
}
