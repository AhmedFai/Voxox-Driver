package com.example.faizan.voxoxdriver;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faizan.voxoxdriver.acceptDenyPOJO.acceptDenyBean;
import com.example.faizan.voxoxdriver.driverNotificationPOJO.notificationBean;
import com.example.faizan.voxoxdriver.driverStatusPOJO.StatusBean;
import com.example.faizan.voxoxdriver.rideStatusPOJO.rideStatusBean;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.security.acl.LastOwnerException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import github.nisrulz.easydeviceinfo.base.EasyLocationMod;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class BookRideFragment extends Fragment implements View.OnClickListener {
    SupportMapFragment mSupportMapFragment;

    Toolbar toolbar;
    Timer timer;
    SharedPreferences pref;
    SharedPreferences.Editor edit;
    CardView noti;
    ProgressBar bar;
    ImageView offI, onI, homeI, accept, deny;
    LinearLayout offLin, onLin, homeLin;
    String notiId;

    TextView notiName, pickupLocation;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book_ride, container, false);

        toolbar = ((MainActivity) getActivity()).findViewById(R.id.toolbar);

        offLin = (LinearLayout) view.findViewById(R.id.offLine);
        onLin = (LinearLayout) view.findViewById(R.id.onLine);
        homeLin = (LinearLayout) view.findViewById(R.id.homeLine);
        offI = (ImageView) view.findViewById(R.id.offIcon);
        onI = (ImageView) view.findViewById(R.id.onIcon);
        homeI = (ImageView) view.findViewById(R.id.homeIcon);
        offLin.setOnClickListener(this);
        onLin.setOnClickListener(this);
        homeLin.setOnClickListener(this);
        bar = (ProgressBar) view.findViewById(R.id.progress);
        notiName = (TextView) view.findViewById(R.id.reqName);
        pickupLocation = (TextView) view.findViewById(R.id.pickupLoc);
        accept = (ImageView) view.findViewById(R.id.accept);
        deny = (ImageView) view.findViewById(R.id.deny);


        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timer.cancel();
                call2.cancel();

                String id = pref.getString("driverId", "");

                bar.setVisibility(View.VISIBLE);

                final Bean b = (Bean) getContext().getApplicationContext();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Allapi cr = retrofit.create(Allapi.class);
                Call<acceptDenyBean> callacc = cr.accept(id, b.notificationId, "1");
                Log.d("acceptId", id);


                callacc.enqueue(new Callback<acceptDenyBean>() {
                    @Override
                    public void onResponse(Call<acceptDenyBean> call, Response<acceptDenyBean> response) {

                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        noti.setVisibility(View.GONE);


                        bar.setVisibility(View.GONE);


                    }

                    @Override
                    public void onFailure(Call<acceptDenyBean> call, Throwable t) {

                        Log.d("accpetkbjcbd", t.toString());

                        bar.setVisibility(View.GONE);
                    }
                });
            }
        });


        deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*timer.cancel();
                call2.cancel();*/

                String id = pref.getString("driverId", "");


                bar.setVisibility(View.VISIBLE);
                final Bean b = (Bean) getContext().getApplicationContext();


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Allapi cr = retrofit.create(Allapi.class);
                Call<acceptDenyBean> call = cr.accept(id, b.notificationId, "2");

                Log.d("notidriverId", id);
                Log.d("notiId", b.notificationId);

                call.enqueue(new Callback<acceptDenyBean>() {
                    @Override
                    public void onResponse(Call<acceptDenyBean> call, Response<acceptDenyBean> response) {


                        Log.d("skjfjfgj", "dgfdg");

                        //if (Objects.equals(response.body().getStatus(), ))


                        noti.setVisibility(View.GONE);


                        // Log.d("nameddafdf", response.body().getData().getUserName());

                        bar.setVisibility(View.GONE);


                    }

                    @Override
                    public void onFailure(Call<acceptDenyBean> call, Throwable t) {

                        Log.d("fkjnf", t.toString());

                        bar.setVisibility(View.GONE);
                    }
                });


            }
        });


        pref = getContext().getSharedPreferences("pref", Activity.MODE_PRIVATE);
        edit = pref.edit();

        noti = (CardView) view.findViewById(R.id.notiBox);
        // progress =(ProgressBar) view.findViewById(R.id.progress);

        mSupportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.bookRideFragment);
        if (mSupportMapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mSupportMapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.bookRideFragment, mSupportMapFragment).commit();
        }

        if (mSupportMapFragment != null) {
            mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(final GoogleMap googleMap) {
                    if (googleMap != null) {

                        googleMap.getUiSettings().setAllGesturesEnabled(true);

                        EasyLocationMod easyLocationMod = new EasyLocationMod(getContext());

                        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }

                        double[] l = easyLocationMod.getLatLong();
                        String lat = String.valueOf(l[0]);
                        String lon = String.valueOf(l[1]);

                        LatLng myLocation = new LatLng(Double.parseDouble(lat), Double.parseDouble(lon));
                        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                        try {
                            List<Address> listAdresses = geocoder.getFromLocation(Double.parseDouble(lat), Double.parseDouble(lon), 1);
                            if (null != listAdresses && listAdresses.size() > 0) {
                                String address = listAdresses.get(0).getAddressLine(0);
                                String state = listAdresses.get(0).getAdminArea();
                                String country = listAdresses.get(0).getCountryName();
                                String subLocality = listAdresses.get(0).getSubLocality();

                                googleMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lon)))
                                        .title("" + subLocality + ", " + state + ", " + country + "")
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker)));


                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Log.d("lat", lat);
                        Log.d("lon", lon);

                        googleMap.setMyLocationEnabled(false);
                        CameraPosition cameraPosition = new CameraPosition.Builder().target(myLocation).zoom(15.0f).build();
                        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                        googleMap.moveCamera(cameraUpdate);


                    }

                }

            });


        }


        return view;
    }


    private void doSomethingRepeatedly() {
        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                try {
                    EasyLocationMod easyLocationMod = new EasyLocationMod(getContext());


                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    double[] l = easyLocationMod.getLatLong();
                    String lat = String.valueOf(l[0]);
                    String lon = String.valueOf(l[1]);
                    String id = pref.getString("driverId", "");

                    Log.d("jns", id);
                    Log.d("lat", lat);
                    Log.d("lon", lon);


                    final Bean b = (Bean) getContext().getApplicationContext();


                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(b.baseURL)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    Allapi cr = retrofit.create(Allapi.class);

                    Call<notificationBean> call = cr.notify(id, lat, lon);
                    call.enqueue(new Callback<notificationBean>() {
                        @Override
                        public void onResponse(Call<notificationBean> call, final Response<notificationBean> response) {
                            if (Objects.equals(response.body().getStatus(), 1)) {


                                try {
                                    Log.d("djdf", "jhas");
                                    noti.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            noti.setVisibility(View.VISIBLE);


                                            if (response.body().getData().size() > 0) {

                                                try {

                                                    notiName.setText(response.body().getData().get(0).getUserName());
                                                    Log.d("nameiudhdu", response.body().getData().get(0).getUserName());

                                                    b.notificationId = response.body().getData().get(0).getBookingId();

                                                    Log.d("NotificationId", b.notificationId);

                                                    String pickLat = response.body().getData().get(0).getPickUpLatitude();
                                                    String pickLon = response.body().getData().get(0).getPickUpLongitude();
                                                    final LatLng myLocation = new LatLng(Double.parseDouble(pickLat), Double.parseDouble(pickLon));
                                                    Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                                                    try {

                                                        List<Address> listAdresses = geocoder.getFromLocation(Double.parseDouble(pickLat), Double.parseDouble(pickLon), 1);
                                                        if (null != listAdresses && listAdresses.size() > 0) {
                                                            String address = listAdresses.get(0).getAddressLine(0);
                                                            String state = listAdresses.get(0).getAdminArea();
                                                            String country = listAdresses.get(0).getCountryName();
                                                            String subLocality = listAdresses.get(0).getSubLocality();
                                                            pickupLocation.setText(address.toString());
                                                            Log.d("addre", address.toString());
                                                        }

                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }

                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }


                                            } else {
                                                //Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                                noti.setVisibility(View.GONE);

                                            }

                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            } else {
                                //Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                            }

                        }

                        @Override
                        public void onFailure(Call<notificationBean> call, Throwable t) {

                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }, 0, 1000 * 1);


    }

    public void statusForRide() {

        EasyLocationMod easyLocationMod = new EasyLocationMod(getContext());


        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        double[] l = easyLocationMod.getLatLong();
        String lat = String.valueOf(l[0]);
        String lon = String.valueOf(l[1]);
        String id = pref.getString("driverId", "");



        final Bean b = (Bean) getContext().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Allapi cr = retrofit.create(Allapi.class);

        Call<rideStatusBean> callRide = cr.rideStatus(id,b.notificationId, lat, lon);
        callRide.enqueue(new Callback<rideStatusBean>() {
            @Override
            public void onResponse(Call<rideStatusBean> call, Response<rideStatusBean> response) {

                if (Objects.equals(response.body().getStatus(), 1)){
                    String pickLat = response.body().getData().getPickUpLatitude();
                    String pickLon = response.body().getData().getPickUpLongitude();
                    String dropLat = response.body().getData().getDropLatitude();
                    String dropLon = response.body().getData().getDropLongitude();
                    Log.d("dfsdsfgsgsfsgsdgd", response.body().getData().getUserName());
                }
            }

            @Override
            public void onFailure(Call<rideStatusBean> call, Throwable t) {


                Log.d("dfsgsgsdgsdgsgsdgssssd",t.toString());

            }
        });


    }

    public void stopTask() {
        if (timer != null) {
            timer.cancel();
        }
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

    Call<StatusBean> call2;

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.offLine: {
                offI.setBackgroundResource(R.drawable.backcar);
                onI.setBackgroundResource(R.drawable.back_circle);
                homeI.setBackgroundResource(R.drawable.back_circle);


                try {
                    timer.cancel();
                    noti.setVisibility(View.GONE);
                    call2.cancel();
                }catch (Exception e){
                    e.printStackTrace();
                }



                String id = pref.getString("driverId", "");
                bar.setVisibility(View.VISIBLE);


                final Bean b = (Bean) getContext().getApplicationContext();


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Allapi cr = retrofit.create(Allapi.class);
                Call<StatusBean> call = cr.status(id, "off");
                call.enqueue(new Callback<StatusBean>() {
                    @Override
                    public void onResponse(Call<StatusBean> call, Response<StatusBean> response) {
                        if (Objects.equals(response.body().getStatus(), 1)) {

                            Toast.makeText(getContext(), "Your duty is off ", Toast.LENGTH_SHORT).show();
                            bar.setVisibility(View.GONE);


                        } else {
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            bar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<StatusBean> call, Throwable t) {

                        bar.setVisibility(View.GONE);
                    }
                });


                break;
            }

            case R.id.onLine:

            {
                offI.setBackgroundResource(R.drawable.back_circle);
                onI.setBackgroundResource(R.drawable.backcar);
                homeI.setBackgroundResource(R.drawable.back_circle);


                String id2 = pref.getString("driverId", "");

                bar.setVisibility(View.VISIBLE);
                final Bean b2 = (Bean) getContext().getApplicationContext();


                Retrofit retrofit2 = new Retrofit.Builder()
                        .baseUrl(b2.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Allapi cr2 = retrofit2.create(Allapi.class);
                call2 = cr2.status(id2, "on");
                call2.enqueue(new Callback<StatusBean>() {
                    @Override
                    public void onResponse(Call<StatusBean> call, Response<StatusBean> response) {
                        if (Objects.equals(response.body().getStatus(), 1)) {

                            doSomethingRepeatedly();

                            Toast.makeText(getContext(), "You are on duty", Toast.LENGTH_SHORT).show();
                            bar.setVisibility(View.GONE);

                        } else {
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            bar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<StatusBean> call, Throwable t) {
                        bar.setVisibility(View.GONE);

                    }
                });


                break;
            }

            case R.id.homeLine: {

                offI.setBackgroundResource(R.drawable.back_circle);
                onI.setBackgroundResource(R.drawable.back_circle);
                homeI.setBackgroundResource(R.drawable.backcar);


                try {
                    timer.cancel();
                    noti.setVisibility(View.GONE);
                    call2.cancel();
                }catch (Exception e){
                    e.printStackTrace();
                }


                String id3 = pref.getString("driverId", "");
                bar.setVisibility(View.VISIBLE);


                final Bean b3 = (Bean) getContext().getApplicationContext();


                Retrofit retrofit3 = new Retrofit.Builder()
                        .baseUrl(b3.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Allapi cr3 = retrofit3.create(Allapi.class);
                Call<StatusBean> call3 = cr3.status(id3, "home");
                call3.enqueue(new Callback<StatusBean>() {
                    @Override
                    public void onResponse(Call<StatusBean> call, Response<StatusBean> response) {
                        if (Objects.equals(response.body().getStatus(), 1)) {

                            Toast.makeText(getContext(), "Thank you Go Home", Toast.LENGTH_SHORT).show();
                            bar.setVisibility(View.GONE);

                        } else {
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            bar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<StatusBean> call, Throwable t) {
                        bar.setVisibility(View.GONE);

                    }
                });
                break;
            }

        }


    }
}
