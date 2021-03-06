package com.example.mywallet.UI.BudgetManager;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mywallet.DatabaseHelper;
import com.example.mywallet.DatabaseObserver;
import com.example.mywallet.Model.Wallet;
import com.example.mywallet.NoAppBarActivity;
import com.example.mywallet.R;
import com.example.mywallet.Model.Budgetmodel;

import com.example.mywallet.UI.BudgetManager.Budget2;
import com.example.mywallet.UI.Income.Income4;
import com.example.mywallet.UI.Income.Incomeadapter;
import com.example.mywallet.Model.IncomeModel;


import java.text.ParseException;
import java.util.ArrayList;

public class Budget1 extends Fragment implements DatabaseObserver {

    private RecyclerView recyclerView;
    private ArrayList<Budgetmodel> budgetModelArrayListList;
    private BudgetAdapter budgetAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private View root;
    ImageView btnAdd,btnDelete,btnEdit;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private DatabaseHelper dbHelper;
    private TextView total,totalused;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Budget1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = DatabaseHelper.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_budget1, container, false);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        onActivityCreated(new Bundle());
        dbHelper.registerDbObserver(this);
    }

        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        btnAdd =root.findViewById(R.id.addButton);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NoAppBarActivity.class);
                intent.putExtra("Fragment", "budgetInsert");
                startActivity(intent);
            }
        });

      setContent();
    }

    public void setContent(){

        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        ArrayList<Budgetmodel>  budgetModelArrayListList = new ArrayList<>();

        budgetModelArrayListList = databaseHelper.getBudgetArray();

        recyclerView = root.findViewById(R.id.repeat);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        budgetAdapter = new BudgetAdapter(getContext(), budgetModelArrayListList);
        recyclerView.setAdapter(budgetAdapter);

        total= root.findViewById(R.id.textView24);
        total.setText(String.valueOf(getTotalBudget(budgetModelArrayListList)));

        totalused = root.findViewById(R.id.textView25);
        totalused.setText(String.valueOf(getTotalUsed(budgetModelArrayListList)));
    }

    public double getTotalBudget(ArrayList<Budgetmodel> budgetmodelArrayList) {
        double total = 0.0;
        for (Budgetmodel budgetmodel : budgetmodelArrayList) {
            total += budgetmodel.getAmount();
        }
        return total;
    }

    public double getTotalUsed(ArrayList<Budgetmodel> budgetmodelArrayList) {
        double total = 0.0;
        for (Budgetmodel budgetmodel : budgetmodelArrayList) {
            total += budgetmodel.getUsedAmount();
        }
        return total;
    }

    @Override
    public void onDatabaseChanged() {
        setContent();
    }
}