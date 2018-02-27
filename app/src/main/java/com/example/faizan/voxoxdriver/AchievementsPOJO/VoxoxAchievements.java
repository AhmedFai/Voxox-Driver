package com.example.faizan.voxoxdriver.AchievementsPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by faizan on 2/26/2018.
 */

public class VoxoxAchievements {


    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("text")
    @Expose
    private String text;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
