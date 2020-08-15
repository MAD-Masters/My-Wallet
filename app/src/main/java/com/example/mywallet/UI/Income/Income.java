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
import com.example.mywallet.UI.Income.Model.INCOME;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Income#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Income extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<INCOME> incomeArrayList;
    private Incomeadapter incomeAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private View root;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Income() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment income.
     */
    // TODO: Rename and change types and number of parameters
    public static Income newInstance(String param1, String param2) {
        Income fragment = new Income();
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
        root =  inflater.inflate(R.layout.fragment_income, container, false);
        return root;
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        incomeArrayList = new ArrayList<>();

        incomeArrayList.add(new INCOME("Boc Credit Card",1,Calendar.getInstance().getTime(),60000.00));
        incomeArrayList.add(new INCOME("Wallet",2,Calendar.getInstance().getTime(),40000.00));

        recyclerView = root.findViewById(R.id.list_daily_expenses_in_detail);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        incomeAdapter = new Incomeadapter(incomeArrayList);
        recyclerView.setAdapter(incomeAdapter);
    }
}