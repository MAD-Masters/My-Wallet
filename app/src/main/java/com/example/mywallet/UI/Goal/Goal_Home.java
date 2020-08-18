package com.example.mywallet.UI.Goal;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mywallet.R;
import com.example.mywallet.UI.Goal.Model.FutureGoal;

import java.util.ArrayList;
import java.util.Calendar;

public class Goal_Home extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<FutureGoal> futuregoalArrayList;
    private GoalAdapter goalAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private View root;
    Button btn;

    public Goal_Home() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_goal_home, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        futuregoalArrayList = new ArrayList<>();

        futuregoalArrayList.add(new FutureGoal(Calendar.getInstance().getTime(),7777.00,"xxxxx",68888.00));
        futuregoalArrayList.add(new FutureGoal(Calendar.getInstance().getTime(),21257.00,"fgdfg",242742.00));

        recyclerView = root.findViewById(R.id.goalInDetail);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        goalAdapter = new GoalAdapter(getContext(),futuregoalArrayList);
        recyclerView.setAdapter(goalAdapter);
    }
}