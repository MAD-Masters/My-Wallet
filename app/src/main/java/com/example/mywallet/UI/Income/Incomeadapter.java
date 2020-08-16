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

public class Incomeadapter extends RecyclerView.Adapter<Incomeadapter.Income1layoutViewHolder> {
    private ArrayList<IncomeModel> income;

    public Incomeadapter(ArrayList<IncomeModel> income) {
        this.income = income;
    }
    public static class Income1layoutViewHolder extends RecyclerView.ViewHolder {

        TextView resource;
        ImageView resourceview;

        public Income1layoutViewHolder(@NonNull View itemView) {
            super(itemView);
            resource = itemView.findViewById(R.id.resource);
            resourceview = itemView.findViewById(R.id.resourceview);
        }
    }
    @NonNull
    @Override
    public Income1layoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.income1layout, parent, false);
        return new Income1layoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Income1layoutViewHolder holder, int position) {
        holder.resource.setText(income.get(position).getResourcename());
        holder.resourceview.setImageResource(R.drawable.creditcard);
    }

    @Override
    public int getItemCount() {
            return income.size();
        }
    }









