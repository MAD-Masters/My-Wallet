package com.example.mywallet.UI.Expenses;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mywallet.R;
import com.example.mywallet.UI.Expenses.Model.DailyExpense;

import java.util.ArrayList;

public class DailyExpenseAdapter extends RecyclerView.Adapter<DailyExpenseAdapter.DailyExpenseViewHolder> {
    private ArrayList<DailyExpense> dailyExpenses;
    private DailyExpenseInterface activity;
    private Dialog dialog;

    public DailyExpenseAdapter(Context context, ArrayList<DailyExpense> dailyExpenses) {
        this.dailyExpenses = dailyExpenses;
        activity = (DailyExpenseInterface) context;
    }

    //Daily Expesne View holder Class
    public  class DailyExpenseViewHolder extends RecyclerView.ViewHolder {
        TextView expenseCategory, amount, note;
        ImageView walletType, bank, btnEdit, btnDelete;

        public DailyExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            expenseCategory = itemView.findViewById(R.id.expenseCategory);
            amount = itemView.findViewById(R.id.amount);
            note = itemView.findViewById(R.id.note);
            walletType = itemView.findViewById(R.id.walletType);
            bank = itemView.findViewById(R.id.bank);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onUpdateBtnExInClick();
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onDeletBtnExInClick();
                }
            });

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

        holder.itemView.setTag(dailyExpenses.get(position));

        holder.expenseCategory.setText(((Integer)dailyExpenses.get(position).getCategoryId()).toString());
        holder.amount.setText(((Double)dailyExpenses.get(position).getAmount()).toString());
        holder.note.setText(dailyExpenses.get(position).getNote());
        holder.walletType.setImageResource(R.drawable.credit_card);
        holder.bank.setImageResource(R.drawable.boc);
    }

    @Override
    public int getItemCount() {
        return dailyExpenses.size();
    }

    //Interface for DailyExpense
    public interface DailyExpenseInterface{
        public void onDeletBtnExInClick();
        public void onUpdateBtnExInClick();
    }

}
