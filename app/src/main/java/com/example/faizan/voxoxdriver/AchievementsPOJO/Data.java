package com.example.faizan.voxoxdriver.AchievementsPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by faizan on 2/26/2018.
 */

public class Data {

    @SerializedName("voxoxAchievements")
    @Expose
    private VoxoxAchievements voxoxAchievements;
    @SerializedName("customerCompliments")
    @Expose
    private CustomerCompliments customerCompliments;

    public VoxoxAchievements getVoxoxAchievements() {
        return voxoxAchievements;
    }

    public void setVoxoxAchievements(VoxoxAchievements voxoxAchievements) {
        this.voxoxAchievements = voxoxAchievements;
    }

    public CustomerCompliments getCustomerCompliments() {
        return customerCompliments;
    }

    public void setCustomerCompliments(CustomerCompliments customerCompliments) {
        this.customerCompliments = customerCompliments;
    }

}
