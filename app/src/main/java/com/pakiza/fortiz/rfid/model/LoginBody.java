package com.pakiza.fortiz.rfid.model;

public class LoginBody {
    String UserId;
    String UserPassword;

    public LoginBody(String userId, String userPassword) {
        UserId = userId;
        UserPassword = userPassword;
    }
}
