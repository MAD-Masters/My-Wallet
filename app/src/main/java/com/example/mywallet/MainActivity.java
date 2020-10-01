package com.example.mywallet;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mywallet.UI.BudgetManager.Budget1;

import com.example.mywallet.UI.BudgetManager.BudgetAdapter;
import com.example.mywallet.UI.BudgetManager.Model.Budget2;
import com.example.mywallet.UI.Expenses.AddExpense;

import com.example.mywallet.UI.Expenses.DailyExpenseAdapter;
import com.example.mywallet.UI.Expenses.DailyExpenseSummaryAdapter;
import com.example.mywallet.UI.Expenses.DailyExpensesInDetail;
import com.example.mywallet.UI.Expenses.Home;
import com.example.mywallet.UI.Goal.Goal1;
import com.example.mywallet.UI.Goal.GoalAdapter;
import com.example.mywallet.UI.Goal.Goal_Home;
import com.example.mywallet.UI.Income.Income;
import com.example.mywallet.UI.Income.Income2;
import com.example.mywallet.UI.Income.Income3;
import com.example.mywallet.UI.Income.Income5;
import com.example.mywallet.UI.Income.Incomeadapter;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements DailyExpenseSummaryAdapter.onDailyExpenseSummaryClick, DailyExpenseAdapter.DailyExpenseInterface, Incomeadapter.IncomeInterface, GoalAdapter.GoalInterface, BudgetAdapter.BudgetInterface {
    BottomAppBar bottomAppBar;
    ImageView navExpenseBtn, navIncomeBtn, navBudgetBtn, navGoalBtn;
    TextView navExpenseText, navIncomeText, navBudgetText, navGoalText;
    FloatingActionButton floatingActionButton;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        String fragment = intent.getStringExtra("Fragment");

        if (fragment != null) {
            if (fragment.equals("ExpenseInDetail")) {
                DailyExpensesInDetail dailyExpensesInDetail = new DailyExpensesInDetail();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.noAppBarFragmentContainer, dailyExpensesInDetail);
                fragmentTransaction.commit();
            }
        } else {
            Home home = new Home();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.nav_host_fragment, home);
            fragmentTransaction.commit();
        }



        floatingActionButton = findViewById(R.id.fab);

        navExpenseBtn = findViewById(R.id.btnExpenses);
        navIncomeBtn = findViewById(R.id.btnIncome);
        navBudgetBtn = findViewById(R.id.btnBudget);
        navGoalBtn = findViewById(R.id.btnGoal);
        navExpenseText = findViewById(R.id.textExpenses);
        navIncomeText = findViewById(R.id.textIncome);
        navGoalText = findViewById(R.id.textGoal);
        navBudgetText = findViewById(R.id.textBudget);

        //Dialog
        dialog = new Dialog(this);

        setUpBottomAppBar();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NoAppBarActivity.class);
                intent.putExtra("Fragment", "Add Expense");
                startActivity(intent);
            }
        });
    }


    public void setUpBottomAppBar() {
        bottomAppBar = findViewById(R.id.nav_view);

        navExpenseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Home home = new Home();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, home);
                fragmentTransaction.commit();
            }
        });

        navIncomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Income income = new Income();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, income);
                fragmentTransaction.commit();
            }
        });

        navGoalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Goal_Home goalHome = new Goal_Home();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, goalHome);
                fragmentTransaction.commit();
            }
        });

        navBudgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Budget1 budget1 = new Budget1();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, budget1);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void onClickDailyExpItem(String date) {
        DailyExpensesInDetail dailyExpensesInDetail = new DailyExpensesInDetail();
        Bundle result = new Bundle();
        result.putString("date", date);
        dailyExpensesInDetail.setArguments(result);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, dailyExpensesInDetail, "EXPENSEinDetail");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onDeletBtnExInClick(final int recordId) {
        dialog.setContentView(R.layout.delete_pop_up);
        TextView message = dialog.findViewById(R.id.message);
        message.setText("Are you sure to delete this record?");
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
                boolean status = databaseHelper.deleteExpenseRecord(recordId);
                ToastMessage toastMessage = new ToastMessage(MainActivity.this, View.inflate(getApplicationContext(), R.layout.item_daily_expenses,null));

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

    @Override
    public void onUpdateBtnExInClick(int recordId) {
        Intent intent = new Intent(MainActivity.this,NoAppBarActivity.class);
        intent.putExtra("Fragment", "Update Expenses");
        intent.putExtra("id", recordId);
        startActivity(intent);
    }

    @Override
    public void onBtnTitleincome(int walletid) {
        Intent intent = new Intent(MainActivity.this,NoAppBarActivity.class);
        intent.putExtra("Fragment", "titleincome");
        intent.putExtra("walletid",walletid);
        startActivity(intent);
        startActivity(intent);

    }

    @Override
    public void onUpdateBtnincome(int walletid) {
        Intent intent = new Intent(MainActivity.this,NoAppBarActivity.class);
        intent.putExtra("Fragment", "updateincome");
        intent.putExtra("walletid",walletid);
        startActivity(intent);
        System.out.println("wlletid"+walletid);
    }

    @Override
    public void onaddBtnincome(int walletid) {
        Intent intent = new Intent(MainActivity.this,NoAppBarActivity.class);
        intent.putExtra("Fragment", "addincome");
        intent.putExtra("id",walletid);
        startActivity(intent);

    }



    @Override
    public void ondeleteincome1(final int walletid) {

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
                boolean status = databaseHelper.deleteWallet(walletid);
                ToastMessage toastMessage = new ToastMessage(MainActivity.this, View.inflate(getApplicationContext(), R.layout.income1layout,null));

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

    public void oneditBtnincome()
    {
        Income3 income3  = new Income3();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, income3);
    }


    @Override
    public void onDeletBtnGoInClick() {
        dialog.setContentView(R.layout.delete_pop_up);
        TextView message = dialog.findViewById(R.id.message);
        message.setText("Are you sure to delete this Goal?");
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void onAddBtnGoalClick() {
        Goal1 goal1 = new Goal1();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, goal1);

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

