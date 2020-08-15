package com.example.mywallet.UI.Expenses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mywallet.R;
import com.example.mywallet.UI.Expenses.Model.DailyExpense;

import java.util.ArrayList;

public class DailyExpenseAdapter extends RecyclerView.Adapter<DailyExpenseAdapter.DailyExpenseViewHolder> {
    private ArrayList<DailyExpense> dailyExpenses;

    public static class DailyExpenseViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView expenseCategory, amount, note;
        ImageView walletType, bank;

        public DailyExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            expenseCategory = itemView.findViewById(R.id.expenseCategory);
            cardView = itemView.findViewById(R.id.cardViewDailyExpense);
            amount = itemView.findViewById(R.id.amount);
            note = itemView.findViewById(R.id.note);
            walletType = itemView.findViewById(R.id.walletType);
            bank = itemView.findViewById(R.id.bank);
        }

    }

    @NonNull
    @Override
    public DailyExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daily_expenses, parent, false);
        return new DailyExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyExpenseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
