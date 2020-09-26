package com.example.mywallet.Model;

public class Budgetmodel{

   int cat_ID;
   double amount;
   double usedAmount;

    public Budgetmodel(int cat_ID, double amount, double usedAmount) {
        this.cat_ID = cat_ID;
        this.amount = amount;
        this.usedAmount = usedAmount;
    }

    public Budgetmodel() {
    }

    public int getCat_ID() {
        return cat_ID;
    }

    public void setCat_ID(int cat_ID) {
        this.cat_ID = cat_ID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getUsedAmount() {
        return usedAmount;
    }

    public void setUsedAmount(double usedAmount) {
        this.usedAmount = usedAmount;
    }
}


