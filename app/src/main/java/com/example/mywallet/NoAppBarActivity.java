package com.example.mywallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.mywallet.UI.Expenses.AddExpense;
import com.example.mywallet.UI.Expenses.UpdateExpense;
import com.example.mywallet.UI.Goal.Goal;
import com.example.mywallet.UI.Goal.Goal1;
import com.example.mywallet.UI.Goal.Goal2;
import com.example.mywallet.UI.Income.Income5;

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
        else if (fragment.equals("addincome")) {
            Income5 income5 = new Income5();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.noAppBarFragmentContainer, income5);
            fragmentTransaction.commit();
        }

        else if(fragment.equals("addgoal")){
            Goal1 goal1 = new Goal1();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.noAppBarFragmentContainer, goal1);
            fragmentTransaction.commit();

        }
        else if(fragment.equals("addgoal12")){
            Goal goal = new Goal();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.noAppBarFragmentContainer, goal);
            fragmentTransaction.commit();
        }

        else if(fragment.equals("addgoal123")){
            Goal2 goal2 = new Goal2();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.noAppBarFragmentContainer, goal2);
            fragmentTransaction.commit();
        }


    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}