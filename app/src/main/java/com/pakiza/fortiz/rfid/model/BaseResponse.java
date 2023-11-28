package com.pakiza.fortiz.rfid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseResponse implements Serializable {

    @SerializedName("statusCode")
    private String responseStatus;

    @SerializedName("message")
    private String messages;

    private Throwable serverError;

    public BaseResponse(Throwable serverError) {
        this.serverError = serverError;
    }

    public BaseResponse() {
    }

    public Throwable getServerError() {
        return serverError;
    }

    public void setServerError(Throwable serverError) {
        this.serverError = serverError;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }
}
