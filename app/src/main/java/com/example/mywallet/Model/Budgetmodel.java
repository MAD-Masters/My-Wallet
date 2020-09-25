package com.example.mywallet.Model;

public class Budgetmodel{

   double number1;
   double number2;
   String text;
   double bar;
   String img;
   int cat_ID;
   double amount;



    public Budgetmodel() {
    }


    public double getNumber1() {
        return number1;
    }

    public void setNumber1(double number1) {
        this.number1 = number1;
    }

    public double getNumber2() {
        return number2;
    }

    public void setNumber2(double number2) {
        this.number2 = number2;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getBar() {
        return bar;
    }

    public void setBar(double bar) {
        this.bar = bar;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public Budgetmodel(double number1, double number2, String text) {
        this.number1 = number1;
        this.number2 = number2;
        this.text = text;
        this.bar = bar;
        this.img = img;
    }
}


