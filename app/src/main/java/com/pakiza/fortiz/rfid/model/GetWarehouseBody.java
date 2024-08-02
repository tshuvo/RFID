package com.pakiza.fortiz.rfid.model;

public class GetWarehouseBody {
    private String areaIds;

    public GetWarehouseBody(String areaIds) {
        this.areaIds = areaIds;
    }

    public String getAreaIds() {
        return areaIds;
    }

    public void setAreaIds(String areaIds) {
        this.areaIds = areaIds;
    }
}
