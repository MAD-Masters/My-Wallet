package com.example.mywallet.UI.Expenses;

import com.example.mywallet.Model.DailyExpense;
import com.example.mywallet.Model.IncomeToWallet;
import com.example.mywallet.Model.MonthlySummary;

import java.util.ArrayList;

public class ExpenseServicesImple {
    public MonthlySummary getMonthlySummary(ArrayList<DailyExpense> dailyExpenses, ArrayList<IncomeToWallet> incomeToWallets) {
        MonthlySummary monthlySummary = new MonthlySummary();
        double bill = 0.00, edu = 0.00, family = 0.00, gifts= 0.00, food= 0.00, loan= 0.00, inflow= 0.00, remainder = 0.00, outflow = 0.00;

        for (DailyExpense dailyExpense : dailyExpenses) {
            outflow = outflow + dailyExpense.getAmount();

            int category = dailyExpense.getCategoryId();

            switch (category){
                case 0:
                    bill = bill + dailyExpense.getAmount();
                    break;
                case 1:
                    edu = edu + dailyExpense.getAmount();
                    break;
                case 2:
                    family = family + dailyExpense.getAmount();
                    break;
                case 3:
                    gifts = gifts + dailyExpense.getAmount();
                    break;
                case 4:
                    food = food + dailyExpense.getAmount();
                    break;
                case 5:
                    loan = loan + dailyExpense.getAmount();
                    break;
            }
        }

        for (IncomeToWallet incomeToWallet : incomeToWallets) {
            inflow = inflow + incomeToWallet.getAmount();
        }

        remainder = inflow - outflow;

        monthlySummary.setInflow((float) inflow);
        monthlySummary.setOutflow((float) outflow);
        monthlySummary.setRemainder((float) remainder);
        monthlySummary.setmEdu((float) edu);
        monthlySummary.setmLoans((float) loan);
        monthlySummary.setmGifts((float) gifts);
        monthlySummary.setmFamily((float) family);
        monthlySummary.setmBills((float) bill);
        monthlySummary.setmFamily((float) food);

        return monthlySummary;
    }
}
