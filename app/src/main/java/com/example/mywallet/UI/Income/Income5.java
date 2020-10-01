package com.example.mywallet.UI.Income;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mywallet.DatabaseHelper;
import com.example.mywallet.MainActivity;
import com.example.mywallet.Model.DailyExpense;
import com.example.mywallet.Model.IncomeModel;
import com.example.mywallet.R;
import com.example.mywallet.ToastMessage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Income5#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Income5 extends Fragment {


    private EditText amount, category, note;
    TextView eText;
    DatePickerDialog picker;
    View view;
    private Button addincome,cansel;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ToastMessage toastMessage;
    int walletid;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Income5() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment income5.
     */
    // TODO: Rename and change types and number of parameters
    public static Income5 newInstance(String param1, String param2) {
        Income5 fragment = new Income5();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         walletid = getActivity().getIntent().getIntExtra("id",0);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_income5, container, false);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        toastMessage = new ToastMessage(getActivity(), view);
        eText = (TextView) view.findViewById(R.id.textInputEditText9);
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        String dateString = format.format(new Date());
        eText.setText(String.format(dateString, currentTime));
        eText.setOnClickListener(new View.OnClickListener() {
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
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        addincome = view.findViewById(R.id.add);
        amount = view.findViewById(R.id.textInputLayout4);
        note = view.findViewById(R.id.textInputEditText5);
        cansel = view.findViewById(R.id.cansel3);

        cansel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        addincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkFields()) {
                    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = Calendar.getInstance().getTime();
                    try {
                        date = format.parse(eText.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    IncomeModel incomeModel = new IncomeModel();
                    incomeModel.setMoney(Double.parseDouble(amount.getText().toString()));
                    incomeModel.setDate(date);
                    incomeModel.setWalletid(walletid);
                    incomeModel.setText(note.getText().toString());


                    DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                    boolean status = databaseHelper.addincome(incomeModel);

                    if (status) {
                        toastMessage.successToast("Successfully Inserted");

                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);

                    } else {
                        toastMessage.errorToast("Insert Failed");
                    }
                }
            }


        });

    }
    //This method checks the fields
    public boolean checkFields() {
        boolean status = true;
        if (amount.getText().length() == 0) {
            toastMessage.errorToast("Amount can not be Empty");
            status = false;
        } else if (note.getText().length() == 0) {
            note.setText(" ");
        }
        return status;
    }
}
