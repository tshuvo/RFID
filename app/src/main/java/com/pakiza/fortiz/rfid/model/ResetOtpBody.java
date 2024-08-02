package com.pakiza.fortiz.rfid.model;

public class ResetOtpBody {
    private final String newPassword;
    private final String otp;

    public ResetOtpBody(String newPassword, String otp) {
        this.newPassword = newPassword;
        this.otp = otp;
    }
}
