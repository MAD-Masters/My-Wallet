package com.example.mywallet.UI.Expenses;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.mywallet.DatabaseHelper;
import com.example.mywallet.Model.DailyExpense;
import com.example.mywallet.Model.IncomeToWallet;
import com.example.mywallet.R;
import com.example.mywallet.Model.DailyExpesnseSummary;
import com.example.mywallet.Model.MonthlySummary;

import java.text.ParseException;
import java.util.ArrayList;

public class MonthlyExpenseViewPagerAdapter extends PagerAdapter {
    Context context;
    int[][] dates;
    private RecyclerView recyclerView;
    private ArrayList<DailyExpesnseSummary> dailyExpesnseSummaryArrayList;
    private DailyExpenseSummaryAdapter dailyExpenseAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public MonthlyExpenseViewPagerAdapter(Context context, int[][] dates) {
        this.context = context;
        this.dates = dates;
    }

    @Override
    public int getCount() {
        return dates.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.monthly_expenses_layout, null);

        TextView currentMonth = layoutScreen.findViewById(R.id.currentMonth);
        TextView inflow = layoutScreen.findViewById(R.id.inflow);
        TextView outflow = layoutScreen.findViewById(R.id.outflow);
        TextView remainder = layoutScreen.findViewById(R.id.remainder);
        TextView food = layoutScreen.findViewById(R.id.food);
        TextView education = layoutScreen.findViewById(R.id.education);
        TextView loan = layoutScreen.findViewById(R.id.loan);
        TextView family = layoutScreen.findViewById(R.id.family);
        TextView gifts = layoutScreen.findViewById(R.id.gifts);
        TextView bills = layoutScreen.findViewById(R.id.bills);
        TextView titleDaily = layoutScreen.findViewById(R.id.dailyExpensesTitle);

        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        ArrayList<DailyExpense> dailyExpenseArrayList = new ArrayList<>();
        ArrayList<IncomeToWallet> incomeToWalletArrayList = new ArrayList<>();

        try {
            System.out.println("Month " + dates[position][0]);
            dailyExpenseArrayList = databaseHelper.getMonthlyExpenses(dates[position][0], dates[position][1]);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ExpenseServicesImple expenseServicesImple = new ExpenseServicesImple();
        MonthlySummary monthlySummary = expenseServicesImple.getMonthlySummary(dailyExpenseArrayList, incomeToWalletArrayList);

        String[] monthArray = {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUN", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};
        String monthString = monthArray[dates[position][0]];

        currentMonth.setText(monthString);
        inflow.setText(String.valueOf(monthlySummary.getInflow()));
        outflow.setText(String.valueOf(monthlySummary.getOutflow()));
        remainder.setText(String.valueOf(monthlySummary.getRemainder()));
        food.setText(String.valueOf(monthlySummary.getmFood()));
        bills.setText(String.valueOf(monthlySummary.getmBills()));
        family.setText(String.valueOf(monthlySummary.getmFamily()));
        gifts.setText(String.valueOf(monthlySummary.getmGifts()));
        loan.setText(String.valueOf(monthlySummary.getmLoans()));
        education.setText(String.valueOf(monthlySummary.getmEdu()));


        recyclerView = layoutScreen.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<DailyExpesnseSummary> dailyExpesnseSummaryArrayList = expenseServicesImple.getDailyExpenseSummary(dailyExpenseArrayList);
        dailyExpenseAdapter = new DailyExpenseSummaryAdapter(context, dailyExpesnseSummaryArrayList, dailyExpenseArrayList);
        recyclerView.setAdapter(dailyExpenseAdapter);

        if (dailyExpesnseSummaryArrayList.size() == 0) {
            titleDaily.setVisibility(View.INVISIBLE);
        }

        container.addView(layoutScreen);

        return layoutScreen;
    }
}
