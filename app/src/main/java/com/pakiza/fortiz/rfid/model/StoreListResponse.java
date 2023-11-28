package com.pakiza.fortiz.rfid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class StoreListResponse extends BaseResponse implements Serializable {
    public StoreListResponse(Throwable serverError) {
        super(serverError);
    }

    @SerializedName("totalCount")
    public String totalCount;
    @SerializedName("data")
    public ArrayList<StoreModel> items;

}
