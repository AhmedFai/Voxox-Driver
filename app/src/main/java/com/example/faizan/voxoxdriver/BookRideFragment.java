package com.example.faizan.voxoxdriver;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.faizan.voxoxdriver.CurrentOperatorBillPOJO.CurrentBillBean;
import com.example.faizan.voxoxdriver.GetBookingStatus.BookingStatusBean;
import com.example.faizan.voxoxdriver.GoogleMapPOJO.DirectionFinder;
import com.example.faizan.voxoxdriver.GoogleMapPOJO.DirectionFinderListener;

import com.example.faizan.voxoxdriver.GoogleMapPOJO.Route;
import com.example.faizan.voxoxdriver.StartRidePOJO.StartRideBean;
import com.example.faizan.voxoxdriver.acceptDenyPOJO.acceptDenyBean;
import com.example.faizan.voxoxdriver.driverNotificationPOJO.notificationBean;
import com.example.faizan.voxoxdriver.driverStatusPOJO.StatusBean;
import com.example.faizan.voxoxdriver.finishRidePOJO.finishRideBean;
import com.example.faizan.voxoxdriver.rideStatusPOJO.rideStatusBean;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;
import github.nisrulz.easydeviceinfo.base.EasyLocationMod;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class BookRideFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener, DirectionFinderListener {
    SupportMapFragment mSupportMapFragment;

    Toolbar toolbar;
    Timer timer;
    SharedPreferences pref;
    SharedPreferences.Editor edit;
    CardView noti, duty, start, billcard;
    ProgressBar bar;
    ImageView offI, onI, homeI;
    LinearLayout offLin, onLin, homeLin, nav, duration;
    String notiId = " ";

    FloatingActionButton mylocationButton;
    LatLngBounds.Builder builder;
    LatLngBounds bounds;

    String pickUpLat = "";
    String pickUpLng = "";

    String dropLat = "";
    String dropLng = "";

    String currentlat, currentLng;

    TextView notiName, pickupLocation;
    TextView balance, payment, cash, hour;

    Button buttonStrt, accept, deny, go, finishRide;
    ConnectionDetector cd;

    GoogleMap map;
    TextView time, value, incentBooking, opBill, totalIncent, lastUpdate;

    LatLng origin, destination;
    CircleImageView user;

    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();

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
        //  homeLin = (LinearLayout) view.findViewById(R.id.homeLine);
        offI = (ImageView) view.findViewById(R.id.offIcon);
        onI = (ImageView) view.findViewById(R.id.onIcon);
        //homeI = (ImageView) view.findViewById(R.id.homeIcon);
        offLin.setOnClickListener(this);
        onLin.setOnClickListener(this);
        // homeLin.setOnClickListener(this);
        bar = (ProgressBar) view.findViewById(R.id.progress);
        notiName = (TextView) view.findViewById(R.id.reqName);
        pickupLocation = (TextView) view.findViewById(R.id.pickupLoc);
        accept = (Button) view.findViewById(R.id.accept);
        deny = (Button) view.findViewById(R.id.deny);
        start = (CardView) view.findViewById(R.id.start);
        duty = (CardView) view.findViewById(R.id.dutycard);
        time = (TextView) view.findViewById(R.id.esTime);
        value = (TextView) view.findViewById(R.id.value);
        buttonStrt = (Button) view.findViewById(R.id.buttonStrt);
        user = (CircleImageView) view.findViewById(R.id.userPic);
        nav = (LinearLayout) view.findViewById(R.id.nav);
        duration = (LinearLayout) view.findViewById(R.id.duration);
        go = (Button) view.findViewById(R.id.go);
        finishRide = (Button) view.findViewById(R.id.finish);
        cd = new ConnectionDetector(getContext());
        billcard = (CardView) view.findViewById(R.id.billCard);


        incentBooking = (TextView) view.findViewById(R.id.inBooking);
        opBill = (TextView) view.findViewById(R.id.opBill);
        totalIncent = (TextView) view.findViewById(R.id.toIncent);
        lastUpdate = (TextView) view.findViewById(R.id.last);

        mylocationButton = (FloatingActionButton) view.findViewById(R.id.fab);
        mylocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bounds = builder.build();
                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 0);
                map.animateCamera(cu);
            }
        });


        finishRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bar.setVisibility(View.VISIBLE);
                String id = pref.getString("driverId", "");
                final Bean b = (Bean) getContext().getApplicationContext();


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Allapi cr = retrofit.create(Allapi.class);
                Call<finishRideBean> call = cr.rideFinish(id, notiId, currentlat, currentLng);
                call.enqueue(new Callback<finishRideBean>() {
                    @Override
                    public void onResponse(Call<finishRideBean> call, Response<finishRideBean> response) {
                        if (Objects.equals(response.body().getStatus(), 1)) {
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            bar.setVisibility(View.GONE);
                            duty.setVisibility(View.VISIBLE);
                            start.setVisibility(View.GONE);
                            billcard.setVisibility(View.VISIBLE);
                            mylocationButton.setVisibility(View.GONE);
                            map.clear();
                            doSomethingRepeatedly();


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

                                    originMarkers.add(map.addMarker(new MarkerOptions()
                                            .position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lon)))
                                            .title("" + subLocality + ", " + state + ", " + country + "")
                                            .icon(bitmapDescriptorFromVector(getContext(), R.drawable.pin))));


                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Log.d("lat", lat);
                            Log.d("lon", lon);

                            map.setMyLocationEnabled(false);
                            CameraPosition cameraPosition = new CameraPosition.Builder().target(myLocation).zoom(15.0f).build();
                            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                            map.moveCamera(cameraUpdate);


                        } else {
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            bar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<finishRideBean> call, Throwable t) {
                        bar.setVisibility(View.GONE);

                    }
                });

            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + dropLat + "," + dropLng);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });


        buttonStrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                bar.setVisibility(View.VISIBLE);
                String id = pref.getString("driverId", "");

                final Bean b = (Bean) getContext().getApplicationContext();


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Allapi cr = retrofit.create(Allapi.class);
                Call<StartRideBean> callStart = cr.rideStart(id, notiId, currentlat, currentLng);

                callStart.enqueue(new Callback<StartRideBean>() {
                    @Override
                    public void onResponse(Call<StartRideBean> call, Response<StartRideBean> response) {

                        if (Objects.equals(response.body().getStatus(), 1)) {
                            bar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            duration.setVisibility(View.GONE);
                            nav.setVisibility(View.VISIBLE);


                            map.clear();
                            if (pickUpLat.length() > 0 && pickUpLng.length() > 0) {

                                Log.d("log ke under", "yahi hai");

                                try {
                                    new DirectionFinder(BookRideFragment.this, pickUpLat, pickUpLng, dropLat, dropLng).execute();
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                            }

                        } else {
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            bar.setVisibility(View.GONE);
                        }


                    }

                    @Override
                    public void onFailure(Call<StartRideBean> call, Throwable t) {
                        bar.setVisibility(View.GONE);

                    }
                });


            }
        });


        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                timer.cancel();
                call.cancel();

                String id = pref.getString("driverId", "");

                bar.setVisibility(View.VISIBLE);

                final Bean b = (Bean) getContext().getApplicationContext();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Allapi cr = retrofit.create(Allapi.class);
                Call<acceptDenyBean> callacc = cr.accept(id, notiId, "1");
                Log.d("acceptId", id);


                callacc.enqueue(new Callback<acceptDenyBean>() {
                    @Override
                    public void onResponse(Call<acceptDenyBean> call, Response<acceptDenyBean> response) {

                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        Log.d("accpted", response.body().getMessage());

                        noti.setVisibility(View.GONE);
                        duty.setVisibility(View.GONE);
                        start.setVisibility(View.VISIBLE);
                        mylocationButton.setVisibility(View.VISIBLE);
                        billcard.setVisibility(View.GONE);


                        bar.setVisibility(View.GONE);


                        statusForRide();


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
                Call<acceptDenyBean> callden = cr.accept(id, notiId, "2");

                Log.d("notidriverId", id);
                Log.d("notiId", notiId);

                callden.enqueue(new Callback<acceptDenyBean>() {
                    @Override
                    public void onResponse(Call<acceptDenyBean> call, Response<acceptDenyBean> response) {

                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        Log.d("skjfjfgj", response.body().getMessage());


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

                        map = googleMap;

                        map.getUiSettings().setAllGesturesEnabled(true);

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

                                originMarkers.add(map.addMarker(new MarkerOptions()
                                        .position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lon)))
                                        .title("" + subLocality + ", " + state + ", " + country + "")
                                        .icon(bitmapDescriptorFromVector(getContext(), R.drawable.pin))));


                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Log.d("lat", lat);
                        Log.d("lon", lon);

                        map.setMyLocationEnabled(false);
                        CameraPosition cameraPosition = new CameraPosition.Builder().target(myLocation).zoom(15.0f).build();
                        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                        map.moveCamera(cameraUpdate);


                    }

                }

            });


        }


        return view;
    }


    public static Bitmap getViewBitmap(View view) {
//Get the dimensions of the view so we can re-layout the view at its current size
//and create a bitmap of the same size
        int width = view.getWidth();
        int height = view.getHeight();

        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);

