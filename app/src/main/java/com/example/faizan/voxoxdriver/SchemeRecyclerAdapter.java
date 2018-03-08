package com.example.faizan.voxoxdriver;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.faizan.voxoxdriver.IncentiveSchemePOJO.Datum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by faizan on 1/17/2018.
 */

public class SchemeRecyclerAdapter extends RecyclerView.Adapter<SchemeRecyclerAdapter.MyViewHolder> {

    List<Datum> scheme = new ArrayList<>();
    Context context;
    public SchemeRecyclerAdapter(Context context, List<Datum> scheme){
        this.context = context;
        this.scheme = scheme;
    }

    public void setGridData(List<Datum> scheme) {
        this.scheme = scheme;
        notifyDataSetChanged();
    }

    @Override
    public SchemeRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.scheme_item,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(SchemeRecyclerAdapter.MyViewHolder holder, int position) {

        final Datum item = scheme.get(position);

        holder.bill.setText(item.getNumRide());
        holder.price.setText(item.getIncentiveAmount() + " Total");

        if (position == scheme.size() - 1)
        {
            holder.line.setVisibility(View.GONE);
        }
        else
        {
            holder.line.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return scheme.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView bill, price, line;


        public MyViewHolder(View itemView) {
            super(itemView);

            bill = (TextView)itemView.findViewById(R.id.bill);
            price = (TextView)itemView.findViewById(R.id.amount);
            line = (TextView)itemView.findViewById(R.id.line);


        }
    }
}
