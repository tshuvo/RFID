package com.pakiza.fortiz.rfid.model;

public class PerformAuditRunBody {
    private String areaId;
    private String barcode;
    private String storeID;
    private String peId;

    public PerformAuditRunBody(String areaId, String barcode, String storeID, String peId) {
        this.areaId = areaId;
        this.barcode = barcode;
        this.storeID = storeID;
        this.peId = peId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getPeId() {
        return peId;
    }

    public void setPeId(String peId) {
        this.peId = peId;
    }
}
