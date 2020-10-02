package com.example.mywallet.UI.Expenses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mywallet.DatabaseHelper;
import com.example.mywallet.DatabaseObserver;
import com.example.mywallet.Model.DailyExpesnseSummary;
import com.example.mywallet.R;
import com.example.mywallet.Model.DailyExpense;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class DailyExpensesInDetail extends Fragment implements DatabaseObserver {
    private RecyclerView recyclerView;
    private ArrayList<DailyExpense> dailyExpenseArrayList;
    private DailyExpenseAdapter dailyExpenseAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private View root;
    private TextView dateH, totalAmount;
    private DailyExpesnseSummary dailyExpesnseSummary;
    private ArrayList<DailyExpense> dailyExpenses;
    private String dateNew;
    private DatabaseHelper dbHelper;

    public DailyExpensesInDetail() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        dateNew = bundle.getString("date");
        dbHelper = DatabaseHelper.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_daily_expenses_in_detail, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Get expenses by Date
        try {
            dailyExpenseArrayList = dbHelper.getDailyExpensesByDate(dateNew);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        setPageContentData();

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = format.format(dailyExpenseArrayList.get(0).getDate());

        dateH.setText(dateString);
    }

    @Override
    public void onResume() {
        super.onResume();
        dbHelper.registerDbObserver(this);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDatabaseChanged() {
        setPageContentData();
    }

    public void setPageContentData() {
        try {
            dailyExpenseArrayList = dbHelper.getDailyExpensesByDate(dateNew);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        TextView textView = root.findViewById(R.id.warning);

        if (dailyExpenseArrayList.size() == 0) {
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.INVISIBLE);
        }
        dateH = root.findViewById(R.id.dateN);
        totalAmount = root.findViewById(R.id.totalAmount);

        recyclerView = root.findViewById(R.id.list_daily_expenses_in_detail);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        dailyExpenseAdapter = new DailyExpenseAdapter(getContext(), dailyExpenseArrayList);
        recyclerView.setAdapter(dailyExpenseAdapter);

        ExpenseServicesImple expenseServicesImple = new ExpenseServicesImple();
        totalAmount.setText(String.valueOf(expenseServicesImple.getTotalExpenses(dailyExpenseArrayList)));
    }
}