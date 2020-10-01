package com.example.mywallet.UI.Goal;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mywallet.DatabaseHelper;
import com.example.mywallet.Model.DailyExpense;
import com.example.mywallet.Model.FutureGoal;
import com.example.mywallet.R;
import com.example.mywallet.ToastMessage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Goal1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Goal1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int record_id;
    private EditText amount,goal;
    TextView date;
    private Button btnUp,btncncel;
    private ToastMessage toastMessage;
    DatePickerDialog picker;
    FutureGoal futurego;
    View view;



    public Goal1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Goal1.
     */
    // TODO: Rename and change types and number of parameters
    public static Goal1 newInstance(String param1, String param2) {
        Goal1 fragment = new Goal1();
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
        view= inflater.inflate(R.layout.fragment_goal1, container, false);
        return view;
    }


    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        try {
            futurego = databaseHelper.getGoalById(record_id);
        } catch (ParseException e) {
            e.printStackTrace();
        }

       date = (TextView) view.findViewById(R.id.uDate);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

      date.setText(format.format(futurego.getDate()));
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        toastMessage = new ToastMessage(getActivity(), view);
        btnUp = view.findViewById(R.id.btnGoUp);
        btncncel = view.findViewById(R.id.btnGoUp);
        amount = view.findViewById(R.id.uAmount);
        goal = view.findViewById(R.id.uGoal);

        //Btn Cancel
        btncncel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        //Set Current Values
        goal.setText(futurego.getGoal());
        amount.setText(String.valueOf(futurego.getTotalAmount()));

       btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              // if (checkFields()) {
                    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = Calendar.getInstance().getTime();
                    try {
                        date = format.parse(date.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                   FutureGoal futureGoal = new FutureGoal();
                    futureGoal.setGoal(goal.getText().toString());
                    futureGoal.setTotalAmount(Double.parseDouble(amount.getText().toString()));
                    futureGoal.setDate(date);
                    futureGoal.setRecord_id(futurego.getRecord_id());

                    DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                    //System.out.println("amount" + dailyExpense.getAmount());
                    boolean status = false;
                    status = databaseHelper.updateGoal(futureGoal);

                    if (status) {
                        toastMessage.successToast("Successfully Updated");
                        getActivity().onBackPressed();
                    } else {
                        toastMessage.errorToast("Insert Failed");
                    }
                }
          //  }
        });
    }


}

