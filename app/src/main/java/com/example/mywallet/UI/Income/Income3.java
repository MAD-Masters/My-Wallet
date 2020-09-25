package com.example.mywallet.UI.Income;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mywallet.DatabaseHelper;
import com.example.mywallet.MainActivity;
import com.example.mywallet.Model.Wallet;
import com.example.mywallet.R;
import com.example.mywallet.ToastMessage;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Income3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Income3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int walletid;
    Wallet wallet;
    private Button update;
    private EditText wallets,bank;
    private ToastMessage toastMessage;
    View view;

    public Income3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment income3.
     */
    // TODO: Rename and change types and number of parameters
    public static Income3 newInstance(String param1, String param2) {
        Income3 fragment = new Income3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        walletid = getActivity().getIntent().getIntExtra("walletid",0);
        System.out.println("wallteid"+walletid);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_income3, container, false);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());

        wallet = databaseHelper.getwalletbyid(walletid);

        toastMessage = new ToastMessage(getActivity(), view);
        update = view.findViewById(R.id.addwallet);
        wallets = view.findViewById(R.id.textInputEditText);
        bank = view.findViewById(R.id.textInputEditText4);


        wallets.setText(wallet.getWalletName());
        bank.setText(wallet.getBank());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Wallet wallet = new Wallet();
                wallet.setWalletName(wallets.getText().toString());
                wallet.setBank(bank.getText().toString());

                DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                boolean status = databaseHelper.updatewallet(wallet);

                if (status) {
                    toastMessage.successToast("Successfully updated");

                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);

                } else {
                    toastMessage.errorToast("update Failed");
                }

            }
        });


    }
}