package com.example.faizan.voxoxdriver.EarningByDayPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by faizan on 2/23/2018.
 */

public class Datum {

    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("dayId")
    @Expose
    private String dayId;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDayId() {
        return dayId;
    }

    public void setDayId(String dayId) {
        this.dayId = dayId;
    }

}
