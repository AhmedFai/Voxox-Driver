package com.example.faizan.voxoxdriver.CurrentOperatorBillPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by faizan on 2/27/2018.
 */

public class Data {

    @SerializedName("driverId")
    @Expose
    private String driverId;
    @SerializedName("incentiveBooking")
    @Expose
    private String incentiveBooking;
    @SerializedName("operatorBill")
    @Expose
    private String operatorBill;
    @SerializedName("totalIncentive")
    @Expose
    private String totalIncentive;
    @SerializedName("lastUpdateTime")
    @Expose
    private String lastUpdateTime;
    @SerializedName("dutyStatus")
    @Expose
    private String dutyStatus;
    @SerializedName("dutyStatusCode")
    @Expose
    private String dutyStatusCode;

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getIncentiveBooking() {
        return incentiveBooking;
    }

    public void setIncentiveBooking(String incentiveBooking) {
        this.incentiveBooking = incentiveBooking;
    }

    public String getOperatorBill() {
        return operatorBill;
    }

    public void setOperatorBill(String operatorBill) {
        this.operatorBill = operatorBill;
    }

    public String getTotalIncentive() {
        return totalIncentive;
    }

    public void setTotalIncentive(String totalIncentive) {
        this.totalIncentive = totalIncentive;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getDutyStatus() {
        return dutyStatus;
    }

    public void setDutyStatus(String dutyStatus) {
        this.dutyStatus = dutyStatus;
    }

    public String getDutyStatusCode() {
        return dutyStatusCode;
    }

    public void setDutyStatusCode(String dutyStatusCode) {
        this.dutyStatusCode = dutyStatusCode;
    }

}
