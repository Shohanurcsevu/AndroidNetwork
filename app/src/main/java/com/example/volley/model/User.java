package com.example.volley.model;

public class User  {

    private String Firstname;
    private String LastName;
    private String Email;
    private String image_url;


    public User() {
    }

    public User(String firstname, String lastName, String email, String image_url) {
        Firstname = firstname;
        LastName = lastName;
        Email = email;
        this.image_url = image_url;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
