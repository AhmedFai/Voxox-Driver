package com.example.faizan.voxoxdriver;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faizan.voxoxdriver.CurrentOperatorBillPOJO.CurrentBillBean;
import com.example.faizan.voxoxdriver.currentBalancePOJO.Day;
import com.example.faizan.voxoxdriver.currentBalancePOJO.cuyrrentBalanceBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class CurrentBalanceStatement extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView grid;
    GridLayoutManager manager;
    List<Day> list;
    BalanceADapter aDapter;
    ProgressBar progress;
    TextView current;
    ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.balance_stateent);
        toolbar = (Toolbar) findViewById(R.id.balanceToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Current Balance Statements");
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.arrow);
        toolbar.setTitleTextColor(Color.WHITE);
        cd = new ConnectionDetector(getApplication());

        progress = (ProgressBar)findViewById(R.id.progress);

        grid = (RecyclerView)findViewById(R.id.grid);
        manager = new GridLayoutManager(this , 1);
        list = new ArrayList<>();
        aDapter = new BalanceADapter(this , list);
        current = (TextView)findViewById(R.id.current);

        grid.setAdapter(aDapter);
        grid.setLayoutManager(manager);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        if (cd.isConnectingToInternet()){

            progress.setVisibility(View.VISIBLE);
            final Bean b = (Bean) getApplicationContext();


            Retrofit retrofit2 = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Allapi cr = retrofit2.create(Allapi.class);
            Call<cuyrrentBalanceBean> call = cr.currentBalamce(b.driverId);


            call.enqueue(new Callback<cuyrrentBalanceBean>() {
                @Override
                public void onResponse(Call<cuyrrentBalanceBean> call, Response<cuyrrentBalanceBean> response) {


                    current.setText(response.body().getData().getCurrentBalance());
                    aDapter.setGridData(response.body().getData().getDays());


                    progress.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<cuyrrentBalanceBean> call, Throwable t) {

                    progress.setVisibility(View.GONE);
                }
            });


        }else {

            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();

        }


    }



    public class BalanceADapter extends RecyclerView.Adapter<BalanceADapter.ViewHolder>
    {

        List<Day> list = new ArrayList<>();
        Context context;
        LayoutInflater inflater;

        public BalanceADapter(Context context , List<Day> list)
        {
            this.context = context;
            this.list = list;
        }


        public void setGridData(List<Day> list)
        {
            this.list = list;
            notifyDataSetChanged();
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.balance_list_nodel, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            Day item = list.get(position);

            holder.day.setText(item.getDay());

            holder.dayend.setText(item.getDayEndBalance());

            holder.data.removeAllViews();

            for (int i = 0 ; i < item.getDayData().size() ; i++)
            {

                View view = inflater.inflate(R.layout.activity_current_balance_statement , null);

                TextView crn = (TextView)view.findViewById(R.id.crn);
                TextView text = (TextView)view.findViewById(R.id.text);
                TextView sign = (TextView)view.findViewById(R.id.sign);
                TextView amount = (TextView)view.findViewById(R.id.amount);
                TextView date = (TextView)view.findViewById(R.id.date);
                TextView type = (TextView)view.findViewById(R.id.type);


                crn.setText("CRN - " + item.getDayData().get(i).getCrn());
                text.setText(item.getDayData().get(i).getText());
                sign.setText(item.getDayData().get(i).getSign());
                amount.setText(item.getDayData().get(i).getAmount());
                date.setText(item.getDayData().get(i).getDate());
                type.setText(item.getDayData().get(i).getCabType());

                holder.data.addView(view);

            }

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder
        {

            TextView day , dayend;
            LinearLayout data;



            public ViewHolder(View itemView) {
                super(itemView);
                day = (TextView)itemView.findViewById(R.id.day);
                dayend = (TextView)itemView.findViewById(R.id.dayend);
                data = (LinearLayout)itemView.findViewById(R.id.data);
            }
        }
    }



}
