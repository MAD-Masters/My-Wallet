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
import com.example.mywallet.DatabaseObserver;
import com.example.mywallet.MainActivity;
import com.example.mywallet.Model.IncomeToWallet;
import com.example.mywallet.Model.Wallet;
import com.example.mywallet.NoAppBarActivity;
import com.example.mywallet.R;
import com.example.mywallet.Model.IncomeModel;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

public class Income extends Fragment implements DatabaseObserver {
    private RecyclerView recyclerView;
    private ArrayList<Wallet> walletArrayListList;
    private Incomeadapter incomeadapter;
    private RecyclerView.LayoutManager layoutManager;
    private View root;
    private TextView wallet,totals;
    double total;
    Button btn;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    int walletid;
    private DatabaseHelper dbHelper;


    public Income() {
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
        root =  inflater.inflate(R.layout.fragment_income, container, false);
        return root;
    }

    public double getTotalIncome(ArrayList<IncomeModel> totincome) {
        double tot = 0.0;
        for (IncomeModel incomeToWallet : totincome) {
            tot = tot + incomeToWallet.getMoney();
        }
        return tot;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btn = root.findViewById(R.id.addresorce);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(),NoAppBarActivity.class);
                intent.putExtra("Fragment", "addresource");
                intent.putExtra("walletid",walletid);
                startActivity(intent);
            }
        });

        try {
            content();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public void onResume() {
        super.onResume();
        onActivityCreated(new Bundle());
        dbHelper.registerDbObserver(this);
    }

    public void content() throws ParseException {

        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        ArrayList<Wallet>  walletArrayListList = new ArrayList<>();
        ArrayList<IncomeModel> incomeModels = new ArrayList<>();


        walletArrayListList = databaseHelper.getWalletsList();
        incomeModels = databaseHelper.getincomesList();
        total = getTotalIncome(incomeModels);



        totals = root.findViewById(R.id.textView29);
        totals.setText(String.valueOf(total));


        recyclerView = root.findViewById(R.id.list_income);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        incomeadapter = new Incomeadapter(getContext(),  walletArrayListList);
        recyclerView.setAdapter(incomeadapter);
    }

    @Override
    public void onDatabaseChanged() throws ParseException {
        content();
    }
}