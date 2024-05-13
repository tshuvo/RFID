package com.pakiza.fortiz.rfid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class WarehouseWiseDataList extends BaseResponse implements Serializable {

    @SerializedName("totalCount")
    public String totalCount;
    @SerializedName("data")
    public ArrayList<WareHouseWiseDataResponseModel> items;

}
