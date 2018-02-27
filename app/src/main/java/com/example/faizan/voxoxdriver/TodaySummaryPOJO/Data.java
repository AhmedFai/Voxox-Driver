package com.example.faizan.voxoxdriver.TodaySummaryPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by faizan on 2/27/2018.
 */

public class Data {

    @SerializedName("driverId")
    @Expose
    private String driverId;
    @SerializedName("currentBalance")
    @Expose
    private String currentBalance;
    @SerializedName("todaysPayment")
    @Expose
    private String todaysPayment;
    @SerializedName("operatorBill")
    @Expose
    private String operatorBill;
    @SerializedName("cashCollected")
    @Expose
    private String cashCollected;
    @SerializedName("loginHours")
    @Expose
    private String loginHours;

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getTodaysPayment() {
        return todaysPayment;
    }

    public void setTodaysPayment(String todaysPayment) {
        this.todaysPayment = todaysPayment;
    }

    public String getOperatorBill() {
        return operatorBill;
    }

    public void setOperatorBill(String operatorBill) {
        this.operatorBill = operatorBill;
    }

    public String getCashCollected() {
        return cashCollected;
    }

    public void setCashCollected(String cashCollected) {
        this.cashCollected = cashCollected;
    }

    public String getLoginHours() {
        return loginHours;
    }

    public void setLoginHours(String loginHours) {
        this.loginHours = loginHours;
    }


}
