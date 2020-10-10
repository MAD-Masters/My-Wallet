package com.example.mywallet;

import com.example.mywallet.Model.DailyExpense;
import com.example.mywallet.Model.IncomeToWallet;
import com.example.mywallet.UI.Expenses.DailyExpenseAdapter;
import com.example.mywallet.UI.Expenses.ExpenseServicesImple;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {
    private ExpenseServicesImple expenseServicesImple;
    @Before
    public void setUp() {
        expenseServicesImple = new ExpenseServicesImple();
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
}