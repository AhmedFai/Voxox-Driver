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
import android.widget.TableLayout;


public class Incentives extends Fragment {

    Toolbar toolbar;
    TabLayout tabLayout;
    public static ViewPager pager;
    IncentivePager adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_incentives,container,false);
        toolbar = ((MainActivity)getActivity()).findViewById(R.id.toolbar);
        tabLayout = (TabLayout) view.findViewById(R.id.incentiveTabLayout);
        pager = (ViewPager) view.findViewById(R.id.incentivePager);



        tabLayout.addTab(tabLayout.newTab().setText("Scheme"));
        tabLayout.addTab(tabLayout.newTab().setText("Incentive Earned"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        adapter = new IncentivePager(getChildFragmentManager(), tabLayout.getTabCount());
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

        tabLayout.getTabAt(0).setText("Scheme");
        tabLayout.getTabAt(1).setText("Incentive Earned");

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


    public class IncentivePager extends FragmentStatePagerAdapter {
        int tab;

        public IncentivePager(FragmentManager fm, int List) {
            super(fm);
            this.tab = List;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new Scheme();
            } else if (position == 1) {
                return new IncentiveEarned();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }



}
