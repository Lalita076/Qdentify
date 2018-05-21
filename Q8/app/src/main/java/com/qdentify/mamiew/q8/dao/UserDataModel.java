package com.qdentify.mamiew.q8.dao;

public class UserDataModel {
    public String email, address, password;

    public UserDataModel() {
    }

    public UserDataModel(String email, String address, String password) {
        this.email = email;
        this.address = address;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
