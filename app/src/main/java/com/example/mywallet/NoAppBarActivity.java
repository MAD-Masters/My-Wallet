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

import com.example.mywallet.UI.BudgetManager.Model.Budget2;
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

public class NoAppBarActivity extends AppCompatActivity implements Income2adapter.Income2Interface {

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
            fragmentTransaction.commit();

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

        } else if (fragment.equals("titleincome")) {
            Income2 income2 = new Income2();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.noAppBarFragmentContainer, income2);
            fragmentTransaction.commit();
        } else if (fragment.equals("addresource")) {
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


    @Override
    public void onUpdateBtnincomemoney(int recordid) {
        Income6 income6 = new Income6();
        Bundle bundle = new Bundle();
        bundle.putInt("id", recordid);
        income6.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.noAppBarFragmentContainer, income6);
        fragmentTransaction.commit();
        System.out.println("recordid"+recordid);

    }

    @Override
    public void ondeleteincome2(final int recordid) {
        dialog.setContentView(R.layout.delete_pop_up);
        /*LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.delete_pop_up, (ViewGroup)findViewById(R.id.deletePopUp));
        TextView message = vie*/

        TextView message = dialog.findViewById(R.id.message);
        message.setText("Are you sure to delete this resource?");
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button delete = dialog.findViewById(R.id.positiveBtn);
        Button cancel = dialog.findViewById(R.id.negativeBtn);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
                boolean status = databaseHelper.deleteincomerecord(recordid);
                ToastMessage toastMessage = new ToastMessage(NoAppBarActivity.this, View.inflate(getApplicationContext(), R.layout.income2layout,null));

                if (status) {
                    toastMessage.successToast("Successfully Deleted");
                } else {
                    toastMessage.errorToast("Delete Failed");
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}