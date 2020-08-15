package com.example.mywallet.UI.Income;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mywallet.R;
import com.example.mywallet.UI.Expenses.DailyExpenseAdapter;
import com.example.mywallet.UI.Expenses.Model.DailyExpense;
import com.example.mywallet.UI.Income.Model.IncomeModel;

import java.util.ArrayList;
import java.util.Calendar;

public class Income extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<IncomeModel> incomeModelArrayListList;
    private Incomeadapter incomeadapter;
    private RecyclerView.LayoutManager layoutManager;
    private View root;


    public Income() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_income, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        incomeModelArrayListList = new ArrayList<>();

        incomeModelArrayListList.add(new IncomeModel("hnb bank",1,Calendar.getInstance().getTime(),40000,"ttt"));
        incomeModelArrayListList.add(new IncomeModel("wallet",2,Calendar.getInstance().getTime(),30000,"rrr"));

        recyclerView = root.findViewById(R.id.list_income);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        incomeadapter = new Incomeadapter(incomeModelArrayListList);
        recyclerView.setAdapter(incomeadapter);
    }
}