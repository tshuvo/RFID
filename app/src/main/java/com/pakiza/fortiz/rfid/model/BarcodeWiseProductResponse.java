package com.pakiza.fortiz.rfid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class BarcodeWiseProductResponse extends BaseResponse implements Serializable {
    public BarcodeWiseProductResponse(Throwable serverError) {
        super(serverError);
    }

    @SerializedName("totalCount")
    public String totalCount;
    @SerializedName("data")
    public ArrayList<StoreModel> items;

}
