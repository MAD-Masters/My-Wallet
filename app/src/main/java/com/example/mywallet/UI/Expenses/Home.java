package com.example.mywallet.UI.Expenses;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mywallet.R;
import com.example.mywallet.UI.Expenses.Model.DailyExpense;
import com.example.mywallet.UI.Expenses.Model.DailyExpesnseSummary;
import com.example.mywallet.UI.Expenses.Model.MonthlySummary;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Home extends Fragment {
    //private RecyclerView recyclerView;
    private ArrayList<DailyExpesnseSummary> dailyExpesnseSummaryArrayList;
    //private DailyExpenseSummaryAdapter dailyExpenseAdapter;
    //private RecyclerView.LayoutManager layoutManager;
    private ViewPager viewPager;
    private MonthlyExpenseViewPagerAdapter monthlyExpenseViewPagerAdapter;
    private ArrayList<MonthlySummary> monthlySummaryArrayList;

    private View root;
    private ImageView greetingImage;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        String greeting = null;
        greetingImage = root.findViewById(R.id.greetingImage);
        TextView greetings = root.findViewById(R.id.greeting);

        if(timeOfDay >= 0 && timeOfDay < 12){
            greeting = "Good Morning";
            greetingImage.setImageResource(R.drawable.morning);
            greetings.setTextColor(getResources().getColor(R.color.primaryColor));
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            greeting = "Good Afternoon";
            greetingImage.setImageResource(R.drawable.afternoon);
            greetings.setTextColor(getResources().getColor(R.color.colorPink));
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            greeting = "Good Evening";
            greetingImage.setImageResource(R.drawable.evening);
            greetings.setTextColor(getResources().getColor(R.color.primaryLightColor));
        }else if(timeOfDay >= 21 && timeOfDay < 24){
            greeting = "Good Night";
            greetingImage.setImageResource(R.drawable.night);
            greetings.setTextColor(getResources().getColor(R.color.yellowLight));
        }

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        String dateString = format.format( new Date());

        TextView dateDisplay = root.findViewById(R.id.dateDisplay);
        dateDisplay.setText(dateString);


        greetings.setText(greeting);

        dailyExpesnseSummaryArrayList = new ArrayList<>();

        dailyExpesnseSummaryArrayList.add(new DailyExpesnseSummary(Calendar.getInstance().getTime(), 1000));
        dailyExpesnseSummaryArrayList.add(new DailyExpesnseSummary(Calendar.getInstance().getTime(), 5000));
/*
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        dailyExpenseAdapter = new DailyExpenseSummaryAdapter(getContext(), dailyExpesnseSummaryArrayList);
        recyclerView.setAdapter(dailyExpenseAdapter);*/

        monthlySummaryArrayList = new ArrayList<>();
        monthlySummaryArrayList.add(new MonthlySummary("August", 500, 500, 100, 230, 0, 0, 0, 0, 0, dailyExpesnseSummaryArrayList));
        monthlySummaryArrayList.add(new MonthlySummary("September", 500, 500, 100, 230, 0, 0, 0, 0, 0, dailyExpesnseSummaryArrayList));

        //Setting up view pager
        viewPager = root.findViewById(R.id.viewPager);
        System.out.println("Hello");
        monthlyExpenseViewPagerAdapter = new MonthlyExpenseViewPagerAdapter(getContext(), monthlySummaryArrayList);

        viewPager.setAdapter(monthlyExpenseViewPagerAdapter);
        viewPager.setCurrentItem(monthlySummaryArrayList.size() - 1);
    }
}