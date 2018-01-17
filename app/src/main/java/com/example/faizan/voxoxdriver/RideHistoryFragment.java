package com.example.faizan.voxoxdriver;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class RideHistoryFragment extends Fragment {

    TabLayout tabLayout;
    public static ViewPager pager;
    PerformancePager adapter;

    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_ride_history,container,false);

        toolbar = ((MainActivity)getActivity()).findViewById(R.id.toolbar);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        pager = (ViewPager) view.findViewById(R.id.pager);
       // pager.setSwipeable(true);


        tabLayout.addTab(tabLayout.newTab().setText("Voxox Score"));
        tabLayout.addTab(tabLayout.newTab().setText("Achievements"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        adapter = new PerformancePager(getChildFragmentManager(), tabLayout.getTabCount());
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

        tabLayout.getTabAt(0).setText("Voxox Score");
        tabLayout.getTabAt(1).setText("Achievements");


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

    public class PerformancePager extends FragmentStatePagerAdapter {
        int tab;

        public PerformancePager(FragmentManager fm, int List) {
            super(fm);
            this.tab = List;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new VoxoxScoreFragment();
            } else if (position == 1) {
                return new AchievementsFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
