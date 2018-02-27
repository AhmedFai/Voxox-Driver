package com.example.faizan.voxoxdriver.currentBalancePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by faizan on 2/27/2018.
 */

public class Day {

    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("dayEndBalance")
    @Expose
    private String dayEndBalance;
    @SerializedName("dayData")
    @Expose
    private List<DayDatum> dayData = null;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDayEndBalance() {
        return dayEndBalance;
    }

    public void setDayEndBalance(String dayEndBalance) {
        this.dayEndBalance = dayEndBalance;
    }

    public List<DayDatum> getDayData() {
        return dayData;
    }

    public void setDayData(List<DayDatum> dayData) {
        this.dayData = dayData;
    }

}
