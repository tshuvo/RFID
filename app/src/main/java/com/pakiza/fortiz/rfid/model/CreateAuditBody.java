package com.pakiza.fortiz.rfid.model;

import java.util.ArrayList;

public class CreateAuditBody {
    private final String id;
    private final String storeId;
    private final String areaId;
    private final String createdBy;
    private final String createdTime;
    private final String updatedBy;
    private final String updatedTime;
    private final String auditorName;
    private final String auditStatus;
    private final ArrayList<AuditDetailsModel>  auditDetails;

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
