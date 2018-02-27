package com.example.faizan.voxoxdriver.ScorePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by faizan on 2/26/2018.
 */

public class Data {

    @SerializedName("carQualityScorePercentage")
    @Expose
    private String carQualityScorePercentage;
    @SerializedName("loginHoursPercentage")
    @Expose
    private String loginHoursPercentage;

    public String getCarQualityScorePercentage() {
        return carQualityScorePercentage;
    }

    public void setCarQualityScorePercentage(String carQualityScorePercentage) {
        this.carQualityScorePercentage = carQualityScorePercentage;
    }

    public String getLoginHoursPercentage() {
        return loginHoursPercentage;
    }

    public void setLoginHoursPercentage(String loginHoursPercentage) {
        this.loginHoursPercentage = loginHoursPercentage;
    }

}
