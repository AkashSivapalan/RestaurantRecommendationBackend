package com.example.Backend.api.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("User")
public class User {

    @Id
    private String id;
    private String fname;

    private String lname;

    private Address address;
    private String email;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public Address getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public User(String id, String fname, String lname, Address address, String email, String password) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.email = email;
        this.password = password;
    }




}
