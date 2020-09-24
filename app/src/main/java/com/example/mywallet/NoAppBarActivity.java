package com.example.mywallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.mywallet.UI.Expenses.AddExpense;
import com.example.mywallet.UI.Expenses.UpdateExpense;
import com.example.mywallet.UI.Income.Income2;
import com.example.mywallet.UI.Income.Income3;
import com.example.mywallet.UI.Income.Income5;
import com.example.mywallet.UI.Income.Income6;

public class NoAppBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_app_bar);

        Intent intent = getIntent();

        String fragment = intent.getStringExtra("Fragment");

        if (fragment.equals("Add Expense")) {
            AddExpense addExpense = new AddExpense();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.noAppBarFragmentContainer, addExpense);
            setActionBarTitle(fragment);
            fragmentTransaction.commit();
        } else if (fragment.equals("UpdateExpenses")) {

        } else if (fragment.equals("Update Expenses")) {
            UpdateExpense updateExpense = new UpdateExpense();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.noAppBarFragmentContainer, updateExpense);
            setActionBarTitle(fragment);
            fragmentTransaction.commit();
        } else if (fragment.equals("addincome")) {
            Income5 income5 = new Income5();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.noAppBarFragmentContainer, income5);
            fragmentTransaction.commit();
        } else if (fragment.equals("updateincome")) {
            Income3 income3 = new Income3();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.noAppBarFragmentContainer, income3);
            fragmentTransaction.commit();

        } else if (fragment.equals("titleincome")) {
            Income2 income2 = new Income2();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.noAppBarFragmentContainer, income2);
            fragmentTransaction.commit();
        } else if (fragment.equals("updateincomemoney")) {
            Income6 income6 = new Income6();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.noAppBarFragmentContainer, income6);
            fragmentTransaction.commit();

        }
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}