package com.example.mywallet.UI.Income;

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
import com.example.mywallet.Model.IncomeModel;
import com.example.mywallet.R;
import com.example.mywallet.ToastMessage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Income6#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Income6 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    DatePickerDialog picker;
    TextView eText;
    View view;
    int incomeid;
    IncomeModel incomeModel;
    private ToastMessage toastMessage;
    private EditText amount,note;
    private Button update;
    private TextView date;



    public Income6() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment income6.
     */
    // TODO: Rename and change types and number of parameters
    public static Income6 newInstance(String param1, String param2) {
        Income6 fragment = new Income6();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        incomeid = bundle.getInt("id");
        System.out.println("income6id"+incomeid);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_income6, container, false);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());

        try {
            incomeModel = databaseHelper.getincomeById(incomeid);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        eText = (TextView) view.findViewById(R.id.textInputEditText2);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        eText.setText(format.format(incomeModel.getDate()));
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

        toastMessage = new ToastMessage(getActivity(), view);
        update = view.findViewById(R.id.update);
        amount = view .findViewById(R.id.textInputEditText6);
        note = view.findViewById(R.id.textInputLayout);

        note.setText(incomeModel.getText());
        amount.setText(String.valueOf(incomeModel.getMoney()));

        System.out.println("update"+incomeModel.getText());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                IncomeModel incomeModel = new IncomeModel();

                incomeModel.setText(note.getText().toString());
                incomeModel.setRecordID(incomeid);
                incomeModel.setMoney(Double.parseDouble(amount.getText().toString()));

                DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                boolean status = databaseHelper.updateincome(incomeModel);

                if (status) {
                    toastMessage.successToast("Successfully updated");
                    getActivity().onBackPressed();
                } else {
                    toastMessage.errorToast("update Failed");
                }

            }
        });
    }
}