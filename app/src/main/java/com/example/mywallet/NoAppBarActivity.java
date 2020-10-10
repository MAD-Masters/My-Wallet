package com.example.mywallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mywallet.UI.BudgetManager.Budget2;
import com.example.mywallet.UI.BudgetManager.UpdateBudget;
import com.example.mywallet.UI.Expenses.AddExpense;
import com.example.mywallet.UI.Expenses.UpdateExpense;

import com.example.mywallet.UI.Goal.Goal;
import com.example.mywallet.UI.Goal.Goal1;
import com.example.mywallet.UI.Goal.Goal2;

import com.example.mywallet.UI.Income.Income2;
import com.example.mywallet.UI.Income.Income2adapter;
import com.example.mywallet.UI.Income.Income3;
import com.example.mywallet.UI.Income.Income4;

import com.example.mywallet.UI.Income.Income5;
import com.example.mywallet.UI.Income.Income6;

public class NoAppBarActivity extends AppCompatActivity{

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_app_bar);

        Intent intent = getIntent();
        dialog = new Dialog(this);

        String fragment = intent.getStringExtra("Fragment");

        if (fragment.equals("Add Expense")) {
            AddExpense addExpense = new AddExpense();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.noAppBarFragmentContainer, addExpense);
            setActionBarTitle("Add Expense");
            fragmentTransaction.commit();
        } else if (fragment.equals("Update Expenses")) {
            UpdateExpense updateExpense = new UpdateExpense();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.noAppBarFragmentContainer, updateExpense);
            setActionBarTitle("Update Expense");
            fragmentTransaction.commit();
        } else if (fragment.equals("addincome")) {
            Income5 income5 = new Income5();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.noAppBarFragmentContainer, income5);
            setActionBarTitle("Add money to Wallet");
            fragmentTransaction.commit();

        } else if (fragment.equals("addgoal")) {
            Goal1 goal1 = new Goal1();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.noAppBarFragmentContainer, goal1);
            fragmentTransaction.commit();

        } else if (fragment.equals("addgoal12")) {
            Goal goal = new Goal();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.noAppBarFragmentContainer, goal);
            setActionBarTitle("Add Goal");
            fragmentTransaction.commit();
        } else if (fragment.equals("addgoal123")) {
            Goal2 goal2 = new Goal2();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.noAppBarFragmentContainer, goal2);
            setActionBarTitle("Add Money for Goal");
            fragmentTransaction.commit();
        }


     else if(fragment.equals("updateincome"))


    {
        Income3 income3 = new Income3();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.noAppBarFragmentContainer, income3);
        fragmentTransaction.commit();

    } else if (fragment.equals("budgetInsert")) {
            Budget2 budget2 = new Budget2();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.noAppBarFragmentContainer, budget2);
            setActionBarTitle("Budget Item Insert");
            fragmentTransaction.commit();

        } else if (fragment.equals("titleincome")) {
            Income2 income2 = new Income2();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.noAppBarFragmentContainer, income2);
            setActionBarTitle("Wallet Credits");
            fragmentTransaction.commit();
        }else if (fragment.equals("budget3")) {
             UpdateBudget budget2 = new UpdateBudget();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.noAppBarFragmentContainer, budget2);
            setActionBarTitle("Budget Item update");
            fragmentTransaction.commit();
        } else if (fragment.equals("addresource")) {
            Income4 income4 = new Income4();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.noAppBarFragmentContainer, income4);
            setActionBarTitle("Add Wallet");
            fragmentTransaction.commit();
        }

    else if(fragment.equals("updateMoney"))

    {
        Income6 income6 = new Income6();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.noAppBarFragmentContainer, income6);
        fragmentTransaction.commit();
    } else if(fragment.equals("addresource"))
    {
        Income4 income4 = new Income4();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.noAppBarFragmentContainer, income4);
        fragmentTransaction.commit();
    }

}

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }


}