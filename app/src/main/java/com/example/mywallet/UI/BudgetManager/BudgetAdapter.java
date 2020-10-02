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
            holder.FoodAndBeverages.setText(getCategoryName(budget.get(position).getCat_ID()));
            holder.foodIcon.setImageResource(getImageforCategory(budget.get(position).getCat_ID()));
        }

        @Override
        public int getItemCount() {
            return budget.size();
        }


        //Daily Budget View holder Class
        public  class BudgetviewHolder extends RecyclerView.ViewHolder {
            TextView number1, number2, FoodAndBeverages;
            ImageView progressBar, foodIcon,update,delete;

            public BudgetviewHolder(@NonNull final View itemView) {
                super(itemView);
                number1 = itemView.findViewById(R.id.number1);
                number2 = itemView.findViewById(R.id.number2);
                FoodAndBeverages = itemView.findViewById(R.id.categoryName);
                progressBar = itemView.findViewById(R.id.progressBar);
                foodIcon = itemView.findViewById(R.id.foodIcon);
                update = itemView.findViewById(R.id.penMark);
                delete = itemView.findViewById(R.id.btnDelete);


                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.onUpdateBudgetClick(budget.get(budget.indexOf(itemView.getTag())).getCat_ID());
                    }
                });

                delete.setOnClickListener(new View.OnClickListener() {
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

        //Get image for category
        public int getImageforCategory(int num) {
            int img = R.drawable.bill;
            switch (num){
                case 1:
                    img = R.drawable.bill;
                    break;
                case 2:
                    img = R.drawable.education;
                    break;
                case 3:
                    img = R.drawable.family;
                    break;
                case 4:
                    img = R.drawable.gift;
                    break;
                case 5:
                    img = R.drawable.food;
                    break;
                case 6:
                    img = R.drawable.loan;
                    break;
            }
            return img;
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



