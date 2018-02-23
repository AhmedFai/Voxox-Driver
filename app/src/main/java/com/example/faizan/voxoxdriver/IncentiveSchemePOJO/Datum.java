package com.example.faizan.voxoxdriver.IncentiveSchemePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by faizan on 2/23/2018.
 */

public class Datum {

    @SerializedName("num_ride")
    @Expose
    private String numRide;
    @SerializedName("incentive_amount")
    @Expose
    private String incentiveAmount;

    public String getNumRide() {
        return numRide;
    }

    public void setNumRide(String numRide) {
        this.numRide = numRide;
    }

    public String getIncentiveAmount() {
        return incentiveAmount;
    }

    public void setIncentiveAmount(String incentiveAmount) {
        this.incentiveAmount = incentiveAmount;
    }

}
