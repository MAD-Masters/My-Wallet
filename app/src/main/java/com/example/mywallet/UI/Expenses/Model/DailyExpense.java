package com.example.mywallet.UI.Expenses.Model;

import java.util.Date;

public class DailyExpense {
    int recordId;
    Date date;
    double amount;
    int walletID;
    String note;

    public DailyExpense(Date date, double amount, int walletID, String note) {
        this.date = date;
        this.amount = amount;
        this.walletID = walletID;
        this.note = note;
    }

    //Setters and Getters

    public int getRecordId() {
        return recordId;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public int getWalletID() {
        return walletID;
    }

    public String getNote() {
        return note;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setWalletID(int walletID) {
        this.walletID = walletID;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
