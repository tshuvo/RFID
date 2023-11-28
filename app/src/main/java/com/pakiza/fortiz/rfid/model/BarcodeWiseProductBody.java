package com.pakiza.fortiz.rfid.model;

public class BarcodeWiseProductBody {
    private String areaId;
    private String barcode;
    private String storeID;

    public BarcodeWiseProductBody(String areaId, String barcode, String storeID) {
        this.areaId = areaId;
        this.barcode = barcode;
        this.storeID = storeID;
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
}
