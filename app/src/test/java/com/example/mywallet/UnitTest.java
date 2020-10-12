package com.example.mywallet;

import com.example.mywallet.Model.Budgetmodel;
import com.example.mywallet.Model.DailyExpense;
import com.example.mywallet.Model.FutureGoal;
import com.example.mywallet.Model.IncomeToWallet;
import com.example.mywallet.UI.BudgetManager.Budget1;
import com.example.mywallet.UI.Expenses.DailyExpenseAdapter;
import com.example.mywallet.UI.Expenses.ExpenseServicesImple;
import com.example.mywallet.UI.Goal.Goal2;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UnitTest {
    private ExpenseServicesImple expenseServicesImple;
    private Budget1 budget1;
    private Goal2 goal2;
    @Before
    public void setUp() {
        expenseServicesImple = new ExpenseServicesImple();
        budget1 = new Budget1();
        goal2 =new Goal2();
    }
    @org.junit.Test
    public void totalWalletIncome_isCorrect() {
        IncomeToWallet i1 = new IncomeToWallet(), i2 = new IncomeToWallet();
        i1.setAmount(10000);
        i2.setAmount(20000);
        ArrayList<IncomeToWallet> arrayList = new ArrayList<>();
        arrayList.add(i1);
        arrayList.add(i2);

        double total = expenseServicesImple.getTotalWalletIncome(arrayList);
        assertEquals(30000.0, total, 0.1);
    }

    @org.junit.Test
    public void totalExpenses_isCorrect() {
        DailyExpense d1 = new DailyExpense(), d2 = new DailyExpense();
        d1.setAmount(1000);
        d2.setAmount(1500);
        ArrayList<DailyExpense> arrayList = new ArrayList<>();
        arrayList.add(d1);
        arrayList.add(d2);

        double total = expenseServicesImple.getTotalExpenses(arrayList);
        assertEquals(25000.0, total, 0.1);
    }

    @Test
    public void categoryName_isCorrect() {
        DailyExpenseAdapter dailyExpenseAdapter = new DailyExpenseAdapter();
        String category =  dailyExpenseAdapter.getCategoryName(1);
        assertEquals("Bill Payment", category);
    }

    @Test
    public void getTotalUsed_isCorrect() {
        Budgetmodel b1 = new Budgetmodel(), b2 = new Budgetmodel();
        b1.setUsedAmount(1000);
        b2.setUsedAmount(2000);
        ArrayList<Budgetmodel> budgetmodels = new ArrayList<>();

        double total = budget1.getTotalUsed(budgetmodels);
        assertEquals(3000.0, total, 0.1);
    }

    @Test
    public void getTotalBudge_isCorrect() {
        Budgetmodel b1 = new Budgetmodel(), b2 = new Budgetmodel();
        b1.setAmount(5000);
        b2.setAmount(2000);
        ArrayList<Budgetmodel> budgetmodels = new ArrayList<>();
        budgetmodels.add(b1);
        budgetmodels.add(b2);
        double total = budget1.getTotalBudget(budgetmodels);
        assertEquals(7000.0, total, 0.1);
    }
    @Test
    public void getTotalAmount_isCorrect() {
        FutureGoal g1 = new FutureGoal(), g2 = new FutureGoal();
        g1.setCurrentAmount(5000);
        g2.setCurrentAmount(2000);
        ArrayList<FutureGoal> futureGoalArrayList = new ArrayList<>();
        futureGoalArrayList.add(g1);
        futureGoalArrayList.add(g2);

        double total = goal2.getTotalAmount(futureGoalArrayList);
        assertEquals(7000.0, total, 0.1);
    }
}