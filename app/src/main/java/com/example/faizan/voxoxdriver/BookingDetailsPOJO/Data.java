package com.example.faizan.voxoxdriver.BookingDetailsPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by faizan on 2/23/2018.
 */

public class Data {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("operatorBill")
    @Expose
    private String operatorBill;
    @SerializedName("bookingStatus")
    @Expose
    private String bookingStatus;
    @SerializedName("bookingStatusCode")
    @Expose
    private String bookingStatusCode;
    @SerializedName("distanceTravelled")
    @Expose
    private String distanceTravelled;
    @SerializedName("rideTime")
    @Expose
    private String rideTime;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOperatorBill() {
        return operatorBill;
    }

    public void setOperatorBill(String operatorBill) {
        this.operatorBill = operatorBill;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getBookingStatusCode() {
        return bookingStatusCode;
    }

    public void setBookingStatusCode(String bookingStatusCode) {
        this.bookingStatusCode = bookingStatusCode;
    }

    public String getDistanceTravelled() {
        return distanceTravelled;
    }

    public void setDistanceTravelled(String distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }

    public String getRideTime() {
        return rideTime;
    }

    public void setRideTime(String rideTime) {
        this.rideTime = rideTime;
    }

}
