package com.example.mywallet.UI.Expenses.Model;

import java.util.ArrayList;

public class MonthlySummary {
    private float inflow, outflow, remainder, mBills, mEdu, mLoans, mGifts, mFamily, mFood;
    private ArrayList<DailyExpesnseSummary> dailyExpenseArrayList;
    private String month;

    public MonthlySummary() {
    }

    public MonthlySummary(String month, float inflow, float outflow, float remainder, float mBills, float mEdu, float mLoans, float mGifts, float mFamily, float mFood, ArrayList<DailyExpesnseSummary> dailyExpenseArrayList) {
        this.month = month;
        this.inflow = inflow;
        this.outflow = outflow;
        this.remainder = remainder;
        this.mBills = mBills;
        this.mEdu = mEdu;
        this.mLoans = mLoans;
        this.mGifts = mGifts;
        this.mFamily = mFamily;
        this.mFood = mFood;
        this.dailyExpenseArrayList = dailyExpenseArrayList;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setInflow(float inflow) {
        this.inflow = inflow;
    }

    public void setOutflow(float outflow) {
        this.outflow = outflow;
    }

    public void setRemainder(float remainder) {
        this.remainder = remainder;
    }

    public void setmBills(float mBills) {
        this.mBills = mBills;
    }

    public void setmEdu(float mEdu) {
        this.mEdu = mEdu;
    }

    public void setmLoans(float mLoans) {
        this.mLoans = mLoans;
    }

    public void setmGifts(float mGifts) {
        this.mGifts = mGifts;
    }

    public void setmFamily(float mFamily) {
        this.mFamily = mFamily;
    }

    public void setmFood(float mFood) {
        this.mFood = mFood;
    }

    public void setDailyExpenseArrayList(ArrayList<DailyExpesnseSummary> dailyExpenseArrayList) {
        this.dailyExpenseArrayList = dailyExpenseArrayList;
    }

    public float getInflow() {
        return inflow;
    }

    public float getOutflow() {
        return outflow;
    }

    public float getRemainder() {
        return remainder;
    }

    public float getmBills() {
        return mBills;
    }

    public float getmEdu() {
        return mEdu;
    }

    public float getmLoans() {
        return mLoans;
    }

    public float getmGifts() {
        return mGifts;
    }

    public float getmFamily() {
        return mFamily;
    }

    public float getmFood() {
        return mFood;
    }

    public ArrayList<DailyExpesnseSummary> getDailyExpenseArrayList() {
        return dailyExpenseArrayList;
    }
}
