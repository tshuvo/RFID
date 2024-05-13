package com.pakiza.fortiz.rfid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WareHouseWiseDataResponseModel implements Serializable {

    @SerializedName("productId")
    String productId;
    @SerializedName("productName")
    String productName;
    @SerializedName("barcode")
    String barcode;
    @SerializedName("quantity")
    String quantity;
    @SerializedName("mrrDId")
    String mrrDid;
    @SerializedName("rfIdStatus")
    String rfIdStatus;
    @SerializedName("rfIdCount")
    String rfIdCount;
    @SerializedName("inStore")
    String inStore;
    @SerializedName("incrementOrDecrement")
    String incrementOrDecrement;

    @SerializedName("stockQty")
    String stockQty;

    @SerializedName("deviation")
    String deviation;

    public WareHouseWiseDataResponseModel() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMrrDid() {
        return mrrDid;
    }

    public void setMrrDid(String mrrDid) {
        this.mrrDid = mrrDid;
    }

    public String getRfIdStatus() {
        return rfIdStatus;
    }

    public void setRfIdStatus(String rfIdStatus) {
        this.rfIdStatus = rfIdStatus;
    }

    public String getRfIdCount() {
        return rfIdCount;
    }

    public void setRfIdCount(String rfIdCount) {
        this.rfIdCount = rfIdCount;
    }

    public String getInStore() {
        return inStore;
    }

    public void setInStore(String inStore) {
        this.inStore = inStore;
    }

    public String getIncrementOrDecrement() {
        return incrementOrDecrement;
    }

    public void setIncrementOrDecrement(String incrementOrDecrement) {
        this.incrementOrDecrement = incrementOrDecrement;
    }

    public String getStockQty() {
        return stockQty;
    }

    public void setStockQty(String stockQty) {
        this.stockQty = stockQty;
    }

    public String getDeviation() {
        return deviation;
    }

    public void setDeviation(String deviation) {
        this.deviation = deviation;
    }
}
