package com.example.mywallet.UI.Income.Model;

import java.util.Date;

public class IncomeModel {

    String resourcename;
    int recordID;
    Date date;
    double money;
    String text;
    String category;

    public IncomeModel(String resourcename, int recordID, Date date, double money,String text) {
        this.resourcename = resourcename;
        this.recordID = recordID;
        this.date = date;
        this.money = money;
        this.text = text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
