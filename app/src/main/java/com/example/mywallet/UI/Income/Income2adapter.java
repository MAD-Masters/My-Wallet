package com.example.mywallet.UI.Income;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mywallet.R;
import com.example.mywallet.UI.Income.Model.IncomeModel;

import java.util.ArrayList;

public class Income2adapter extends RecyclerView.Adapter<Income2adapter.Income2layoutViewHolder> {
private ArrayList<IncomeModel> income;

public Income2adapter(ArrayList<IncomeModel> income) {
        this.income = income;
        }

    @NonNull
    @Override
    public Income2layoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.income2layout, parent, false);
        return new Income2adapter.Income2layoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Income2layoutViewHolder holder, int position) {
        holder.date.setText((CharSequence) income.get(position).getDate());
        holder.amount.setText(((Double)income.get(position).getMoney()).toString());
        holder.text.setText(income.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return income.size();
    }

    public static class Income2layoutViewHolder extends RecyclerView.ViewHolder {

    TextView date,amount,text;


    public Income2layoutViewHolder(@NonNull View itemView) {
        super(itemView);
        date = itemView.findViewById(R.id.date);
        amount = itemView.findViewById(R.id.amount);
        text = itemView.findViewById(R.id.text);
    }
}

}






