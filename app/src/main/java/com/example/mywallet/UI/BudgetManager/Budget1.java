package com.example.mywallet.UI.BudgetManager;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mywallet.R;
import com.example.mywallet.Model.Budgetmodel;

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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Budget1.
     */
    // TODO: Rename and change types and number of parameters
    public static Budget1 newInstance(String param1, String param2) {
        Budget1 fragment = new Budget1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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