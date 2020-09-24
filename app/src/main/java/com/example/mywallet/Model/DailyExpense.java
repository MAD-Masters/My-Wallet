package com.example.mywallet.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class DailyExpense implements Parcelable {
    int recordId;
    Date date;
    double amount;
    int walletID;
    int categoryId;
    String note;

    public DailyExpense() {
    }

    public DailyExpense(Date date, double amount, int walletID, int categoryId, String note) {
        this.date = date;
        this.amount = amount;
        this.walletID = walletID;
        this.categoryId = categoryId;
        this.note = note;
    }

    protected DailyExpense(Parcel in) {
        recordId = in.readInt();
        amount = in.readDouble();
        walletID = in.readInt();
        categoryId = in.readInt();
        note = in.readString();
    }

    public static final Creator<DailyExpense> CREATOR = new Creator<DailyExpense>() {
        @Override
        public DailyExpense createFromParcel(Parcel in) {
            return new DailyExpense(in);
        }

        @Override
        public DailyExpense[] newArray(int size) {
            return new DailyExpense[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(recordId);
        dest.writeDouble(amount);
        dest.writeInt(walletID);
        dest.writeInt(categoryId);
        dest.writeString(note);
    }
}
