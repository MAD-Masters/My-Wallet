package com.example.mywallet.UI.Expenses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mywallet.R;
import com.example.mywallet.UI.Expenses.Model.DailyExpense;
import com.example.mywallet.UI.Expenses.Model.DailyExpesnseSummary;

import java.util.ArrayList;

public class DailyExpenseSummaryAdapter extends RecyclerView.Adapter<DailyExpenseSummaryAdapter.DailyExpenseViewHolder> {
    private ArrayList<DailyExpesnseSummary> dailyExpenses;
    private onDailyExpenseSummaryClick activity;

    public DailyExpenseSummaryAdapter(Context context, ArrayList<DailyExpesnseSummary> dailyExpenses) {
        this.dailyExpenses = dailyExpenses;
        this.activity = (onDailyExpenseSummaryClick) context;
    }

    //Daily Expesnes View holder Class
    public class DailyExpenseViewHolder extends RecyclerView.ViewHolder {
        TextView date, amount;

        public DailyExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            amount = itemView.findViewById(R.id.amount);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onClickDailyExpItem();
                }
            });
        }

    }

    public interface onDailyExpenseSummaryClick {
        public void onClickDailyExpItem();
    }

    @NonNull
    @Override
    public DailyExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_daily_expense, parent, false);
        return new DailyExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyExpenseViewHolder holder, int position) {
        holder.itemView.setTag(dailyExpenses.get(position));
        holder.amount.setText(((Double)dailyExpenses.get(position).getTotalAmount()).toString());
        holder.date.setText(dailyExpenses.get(position).getDate().toString());
    }

    @Override
    public int getItemCount() {
        return dailyExpenses.size();
    }
}
