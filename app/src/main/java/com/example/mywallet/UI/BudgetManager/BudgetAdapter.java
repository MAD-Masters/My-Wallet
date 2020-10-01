package com.example.mywallet.UI.BudgetManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mywallet.Model.DailyExpense;
import com.example.mywallet.R;

import com.example.mywallet.Model.Budgetmodel;

import com.example.mywallet.UI.Expenses.DailyExpenseAdapter;
import com.example.mywallet.Model.DailyExpense;
import com.example.mywallet.UI.Income.Incomeadapter;


import java.util.ArrayList;


    public class BudgetAdapter extends RecyclerView.Adapter<BudgetAdapter.BudgetviewHolder> {
        private ArrayList<Budgetmodel> budget;
        private BudgetInterface activity;
        private Button addBTN;
        private Button btnEdit, btnDelete;


        public BudgetAdapter(Context context, ArrayList<Budgetmodel> budget) {
            this.budget = budget;
            activity = (BudgetInterface) context;
        }

        @NonNull
        @Override
        public BudgetviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_budget, parent, false);
            return new BudgetviewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull BudgetviewHolder holder, int position) {
            holder.itemView.setTag(budget.get(position));
            holder.number1.setText(((Double) budget.get(position).getAmount()).toString());
            holder.number2.setText(((Double) budget.get(position).getUsedAmount()).toString());
            //holder.FoodAndBeverages.setText(budget.get(position).getCat_ID());
            holder.progressBar.setImageResource(R.drawable.rectangle);
            holder.foodIcon.setImageResource(R.drawable.food);
        }

        @Override
        public int getItemCount() {
            return budget.size();
        }


        //Daily Budget View holder Class
        public  class BudgetviewHolder extends RecyclerView.ViewHolder {
            TextView number1, number2, FoodAndBeverages;
            ImageView progressBar, foodIcon;

            public BudgetviewHolder(@NonNull final View itemView) {
                super(itemView);
                number1 = itemView.findViewById(R.id.number1);
                number2 = itemView.findViewById(R.id.number2);
                FoodAndBeverages = itemView.findViewById(R.id.FoodAndBeverages);
                progressBar = itemView.findViewById(R.id.progressBar);
                foodIcon = itemView.findViewById(R.id.foodIcon);

                btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.onUpdateBudgetClick(budget.get(budget.indexOf(itemView.getTag())).getCat_ID());
                    }
                });

                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.onDeletBtnBudget(budget.get(budget.indexOf(itemView.getTag())).getCat_ID());
                    }
                });

            }

        }

        public interface BudgetInterface {

            public void onUpdateBudgetClick(int Cat_ID);
            public void onDeletBtnBudget(int Cat_ID);
        }
            }



