package com.pakiza.fortiz.rfid.model;

public class GetAllReceiveNoWiseProductFormWareHouseBody {
    private String areaId;
    private String receiveType;
    private String receiveNo;
    private String storeID;

    public GetAllReceiveNoWiseProductFormWareHouseBody() {
    }

    public GetAllReceiveNoWiseProductFormWareHouseBody(String areaId, String receiveType, String receiveNo, String storeID) {
        this.areaId = areaId;
        this.receiveType = receiveType;
        this.receiveNo = receiveNo;
        this.storeID = storeID;
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

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }
}
