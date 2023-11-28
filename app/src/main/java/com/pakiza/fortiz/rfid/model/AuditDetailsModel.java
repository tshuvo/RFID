package com.pakiza.fortiz.rfid.model;

public class AuditDetailsModel {
    private String id;
    private String refId;
    private String productId;
    private String mrrDId;
    private String rfid;
    private String peId;
    private String rfIdStatus;

    public AuditDetailsModel(String id, String refId,
                             String productId, String mrrDId,
                             String rfid, String peId, String rfIdStatus) {
        this.id = id;
        this.refId = refId;
        this.productId = productId;
        this.mrrDId = mrrDId;
        this.rfid = rfid;
        this.peId = peId;
        this.rfIdStatus = rfIdStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
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

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getPeId() {
        return peId;
    }

    public void setPeId(String peId) {
        this.peId = peId;
    }

    public String getRfIdStatus() {
        return rfIdStatus;
    }

    public void setRfIdStatus(String rfIdStatus) {
        this.rfIdStatus = rfIdStatus;
    }
}
