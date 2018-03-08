package com.example.faizan.voxoxdriver;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.faizan.voxoxdriver.IncentivesEarnedPojo.Datum;
import com.example.faizan.voxoxdriver.IncentivesEarnedPojo.IncentivesEarnedBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class IncentiveEarned extends Fragment {




    RecyclerView recyclerView;
    GridLayoutManager manager;
    IncentiveItemAdpater adpater;
    List<Datum> list;
    ProgressBar bar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_incentive_earned,container,false);

        list = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.incentiveRecycler);
        manager = new GridLayoutManager(getContext(),1);
        adpater = new IncentiveItemAdpater(getContext(),list);
        recyclerView.setAdapter(adpater);
        recyclerView.setLayoutManager(manager);
        recyclerView.setNestedScrollingEnabled(false);
        bar = (ProgressBar)view.findViewById(R.id.progress);


        bar.setVisibility(View.VISIBLE);

        final Bean b = (Bean) getContext().getApplicationContext();

        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Allapi cr = retrofit2.create(Allapi.class);
        Call<IncentivesEarnedBean> call = cr.earned(b.driverId);
        call.enqueue(new Callback<IncentivesEarnedBean>() {
            @Override
            public void onResponse(Call<IncentivesEarnedBean> call, Response<IncentivesEarnedBean> response) {
                if (Objects.equals(response.body().getStatus(),"1")){
                   // Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    bar.setVisibility(View.GONE);
                    adpater.setGridData(response.body().getData());
                }else {
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    bar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<IncentivesEarnedBean> call, Throwable t) {

                bar.setVisibility(View.GONE);
            }
        });

        return view;
    }

}
