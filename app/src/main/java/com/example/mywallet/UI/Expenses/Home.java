package com.example.mywallet.UI.Expenses;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mywallet.DatabaseHelper;
import com.example.mywallet.DatabaseObserver;
import com.example.mywallet.R;
import com.example.mywallet.Model.DailyExpesnseSummary;
import com.example.mywallet.Model.MonthlySummary;
import com.example.mywallet.UserSettings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Home extends Fragment implements DatabaseObserver {
    private ViewPager viewPager;
    private MonthlyExpenseViewPagerAdapter monthlyExpenseViewPagerAdapter;

    private View root;
    private ImageView greetingImage;
    private DatabaseHelper dbHelper;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = DatabaseHelper.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setPageContent();
    }

    @Override
    public void onResume() {
        super.onResume();
        dbHelper.registerDbObserver(this);
        Log.d("db", "onResume: db observer register");
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onDatabaseChanged() {
        Log.d("db", "Home DB Changed");
        setPageContent();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setPageContent() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        String greeting = null;
        greetingImage = root.findViewById(R.id.greetingImage);
        TextView greetings = root.findViewById(R.id.greeting);

        if(timeOfDay >= 0 && timeOfDay < 12){
            greeting = "Good Morning";
            greetingImage.setImageResource(R.drawable.morning);
            greetings.setTextColor(getResources().getColor(R.color.yellowLight));
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            greeting = "Good Afternoon";
            greetingImage.setImageResource(R.drawable.afternoon);
            greetings.setTextColor(getResources().getColor(R.color.yellowLight));
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            greeting = "Good Evening";
            greetingImage.setImageResource(R.drawable.evening);
            greetings.setTextColor(getResources().getColor(R.color.yellowLight));
        }else if(timeOfDay >= 21 && timeOfDay < 24){
            greeting = "Good Night";
            greetingImage.setImageResource(R.drawable.night);
            greetings.setTextColor(getResources().getColor(R.color.yellowLight));
        }

        greetings.setText(greeting);

        ExpenseServicesImple expenseServicesImple = new ExpenseServicesImple();
        int dates[][] = new int[0][];
        try {
            dates = expenseServicesImple.getArrayForViewPager(getContext());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Setting up view pager
        viewPager = root.findViewById(R.id.viewPager);
        monthlyExpenseViewPagerAdapter = new MonthlyExpenseViewPagerAdapter(getContext(), dates);

        viewPager.setAdapter(monthlyExpenseViewPagerAdapter);
        viewPager.setCurrentItem(dates.length - 1);

        ImageView user = root.findViewById(R.id.userImg);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserSettings.class);
                startActivity(intent);
            }
        });
    }
}