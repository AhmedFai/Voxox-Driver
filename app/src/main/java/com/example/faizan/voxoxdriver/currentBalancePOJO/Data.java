package com.example.faizan.voxoxdriver.currentBalancePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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
    @SerializedName("days")
    @Expose
    private List<Day> days = null;

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

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }


}
