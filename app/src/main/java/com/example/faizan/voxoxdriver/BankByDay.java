package com.example.faizan.voxoxdriver;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class BankByDay extends AppCompatActivity {

    RecyclerView grid;
    GridLayoutManager manager;
    BankByDayAdapter adapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_by_day);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.arrow);
        toolbar.setTitle("Net Bank By Day");
        toolbar.setTitleTextColor(Color.WHITE);

        grid = (RecyclerView) findViewById(R.id.grid);
        manager = new GridLayoutManager(getApplication(), 1);
        adapter = new BankByDayAdapter(getApplication());
        grid.setLayoutManager(manager);
        grid.setAdapter(adapter);
        grid.setNestedScrollingEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
