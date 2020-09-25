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

import com.example.mywallet.NoAppBarActivity;
import com.example.mywallet.R;
import com.example.mywallet.Model.Budgetmodel;

import com.example.mywallet.UI.BudgetManager.Model.Budget2;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Budget1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Budget1 extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Budgetmodel> budgetModelArrayListList;
    private BudgetAdapter budgetAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private View root;
    ImageView btnAdd;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_budget1, container, false);
        return root;
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

       budgetModelArrayListList = new ArrayList<>();

        budgetModelArrayListList.add(new Budgetmodel(25000.00,40000,"food and beverages"));
        budgetModelArrayListList.add(new Budgetmodel(50000.00,50000,"clothes"));

        recyclerView = root.findViewById(R.id.repeat);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

       budgetAdapter = new BudgetAdapter(getContext(), budgetModelArrayListList);
        recyclerView.setAdapter(budgetAdapter);


    }
}