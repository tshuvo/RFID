package com.pakiza.fortiz.rfid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class WarehouseListResponse extends BaseResponse implements Serializable {


    public WarehouseListResponse(Throwable serverError) {
        super(serverError);
    }

    @SerializedName("totalCount")
    public String totalCount;
    @SerializedName("data")
    public ArrayList<WarehouseModel> items;

}
