package com.example.faizan.voxoxdriver;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.faizan.voxoxdriver.EarningByDayPOJO.Datum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by faizan on 1/16/2018.
 */

public class EarningByDayAdapter extends RecyclerView.Adapter<EarningByDayAdapter.MyViewHolder> {

    List<Datum> earning = new ArrayList<>();
    Context context;
    public EarningByDayAdapter(Context context, List<Datum> earning){
        this.earning = earning;
        this.context = context;
    }

    public void setGridData(List<Datum> earning) {
        this.earning = earning;
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.earning, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final Datum item = earning.get(position);
        holder.date.setText(item.getDay());
        holder.amount.setText(item.getAmount());
        if (position == earning.size() - 1)
        {
            holder.hide.setVisibility(View.GONE);
        }
        else
        {
            holder.hide.setVisibility(View.VISIBLE);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,NetEarning.class);
                i.putExtra("dayId", item.getDayId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return earning.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView date, amount, hide;

        public MyViewHolder(View itemView) {
            super(itemView);
            date = (TextView)itemView.findViewById(R.id.date);
            amount = (TextView)itemView.findViewById(R.id.amount);
            hide = (TextView)itemView.findViewById(R.id.hide);
        }
    }
}
