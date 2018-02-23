package com.example.faizan.voxoxdriver;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;



public class SupportFragment extends Fragment {

    CardView cBalance, byday,bank;
    LinearLayout opBill;

    Toolbar toolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_support,container,false);

        toolbar = ((MainActivity)getActivity()).findViewById(R.id.toolbar);

        cBalance = (CardView) view.findViewById(R.id.currentBalance);

        byday = (CardView) view.findViewById(R.id.byDay);

       // bank = (CardView) view.findViewById(R.id.bankCard);

        opBill = (LinearLayout) view.findViewById(R.id.operatorBill);

        opBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), OperatorBill.class);
                startActivity(intent);
            }
        });

        cBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getContext(), CurrentBalanceStatement.class);
                startActivity(i);

            }
        });

        byday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getContext(), EarningByDay.class);
                startActivity(in);
            }
        });

  /*      bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(getContext(),BankByDay.class);
                startActivity(b);
            }
        });*/
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        toolbar.setNavigationIcon(null);

        /*toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFragmentManager().popBackStack();

            }
        });*/

    }
}
