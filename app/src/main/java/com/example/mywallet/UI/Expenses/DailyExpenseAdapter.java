package com.example.mywallet.UI.Expenses;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mywallet.R;
import com.example.mywallet.Model.DailyExpense;

import java.util.ArrayList;

public class DailyExpenseAdapter extends RecyclerView.Adapter<DailyExpenseAdapter.DailyExpenseViewHolder> {
    private ArrayList<DailyExpense> dailyExpenses;
    private DailyExpenseInterface activity;
    private Dialog dialog;
    private LinearLayout linearLayoutNote;

    public DailyExpenseAdapter(Context context, ArrayList<DailyExpense> dailyExpenses) {
        this.dailyExpenses = dailyExpenses;
        activity = (DailyExpenseInterface) context;
    }

    //Daily Expesne View holder Class
    public  class DailyExpenseViewHolder extends RecyclerView.ViewHolder {
        TextView expenseCategory, amount, note;
        ImageView  catImage, btnEdit, btnDelete;

        public DailyExpenseViewHolder(@NonNull final View itemView) {
            super(itemView);
            expenseCategory = itemView.findViewById(R.id.expenseCategory);
            note = itemView.findViewById(R.id.note);
            amount = itemView.findViewById(R.id.amount);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            catImage = itemView.findViewById(R.id.expenseCatImg);
            linearLayoutNote = itemView.findViewById(R.id.noteView);

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onUpdateBtnExInClick(dailyExpenses.get(dailyExpenses.indexOf(itemView.getTag())).getRecordId());
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onDeletBtnExInClick(dailyExpenses.get(dailyExpenses.indexOf(itemView.getTag())).getRecordId());
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
        holder.expenseCategory.setText(getCategoryName(dailyExpenses.get(position).getCategoryId()));
        holder.amount.setText(((Double)dailyExpenses.get(position).getAmount()).toString());
        if (dailyExpenses.get(position).getNote().length() > 0){
            holder.note.setText(dailyExpenses.get(position).getNote());
        } else {
            linearLayoutNote.removeView(holder.note);
        }

        holder.catImage.setImageResource(getImageforCategory(dailyExpenses.get(position).getCategoryId()));
    }

    @Override
    public int getItemCount() {
        return dailyExpenses.size();
    }

    //Interface for DailyExpense
    public interface DailyExpenseInterface{
        public void onDeletBtnExInClick(int recordId);
        public void onUpdateBtnExInClick(int recordId);
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
