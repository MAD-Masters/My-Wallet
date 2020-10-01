package com.example.mywallet.UI.Income;

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
import android.widget.EditText;

import com.example.mywallet.DatabaseHelper;
import com.example.mywallet.MainActivity;
import com.example.mywallet.Model.Wallet;
import com.example.mywallet.R;
import com.example.mywallet.ToastMessage;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Income4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Income4 extends Fragment {

    private EditText mywallet,bank;
    private Button addwallet;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ToastMessage toastMessage;
    View view;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Income4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment income4.
     */
    // TODO: Rename and change types and number of parameters
    public static Income4 newInstance(String param1, String param2) {
        Income4 fragment = new Income4();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_income4, container, false);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        toastMessage = new ToastMessage(getActivity(), view);


        mywallet = view.findViewById(R.id.textInputEditText8);
        bank = view.findViewById(R.id.textInputEditText3);
        addwallet = view.findViewById(R.id.update);

        addwallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Wallet wallet = new Wallet();
               wallet.setWalletName(mywallet.getText().toString());
               wallet.setBank(bank.getText().toString());

                DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                boolean status = databaseHelper.addwallet(wallet);

                if (status) {
                    toastMessage.successToast("Successfully Inserted");

                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);

                } else {
                    toastMessage.errorToast("Insert Failed");
                }


            }
        });
    }
}