//Cause the view to re-layout
        view.measure(measuredWidth, measuredHeight);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

//Create a bitmap backed Canvas to draw the view into
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);

//Now that the view is laid out and we have a canvas, ask the view to draw itself into the canvas
        view.draw(c);

        return b;
    }


    private BitmapDescriptor bitmapDescriptorFromVector(Context context, @DrawableRes int vectorDrawableResourceId) {
        Drawable background = ContextCompat.getDrawable(context, R.drawable.pin);
        background.setBounds(0, 0, background.getIntrinsicWidth(), background.getIntrinsicHeight());
//Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorDrawableResourceId);
//vectorDrawable.setBounds(40, 20, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(background.getIntrinsicWidth(), background.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        background.draw(canvas);
//vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


    private void sendRequest() {

        Log.d("send request", "hit");

        if (currentLng.length() > 0 && currentLng.length() > 0) {

            Log.d("log ke under", "yahi hai");

            try {
                new DirectionFinder(this, currentlat, currentLng, pickUpLat, pickUpLng).execute();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }


    }


    Call<notificationBean> call;

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

                    call = cr.notify(id, lat, lon);
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


                                                    DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                                                            .cacheOnDisk(true).resetViewBeforeLoading(true).build();

                                                    ImageLoader loader = ImageLoader.getInstance();

                                                    loader.displayImage(response.body().getData().get(0).getPicture(), user, options);

                                                    Log.d("nameiudhdu", response.body().getData().get(0).getUserName());

                                                    notiId = response.body().getData().get(0).getBookingId();

                                                    Log.d("NotificationId", notiId);

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
        currentlat = String.valueOf(l[0]);
        currentLng = String.valueOf(l[1]);
        String id = pref.getString("driverId", "");


        final Bean b = (Bean) getContext().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Allapi cr = retrofit.create(Allapi.class);

        Call<rideStatusBean> callRide = cr.rideStatus(id, notiId, currentlat, currentLng);
        callRide.enqueue(new Callback<rideStatusBean>() {
            @Override
            public void onResponse(Call<rideStatusBean> call, Response<rideStatusBean> response) {

                if (Objects.equals(response.body().getStatus(), 1)) {
                    pickUpLat = response.body().getData().getPickUpLatitude();
                    pickUpLng = response.body().getData().getPickUpLongitude();
                    dropLat = response.body().getData().getDropLatitude();
                    dropLng = response.body().getData().getDropLongitude();
                    Log.d("pickup wale Lat", response.body().getData().getPickUpLatitude());
                    Log.d("pickup wale lng", response.body().getData().getPickUpLongitude());
                    Log.d("drop wale lat", response.body().getData().getDropLatitude());
                    Log.d("drop wale lng", response.body().getData().getDropLongitude());
                    Log.d("dfsdsfgsgsfsgsdgd", response.body().getData().getUserName());

                   /* origin = new LatLng(Double.parseDouble(pickLat), Double.parseDouble(pickLon));
                    destination = new LatLng(Double.parseDouble(dropLat), Double.parseDouble(dropLon));*/

                    sendRequest();

                }

                builder = new LatLngBounds.Builder();
            }

            @Override
            public void onFailure(Call<rideStatusBean> call, Throwable t) {


                Log.d("dfsgsgsdgsdgsgsdgssssd", t.toString());

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
        // bar.setVisibility(View.VISIBLE);


       /* final Bean b = (Bean) getContext().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Allapi cr = retrofit.create(Allapi.class);
        Call<BookingStatusBean> call = cr.getBooking(b.driverId);
        call.enqueue(new Callback<BookingStatusBean>() {
            @Override
            public void onResponse(Call<BookingStatusBean> call, Response<BookingStatusBean> response) {

              String status = response.body().getData().getStatusCode();
              switch (status)
              {
                  case "1" : {
                      noti.setVisibility(View.GONE);
                      duty.setVisibility(View.GONE);
                      start.setVisibility(View.VISIBLE);
                      mylocationButton.setVisibility(View.VISIBLE);
                      billcard.setVisibility(View.GONE);

                      break;
                  }

                  case "2" : {

                      duration.setVisibility(View.GONE);
                      nav.setVisibility(View.VISIBLE);

                      break;
                  }

                  case "3" : {


                      bar.setVisibility(View.GONE);
                      duty.setVisibility(View.VISIBLE);
                      start.setVisibility(View.GONE);
                      billcard.setVisibility(View.VISIBLE);
                      mylocationButton.setVisibility(View.GONE);
                  }
              }

            }

            @Override
            public void onFailure(Call<BookingStatusBean> call, Throwable t) {

            }
        });*/


        currentOperator();


    }

    Call<StatusBean> call2;

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.offLine: {
                offI.setBackgroundResource(R.drawable.backcar);
                onI.setBackgroundResource(R.drawable.back_circle);
                //homeI.setBackgroundResource(R.drawable.back_circle);


                try {
                    timer.cancel();
                    noti.setVisibility(View.GONE);
                    call.cancel();
                } catch (Exception e) {
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
                Call<StatusBean> call = cr.status(id, "0");
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
                // homeI.setBackgroundResource(R.drawable.back_circle);


                String id2 = pref.getString("driverId", "");

                bar.setVisibility(View.VISIBLE);
                final Bean b2 = (Bean) getContext().getApplicationContext();


                Retrofit retrofit2 = new Retrofit.Builder()
                        .baseUrl(b2.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Allapi cr2 = retrofit2.create(Allapi.class);
                call2 = cr2.status(id2, "1");
                Log.d("driverkiId", id2);
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

/*
            case R.id.homeLine: {

                offI.setBackgroundResource(R.drawable.back_circle);
                onI.setBackgroundResource(R.drawable.back_circle);
                homeI.setBackgroundResource(R.drawable.backcar);


                try {
                    timer.cancel();
                    noti.setVisibility(View.GONE);
                    call2.cancel();
                } catch (Exception e) {
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
*/

        }


    }


    @Override
    public void onDirectionFinderStart() {

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline : polylinePaths) {
                polyline.remove();
            }
        }

    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {

        Log.d("success method", "sucssdced");

        // progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        map.clear();
        builder = new LatLngBounds.Builder();

        for (Route route : routes) {
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 16));
            time.setText(route.duration.text);
            value.setText(route.distance.text);

            Log.d("duration", route.duration.text);
            Log.d("distance", route.distance.text);

            Marker marker1 = map.addMarker(new MarkerOptions()
                    .icon(bitmapDescriptorFromVector(getContext(), R.drawable.pin))
                    .title(route.startAddress)
                    .position(route.startLocation));

            originMarkers.add(marker1);
            marker1.showInfoWindow();
            builder.include(marker1.getPosition());

            Marker marker2 = map.addMarker(new MarkerOptions()
                    // .icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green))
                    .title(route.endAddress)
                    .position(route.endLocation));
            destinationMarkers.add(marker2);
            marker2.showInfoWindow();

            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.BLACK).
                    width(10);

            for (int i = 0; i < route.points.size(); i++) {
                polylineOptions.add(route.points.get(i));
                builder.include(route.points.get(i));
            }

            polylinePaths.add(map.addPolyline(polylineOptions));
        }

        bounds = builder.build();
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 0);


        map.animateCamera(cu);


    }

    public void currentOperator() {

        if (cd.isConnectingToInternet()) {

            bar.setVisibility(View.VISIBLE);
            final Bean b = (Bean) getContext().getApplicationContext();


            Retrofit retrofit2 = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Allapi cr = retrofit2.create(Allapi.class);
            Call<CurrentBillBean> call = cr.current(b.driverId);
            call.enqueue(new Callback<CurrentBillBean>() {
                @Override
                public void onResponse(Call<CurrentBillBean> call, Response<CurrentBillBean> response) {

                    if (Objects.equals(response.body().getStatus(), "1")) {

                        bar.setVisibility(View.GONE);
                        incentBooking.setText(response.body().getData().getIncentiveBooking());
                        opBill.setText(response.body().getData().getOperatorBill());
                        totalIncent.setText(response.body().getData().getTotalIncentive());
                        lastUpdate.setText(response.body().getData().getLastUpdateTime());

                        if (Objects.equals(response.body().getData().getDutyStatusCode(), "1")) {

                            offI.setBackgroundResource(R.drawable.back_circle);
                            onI.setBackgroundResource(R.drawable.backcar);
                            doSomethingRepeatedly();

                        } else {

                            offI.setBackgroundResource(R.drawable.backcar);
                            onI.setBackgroundResource(R.drawable.back_circle);

                        }


                    } else {
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        bar.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onFailure(Call<CurrentBillBean> call, Throwable t) {
                    bar.setVisibility(View.GONE);

                }
            });


        } else {
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        map.setPadding(20, 400, 20, 560);

    }
}
