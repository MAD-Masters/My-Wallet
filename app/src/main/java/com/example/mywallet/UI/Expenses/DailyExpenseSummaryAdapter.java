package com.example.mywallet.UI.Expenses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mywallet.Model.DailyExpense;
import com.example.mywallet.R;
import com.example.mywallet.Model.DailyExpesnseSummary;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DailyExpenseSummaryAdapter extends RecyclerView.Adapter<DailyExpenseSummaryAdapter.DailyExpenseViewHolder> {
    private ArrayList<DailyExpesnseSummary> dailyExpenses;
    private onDailyExpenseSummaryClick activity;
    private ArrayList<DailyExpense> dailyExpenseArrayList;

    public DailyExpenseSummaryAdapter(Context context, ArrayList<DailyExpesnseSummary> dailyExpenses, ArrayList<DailyExpense> dailyExpenseArrayList) {
        this.dailyExpenses = dailyExpenses;
        this.activity = (onDailyExpenseSummaryClick) context;
        this.dailyExpenseArrayList = dailyExpenseArrayList;
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
                    activity.onClickDailyExpItem(dailyExpenses.get(dailyExpenses.indexOf((v.getTag()))), dailyExpenseArrayList);
                }
            });
        }

    }

    public interface onDailyExpenseSummaryClick {
        public void onClickDailyExpItem(DailyExpesnseSummary dailyExpesnseSummary, ArrayList<DailyExpense> dailyExpenseArrayList);
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

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = format.format( dailyExpenses.get(position).getDate());
        holder.date.setText(dateString);
    }

    @Override
    public int getItemCount() {
        return dailyExpenses.size();
    }
}
