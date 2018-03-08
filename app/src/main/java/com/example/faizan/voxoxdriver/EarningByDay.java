package com.example.faizan.voxoxdriver;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.faizan.voxoxdriver.EarningByDayPOJO.Datum;
import com.example.faizan.voxoxdriver.EarningByDayPOJO.EarningByDayBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class EarningByDay extends AppCompatActivity {
    RecyclerView grid;
    EarningByDayAdapter adapter;
    GridLayoutManager manager;
    Toolbar toolbar;
    ProgressBar bar;
    List<Datum> list;
    ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earning_by_day);
        toolbar = (Toolbar) findViewById(R.id.ebdToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.arrow);
        toolbar.setTitle("Net Earning By Day");
        toolbar.setTitleTextColor(Color.WHITE);
        cd = new ConnectionDetector(getApplication());

        list = new ArrayList<>();
        grid = (RecyclerView) findViewById(R.id.earningRecycler);
        //grid = findViewById(R.id.earningRecycler);
        manager = new GridLayoutManager(this, 1);
        adapter = new EarningByDayAdapter(this, list);
        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);
        grid.setNestedScrollingEnabled(false);
        bar = (ProgressBar)findViewById(R.id.progress);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (cd.isConnectingToInternet()){

            bar.setVisibility(View.VISIBLE);
            final Bean b = (Bean) getApplicationContext();

            Retrofit retrofit2 = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Allapi cr = retrofit2.create(Allapi.class);
            Call<EarningByDayBean> call = cr.earningByDay(b.driverId);
            call.enqueue(new Callback<EarningByDayBean>() {
                @Override
                public void onResponse(Call<EarningByDayBean> call, Response<EarningByDayBean> response) {
                    if (Objects.equals(response.body().getStatus(),"1")){
                        bar.setVisibility(View.GONE);
                        adapter.setGridData(response.body().getData());

                    }else {
                        Toast.makeText(EarningByDay.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        bar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<EarningByDayBean> call, Throwable t) {

                    bar.setVisibility(View.GONE);
                }
            });



        }else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();

        }

    }
}
