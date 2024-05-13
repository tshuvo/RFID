package com.pakiza.fortiz.rfid.api;

//import static com.pakiza.fortiz.rfid.api.ApiUrl.API_GET_AUDIT_LIST;

import static com.pakiza.fortiz.rfid.api.ApiUrl.API_CREATE_AUDIT;
import static com.pakiza.fortiz.rfid.api.ApiUrl.API_ERROR_RFID_STATUS_WAREHOUSE;
import static com.pakiza.fortiz.rfid.api.ApiUrl.API_GET_BARCODE_WISE_PRODUCT;
//import static com.pakiza.fortiz.rfid.api.ApiUrl.API_GET_LOCATION_LIST;
import static com.pakiza.fortiz.rfid.api.ApiUrl.API_GET_STORE_LIST;
import static com.pakiza.fortiz.rfid.api.ApiUrl.API_GET_WAREHOUSE_LIST;
import static com.pakiza.fortiz.rfid.api.ApiUrl.API_LOGIN;
import static com.pakiza.fortiz.rfid.api.ApiUrl.API_PERFORM_AUDIT_RUN;
import static com.pakiza.fortiz.rfid.api.ApiUrl.API_RESEND_OTP;
import static com.pakiza.fortiz.rfid.api.ApiUrl.API_SEND_OTP;
import static com.pakiza.fortiz.rfid.api.ApiUrl.AUTH;

import com.pakiza.fortiz.rfid.model.AppConfigBody;
import com.pakiza.fortiz.rfid.model.AuditMasterResponse;
import com.pakiza.fortiz.rfid.model.BarcodeWiseProductBody;
import com.pakiza.fortiz.rfid.model.BaseResponse;
import com.pakiza.fortiz.rfid.model.CreateAuditBody;
import com.pakiza.fortiz.rfid.model.ErrorRfidStatusResponse;
import com.pakiza.fortiz.rfid.model.ErrorRfidStatusWarehouseBody;
import com.pakiza.fortiz.rfid.model.GetAllReceiveNoWiseProductFormWareHouseBody;
import com.pakiza.fortiz.rfid.model.LoginBody;
import com.pakiza.fortiz.rfid.model.LoginResponse;
import com.pakiza.fortiz.rfid.model.PerformAuditRunBody;
import com.pakiza.fortiz.rfid.model.PerformAuditRunResponse;
import com.pakiza.fortiz.rfid.model.ResetOtpBody;
import com.pakiza.fortiz.rfid.model.ResetOtpResponse;
import com.pakiza.fortiz.rfid.model.SendOtpBody;
import com.pakiza.fortiz.rfid.model.StoreListResponse;
import com.pakiza.fortiz.rfid.model.WareHouseWiseDataResponseModel;
import com.pakiza.fortiz.rfid.model.WarehouseListResponse;
import com.pakiza.fortiz.rfid.model.WarehouseWiseDataList;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @Headers("Content-Type: application/json")
    @POST(API_LOGIN)
    Flowable<LoginResponse> saveAppConfigration(@Body AppConfigBody body);

    @Headers("Content-Type: application/json")
    @POST(API_LOGIN)
    Flowable<LoginResponse> getLoginApi(@Body LoginBody body);

    //
    @Headers("Content-Type: application/json")
    @POST(API_CREATE_AUDIT)
    Flowable<BaseResponse> createAudit(@Header(AUTH) String token, @Body CreateAuditBody body);

    //
    @Headers("Content-Type: application/json")
    @POST(API_PERFORM_AUDIT_RUN)
    Flowable<PerformAuditRunResponse> performAuditRun(@Header(AUTH) String token, @Body PerformAuditRunBody body);

    //
    @Headers("Content-Type: application/json")
    @POST(API_GET_BARCODE_WISE_PRODUCT)
    Flowable<WarehouseWiseDataList> getAllReceiveNoWiseProductFromWareHouse
    (@Header(AUTH) String token, @Body GetAllReceiveNoWiseProductFormWareHouseBody body);


    @Headers("Content-Type: application/json")
    @POST(API_ERROR_RFID_STATUS_WAREHOUSE)
    Flowable<ErrorRfidStatusResponse> getWareHouseRfidStatusErrorDataList
            (@Header(AUTH) String token, @Body ErrorRfidStatusWarehouseBody body);


    @Headers("Content-Type: application/json")
    @POST(API_GET_WAREHOUSE_LIST)
    Flowable<WarehouseListResponse> getWareHouse(@Header(AUTH) String token);

    @Headers("Content-Type: application/json")
    @POST(API_GET_STORE_LIST)
    Flowable<StoreListResponse> getStores(@Header(AUTH) String token);

    //
//
    @Headers("Content-Type: application/json")
    @POST(API_GET_STORE_LIST)
    Flowable<AuditMasterResponse> getByAuditMasterFormStatus(@Header(AUTH) String token);
//
//    @Headers("Content-Type: application/json")
//    @POST(API_GET_BARCODE_WISE_PRODUCT)
//    Flowable<AuditListResponse> getBarcodeWiseProduct(@Header(AUTH) String token, @Body BarcodeWiseProductBody body);

    @Headers("Content-Type: application/json")
    @POST(API_SEND_OTP)
    Flowable<BaseResponse> sendOtpTOEmail(@Body SendOtpBody email);

    @Headers("Content-Type: application/json")
    @POST(API_RESEND_OTP)
    Flowable<ResetOtpResponse> resendOtp(@Body ResetOtpBody resetOtpBody);

}
