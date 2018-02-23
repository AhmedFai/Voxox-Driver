package com.example.faizan.voxoxdriver.OperatorBillPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by faizan on 2/23/2018.
 */

public class Data {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("totalOperatorBill")
    @Expose
    private Double totalOperatorBill;
    @SerializedName("totalBooking")
    @Expose
    private Integer totalBooking;
    @SerializedName("bookingData")
    @Expose
    private List<BookingDatum> bookingData = null;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getTotalOperatorBill() {
        return totalOperatorBill;
    }

    public void setTotalOperatorBill(Double totalOperatorBill) {
        this.totalOperatorBill = totalOperatorBill;
    }

    public Integer getTotalBooking() {
        return totalBooking;
    }

    public void setTotalBooking(Integer totalBooking) {
        this.totalBooking = totalBooking;
    }

    public List<BookingDatum> getBookingData() {
        return bookingData;
    }

    public void setBookingData(List<BookingDatum> bookingData) {
        this.bookingData = bookingData;
    }


}
