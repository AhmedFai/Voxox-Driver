package com.example.faizan.voxoxdriver.EarningDetailsByDayPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by faizan on 2/23/2018.
 */

public class Data {


    @SerializedName("dayId")
    @Expose
    private String dayId;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("netEarning")
    @Expose
    private String netEarning;
    @SerializedName("totalBooking")
    @Expose
    private String totalBooking;
    @SerializedName("operatorBill")
    @Expose
    private String operatorBill;
    @SerializedName("incentive")
    @Expose
    private String incentive;
    @SerializedName("tax")
    @Expose
    private String tax;
    @SerializedName("cashCollected")
    @Expose
    private String cashCollected;

    public String getDayId() {
        return dayId;
    }

    public void setDayId(String dayId) {
        this.dayId = dayId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getNetEarning() {
        return netEarning;
    }

    public void setNetEarning(String netEarning) {
        this.netEarning = netEarning;
    }

    public String getTotalBooking() {
        return totalBooking;
    }

    public void setTotalBooking(String totalBooking) {
        this.totalBooking = totalBooking;
    }

    public String getOperatorBill() {
        return operatorBill;
    }

    public void setOperatorBill(String operatorBill) {
        this.operatorBill = operatorBill;
    }

    public String getIncentive() {
        return incentive;
    }

    public void setIncentive(String incentive) {
        this.incentive = incentive;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getCashCollected() {
        return cashCollected;
    }

    public void setCashCollected(String cashCollected) {
        this.cashCollected = cashCollected;
    }
}
