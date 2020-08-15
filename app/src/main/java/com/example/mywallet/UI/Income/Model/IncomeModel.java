package com.example.mywallet.UI.Income.Model;

import java.util.Date;

public class IncomeModel {

    String resourcename;
    int recordID;
    Date date;
    double money;

    public IncomeModel(String resourcename, int recordID, Date date, double money) {
        this.resourcename = resourcename;
        this.recordID = recordID;
        this.date = date;
        this.money = money;
    }

    public String getResourcename() {
        return resourcename;
    }

    public void setResourcename(String resourcename) {
        this.resourcename = resourcename;
    }

    public int getRecordID() {
        return recordID;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
