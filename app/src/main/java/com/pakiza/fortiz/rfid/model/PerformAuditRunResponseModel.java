package com.pakiza.fortiz.rfid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PerformAuditRunResponseModel implements Serializable {

    @SerializedName("productID")
    private String productID;
    @SerializedName("productName")
    private String productName;
    @SerializedName("productCode")
    private String productCode;
    @SerializedName("barcode")
    private String barcode;
    @SerializedName("quantity")
    private String quantity;
    @SerializedName("areaID")
    private String areaID;
    @SerializedName("rfid")
    private String rfid;
    @SerializedName("mrrDId")
    private String mrrDId;
    @SerializedName("peId")
    private String peId;


    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAreaID() {
        return areaID;
    }

    public void setAreaID(String areaID) {
        this.areaID = areaID;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getMrrDId() {
        return mrrDId;
    }

    public void setMrrDId(String mrrDId) {
        this.mrrDId = mrrDId;
    }

    public String getPeId() {
        return peId;
    }

    public void setPeId(String peId) {
        this.peId = peId;
    }
}
