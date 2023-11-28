package com.pakiza.fortiz.rfid.model;

public class SyncAndStoreRfidUpdateBody {
    private String productID;
    private String rfid;
    private String mrrDId;
    private String id;
    private String status;

    public SyncAndStoreRfidUpdateBody() {
    }

    public SyncAndStoreRfidUpdateBody(String productID, String rfid, String mrrDId, String id, String status) {
        this.productID = productID;
        this.rfid = rfid;
        this.mrrDId = mrrDId;
        this.id = id;
        this.status = status;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
