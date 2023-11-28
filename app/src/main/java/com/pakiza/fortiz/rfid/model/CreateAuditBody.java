package com.pakiza.fortiz.rfid.model;

import java.util.ArrayList;

public class CreateAuditBody {
    private String id;
    private String storeId;
    private String areaId;
    private String createdBy;
    private String createdTime;
    private String updatedBy;
    private String updatedTime;
    private String auditorName;
    private String auditStatus;
    private ArrayList<AuditDetailsModel>  auditDetails;

    public CreateAuditBody(String id, String storeId,
                           String areaId, String createdBy, String createdTime, String updatedBy, String updatedTime, String auditorName, String auditStatus,
                           ArrayList<AuditDetailsModel> auditDetails) {
        this.id = id;
        this.storeId = storeId;
        this.areaId = areaId;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.updatedBy = updatedBy;
        this.updatedTime = updatedTime;
        this.auditorName = auditorName;
        this.auditStatus = auditStatus;
        this.auditDetails = auditDetails;
    }
}
