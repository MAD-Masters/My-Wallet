package com.example.mywallet.UI.Goal;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mywallet.DatabaseHelper;
import com.example.mywallet.Model.FutureGoal;
import com.example.mywallet.R;
import com.example.mywallet.ToastMessage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Goal2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Goal2 extends Fragment {

    private Button btnAddamount;
    private int record_id;
    View view;
    FutureGoal futurego;
    ToastMessage toastMessage;
    private EditText amount;
    //int Record_id;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Goal2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Goal2.
     */
    // TODO: Rename and change types and number of parameters
    public static Goal2 newInstance(String param1, String param2) {
        Goal2 fragment = new Goal2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        record_id = getActivity().getIntent().getIntExtra("id", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      view = inflater.inflate(R.layout.fragment_goal2, container, false);
      return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnAddamount = view.findViewById(R.id.btnCamount);
       amount = view.findViewById(R.id.currentA);
        toastMessage = new ToastMessage(getActivity(), view);

        btnAddamount.setOnClickListener(new View.OnClickListener() { // code for add amount button
            @Override
            public void onClick(View v) {

                FutureGoal futureGoal = new FutureGoal(); // create a object 
                futureGoal.setCurrentAmount(Double.parseDouble(amount.getText().toString()));
              //  futureGoal.setRecord_id(futurego.getRecord_id());

                DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                //System.out.println("amount" + dailyExpense.getAmount());
                boolean status = false;
                status = databaseHelper.addAmount(record_id, Double.parseDouble(amount.getText().toString()));

                if (status) {   //toast message
                    toastMessage.successToast("Successfully Updated");
                    getActivity().onBackPressed();
                } else {
                    toastMessage.errorToast("Insert Failed");
                }
            }
            //  }
        });
    }
    DatabaseHelper databaseHelper=new DatabaseHelper(getContext());
    ArrayList<FutureGoal> futuregoalArrayList=new ArrayList<>();

    public double getTotalAmount(ArrayList<FutureGoal>futuregoalArrayList) {
        double total = 0.0;

        for (FutureGoal futuregoal : futuregoalArrayList) {
            total = total + futuregoal.getCurrentAmount();
        }
        return total;

    }

}
