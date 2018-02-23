package com.example.faizan.voxoxdriver.OperatorBillPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by faizan on 2/23/2018.
 */

public class BookingDatum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("crn")
    @Expose
    private String crn;
    @SerializedName("dateTime")
    @Expose
    private String dateTime;
    @SerializedName("cabType")
    @Expose
    private String cabType;
    @SerializedName("cabTypeId")
    @Expose
    private String cabTypeId;
    @SerializedName("cabIcon")
    @Expose
    private String cabIcon;
    @SerializedName("rideStatus")
    @Expose
    private String rideStatus;
    @SerializedName("rideStatusCode")
    @Expose
    private String rideStatusCode;
    @SerializedName("cashCollected")
    @Expose
    private String cashCollected;
    @SerializedName("voxoxMoney")
    @Expose
    private String voxoxMoney;
    @SerializedName("voxoxToPay")
    @Expose
    private String voxoxToPay;
    @SerializedName("operatorBill")
    @Expose
    private String operatorBill;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCabType() {
        return cabType;
    }

    public void setCabType(String cabType) {
        this.cabType = cabType;
    }

    public String getCabTypeId() {
        return cabTypeId;
    }

    public void setCabTypeId(String cabTypeId) {
        this.cabTypeId = cabTypeId;
    }

    public String getCabIcon() {
        return cabIcon;
    }

    public void setCabIcon(String cabIcon) {
        this.cabIcon = cabIcon;
    }

    public String getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(String rideStatus) {
        this.rideStatus = rideStatus;
    }

    public String getRideStatusCode() {
        return rideStatusCode;
    }

    public void setRideStatusCode(String rideStatusCode) {
        this.rideStatusCode = rideStatusCode;
    }

    public String getCashCollected() {
        return cashCollected;
    }

    public void setCashCollected(String cashCollected) {
        this.cashCollected = cashCollected;
    }

    public String getVoxoxMoney() {
        return voxoxMoney;
    }

    public void setVoxoxMoney(String voxoxMoney) {
        this.voxoxMoney = voxoxMoney;
    }

    public String getVoxoxToPay() {
        return voxoxToPay;
    }

    public void setVoxoxToPay(String voxoxToPay) {
        this.voxoxToPay = voxoxToPay;
    }

    public String getOperatorBill() {
        return operatorBill;
    }

    public void setOperatorBill(String operatorBill) {
        this.operatorBill = operatorBill;
    }


}
