package com.example.mywallet.UI.BudgetManager;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mywallet.DatabaseHelper;
import com.example.mywallet.Model.Budgetmodel;
import com.example.mywallet.Model.Category;
import com.example.mywallet.R;
import com.example.mywallet.ToastMessage;

import java.util.ArrayList;

public class Budget2 extends Fragment {

    int categoryId = 1;
    private Button budbtn;
     View view;
     private EditText budamount;
     TextView category;
     private ToastMessage toastMessage;

    public Budget2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("msg", "vIEW CREATED");
        view =  inflater.inflate(R.layout.fragment_budget2, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        budamount=view.findViewById(R.id.budamount);

        budbtn=view.findViewById(R.id.addBtn);

        toastMessage = new ToastMessage(getActivity(), view);

        category = view.findViewById(R.id.categoryId);

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                ArrayList<Category> arrayList = databaseHelper.getCategories();
                final String[] categoryNames = new String[arrayList.size()];
                for(int i = 0; i < arrayList.size(); i++) {
                    categoryNames[i] = arrayList.get(i).getCategoryName();
                }

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                mBuilder.setTitle("Select the Category");
                mBuilder.setIcon(R.drawable.bill);
                mBuilder.setSingleChoiceItems(categoryNames, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        category.setText(categoryNames[which]);
                        categoryId = which+1;
                        dialog.dismiss();
                    }
                });

                mBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

       budbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("click add");
                Budgetmodel budgetmodel = new Budgetmodel();
                budgetmodel.setCat_ID (categoryId);
                budgetmodel.setAmount(Double.parseDouble(budamount.getText().toString()));


                DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                boolean status = databaseHelper.addBudget(budgetmodel);

                if (status) {
                    toastMessage.successToast("Successfully Inserted");
                    getActivity().onBackPressed();

                } else {
                    toastMessage.errorToast("Insert Failed");
                }
            }});
    }
}