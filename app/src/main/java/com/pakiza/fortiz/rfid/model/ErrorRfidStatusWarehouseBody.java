package com.pakiza.fortiz.rfid.model;

public class ErrorRfidStatusWarehouseBody {
    private String mrrDId;
    private String productID;

    public ErrorRfidStatusWarehouseBody() {
    }

    public ErrorRfidStatusWarehouseBody(String mrrDId, String productID) {
        this.mrrDId = mrrDId;
        this.productID = productID;
    }

    public String getMrrDId() {
        return mrrDId;
    }

    public void setMrrDId(String mrrDId) {
        this.mrrDId = mrrDId;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }
}
