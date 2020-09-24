package com.example.mywallet.UI.Goal;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mywallet.R;
import com.example.mywallet.Model.FutureGoal;

import java.util.ArrayList;
import java.util.Date;

public class GoalAdapter extends RecyclerView.Adapter<com.example.mywallet.UI.Goal.GoalAdapter.GoalViewHolder> {
    private ArrayList<FutureGoal> futuregoal;
    private GoalInterface activity1;

    public  GoalAdapter(Context context, ArrayList<FutureGoal> futuregoal) {
        this.futuregoal = futuregoal;
        activity1= (GoalInterface) context;
    }

    public  class GoalViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView goal, totalAmount, date;
        ImageView btngoal;
        Button btndelete;



        public GoalViewHolder(@NonNull View itemView) {
            super(itemView);
            goal = itemView.findViewById(R.id.goal);
            cardView = itemView.findViewById(R.id.cardViewFutureGoal);
            totalAmount = itemView.findViewById(R.id.totalAmount);
           date = itemView.findViewById(R.id.date);
            btngoal = itemView.findViewById(R.id.btngoal);
            btndelete=itemView.findViewById(R.id.btndelete);


                    btndelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            activity1.onDeletBtnGoInClick();             }
                    });

                            btngoal.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    activity1.onAddBtnGoalClick();
                        }
            });

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

    public interface GoalInterface{
        public  void onDeletBtnGoInClick();
        public void onAddBtnGoalClick();

    }

}