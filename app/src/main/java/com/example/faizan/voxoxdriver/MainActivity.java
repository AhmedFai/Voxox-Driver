package com.example.faizan.voxoxdriver;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    AHBottomNavigation bottom;
    Toolbar toolbar;
    DrawerLayout drawer;
    TextView refer, training, money, news, care,manage,logout;
    SharedPreferences pref;

    SharedPreferences.Editor edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        refer = (TextView)findViewById(R.id.refer);
        training = (TextView)findViewById(R.id.training);
        money = (TextView)findViewById(R.id.money);
        news = (TextView)findViewById(R.id.news);
        care = (TextView)findViewById(R.id.care);
        manage = (TextView)findViewById(R.id.manageProfile);
        logout = (TextView)findViewById(R.id.logout);
        pref = getApplication().getSharedPreferences("pref", MODE_PRIVATE);
        edit = pref.edit();


        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FragmentManager fm = getSupportFragmentManager();

                FragmentTransaction fp = fm.beginTransaction();
                while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStackImmediate();
                }
                ProfileFragment fragment = new ProfileFragment();
                fp.replace(R.id.replace, fragment);
                fp.addToBackStack(null);
                fp.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                fp.commit();
                // toolbar.setTitle("Profile");
                // toolbar.setTitleTextColor(Color.BLACK);

                drawer.closeDrawer(GravityCompat.START);


            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edit.remove("phone");
                edit.remove("password");
                edit.remove("driverId");
                edit.apply();

                Intent i = new Intent(MainActivity.this, loginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });



        bottom = (AHBottomNavigation)findViewById(R.id.bottom_navigation);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
       // getSupportActionBar().setLogo(R.mipmap.cab_logo);
       // getSupportActionBar().setDisplayUseLogoEnabled(false);

        drawer = (DrawerLayout)findViewById(R.id.drawer);


        refer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStackImmediate();
                }
                Refer fragment = new Refer();
                ft.replace(R.id.replace, fragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();
                // toolbar.setTitle("Profile");
                // toolbar.setTitleTextColor(Color.BLACK);

                drawer.closeDrawer(GravityCompat.START);

            }
        });

        training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStackImmediate();
                }
                Training fragment = new Training();
                ft.replace(R.id.replace, fragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();
                // toolbar.setTitle("Transaction History");
                // toolbar.setTitleTextColor(Color.BLACK);

                drawer.closeDrawer(GravityCompat.START);

            }
        });

        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStackImmediate();
                }
                VoxoxMoney fragment = new VoxoxMoney();
                ft.replace(R.id.replace, fragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();
                // toolbar.setTitle("The Concept");
                //toolbar.setTitleTextColor(Color.BLACK);

                drawer.closeDrawer(GravityCompat.START);

            }
        });

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStackImmediate();
                }
                PartnerNews fragment = new PartnerNews();
                ft.replace(R.id.replace, fragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();
                //toolbar.setTitle("Notification");
                //toolbar.setTitleTextColor(Color.BLACK);

                drawer.closeDrawer(GravityCompat.START);

            }
        });

        care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStackImmediate();
                }
                PartnerCare fragment = new PartnerCare();
                ft.replace(R.id.replace, fragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();
                // toolbar.setTitle("Rate The App");
                //toolbar.setTitleTextColor(Color.BLACK);

                drawer.closeDrawer(GravityCompat.START);

            }
        });




        AHBottomNavigationItem item1 =
                new AHBottomNavigationItem("Duty", R.drawable.deliverysvg);

        AHBottomNavigationItem item2 =
                new AHBottomNavigationItem("Account", R.drawable.calculatorsvg);

        AHBottomNavigationItem item3 =
                new AHBottomNavigationItem("Incentives", R.drawable.coinssvg);

        AHBottomNavigationItem item4 =
                new AHBottomNavigationItem("Performance", R.drawable.medalsvg);

        AHBottomNavigationItem item5 =
                new AHBottomNavigationItem("More", R.drawable.moresvg);

        bottom.addItem(item1);
        bottom.addItem(item2);
        bottom.addItem(item3);
        bottom.addItem(item4);
        bottom.addItem(item5);

        bottom.setBehaviorTranslationEnabled(false);

        bottom.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        bottom.setDefaultBackgroundColor(Color.parseColor("#222222"));
        bottom.setAccentColor(Color.parseColor("#ffcd2e"));
        bottom.setInactiveColor(Color.parseColor("#ffffff"));
        bottom.setCurrentItem(0);


        bottom.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                switch (position) {
                    case 0:



                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                        while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                            getSupportFragmentManager().popBackStackImmediate();
                        }

                        BookRideFragment frag1 = new BookRideFragment();
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
                        //ft2.addToBackStack(null);
                        ft2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                        ft2.commit();

                        return true;
                    case 2:




                        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();

                        while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                            getSupportFragmentManager().popBackStackImmediate();
                        }

                        Incentives frag2 = new Incentives();
                        ft1.replace(R.id.replace, frag2);
                       // ft1.addToBackStack(null);
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
                        //ft3.addToBackStack(null);
                        ft3.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                        ft3.commit();

                        return true;
                    case 4:


                        if (drawer.isDrawerOpen(GravityCompat.START))
                        {
                            drawer.closeDrawer(GravityCompat.START);
                        }
                        else
                        {
                            drawer.openDrawer(GravityCompat.START);
                        }


                        /*FragmentTransaction ft5 = getSupportFragmentManager().beginTransaction();

                        while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                            getSupportFragmentManager().popBackStackImmediate();
                        }

                        NotificationFragment frag5 = new NotificationFragment();
                        ft5.replace(R.id.replace, frag5);
                        ft5.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                        ft5.commit();*/

                        return false;
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



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu , menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_notification)
        {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStackImmediate();
            }
            NotificationFragment fragment = new NotificationFragment();
            ft.replace(R.id.replace, fragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            ft.commit();

        }


        return super.onOptionsItemSelected(item);
    }

}
