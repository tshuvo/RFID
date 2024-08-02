package com.pakiza.fortiz.rfid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class AllWarehouseResponse extends BaseResponse implements Serializable {

    @SerializedName("totalCount")
    private String totalCount;
    @SerializedName("data")
    private ArrayList<WarehouseDataModel> data;

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public ArrayList<WarehouseDataModel> getData() {
        return data;
    }

    public void setData(ArrayList<WarehouseDataModel> data) {
        this.data = data;
    }
}
