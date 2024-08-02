package com.pakiza.fortiz.rfid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class AllCompanyResponse extends BaseResponse implements Serializable {

    @SerializedName("totalCount")
    private String totalCount;
    @SerializedName("data")
    private ArrayList<CompanyDataModel> data;

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }


    public ArrayList<CompanyDataModel> getData() {
        return data;
    }

    public void setData(ArrayList<CompanyDataModel> data) {
        this.data = data;
    }
}
