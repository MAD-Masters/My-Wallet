package com.example.mywallet.UI.Expenses;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mywallet.OnBoarding.ScreenItem;
import com.example.mywallet.R;
import com.example.mywallet.UI.Expenses.Model.DailyExpesnseSummary;
import com.example.mywallet.UI.Expenses.Model.MonthlySummary;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MonthlyExpenseViewPagerAdapter extends PagerAdapter {
    Context context;
    List<MonthlySummary> monthlySummaryList;
    private RecyclerView recyclerView;
    private ArrayList<DailyExpesnseSummary> dailyExpesnseSummaryArrayList;
    private DailyExpenseSummaryAdapter dailyExpenseAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public MonthlyExpenseViewPagerAdapter(Context context, List<MonthlySummary> monthlySummaryList) {
        this.context = context;
        this.monthlySummaryList = monthlySummaryList;
    }
    @Override
    public int getCount() {
        return monthlySummaryList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

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

        currentMonth.setText(monthlySummaryList.get(position).getMonth());
        inflow.setText(String.valueOf(monthlySummaryList.get(position).getInflow()));
        outflow.setText(String.valueOf(monthlySummaryList.get(position).getOutflow()));
        remainder.setText(String.valueOf(monthlySummaryList.get(position).getRemainder()));
        food.setText(String.valueOf(monthlySummaryList.get(position).getmFood()));
        bills.setText(String.valueOf(monthlySummaryList.get(position).getmBills()));
        family.setText(String.valueOf(monthlySummaryList.get(position).getmFamily()));
        gifts.setText(String.valueOf(monthlySummaryList.get(position).getmGifts()));
        loan.setText(String.valueOf(monthlySummaryList.get(position).getmLoans()));
        education.setText(String.valueOf(monthlySummaryList.get(position).getmEdu()));


        dailyExpesnseSummaryArrayList = new ArrayList<>();

        dailyExpesnseSummaryArrayList.add(new DailyExpesnseSummary(Calendar.getInstance().getTime(), 1000));
        dailyExpesnseSummaryArrayList.add(new DailyExpesnseSummary(Calendar.getInstance().getTime(), 5000));

        recyclerView = layoutScreen.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        dailyExpenseAdapter = new DailyExpenseSummaryAdapter(context, dailyExpesnseSummaryArrayList);
        recyclerView.setAdapter(dailyExpenseAdapter);

        container.addView(layoutScreen);

        return layoutScreen;
    }
}
