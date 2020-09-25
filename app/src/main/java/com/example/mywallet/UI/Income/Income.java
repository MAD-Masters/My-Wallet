package com.example.mywallet.UI.Income;

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
import android.widget.TextView;

import com.example.mywallet.DatabaseHelper;
import com.example.mywallet.MainActivity;
import com.example.mywallet.Model.Wallet;
import com.example.mywallet.NoAppBarActivity;
import com.example.mywallet.R;
import com.example.mywallet.Model.IncomeModel;

import java.util.ArrayList;
import java.util.Calendar;

public class Income extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Wallet> walletArrayListList;
    private Incomeadapter incomeadapter;
    private RecyclerView.LayoutManager layoutManager;
    private View root;
    private TextView wallet;
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

        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        ArrayList<Wallet>  walletArrayListList = new ArrayList<>();


        walletArrayListList = databaseHelper.getWalletsList();


        recyclerView = root.findViewById(R.id.list_income);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        incomeadapter = new Incomeadapter(getContext(),  walletArrayListList);
        recyclerView.setAdapter(incomeadapter);
    }
}