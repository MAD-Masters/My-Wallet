package com.example.mywallet.UI.Income;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mywallet.R;
import com.example.mywallet.Model.IncomeModel;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Income2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Income2 extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<IncomeModel> incomeModelArrayListList;
    private Income2adapter income2adapter;
    private RecyclerView.LayoutManager layoutManager;
    private View root;
    Button btn;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Income2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment income2.
     */
    // TODO: Rename and change types and number of parameters
    public static Income2 newInstance(String param1, String param2) {
        Income2 fragment = new Income2();
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
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_income2, container, false);
        return root;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn = root.findViewById(R.id.addmoney);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Income5 income5 = new Income5();
                fragmentManager = getParentFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, income5);
                fragmentTransaction.commit();
            }
        });

        incomeModelArrayListList = new ArrayList<>();

        incomeModelArrayListList.add(new IncomeModel("hnb bank", 1, Calendar.getInstance().getTime(), 40000, "Lorem  ispham dika kliokalsd "));
        incomeModelArrayListList.add(new IncomeModel("wallet", 2, Calendar.getInstance().getTime(), 30000, "Lorem  ispham dika kliokalsd "));

        recyclerView = root.findViewById(R.id.list_income);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        income2adapter = new Income2adapter(getContext() ,incomeModelArrayListList);
        recyclerView.setAdapter(income2adapter);
    }
}