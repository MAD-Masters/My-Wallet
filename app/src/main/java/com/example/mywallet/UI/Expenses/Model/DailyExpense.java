package com.example.mywallet.UI.Expenses.Model;

import java.util.Date;

public class DailyExpense {
    int recordId;
    Date date;
    double amount;
    int walletID;
    int categoryId;
    String note;

    public DailyExpense(Date date, double amount, int walletID, int categoryId, String note) {
        this.date = date;
        this.amount = amount;
        this.walletID = walletID;
        this.categoryId = categoryId;
        this.note = note;
    }
    //Setters and Getters

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getWalletID() {
        return walletID;
    }

    public void setWalletID(int walletID) {
        this.walletID = walletID;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
