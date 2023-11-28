package com.pakiza.fortiz.rfid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserModel implements Serializable {
    @SerializedName("sl")
    int serial;
    @SerializedName("id")
    String id;
    @SerializedName("email")
    String email;
    @SerializedName("userId")
    String userId;
    @SerializedName("userPassword")
    String userPassword;
    @SerializedName("userType")
    String userType;
    @SerializedName("isActive")
    String isActive;
    @SerializedName("activeRegions")
    String activeRegions;
    @SerializedName("empID")
    String empId;
    @SerializedName("empName")
    String empName;
    @SerializedName("organization")
    String organization;
    @SerializedName("designation")
    String designation;
    @SerializedName("depName")
    String depName;
    @SerializedName("intimes")
    String intimes;
    @SerializedName("address")
    String address;
    @SerializedName("emailaddress")
    String emailAddress;
    @SerializedName("deptName")
    String deptName;
    @SerializedName("tableNativeUser")
    String tableNativeUser;
    @SerializedName("logStatus")
    String logStatus;
    @SerializedName("phone")
    String phone;
    @SerializedName("mobile")
    String mobile;
    @SerializedName("regionName")
    String regionName;
    @SerializedName("mainDepartmentName")
    String mainDepartmentName;
    @SerializedName("managerId")
    String managerId;
    @SerializedName("manager")
    String manager;
    @SerializedName("regionId")
    String regionId;
    @SerializedName("organizationId")
    String organizationId;
    @SerializedName("empGID")
    String empGID;
    @SerializedName("mainDeptId")
    String mainDeptId;
    @SerializedName("departmentId")
    String departmentId;
    @SerializedName("photoUrl")
    String photoUrl;
    @SerializedName("updated_By")
    String updatedBy;
    @SerializedName("updated_Date")
    String updatedDate;
    @SerializedName("token")
    String token;

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getActiveRegions() {
        return activeRegions;
    }

    public void setActiveRegions(String activeRegions) {
        this.activeRegions = activeRegions;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getIntimes() {
        return intimes;
    }

    public void setIntimes(String intimes) {
        this.intimes = intimes;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getTableNativeUser() {
        return tableNativeUser;
    }

    public void setTableNativeUser(String tableNativeUser) {
        this.tableNativeUser = tableNativeUser;
    }

    public String getLogStatus() {
        return logStatus;
    }

    public void setLogStatus(String logStatus) {
        this.logStatus = logStatus;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
//
//    public String getOtherPermission() {
//        return otherPermission;
//    }
//
//    public void setOtherPermission(String otherPermission) {
//        this.otherPermission = otherPermission;
//    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getMainDepartmentName() {
        return mainDepartmentName;
    }

    public void setMainDepartmentName(String mainDepartmentName) {
        this.mainDepartmentName = mainDepartmentName;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getEmpGID() {
        return empGID;
    }

    public void setEmpGID(String empGID) {
        this.empGID = empGID;
    }

    public String getMainDeptId() {
        return mainDeptId;
    }

    public void setMainDeptId(String mainDeptId) {
        this.mainDeptId = mainDeptId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
