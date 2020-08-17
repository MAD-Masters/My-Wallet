package com.example.mywallet;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mywallet.UI.BudgetManager.Budget1;
import com.example.mywallet.UI.Expenses.AddExpense;
import com.example.mywallet.UI.Expenses.DailyExpenseAdapter;
import com.example.mywallet.UI.Expenses.DailyExpenseSummaryAdapter;
import com.example.mywallet.UI.Expenses.DailyExpensesInDetail;
import com.example.mywallet.UI.Expenses.Home;
import com.example.mywallet.UI.Expenses.UpdateExpense;
import com.example.mywallet.UI.Goal.Goal;
import com.example.mywallet.UI.Goal.Goal_Home;
import com.example.mywallet.UI.Income.Income;
import com.example.mywallet.UI.Income.Income2;
import com.example.mywallet.UI.Income.Income3;
import com.example.mywallet.UI.Income.Income4;
import com.example.mywallet.UI.Income.Incomeadapter;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity implements DailyExpenseSummaryAdapter.onDailyExpenseSummaryClick, DailyExpenseAdapter.DailyExpenseInterface, Incomeadapter.IncomeInterface {
    BottomAppBar bottomAppBar;
    ImageView navExpenseBtn, navIncomeBtn, navBudgetBtn, navGoalBtn;
    TextView navExpenseText, navIncomeText, navBudgetText, navGoalText;
    FloatingActionButton floatingActionButton;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Home home = new Home();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.nav_host_fragment, home);
        fragmentTransaction.commit();

        floatingActionButton = findViewById(R.id.fab);

        navExpenseBtn = findViewById(R.id.btnExpenses);
        navIncomeBtn = findViewById(R.id.btnIncome);
        navBudgetBtn = findViewById(R.id.btnBudget);
        navGoalBtn = findViewById(R.id.btnGoal);
        navExpenseText = findViewById(R.id.textExpenses);
        navIncomeText = findViewById(R.id.textIncome);
        navGoalText = findViewById(R.id.textGoal);
        navBudgetText = findViewById(R.id.textBudget);

        setUpBottomAppBar();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NoAppBarActivity.class);
                intent.putExtra("Fragment", "AddExpense");
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
    public void onClickDailyExpItem() {
        DailyExpensesInDetail dailyExpensesInDetail = new DailyExpensesInDetail();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, dailyExpensesInDetail);
        fragmentTransaction.commit();
    }

    @Override
    public void onDeletBtnExInClick() {
    }

    @Override
    public void onUpdateBtnExInClick() {
        UpdateExpense updateExpense = new UpdateExpense();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, updateExpense);
        fragmentTransaction.commit();
    }

    @Override
    public void onBtnTitleincome() {
        Income2 income = new Income2();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, income);
        fragmentTransaction.commit();

    }

    @Override
    public void onUpdateBtnincome() {
        Income3 income3 = new Income3();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, income3);
        fragmentTransaction.commit();

    }

    @Override
    public void onaddBtnincome() {
        Income4 income4 = new Income4();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, income4);
        fragmentTransaction.commit();

    }
}