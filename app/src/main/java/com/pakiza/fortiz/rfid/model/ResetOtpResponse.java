package com.pakiza.fortiz.rfid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResetOtpResponse extends BaseResponse implements Serializable {
    @SerializedName("data")
    public ResetOtpResponseDataModel data;

}
