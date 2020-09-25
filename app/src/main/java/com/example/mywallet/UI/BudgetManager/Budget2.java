package com.example.mywallet.UI.BudgetManager;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mywallet.DatabaseHelper;
import com.example.mywallet.Model.Category;
import com.example.mywallet.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Budget2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Budget2 extends Fragment {

    int categoryId;
    TextView category;
    View view;

    public Budget2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_budget2, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        category = view.findViewById(R.id.category);

        //Set category dialog box
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
    }
}