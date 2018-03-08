package com.example.faizan.voxoxdriver;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.faizan.voxoxdriver.OperatorBillPOJO.BookingDatum;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by faizan on 1/16/2018.
 */

public class OperatorBillAdapter extends RecyclerView.Adapter<OperatorBillAdapter.MyViewHolder> {

    List<BookingDatum> operator = new ArrayList<>();
    Context context;

    public OperatorBillAdapter(Context context, List<BookingDatum> operator)
    {

        this.context = context;
        this.operator = operator;
    }

    @Override
    public OperatorBillAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.operator_bill_card , parent , false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OperatorBillAdapter.MyViewHolder holder, int position) {

        final BookingDatum item = operator.get(position);
        holder.crn.setText("CRN : " + item.getCrn());
        holder.type.setText(item.getCabType());
        holder.date.setText(item.getDateTime());
        holder.status.setText(item.getRideStatus());
        holder.cash.setText(item.getCashCollected());
        holder.money.setText(item.getVoxoxMoney());
        holder.pay.setText(item.getVoxoxToPay());
        holder.bill.setText(item.getOperatorBill());

        ImageLoader loader = ImageLoader.getInstance();
        loader.displayImage(item.getCabIcon(),holder.image);


        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,BookingDetails.class);
                i.putExtra("BillId", item.getCrn());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return operator.size();
    }

    public void setGridData(List<BookingDatum> operator) {
        this.operator = operator;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView view, crn, type, date, status, cash, money, pay, bill;
        ImageView image;
        public MyViewHolder(final View itemView) {
            super(itemView);

            view = (TextView)itemView.findViewById(R.id.viewDetail);
            crn = (TextView)itemView.findViewById(R.id.crn);
            type = (TextView)itemView.findViewById(R.id.type);
            date = (TextView)itemView.findViewById(R.id.date);
            status = (TextView)itemView.findViewById(R.id.status);
            cash = (TextView)itemView.findViewById(R.id.cash);
            money = (TextView)itemView.findViewById(R.id.money);
            pay = (TextView)itemView.findViewById(R.id.pay);
            bill = (TextView)itemView.findViewById(R.id.bill);
            image = (ImageView)itemView.findViewById(R.id.image);


        }
    }
}
