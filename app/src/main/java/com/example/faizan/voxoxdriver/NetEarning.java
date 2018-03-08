package com.example.faizan.voxoxdriver;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faizan.voxoxdriver.EarningDetailsByDayPOJO.EarningDetailsBean;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class NetEarning extends AppCompatActivity {

    Toolbar toolbar;
    LinearLayout ln;
    String dayId;
    ProgressBar bar;
    TextView date, amount, booking, bill, incentive, tax, earning, cash;
    ConnectionDetector cd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_earning);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
       // ln = (LinearLayout)findViewById(R.id.incentive);
        setSupportActionBar(toolbar);
        dayId = getIntent().getStringExtra("dayId");
        Log.d("dayId", dayId);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
         toolbar.setTitle("Net Earning");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow);
        date = (TextView)findViewById(R.id.date);
        amount = (TextView)findViewById(R.id.amount);
        booking = (TextView)findViewById(R.id.booking);
        bill = (TextView)findViewById(R.id.operatorBill);
        incentive = (TextView)findViewById(R.id.incent);
        tax = (TextView)findViewById(R.id.tax);
        earning = (TextView)findViewById(R.id.earning);
        cash = (TextView)findViewById(R.id.cash);
        bar = (ProgressBar)findViewById(R.id.progress);
        cd = new ConnectionDetector(getApplication());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
 /*       ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NetEarning.this, IncentiveBreakup.class);
                startActivity(i);
            }
        });*/

        if (cd.isConnectingToInternet()){
            bar.setVisibility(View.VISIBLE);
            final Bean b = (Bean) getApplicationContext();

            Retrofit retrofit2 = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Allapi cr = retrofit2.create(Allapi.class);
            Call<EarningDetailsBean> call = cr.earningDetails(b.driverId, dayId);
            Log.d("driverId", b.driverId);
            Log.d("daywaliId", dayId);

            call.enqueue(new Callback<EarningDetailsBean>() {
                @Override
                public void onResponse(Call<EarningDetailsBean> call, Response<EarningDetailsBean> response) {
                    if (Objects.equals(response.body().getStatus(),"1")){
                        bar.setVisibility(View.GONE);
                        date.setText(response.body().getData().getDay());
                        amount.setText(response.body().getData().getNetEarning());
                        booking.setText(response.body().getData().getTotalBooking());
                        bill.setText(response.body().getData().getOperatorBill());
                        incentive.setText("₹ " + response.body().getData().getIncentive());
                        tax.setText(response.body().getData().getTax());
                        earning.setText(response.body().getData().getNetEarning());
                        cash.setText(response.body().getData().getCashCollected());
                    }else {
                        bar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<EarningDetailsBean> call, Throwable t) {

                    bar.setVisibility(View.GONE);
                }
            });

        }else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();

        }

    }
}
