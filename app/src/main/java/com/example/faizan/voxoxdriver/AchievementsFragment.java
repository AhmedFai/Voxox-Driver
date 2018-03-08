package com.example.faizan.voxoxdriver;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faizan.voxoxdriver.AchievementsPOJO.AchievementsBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class AchievementsFragment extends Fragment {

    ImageView rideImage, pickImage;
    TextView ride, number, pick, pickNum;
    ProgressBar bar;
    ConnectionDetector cd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_achievements, container,false);

        rideImage = (ImageView)view.findViewById(R.id.rideImage);
        number = (TextView)view.findViewById(R.id.rideNumber);
        ride = (TextView)view.findViewById(R.id.rideText);

        pickImage = (ImageView)view.findViewById(R.id.onpick);
        pickNum = (TextView)view.findViewById(R.id.onpickTime);
        pick = (TextView)view.findViewById(R.id.image);
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
            Call<AchievementsBean> call = cr.achieve(b.driverId);
            call.enqueue(new Callback<AchievementsBean>() {
                @Override
                public void onResponse(Call<AchievementsBean> call, Response<AchievementsBean> response) {
                    if (Objects.equals(response.body().getStatus(), "1")){

                        bar.setVisibility(View.GONE);
                        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                                .cacheOnDisk(true).resetViewBeforeLoading(true).build();

                        ImageLoader loader = ImageLoader.getInstance();

                        loader.displayImage(response.body().getData().getVoxoxAchievements().getIcon(), rideImage, options);
                        number.setText(response.body().getData().getVoxoxAchievements().getRating());
                        ride.setText(response.body().getData().getVoxoxAchievements().getText());


                        loader.displayImage(response.body().getData().getCustomerCompliments().getIcon(),pickImage, options);
                        pickNum.setText(response.body().getData().getCustomerCompliments().getRating());
                        pick.setText(response.body().getData().getCustomerCompliments().getText());

                    }else {
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        bar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<AchievementsBean> call, Throwable t) {

                    bar.setVisibility(View.GONE);
                }
            });



        }else {
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }


        return view;
    }

}
