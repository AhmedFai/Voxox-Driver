package com.example.faizan.voxoxdriver.acceptDenyPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by faizan on 2/2/2018.
 */

public class Data {
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("driverId")
    @Expose
    private String driverId;
    @SerializedName("bookingId")
    @Expose
    private String bookingId;
    @SerializedName("pickUpLatitude")
    @Expose
    private String pickUpLatitude;
    @SerializedName("pickUpLongitude")
    @Expose
    private String pickUpLongitude;
    @SerializedName("dropLatitude")
    @Expose
    private String dropLatitude;
    @SerializedName("dropLongitude")
    @Expose
    private String dropLongitude;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getPickUpLatitude() {
        return pickUpLatitude;
    }

    public void setPickUpLatitude(String pickUpLatitude) {
        this.pickUpLatitude = pickUpLatitude;
    }

    public String getPickUpLongitude() {
        return pickUpLongitude;
    }

    public void setPickUpLongitude(String pickUpLongitude) {
        this.pickUpLongitude = pickUpLongitude;
    }

    public String getDropLatitude() {
        return dropLatitude;
    }

    public void setDropLatitude(String dropLatitude) {
        this.dropLatitude = dropLatitude;
    }

    public String getDropLongitude() {
        return dropLongitude;
    }

    public void setDropLongitude(String dropLongitude) {
        this.dropLongitude = dropLongitude;
    }}
