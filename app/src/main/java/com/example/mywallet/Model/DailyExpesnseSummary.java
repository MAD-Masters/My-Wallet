package com.example.mywallet.Model;

import java.util.Date;

public class DailyExpesnseSummary {
    Date date;
    double totalAmount;

    public DailyExpesnseSummary(Date date, double totalAmount) {
        this.date = date;
        this.totalAmount = totalAmount;
    }

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
}
