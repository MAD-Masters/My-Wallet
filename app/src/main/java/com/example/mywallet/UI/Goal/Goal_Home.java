package com.example.mywallet.UI.Goal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mywallet.DatabaseHelper;
import com.example.mywallet.DatabaseObserver;
import com.example.mywallet.MainActivity;
import com.example.mywallet.NoAppBarActivity;
import com.example.mywallet.R;
import com.example.mywallet.Model.FutureGoal;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

public class Goal_Home extends Fragment implements DatabaseObserver {

    private RecyclerView recyclerView;
    private ArrayList<FutureGoal> futuregoalArrayList;
    private GoalAdapter goalAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private View root;
    private Button btn;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private DatabaseHelper dbHelper;

    public Goal_Home() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = DatabaseHelper.getInstance(getContext());
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
        btn = root.findViewById(R.id.btngoal1);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NoAppBarActivity.class);
                intent.putExtra("Fragment", "addgoal12");
                startActivity(intent);
            }

        });

        setContent();
    }

    public void onResume() {
        super.onResume();
        dbHelper.registerDbObserver(this);
    }

    public void setContent() {
        DatabaseHelper databaseHelper=new DatabaseHelper(getContext());
        ArrayList<FutureGoal> futuregoalArrayList=new ArrayList<>();

        try {
            futuregoalArrayList = databaseHelper.getfutureGoal();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        recyclerView = root.findViewById(R.id.goalInDetail);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        goalAdapter = new GoalAdapter(getContext(),futuregoalArrayList);
        recyclerView.setAdapter(goalAdapter);
    }

    @Override
    public void onDatabaseChanged() {
        setContent();
    }
}