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

import com.example.faizan.voxoxdriver.IncentiveSchemePOJO.Datum;
import com.example.faizan.voxoxdriver.IncentiveSchemePOJO.IncentiveSchemeBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class Scheme extends Fragment {

    SchemeRecyclerAdapter adapter;
    GridLayoutManager manager;
    RecyclerView recycler;
    List<Datum> list;
    ProgressBar bar;
    ConnectionDetector cd;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scheme,container,false);

        list = new ArrayList<>();
        recycler = (RecyclerView) view.findViewById(R.id.schemeRecycler);
        manager = new GridLayoutManager(getContext(),1);
        adapter = new SchemeRecyclerAdapter(getContext(),list );
        recycler.setLayoutManager(manager);
        recycler.setAdapter(adapter);
        recycler.setNestedScrollingEnabled(false);
        bar = (ProgressBar) view.findViewById(R.id.progress);
        cd = new ConnectionDetector(getContext());

        if (cd.isConnectingToInternet()){

            bar.setVisibility(View.VISIBLE);

            final Bean b = (Bean) getContext().getApplicationContext();

            Retrofit retrofit2 = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Allapi cr = retrofit2.create(Allapi.class);
            Call<IncentiveSchemeBean> call = cr.scheme(b.driverId);
            call.enqueue(new Callback<IncentiveSchemeBean>() {
                @Override
                public void onResponse(Call<IncentiveSchemeBean> call, Response<IncentiveSchemeBean> response) {
                    if (Objects.equals(response.body().getStatus(),"1")){
                        // Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        bar.setVisibility(View.GONE);

                        adapter.setGridData(response.body().getData());
                    }else {
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        bar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<IncentiveSchemeBean> call, Throwable t) {

                    bar.setVisibility(View.GONE);
                }
            });

        }else {
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();

        }


        return view;
    }

}
