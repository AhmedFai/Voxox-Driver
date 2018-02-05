package com.example.faizan.voxoxdriver.GoogleMapPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by faizan on 2/5/2018.
 */

public class Distance {
    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("value")
    @Expose
    private Integer value;

    public Distance(String text, int value) {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
