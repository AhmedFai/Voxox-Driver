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
    private int value;

    public Distance(String text, int value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
