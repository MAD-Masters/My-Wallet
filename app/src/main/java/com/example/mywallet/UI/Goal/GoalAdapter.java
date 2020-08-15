package com.example.mywallet.UI.Goal;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mywallet.R;
import com.example.mywallet.UI.Goal.Model.FutureGoal;

import java.util.ArrayList;
import java.util.Date;

public class GoalAdapter extends RecyclerView.Adapter<com.example.mywallet.UI.Goal.GoalAdapter.GoalViewHolder> {
    private ArrayList<FutureGoal> futuregoal;

    public  GoalAdapter(ArrayList<FutureGoal> futuregoal) {
        this.futuregoal = futuregoal;
    }

    public static class GoalViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView goal, totalAmount, date;


        public GoalViewHolder(@NonNull View itemView) {
            super(itemView);
            goal = itemView.findViewById(R.id.goal);
            cardView = itemView.findViewById(R.id.cardViewFutureGoal);
            totalAmount = itemView.findViewById(R.id.totalAmount);
           date = itemView.findViewById(R.id.date);

        }

    }

    @NonNull
    @Override
    public com.example.mywallet.UI.Goal.GoalAdapter.GoalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goal5, parent, false);
        return new com.example.mywallet.UI.Goal.GoalAdapter.GoalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.mywallet.UI.Goal.GoalAdapter.GoalViewHolder holder, int position) {
        holder.itemView.setTag(futuregoal.get(position));
        holder.date.setText(((Date)futuregoal.get(position).getDate()).toString());
        holder.totalAmount.setText(((Double)futuregoal.get(position).getTotalAmount()).toString());
        holder.goal.setText(futuregoal.get(position).getGoal());

    }

    @Override
    public int getItemCount() {
        return futuregoal.size();
    }
}