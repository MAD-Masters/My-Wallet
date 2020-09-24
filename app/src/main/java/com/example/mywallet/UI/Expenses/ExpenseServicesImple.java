package com.example.mywallet.UI.Expenses;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.mywallet.DatabaseHelper;
import com.example.mywallet.Model.DailyExpense;
import com.example.mywallet.Model.DailyExpesnseSummary;
import com.example.mywallet.Model.IncomeToWallet;
import com.example.mywallet.Model.MonthlySummary;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

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
        monthlySummary.setmFood((float) food);

        return monthlySummary;
    }

    public ArrayList<DailyExpesnseSummary> getDailyExpenseSummary(ArrayList<DailyExpense> dailyExpenses) {
        ArrayList<DailyExpesnseSummary> dailyExpesnseSummaries = new ArrayList<>();
        Map<Date,Double> newMap = new TreeMap<>();
        for (DailyExpense dailyExpense : dailyExpenses) {
            if (newMap.containsKey(dailyExpense.getDate())) {
                Double amount = newMap.get(dailyExpense.getDate());
                newMap.put(dailyExpense.getDate(), amount+dailyExpense.getAmount());
            } else {
                newMap.put(dailyExpense.getDate(), dailyExpense.getAmount());
            }
        }

        Set<Date> keyValues = newMap.keySet();

        for(Date mDate : keyValues) {
            DailyExpesnseSummary dailyExpesnseSummary = new DailyExpesnseSummary();
            dailyExpesnseSummary.setDate(mDate);
            dailyExpesnseSummary.setTotalAmount(newMap.get(mDate));

            dailyExpesnseSummaries.add(dailyExpesnseSummary);
        }
        System.out.println("hello" + dailyExpesnseSummaries.size());
        return dailyExpesnseSummaries;
    }

    public ArrayList<DailyExpense> getExpenseArrayListByDay(String date, ArrayList<DailyExpense> dailyExpenseArrayList) {
        ArrayList<DailyExpense> dailyExpenses = new ArrayList<>();
        for (DailyExpense dailyExpense : dailyExpenseArrayList) {
            if (dailyExpense.getDate().toString().equals(date)) {
                dailyExpenses.add(dailyExpense);
            }
        }
        return dailyExpenses;
    }

    public double getTotalExpenses(ArrayList<DailyExpense> dailyExpenses) {
        double total = 0.0;

        for (DailyExpense dailyExpense : dailyExpenses) {
            total = total + dailyExpense.getAmount();
        }

        return total;
    }

    //This method will find out the int array for viewPager
    @RequiresApi(api = Build.VERSION_CODES.O)
    public int[][] getArrayForViewPager(Context context) throws ParseException {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        ArrayList<Date> datesFromIncome = databaseHelper.getDatesFromIncome();
        ArrayList<Date> datesFromExpenses = databaseHelper.getDatesFromExpenses();

        Date startDate;
        Date endDate = Calendar.getInstance().getTime();

        if (datesFromExpenses.size() == 0 && datesFromIncome.size() == 0) {
            DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            SharedPreferences sharedPreferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
            String dateS = sharedPreferences.getString("date", Calendar.getInstance().getTime().toString());
            startDate = formatter.parse(dateS);
        } else {

            TreeSet<Date> dateTreeSetIncome = new TreeSet<>(datesFromIncome);
            TreeSet<Date> dateTreeSetExpenses = new TreeSet<>(datesFromExpenses);

            if (dateTreeSetExpenses.size() == 0) {
                startDate = dateTreeSetIncome.first();
            } else if (dateTreeSetIncome.size() == 0) {
                startDate = dateTreeSetExpenses.first();
            } else if (dateTreeSetIncome.first().compareTo(dateTreeSetExpenses.first()) >= 0) {
                startDate = dateTreeSetExpenses.first();
            } else {
                startDate = dateTreeSetIncome.first();
            }
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        /*YearMonth startYearMonth = YearMonth.from(LocalDate.parse(dateFormat.format(startDate)));
        YearMonth endYearMonth = YearMonth.from(LocalDate.parse(dateFormat.format(endDate)));
        long monthsBetween = ChronoUnit.MONTHS.between(startYearMonth, endYearMonth);*/

        int startMonth = startDate.getMonth();
        int startYear = startDate.getYear();

        int endMonth = endDate.getMonth();
        int endYear = endDate.getYear();

        int yearDifference = endYear - startYear;

        int monthDifference = 0;
        if (yearDifference == 0) {
            monthDifference = endMonth - startMonth;
        } else if (yearDifference == 1) {
            monthDifference = endMonth + (12 - startMonth);
        } else {
            monthDifference = 12 * (yearDifference - 1) + endMonth + (12 - startMonth);
        }

        int monthsBetween = yearDifference * 12 + monthDifference;

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);

        int array[][] = new int[(int) monthsBetween + 1][2];

        for (int i = -1; i < monthsBetween; i++) {
            if (month == 12) {
                month = 0;
                year = year + 1;
            }
            array[i+1][0] = month++;
            array[i+1][1] = year;
        }

        for (int i = -1; i < monthsBetween; i++) {
            System.out.println(array[i+1][0] + " " +array[i+1][1] );
        }

        return array;
    }
}
