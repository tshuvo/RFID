package com.pakiza.fortiz.rfid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class PerformAuditRunResponse extends BaseResponse implements Serializable {

    @SerializedName("totalCount")
    private String totalCount;
    @SerializedName("data")
    private ArrayList<PerformAuditRunResponseModel> data;

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public ArrayList<PerformAuditRunResponseModel> getData() {
        return data;
    }

    public void setData(ArrayList<PerformAuditRunResponseModel> data) {
        this.data = data;
    }
}
