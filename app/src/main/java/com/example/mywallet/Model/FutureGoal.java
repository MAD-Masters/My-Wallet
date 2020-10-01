package com.example.mywallet.Model;

import java. util.Date;

public class FutureGoal {

    Date date;
    double currentAmount;
    double totalAmount;
    String goal;

    public FutureGoal(Date date, double currentAmount, String goal, Double totalAmount) {
        this.date = date;
        this.currentAmount = currentAmount;
        this.goal= goal;
        this.totalAmount = totalAmount;

    }

    public FutureGoal() {
        
    }

    //Setters and Getters

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String Goal) {
        this.goal = goal;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount;
    }


}





