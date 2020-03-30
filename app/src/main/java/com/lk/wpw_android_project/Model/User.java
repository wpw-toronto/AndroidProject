package com.lk.wpw_android_project.Model;

public class User {
    private String Password;
    private String Email;

    public User() {
    }

    public User(String name, String password, String email, String address, String phone) {

        Password = password;
        Email = email;
    }


    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }


    public User(String email, String password) {
        Email = email;
        Password = password;
    }

}
