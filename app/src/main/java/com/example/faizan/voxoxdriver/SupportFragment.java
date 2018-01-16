package com.example.faizan.voxoxdriver;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class SupportFragment extends Fragment {

    CardView cBalance;
    LinearLayout opBill;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_support,container,false);

        cBalance = (CardView) view.findViewById(R.id.currentBalance);

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
        return view;
    }
}
