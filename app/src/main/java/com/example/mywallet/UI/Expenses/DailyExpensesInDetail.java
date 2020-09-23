package com.example.mywallet.UI.Expenses;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mywallet.R;
import com.example.mywallet.Model.DailyExpense;

import java.util.ArrayList;
import java.util.Calendar;


public class DailyExpensesInDetail extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<DailyExpense> dailyExpenseArrayList;
    private DailyExpenseAdapter dailyExpenseAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private View root;

    public DailyExpensesInDetail() {
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
        root = inflater.inflate(R.layout.fragment_daily_expenses_in_detail, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        dailyExpenseArrayList = new ArrayList<>();

        dailyExpenseArrayList.add(new DailyExpense(Calendar.getInstance().getTime(), 1000, 1, 1, "For buying a bag"));
        dailyExpenseArrayList.add(new DailyExpense(Calendar.getInstance().getTime(), 890, 1, 1, "Taxi"));

        recyclerView = root.findViewById(R.id.list_daily_expenses_in_detail);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        dailyExpenseAdapter = new DailyExpenseAdapter(getContext(), dailyExpenseArrayList);
        recyclerView.setAdapter(dailyExpenseAdapter);
    }
}