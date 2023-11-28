package com.pakiza.fortiz.rfid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class AuditMasterResponse extends BaseResponse implements Serializable {
    @SerializedName("totalCount")
    private String totalCount;
    @SerializedName("data")
    private ArrayList<AuditMasterModel> data;

}
