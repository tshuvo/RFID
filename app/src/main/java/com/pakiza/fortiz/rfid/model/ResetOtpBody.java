package com.pakiza.fortiz.rfid.model;

public class ResetOtpBody {
    private String newPassword;
    private String otp;

    public ResetOtpBody(String newPassword, String otp) {
        this.newPassword = newPassword;
        this.otp = otp;
    }
}
