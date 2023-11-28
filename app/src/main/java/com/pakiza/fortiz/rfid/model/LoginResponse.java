package com.pakiza.fortiz.rfid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponse extends BaseResponse implements Serializable {

    @SerializedName("user")
    UserModel userModel;

    @SerializedName("token")
    String token;

    public LoginResponse(Throwable serverError) {
        super(serverError);
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
