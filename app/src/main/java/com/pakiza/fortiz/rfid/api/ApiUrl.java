package com.pakiza.fortiz.rfid.api;

import android.content.Context;

import com.pakiza.fortiz.rfid.common.CommonService;

public interface ApiUrl {



    String BASE_URL = "http://175.29.181.232:8069/api/v1/";
    //    String BASE_URL = "http://103.138.169.250/api/v1/";
    String AUTH = "Authorization";
    String BEARER = "Bearer ";
    String API_LOGIN = "Accounts/Login";
    String ANDROID = "android/";
    String API_LOGOUT = "logout";
    String API_GET_WAREHOUSE_LIST = "WarehouseInventory/GetAllWareHouse";
    String API_SEND_OTP = "Accounts/SendOTP";
    String API_RESEND_OTP = "Accounts/ResetPassword";
    String API_CREATE_AUDIT = "Audit/CreateAudit";
    String API_PERFORM_AUDIT_RUN = "Audit/PerformAuditRun";
    String API_ERROR_RFID_STATUS_WAREHOUSE = "WarehouseInventory/ErrorRFIDStatusCheckForWarehouse";
    String API_WAREHOUSE_RFID = "WarehouseInventory/SaveWisehouseRFID";
    String API_ALL_COMPANY = "WarehouseInventory/GetAllCompany";
    String API_ALL_WAREHOUSE = "WarehouseInventory/GetAllWareHouse";
    String API_AUDIT_MASTER = "Audit/GetByAuditMasterFromStatus";
    String API_GET_STORE_LIST = "WarehouseInventory/GetAllStore";
    String API_GET_Warehouse_Wise_Report = "WarehouseInventory/GetAllWarehousBarcodeAndRcvNoWiseReport";
    String API_GET_BARCODE_WISE_PRODUCT = "WarehouseInventory/GetAllReceiveNoWiseProductFormWareHouse";

}
