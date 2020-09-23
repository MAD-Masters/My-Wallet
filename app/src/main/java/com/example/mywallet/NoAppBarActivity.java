package com.example.mywallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.mywallet.UI.Expenses.AddExpense;
import com.example.mywallet.UI.Expenses.UpdateExpense;

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
        } else if (fragment.equals("Update Expenses")) {
            UpdateExpense updateExpense = new UpdateExpense();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.noAppBarFragmentContainer, updateExpense);
            setActionBarTitle(fragment);
            fragmentTransaction.commit();
        }
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}