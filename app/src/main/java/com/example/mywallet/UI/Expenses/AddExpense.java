package com.example.mywallet.UI.Expenses;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mywallet.DatabaseHelper;
import com.example.mywallet.MainActivity;
import com.example.mywallet.NoAppBarActivity;
import com.example.mywallet.R;
import com.example.mywallet.UI.Expenses.Model.DailyExpense;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class AddExpense extends Fragment {
    DatePickerDialog picker;
    TextView eText;
    private EditText amount, category, wallet, note;
    View view;
    private Button btnAddExpense;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public AddExpense() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_expense, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        eText = (TextView) view.findViewById(R.id.dateInput);
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        String dateString = format.format( new Date());
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

        btnAddExpense = view.findViewById(R.id.btnSaveExpense);
        amount = view.findViewById(R.id.amount);
        wallet = view.findViewById(R.id.walletId);
        category = view.findViewById(R.id.categoryId);
        note = view.findViewById(R.id.note);

        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFields()) {
                    System.out.println("Helloworld");
                    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = Calendar.getInstance().getTime();
                    try {
                        date = format.parse(eText.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    DailyExpense dailyExpense = new DailyExpense();
                    dailyExpense.setAmount(Double.parseDouble(amount.getText().toString()));
                    dailyExpense.setCategoryId(Integer.parseInt(category.getText().toString()));
                    dailyExpense.setWalletID(Integer.parseInt(wallet.getText().toString()));
                    dailyExpense.setDate(date);
                    dailyExpense.setNote(note.getText().toString());

                    DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                    boolean status = databaseHelper.addExpense(dailyExpense);
                    System.out.println("Status : " + status);
                    if (status) {
                        successToast("Successfully Inserted");

                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);

                    } else {
                        errorToast("Insert Failed");
                    }
                }
            }
        });
    }

    public void errorToast(String message) {
        View toastView = getLayoutInflater().inflate(R.layout.toast_red, (ViewGroup) view.findViewById(R.id.toast_lin_lay));
        TextView toastText = toastView.findViewById(R.id.toast_text_red);
        toastText.setText(message);

        Toast toast = new Toast(getActivity());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(toastView);
        toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);
        toast.show();
    }

    public void successToast(String message) {
        View toastView = getLayoutInflater().inflate(R.layout.toast_blue, (ViewGroup) view.findViewById(R.id.toast_lin_lay));
        TextView toastText = toastView.findViewById(R.id.toast_text_red);
        toastText.setText(message);

        Toast toast = new Toast(getActivity());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(toastView);
        toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);
        toast.show();
    }

    public boolean checkFields() {
        System.out.println("Check Fields");
        boolean status = true;
        if (amount.getText().length() == 0) {
            errorToast("Amount can not be Empty");
            status = false;
        } else if (category.getText().length() == 0) {
            status = false;
            errorToast("Category can not be Empty");
        } else if (wallet.getText().length() == 0) {
            status = false;
            errorToast("Wallet can not be Empty");
        } else if (note.getText().length() == 0) {
            note.setText(" ");
        }
        return status;
    }
}