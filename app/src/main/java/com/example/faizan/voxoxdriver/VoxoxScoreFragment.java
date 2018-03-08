package com.example.faizan.voxoxdriver;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.example.faizan.voxoxdriver.ScorePOJO.ScoreBean;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class VoxoxScoreFragment extends Fragment {

    RoundCornerProgressBar quality, hour;

    ProgressBar bar;
    ConnectionDetector cd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_voxox_score, container, false);

        quality = (RoundCornerProgressBar)view.findViewById(R.id.score);
        hour = (RoundCornerProgressBar)view.findViewById(R.id.hours);
        bar = (ProgressBar)view.findViewById(R.id.progress);
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
            Call<ScoreBean> call = cr.score(b.driverId);
            call.enqueue(new Callback<ScoreBean>() {
                @Override
                public void onResponse(Call<ScoreBean> call, Response<ScoreBean> response) {
                    if (Objects.equals(response.body().getStatus(),"1")){
                        bar.setVisibility(View.GONE);



                        quality.setProgress(Float.parseFloat(response.body().getData().getCarQualityScorePercentage()));
                        hour.setProgress(Float.parseFloat(response.body().getData().getLoginHoursPercentage()));
                        Log.d("score", response.body().getData().getCarQualityScorePercentage());
                        Log.d("hours", response.body().getData().getLoginHoursPercentage());

                    }else {
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        bar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<ScoreBean> call, Throwable t) {
                    bar.setVisibility(View.GONE);


                }
            });


        }else {
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();

        }


        return view;
    }

}
