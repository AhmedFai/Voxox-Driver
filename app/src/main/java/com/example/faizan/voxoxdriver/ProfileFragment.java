package com.example.faizan.voxoxdriver;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faizan.voxoxdriver.GetProfilePOJO.GetProfileBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.utils.L;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class ProfileFragment extends Fragment {
    Toolbar toolbar;
    CircleImageView image;
    TextView name, contact, mail;
    SharedPreferences pref;
    SharedPreferences.Editor edit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        toolbar = ((MainActivity)getActivity()).findViewById(R.id.toolbar);
        pref = getContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        edit = pref.edit();
        image = (CircleImageView) view.findViewById(R.id.profileImage);
        name = (TextView) view.findViewById(R.id.userName);
        contact = (TextView) view.findViewById(R.id.contact);
        mail = (TextView) view.findViewById(R.id.email);

        final Bean b = (Bean) getContext().getApplicationContext();

        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Allapi cr = retrofit2.create(Allapi.class);
        String id = pref.getString("driverId", "");
        Log.d("driverIdProfile", id);
        Log.d("beandriverId", b.driverId);
        Call<GetProfileBean> call = cr.profile(id);
        call.enqueue(new Callback<GetProfileBean>() {
            @Override
            public void onResponse(Call<GetProfileBean> call, Response<GetProfileBean> response) {

                if (Objects.equals(response.body().getStatus(),"1")){
                    DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                            .cacheOnDisk(true).resetViewBeforeLoading(true).build();

                    ImageLoader loader = ImageLoader.getInstance();

                    MainActivity activity = (MainActivity)getContext();

                    activity.loadImage();

                    loader.displayImage(response.body().getData().getImage(), image, options);
                    name.setText(response.body().getData().getUsername());
                    contact.setText(response.body().getData().getPhone());
                    mail.setText(response.body().getData().getEmail());

                    edit.putString("image", response.body().getData().getImage());
                    edit.apply();
                }
                else {
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetProfileBean> call, Throwable t) {


                Log.d("Failure", t.toString());
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        toolbar.setNavigationIcon(R.drawable.arrow);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFragmentManager().popBackStack();

            }
        });

    }
}
