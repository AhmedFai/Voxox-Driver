package com.example.faizan.voxoxdriver;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by faizan on 1/17/2018.
 */

public class BankByDayAdapter extends RecyclerView.Adapter<BankByDayAdapter.MyViewHolder> {
    Context context;
    public BankByDayAdapter(Context context){
        this.context = context;
    }
    @Override
    public BankByDayAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bank_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BankByDayAdapter.MyViewHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, FinalBankTranfer.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
