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

import com.example.mywallet.R;

import com.example.mywallet.Model.Budgetmodel;

import java.util.ArrayList;


    public class BudgetAdapter extends RecyclerView.Adapter<BudgetAdapter.BudgetviewHolder>{
        private ArrayList<Budgetmodel> budget;
        private BudgetInterface activity;
        private Button addBTN;

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
            holder.number1.setText(((Double)budget.get(position).getNumber1()).toString());
            holder.number2.setText(((Double)budget.get(position).getNumber2()).toString());
            holder.FoodAndBeverages.setText(budget.get(position).getText());
            holder.progressBar.setImageResource(R.drawable.rectangle);
            holder.foodIcon.setImageResource(R.drawable.food);
        }

        @Override
        public int getItemCount() {
            return budget.size();
        }

        //Daily Expesne View holder Class
        public  class BudgetviewHolder extends RecyclerView.ViewHolder {
            TextView number1, number2, FoodAndBeverages;
            ImageView progressBar, foodIcon;

            public BudgetviewHolder(@NonNull View itemView) {
                super(itemView);
                number1 = itemView.findViewById(R.id.number1);
                number2 = itemView.findViewById(R.id.number2);
                FoodAndBeverages= itemView.findViewById(R.id.FoodAndBeverages);
                progressBar = itemView.findViewById(R.id.progressBar);
                foodIcon = itemView.findViewById(R.id.foodIcon);

            }
            
                }

        public interface BudgetInterface {

            public void onBtnAddBudget();

        }
            }



