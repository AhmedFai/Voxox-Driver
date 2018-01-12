package com.example.faizan.voxoxdriver;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

public class MainActivity extends AppCompatActivity {
    AHBottomNavigation bottom;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottom = (AHBottomNavigation)findViewById(R.id.bottom_navigation);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.cab_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        AHBottomNavigationItem item1 =
                new AHBottomNavigationItem("Profile", R.drawable.user_32_white);

        AHBottomNavigationItem item2 =
                new AHBottomNavigationItem("Support", R.drawable.message_32_white);

        AHBottomNavigationItem item3 =
                new AHBottomNavigationItem("Book Ride", R.drawable.location_32_white);

        AHBottomNavigationItem item4 =
                new AHBottomNavigationItem("Ride History", R.drawable.history_32_white);

        AHBottomNavigationItem item5 =
                new AHBottomNavigationItem("Notification", R.drawable.notification_32_white);

        bottom.addItem(item1);
        bottom.addItem(item2);
        bottom.addItem(item3);
        bottom.addItem(item4);
        bottom.addItem(item5);

        bottom.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        bottom.setDefaultBackgroundColor(Color.parseColor("#222222"));
        bottom.setAccentColor(Color.parseColor("#ffcd2e"));
        bottom.setInactiveColor(Color.parseColor("#ffffff"));
        bottom.setCurrentItem(2);


        bottom.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                switch (position) {
                    case 0:



                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                        while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                            getSupportFragmentManager().popBackStackImmediate();
                        }

                        ProfileFragment frag1 = new ProfileFragment();
                        ft.replace(R.id.replace, frag1);
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                        ft.commit();

                        return true;
                    case 1:



                        FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();

                        while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                            getSupportFragmentManager().popBackStackImmediate();
                        }

                        SupportFragment frag3 = new SupportFragment();
                        ft2.replace(R.id.replace, frag3);
                        ft2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                        ft2.commit();

                        return true;
                    case 2:




                        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();

                        while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                            getSupportFragmentManager().popBackStackImmediate();
                        }

                        BookRideFragment frag2 = new BookRideFragment();
                        ft1.replace(R.id.replace, frag2);
                        ft1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                        ft1.commit();


                        return true;
                    case 3:



                        FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();

                        while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                            getSupportFragmentManager().popBackStackImmediate();
                        }

                        RideHistoryFragment frag4 = new RideHistoryFragment();
                        ft3.replace(R.id.replace, frag4);
                        ft3.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                        ft3.commit();

                        return true;
                    case 4:



                        FragmentTransaction ft5 = getSupportFragmentManager().beginTransaction();

                        while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                            getSupportFragmentManager().popBackStackImmediate();
                        }

                        NotificationFragment frag5 = new NotificationFragment();
                        ft5.replace(R.id.replace, frag5);
                        ft5.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                        ft5.commit();

                        return true;
                }

                return false;
            }
        });
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft1 = fm.beginTransaction();
        BookRideFragment frag2 = new BookRideFragment();
        ft1.replace(R.id.replace, frag2);
        //ft.addToBackStack(null);
        ft1.commit();


    }
}
