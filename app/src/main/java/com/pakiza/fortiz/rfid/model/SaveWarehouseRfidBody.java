package com.pakiza.fortiz.rfid.model;

import java.util.ArrayList;
import java.util.List;

public class SaveWarehouseRfidBody {
    private int count;
    private String productID;
    private String mrrDId;
    private String areaId;
    private List<ProductRFIdRequest> productRFIdRequests;

    public SaveWarehouseRfidBody(int count, String productID,
                                 String mrrDId, String areaId,
                                 List<ProductRFIdRequest> productRFIdRequests) {
        this.count = count;
        this.productID = productID;
        this.mrrDId = mrrDId;
        this.areaId = areaId;
        this.productRFIdRequests = productRFIdRequests;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getMrrDId() {
        return mrrDId;
    }

    public void setMrrDId(String mrrDId) {
        this.mrrDId = mrrDId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public List<ProductRFIdRequest> getProductRFIdRequests() {
        return productRFIdRequests;
    }

    public void setProductRFIdRequests(List<ProductRFIdRequest> productRFIdRequests) {
        this.productRFIdRequests = productRFIdRequests;
    }

    public static class ProductRFIdRequest {
        private String rfId;

        public ProductRFIdRequest(String rfId) {
            this.rfId = rfId;
        }

        public String getRfId() {
            return rfId;
        }

        public void setRfId(String rfId) {
            this.rfId = rfId;
        }
    }
}
