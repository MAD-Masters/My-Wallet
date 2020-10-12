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
import android.widget.TextView;

import com.example.mywallet.DatabaseHelper;
import com.example.mywallet.DatabaseObserver;
import com.example.mywallet.R;
import com.example.mywallet.Model.IncomeModel;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Income2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Income2 extends Fragment implements DatabaseObserver {
    private RecyclerView recyclerView;
    private ArrayList<IncomeModel> incomeModelArrayListList;
    private Income2adapter income2adapter;
    private RecyclerView.LayoutManager layoutManager;
    private View root;
    private TextView fullamount;
    double amount;
    Button btn;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private DatabaseHelper dbHelper;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
     int walletid;

    public Income2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment income2.
     */
    // TODO: Rename and change types and number of parameters
    public static Income2 newInstance(String param1, String param2) {
        Income2 fragment = new Income2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        walletid = bundle.getInt("walletid",0);
        dbHelper = DatabaseHelper.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_income2, container, false);
        return root;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

      content();
    }

    public void onResume() {
        super.onResume();
        onActivityCreated(new Bundle());
        dbHelper.registerDbObserver(this);
    }

    @Override
    public void onDatabaseChanged() {
        content();

    }

    public void content(){
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        ArrayList<IncomeModel> incomeModelArrayListList = new ArrayList<>();

        amount = databaseHelper.getfullamountbyid(walletid);

        fullamount = root.findViewById(R.id.currentA);
        fullamount.setText(String.valueOf(amount));

        try {
            incomeModelArrayListList = databaseHelper.getincomesListbyid(walletid);
            Log.d("msg", "Array List Called");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        recyclerView = root.findViewById(R.id.list_income);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        income2adapter = new Income2adapter(getContext() ,incomeModelArrayListList);
        recyclerView.setAdapter(income2adapter);
    }
}