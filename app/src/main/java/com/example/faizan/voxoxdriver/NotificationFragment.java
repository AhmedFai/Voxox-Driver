package com.example.faizan.voxoxdriver;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class NotificationFragment extends Fragment {

    RecyclerView notificationRecycler;
    notificationCardAdapter adapter;
    GridLayoutManager manager;
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_notification,container,false);

        toolbar = ((MainActivity)getActivity()).findViewById(R.id.toolbar);

        notificationRecycler = (RecyclerView) view.findViewById(R.id.notificationRecycle);
        notificationRecycler = view.findViewById(R.id.notificationRecycle);
        manager = new GridLayoutManager(getContext(),1);
        adapter = new notificationCardAdapter(getContext());
        notificationRecycler.setLayoutManager(manager);
        notificationRecycler.setAdapter(adapter);

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
