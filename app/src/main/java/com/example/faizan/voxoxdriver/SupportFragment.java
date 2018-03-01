package com.example.faizan.voxoxdriver;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faizan.voxoxdriver.TodaySummaryPOJO.SummaryBean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class SupportFragment extends Fragment {

    CardView cBalance, byday,bank;
    LinearLayout opBill;
    ProgressBar bar;
    TextView balance, payment, cash, hour;

    Toolbar toolbar;
    ConnectionDetector cd;
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
        balance = (TextView)view.findViewById(R.id.bal);
        payment = (TextView)view.findViewById(R.id.pay);
        cash = (TextView)view.findViewById(R.id.cash);
        hour = (TextView)view.findViewById(R.id.hour);
        cd = new ConnectionDetector(getContext());

       // bank = (CardView) view.findViewById(R.id.bankCard);

        opBill = (LinearLayout) view.findViewById(R.id.operatorBill);
        bar = (ProgressBar)view.findViewById(R.id.progress);

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

        if (cd.isConnectingToInternet()){

            bar.setVisibility(View.VISIBLE);
            final Bean b = (Bean) getContext().getApplicationContext();

            Retrofit retrofit2 = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Allapi cr = retrofit2.create(Allapi.class);

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = mdformat.format(calendar.getTime());

            Log.d("date" , strDate);

            // String date = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day);
            Call<SummaryBean> call = cr.summary(b.driverId,strDate);
            call.enqueue(new Callback<SummaryBean>() {
                @Override
                public void onResponse(Call<SummaryBean> call, Response<SummaryBean> response) {
                    if (Objects.equals(response.body().getStatus(),"1")){
                        bar.setVisibility(View.GONE);

                        balance.setText(response.body().getData().getCurrentBalance());
                        payment.setText(response.body().getData().getTodaysPayment());
                        cash.setText(response.body().getData().getCashCollected());
                        hour.setText(response.body().getData().getLoginHours());


                    }else {
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        bar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<SummaryBean> call, Throwable t) {
                    bar.setVisibility(View.GONE);

                }
            });


        }else {
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();

        }

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
