package com.example.mywallet.UI.Income;

import android.content.Context;
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
    private IncomeInterface activity;

    public Incomeadapter(Context context, ArrayList<IncomeModel> income) {

        this.income = income;
        activity = (IncomeInterface)context;
    }

    public  class Income1layoutViewHolder extends RecyclerView.ViewHolder {

        TextView resource,resourcetitle;
        ImageView resourceview,btnAdd,btnEdit,btndelete;

        public Income1layoutViewHolder(@NonNull View itemView) {
            super(itemView);
            resourceview = itemView.findViewById(R.id.resourceview);
            resourcetitle = itemView.findViewById(R.id.resourcetitle);
            btnAdd = itemView.findViewById((R.id.btnAdd));
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btndelete = itemView.findViewById(R.id.btndelete);


           btnAdd.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   activity.onaddBtnincome();

               }

           });
           btnEdit.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   activity.onUpdateBtnincome();
               }
           });
           resourcetitle.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   activity.onBtnTitleincome();
               }
           });
           btndelete.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   activity.ondeleteincome1();
               }
           });


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
        holder.resourcetitle.setText(income.get(position).getResourcename());
        holder.resourceview.setImageResource(R.drawable.creditcard);
    }

    @Override
    public int getItemCount() {
        return income.size();
    }




    public interface IncomeInterface {


        public void onBtnTitleincome();
        public void onUpdateBtnincome();
        public void onaddBtnincome();
        public void ondeleteincome1();

    }

}






