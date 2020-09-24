package com.example.mywallet.UI.Income;

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

import com.example.mywallet.R;
import com.example.mywallet.Model.IncomeModel;

import java.util.ArrayList;
import java.util.Calendar;

public class Income extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<IncomeModel> incomeModelArrayListList;
    private Incomeadapter incomeadapter;
    private RecyclerView.LayoutManager layoutManager;
    private View root;
    Button btn;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


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
        root =  inflater.inflate(R.layout.fragment_income, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btn = root.findViewById(R.id.addresorce);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Btn", "LKJFSKDJF");
                Income4 income4 = new Income4();
                fragmentManager = getParentFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, income4);
                fragmentTransaction.commit();
            }
        });

        incomeModelArrayListList = new ArrayList<>();

        incomeModelArrayListList.add(new IncomeModel("Boc Credit Card",1,Calendar.getInstance().getTime(),40000,"Lorem  ispham dika kliokalsd "));
        incomeModelArrayListList.add(new IncomeModel("wallet",2,Calendar.getInstance().getTime(),30000,"Lorem  ispham dika kliokalsd "));

        recyclerView = root.findViewById(R.id.list_income);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        incomeadapter = new Incomeadapter(getContext(), incomeModelArrayListList);
        recyclerView.setAdapter(incomeadapter);
    }
}