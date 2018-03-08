package com.example.faizan.voxoxdriver;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

public class IncentiveBreakup extends AppCompatActivity {

    Toolbar toolbar;
    LinearLayout opBill,acBill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incentive_breakup);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        opBill = (LinearLayout)findViewById(R.id.ineoBill);
        acBill = (LinearLayout)findViewById(R.id.aoBill);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Incentive Breakup");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        opBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(IncentiveBreakup.this,IncentiveEligibleOperatorBill.class);
                startActivity(i);
            }
        });
        acBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(IncentiveBreakup.this,ActualOperatorBill.class);
                startActivity(in);
            }
        });
    }
}
