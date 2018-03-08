package com.example.faizan.voxoxdriver;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.faizan.voxoxdriver.IncentivesEarnedPojo.Datum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by faizan on 1/17/2018.
 */

public class IncentiveItemAdpater extends RecyclerView.Adapter<IncentiveItemAdpater.MyViewHolder> {

    List<Datum> earned = new ArrayList<>();
    Context context;
    public IncentiveItemAdpater(Context context, List<Datum> earned){
        this.context = context;
        this.earned = earned;

    }

    public void setGridData(List<Datum> earned) {
        this.earned = earned;
        notifyDataSetChanged();
    }

    @Override
    public IncentiveItemAdpater.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.incentive_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IncentiveItemAdpater.MyViewHolder holder, int position) {


        final Datum item = earned.get(position);

        holder.date.setText(item.getDay());
        holder.amount.setText(item.getAmount());

        if (position == earned.size() - 1)
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
        return earned.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date, amount,line;

        public MyViewHolder(View itemView) {
            super(itemView);
            date = (TextView)itemView.findViewById(R.id.date);
            amount = (TextView)itemView.findViewById(R.id.amount);
            line = (TextView)itemView.findViewById(R.id.line);
        }
    }
}
