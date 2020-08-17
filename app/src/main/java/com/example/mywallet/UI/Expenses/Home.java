package com.example.mywallet.UI.Expenses;

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
import android.widget.Button;

import com.example.mywallet.R;
import com.example.mywallet.UI.Expenses.Model.DailyExpense;
import com.example.mywallet.UI.Expenses.Model.DailyExpesnseSummary;

import java.util.ArrayList;
import java.util.Calendar;

public class Home extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<DailyExpesnseSummary> dailyExpesnseSummaryArrayList;
    private DailyExpenseSummaryAdapter dailyExpenseAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private View root;

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

        dailyExpesnseSummaryArrayList = new ArrayList<>();

        dailyExpesnseSummaryArrayList.add(new DailyExpesnseSummary(Calendar.getInstance().getTime(), 1000));
        dailyExpesnseSummaryArrayList.add(new DailyExpesnseSummary(Calendar.getInstance().getTime(), 5000));

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        dailyExpenseAdapter = new DailyExpenseSummaryAdapter(getContext(), dailyExpesnseSummaryArrayList);
        recyclerView.setAdapter(dailyExpenseAdapter);
    }
}