package com.pakiza.fortiz.rfid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AuditMasterModel implements Serializable {

    @SerializedName("id")
    private String id;
    @SerializedName("storeId")
    private String storeId;
    @SerializedName("areaId")
    private String areaId;
    @SerializedName("createdBy")
    private String createdBy;
    @SerializedName("createdTime")
    private String createdTime;
    @SerializedName("updatedBy")
    private String updatedBy;
    @SerializedName("updatedTime")
    private String updatedTime;
    @SerializedName("auditorName")
    private String auditorName;
    @SerializedName("auditStatus")
    private String auditStatus;

}
