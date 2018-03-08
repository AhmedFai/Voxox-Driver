package com.example.faizan.voxoxdriver;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faizan.voxoxdriver.OperatorBillPOJO.BookingDatum;
import com.example.faizan.voxoxdriver.OperatorBillPOJO.OperatorBillBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class OperatorBill extends AppCompatActivity {

    TextView date, amount, bookings;
    RecyclerView grid;
    OperatorBillAdapter adapter;
    GridLayoutManager manager;
    Toolbar toolbar;
    List<BookingDatum> list;
    ProgressBar bar;
    ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator_bill);
        toolbar = (Toolbar)findViewById(R.id.opToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        toolbar.setTitle("Operator Bill");
        toolbar.setTitleTextColor(Color.WHITE);
        cd = new ConnectionDetector(getApplication());

        list = new ArrayList<>();
        toolbar.setNavigationIcon(R.drawable.arrow);
        //arr = (TextView)findViewById(R.id.arrow);
        grid = (RecyclerView)findViewById(R.id.grid);
        grid = findViewById(R.id.grid);
        manager= new GridLayoutManager(this,1);
        adapter = new OperatorBillAdapter(this, list);
        grid.setNestedScrollingEnabled(false);
        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);
        bar = (ProgressBar)findViewById(R.id.progress);
        date = (TextView)findViewById(R.id.date);
        amount = (TextView)findViewById(R.id.amount);
        bookings = (TextView)findViewById(R.id.booking);
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
            Call<OperatorBillBean> call = cr.operatorBill(b.driverId);
            call.enqueue(new Callback<OperatorBillBean>() {
                @Override
                public void onResponse(Call<OperatorBillBean> call, Response<OperatorBillBean> response) {
                    if (Objects.equals(response.body().getStatus(), "1")){
                        Toast.makeText(OperatorBill.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        bar.setVisibility(View.GONE);
                        date.setText(response.body().getData().getDate());
                        amount.setText("₹ " + String.valueOf(response.body().getData().getTotalOperatorBill()));
                        bookings.setText(String.valueOf(response.body().getData().getTotalBooking()));
                        adapter.setGridData(response.body().getData().getBookingData());

                    }else {
                        Toast.makeText(OperatorBill.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        bar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<OperatorBillBean> call, Throwable t) {
                    bar.setVisibility(View.GONE);
                }
            });




        }else {

            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();

        }




    }
}
