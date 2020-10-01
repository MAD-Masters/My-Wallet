package com.example.mywallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mywallet.UI.BudgetManager.Model.Budget2;
import com.example.mywallet.UI.Expenses.AddExpense;
import com.example.mywallet.UI.Expenses.UpdateExpense;
import com.example.mywallet.UI.Income.Income2;
import com.example.mywallet.UI.Income.Income2adapter;
import com.example.mywallet.UI.Income.Income3;
import com.example.mywallet.UI.Income.Income5;
import com.example.mywallet.UI.Income.Income6;

public class NoAppBarActivity extends AppCompatActivity implements Income2adapter.Income2Interface {

    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_app_bar);

        Intent intent = getIntent();
        dialog = new Dialog(this);

        String fragment = intent.getStringExtra("Fragment");

        if (fragment.equals("AddExpense")) {
            AddExpense addExpense = new AddExpense();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.noAppBarFragmentContainer, addExpense);
            fragmentTransaction.commit();
        } else if (fragment.equals("UpdateExpenses")) {

            UpdateExpense updateExpense = new UpdateExpense();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.noAppBarFragmentContainer, updateExpense);
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

        } else if (fragment.equals("budgetInsert")) {
            Budget2 budget2 = new Budget2();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.noAppBarFragmentContainer, budget2);
            fragmentTransaction.addToBackStack(null);
            setActionBarTitle("Budget Item Insert");
            fragmentTransaction.commit();
        }

        } else if (fragment.equals("titleincome")) {
            Income2 income2 = new Income2();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.noAppBarFragmentContainer, income2);
            fragmentTransaction.commit();
        }
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onUpdateBtnincomemoney() {
        Income6 income6 = new Income6();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.noAppBarFragmentContainer, income6);
        fragmentTransaction.commit();

    }

    @Override
    public void ondeleteincome2() {
        dialog.setContentView(R.layout.delete_pop_up);
        /*LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.delete_pop_up, (ViewGroup)findViewById(R.id.deletePopUp));
        TextView message = vie*/

        TextView message = dialog.findViewById(R.id.message);
        message.setText("Are you sure to delete this resource?");
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }
}