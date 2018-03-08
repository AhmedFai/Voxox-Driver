package com.example.faizan.voxoxdriver;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faizan.voxoxdriver.BookingDetailsPOJO.BookingDetailsBean;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class BookingDetails extends AppCompatActivity {

    Toolbar toolbar;
    String BillId;
    ProgressBar bar;
    TextView bill, status, kilometer, time;
    ConnectionDetector cd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle(BillId);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow);
        bar = (ProgressBar)findViewById(R.id.progress);
        bill = (TextView)findViewById(R.id.bill);
        status = (TextView)findViewById(R.id.status);
        kilometer = (TextView)findViewById(R.id.km);
        time = (TextView)findViewById(R.id.min);
        cd = new ConnectionDetector(getApplication());
        BillId = getIntent().getStringExtra("BillId");
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
            Call<BookingDetailsBean> call = cr.details(BillId);
            call.enqueue(new Callback<BookingDetailsBean>() {
                @Override
                public void onResponse(Call<BookingDetailsBean> call, Response<BookingDetailsBean> response) {
                    if (Objects.equals(response.body().getStatus(),"1")){
                        // Toast.makeText(BookingDetails.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        bar.setVisibility(View.GONE);
                        bill.setText("₹ " + response.body().getData().getOperatorBill());
                        status.setText(response.body().getData().getBookingStatus());
                        kilometer.setText(String.valueOf(response.body().getData().getDistanceTravelled()));
                        time.setText(response.body().getData().getRideTime());


                    }else {
                        Toast.makeText(BookingDetails.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        bar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<BookingDetailsBean> call, Throwable t) {

                    bar.setVisibility(View.GONE);
                }
            });

        }else {

            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();

        }

    }
}
