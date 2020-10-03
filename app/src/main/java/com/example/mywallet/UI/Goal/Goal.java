package com.example.mywallet.UI.Goal;

import android.app.DatePickerDialog;
import android.content.Intent;
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

import com.example.mywallet.DatabaseHelper;
import com.example.mywallet.MainActivity;
import com.example.mywallet.R;
import com.example.mywallet.ToastMessage;
import com.example.mywallet.Model.DailyExpense;
import com.example.mywallet.Model.FutureGoal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Goal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Goal extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View view;
    private Button btnAddgoal;
    private EditText goal1,amount;
    TextView date;
    private ToastMessage toastMessage;
    DatePickerDialog picker;

    public Goal() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Goal.
     */
    // TODO: Rename and change types and number of parameters
    public static Goal newInstance(String param1, String param2) {
        Goal fragment = new Goal();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_goal, container, false);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       date = (TextView) view.findViewById(R.id.adddate);
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        String dateString = format.format( new Date());
        date.setText(String.format(dateString, currentTime));
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

        goal1 =view.findViewById(R.id.addgoal);
        amount=view.findViewById(R.id.addamount);
     //   date=view.findViewById(R.id.adddate);
        btnAddgoal=view.findViewById(R.id.btnAddgoal);

        toastMessage = new ToastMessage(getActivity(), view);

        btnAddgoal.setOnClickListener(new View.OnClickListener() { //code for insert button
            @Override
            public void onClick(View v) {
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Date dateS = Calendar.getInstance().getTime();
                try {
                    dateS = format.parse(date.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                FutureGoal futureGoal = new FutureGoal();
                futureGoal.setGoal(goal1.getText().toString());
                System.out.println("add goal" + futureGoal.getGoal());
                futureGoal.setTotalAmount(Double.parseDouble(amount.getText().toString()));
                futureGoal.setDate(dateS);


                DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                boolean status = databaseHelper.addGoal(futureGoal);

                if (status) {
                    toastMessage.successToast("Successfully Inserted");

                    Intent intent = new Intent(getContext(), MainActivity.class);
                    getActivity().onBackPressed();

                } else {
                    toastMessage.errorToast("Insert Failed");
                }
            }});

    }
}
