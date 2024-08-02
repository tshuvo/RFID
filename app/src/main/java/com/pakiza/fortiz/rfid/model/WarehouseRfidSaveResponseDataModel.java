package com.pakiza.fortiz.rfid.model;

import com.google.gson.annotations.SerializedName;

public class WarehouseRfidSaveResponseDataModel {

    @SerializedName("id")
    String id;
    @SerializedName("grNo")
    String grNo;
    @SerializedName("revDetailId")
    String revDetailId;
    @SerializedName("mrrDId")
    String mrrDId;
    @SerializedName("productName")
    String productName;
    @SerializedName("productID")
    String productId;
    @SerializedName("quantity")
    String quantity;
    @SerializedName("rfidStatus")
    String rfidStatus;
    @SerializedName("areaId")
    String areaId;
    @SerializedName("rfid")
    String rfId;

    public WarehouseRfidSaveResponseDataModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrNo() {
        return grNo;
    }

    public void setGrNo(String grNo) {
        this.grNo = grNo;
    }

    public String getRevDetailId() {
        return revDetailId;
    }

    public void setRevDetailId(String revDetailId) {
        this.revDetailId = revDetailId;
    }

    public String getMrrDId() {
        return mrrDId;
    }

    public void setMrrDId(String mrrDId) {
        this.mrrDId = mrrDId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getRfidStatus() {
        return rfidStatus;
    }

    public void setRfidStatus(String rfidStatus) {
        this.rfidStatus = rfidStatus;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getRfId() {
        return rfId;
    }

    public void setRfId(String rfId) {
        this.rfId = rfId;
    }
}
