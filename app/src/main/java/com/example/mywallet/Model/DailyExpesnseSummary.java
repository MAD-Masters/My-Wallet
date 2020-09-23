package com.example.mywallet.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class DailyExpesnseSummary implements Parcelable {
    Date date;
    double totalAmount;

    public DailyExpesnseSummary(Date date, double totalAmount) {
        this.date = date;
        this.totalAmount = totalAmount;
    }

    public DailyExpesnseSummary() {
    }

    protected DailyExpesnseSummary(Parcel in) {
        totalAmount = in.readDouble();
    }

    public static final Creator<DailyExpesnseSummary> CREATOR = new Creator<DailyExpesnseSummary>() {
        @Override
        public DailyExpesnseSummary createFromParcel(Parcel in) {
            return new DailyExpesnseSummary(in);
        }

        @Override
        public DailyExpesnseSummary[] newArray(int size) {
            return new DailyExpesnseSummary[size];
        }
    };

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(totalAmount);
    }
}
