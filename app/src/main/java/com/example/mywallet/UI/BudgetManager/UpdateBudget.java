package com.example.mywallet.UI.BudgetManager;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mywallet.DatabaseHelper;
import com.example.mywallet.Model.Budgetmodel;
import com.example.mywallet.R;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateBudget#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateBudget extends Fragment {

    private int BudId;
    Budgetmodel bud;
    TextView eText;
    EditText amount;
    View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UpdateBudget() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateBudget.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateBudget newInstance(String param1, String param2) {
        UpdateBudget fragment = new UpdateBudget();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BudId = getActivity().getIntent().getIntExtra("id", 0);
        System.out.println("id " + BudId);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_update_budget, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        try {
            bud = databaseHelper.getDailyBudgetById(BudId);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String catName= getCategoryName(bud.getCat_ID());
        amount= view.findViewById(R.id.textView10);
        amount.setText(String.valueOf(bud.getAmount()));
        TextView category = view.findViewById(R.id.categoryId);
        category.setText(catName);

            }


    //Get image for category
    public String getCategoryName(int num) {
        String name ="Payment";
        switch (num){
            case 1:
                name ="Bill Payment";
                break;
            case 2:
                name ="Educational Payment";
                break;
            case 3:
                name ="Family Expenses";
                break;
            case 4:
                name ="Expenses for Gifts";
                break;
            case 5:
                name ="Expenses for Food";
                break;
            case 6:
                name ="Loan Repayment";
                break;
        }
        return name;
    }
        }

