package com.example.mywallet.UI.Expenses;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mywallet.DatabaseHelper;
import com.example.mywallet.MainActivity;
import com.example.mywallet.Model.Category;
import com.example.mywallet.Model.DailyExpense;
import com.example.mywallet.Model.Wallet;
import com.example.mywallet.R;
import com.example.mywallet.ToastMessage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class UpdateExpense extends Fragment {

    DatePickerDialog picker;
    TextView eText;
    DailyExpense dailyEx;
    private int recordId;
    private EditText amount, note;
    private TextView category, wallet;
    View view;
    private Button btnAddExpense, btnCancel;
    private ToastMessage toastMessage;
    private int categoryId, walletId;

    public UpdateExpense() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recordId = getActivity().getIntent().getIntExtra("id", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_update_expense, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        try {
            dailyEx = databaseHelper.getDailyExpenseById(recordId);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        eText = (TextView) view.findViewById(R.id.dateInput);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        eText.setText(format.format(dailyEx.getDate()));
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
        btnAddExpense = view.findViewById(R.id.btnUpdate);
        btnCancel = view.findViewById(R.id.cansel);
        amount = view.findViewById(R.id.amount);
        wallet = view.findViewById(R.id.walletId);
        category = view.findViewById(R.id.categoryId);
        note = view.findViewById(R.id.note);
        categoryId = -1;
        walletId = -1;

        //Btn Cancel
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        //Set Current Values
        amount.setText(String.valueOf(dailyEx.getAmount()));
        category.setText(databaseHelper.getCategoryName(dailyEx.getCategoryId()));
        categoryId = dailyEx.getCategoryId();
        walletId = dailyEx.getWalletID();
        wallet.setText(databaseHelper.getWalletNameById(dailyEx.getWalletID()));
        note.setText(dailyEx.getNote());

        //Set category dialog box
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                ArrayList<Category> arrayList = databaseHelper.getCategories();
                final String[] categoryNames = new String[arrayList.size()];
                for(int i = 0; i < arrayList.size(); i++) {
                    categoryNames[i] = arrayList.get(i).getCategoryName();
                }

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                mBuilder.setTitle("Select the Category");
                mBuilder.setIcon(R.drawable.bill);
                mBuilder.setSingleChoiceItems(categoryNames, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        category.setText(categoryNames[which]);
                        categoryId = which+1;
                        dialog.dismiss();
                    }
                });

                mBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        //Set wallet dialog box
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                final ArrayList<Wallet> arrayList = databaseHelper.getWalletsList();
                final String[] walletNames = new String[arrayList.size()];
                for(int i = 0; i < arrayList.size(); i++) {
                    walletNames[i] = arrayList.get(i).getWalletName();
                }
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());

                if(arrayList.size() > 0) {
                    mBuilder.setTitle("Select the Wallet");
                    mBuilder.setIcon(R.drawable.walletmoney);
                    mBuilder.setSingleChoiceItems(walletNames, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            wallet.setText(walletNames[which]);
                            walletId = arrayList.get(which).getWalletId();
                            dialog.dismiss();
                        }
                    });

                    mBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog mDialog = mBuilder.create();
                    mDialog.show();
                } else {
                    final Dialog dialog = new Dialog(getContext());
                    dialog.setContentView(R.layout.delete_pop_up);
                    TextView message = dialog.findViewById(R.id.message);
                    message.setText("No Wallets Found. Create One.");
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    Button create = dialog.findViewById(R.id.positiveBtn);
                    create.setText("GO");
                    create.setBackground(getResources().getDrawable(R.drawable.background_blue_fully_rounded));

                    Button cancel = dialog.findViewById(R.id.negativeBtn);
                    cancel.setText("LATER");

                    create.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    });

                    dialog.show();
                }
            }
        });

        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFields()) {
                    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = Calendar.getInstance().getTime();
                    try {
                        date = format.parse(eText.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    DailyExpense dailyExpense = new DailyExpense();
                    dailyExpense.setAmount(Double.parseDouble(amount.getText().toString()));
                    dailyExpense.setCategoryId(categoryId);
                    dailyExpense.setWalletID(walletId);
                    dailyExpense.setDate(date);
                    dailyExpense.setNote(note.getText().toString());
                    dailyExpense.setRecordId(dailyEx.getRecordId());

                    DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                    System.out.println("amount" + dailyExpense.getAmount());
                    boolean status = false;
                    status = databaseHelper.updateExpense(dailyExpense);

                    if (status) {
                        toastMessage.successToast("Successfully Updated");
                        getActivity().onBackPressed();

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
        } else if (categoryId == -1) {
            status = false;
            toastMessage.errorToast("Category can not be Empty");
        } else if (walletId == -1) {
            status = false;
            toastMessage.errorToast("Wallet can not be Empty");
        } else if (note.getText().length() == 0) {
            note.setText(" ");
        }
        return status;
    }
}