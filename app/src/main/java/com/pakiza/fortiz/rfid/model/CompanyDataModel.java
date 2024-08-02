package com.pakiza.fortiz.rfid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CompanyDataModel implements Serializable {
    @SerializedName("companyID")
    private String companyID;

    @SerializedName("companyName")
    private String companyName;

    @SerializedName("companyAddress")
    private String companyAddress;

    @SerializedName("phone")
    private String phone;

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
