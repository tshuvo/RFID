package com.pakiza.fortiz.rfid.model;

public class WarehouseStoreScanRfidBody {
    private String productId;
    private String mrrDId;

    public WarehouseStoreScanRfidBody(String productId, String mrrDId) {
        this.productId = productId;
        this.mrrDId = mrrDId;
    }

    public WarehouseStoreScanRfidBody() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMrrDId() {
        return mrrDId;
    }

    public void setMrrDId(String mrrDId) {
        this.mrrDId = mrrDId;
    }
}
