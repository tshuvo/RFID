package com.pakiza.fortiz.rfid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WarehouseDataModel implements Serializable {

    @SerializedName("id")
    private String id;
    @SerializedName("wareHouseName")
    private String wareHouseName;
    @SerializedName("outletName")
    private String outletName;
    @SerializedName("shortName")
    private String shortName;
    @SerializedName("codeNo")
    private String codeNo;
    @SerializedName("isActive")
    private String isActive;
    @SerializedName("storeKey")
    private String storeKey;
    @SerializedName("companyID")
    private String companyID;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWareHouseName() {
        return wareHouseName;
    }

    public void setWareHouseName(String wareHouseName) {
        this.wareHouseName = wareHouseName;
    }

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCodeNo() {
        return codeNo;
    }

    public void setCodeNo(String codeNo) {
        this.codeNo = codeNo;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getStoreKey() {
        return storeKey;
    }

    public void setStoreKey(String storeKey) {
        this.storeKey = storeKey;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }
}
