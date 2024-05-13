package com.pakiza.fortiz.rfid.model;

public class GetAllReceiveNoWiseProductFormWareHouseBody {
    private String areaId;
    private String receiveType;
    private String receiveNo;
    private String barcode;

    public GetAllReceiveNoWiseProductFormWareHouseBody() {
    }

    public GetAllReceiveNoWiseProductFormWareHouseBody(String areaId, String receiveType, String receiveNo, String barcode) {
        this.areaId = areaId;
        this.receiveType = receiveType;
        this.receiveNo = receiveNo;
        this.barcode = barcode;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(String receiveType) {
        this.receiveType = receiveType;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
