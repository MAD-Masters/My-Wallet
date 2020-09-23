package com.example.mywallet.UI.Expenses;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mywallet.Model.DailyExpesnseSummary;
import com.example.mywallet.R;
import com.example.mywallet.Model.DailyExpense;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class DailyExpensesInDetail extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<DailyExpense> dailyExpenseArrayList;
    private DailyExpenseAdapter dailyExpenseAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private View root;
    private TextView dateH, totalAmount;
    private DailyExpesnseSummary dailyExpesnseSummary;
    private ArrayList<DailyExpense> dailyExpenses;

    public DailyExpensesInDetail() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        dailyExpenses = bundle.getParcelableArrayList("dailyArray");
        dailyExpesnseSummary = bundle.getParcelable("summary");
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

        ExpenseServicesImple expenseServicesImple = new ExpenseServicesImple();
        dailyExpenseArrayList = expenseServicesImple.getExpenseArrayListByDay(dailyExpesnseSummary.getDate().toString(), dailyExpenses);

        recyclerView = root.findViewById(R.id.list_daily_expenses_in_detail);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        dailyExpenseAdapter = new DailyExpenseAdapter(getContext(), dailyExpenseArrayList);
        recyclerView.setAdapter(dailyExpenseAdapter);

        dateH = root.findViewById(R.id.dateN);
        totalAmount = root.findViewById(R.id.totalAmount);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = format.format(dailyExpesnseSummary.getDate());

        dateH.setText(dateString);
        totalAmount.setText(String.valueOf(dailyExpesnseSummary.getTotalAmount()));
    }
}