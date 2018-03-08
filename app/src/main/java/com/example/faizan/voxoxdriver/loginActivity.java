package com.example.faizan.voxoxdriver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.faizan.voxoxdriver.loginPOJO.loginBean;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class loginActivity extends AppCompatActivity {

    EditText phone,password;
    SharedPreferences pref;
    SharedPreferences.Editor edit;
    ProgressBar progress;
    Button login;
    ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
        edit = pref.edit();
        progress = (ProgressBar) findViewById(R.id.progress);
        phone = (EditText)findViewById(R.id.phn);
        password = (EditText)findViewById(R.id.pasword);
        login = (Button)findViewById(R.id.loginbtn);
        cd = new ConnectionDetector(getApplication());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cd.isConnectingToInternet()){

                    final String p = phone.getText().toString();
                    final String ps = password.getText().toString();

                    if(!TextUtils.isEmpty(p)){

                        if (!TextUtils.isEmpty(ps)){

                            progress.setVisibility(View.VISIBLE);
                            final Bean b = (Bean) getApplicationContext();
                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(b.baseURL)
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();
                            Allapi cr = retrofit.create(Allapi.class);
                            Call<loginBean> call = cr.login(p,ps);
                            call.enqueue(new Callback<loginBean>() {
                                @Override
                                public void onResponse(Call<loginBean> call, Response<loginBean> response) {


                                    if (Objects.equals(response.body().getStatus(), "1")) {

                                        b.phone = "";
                                        b.password = "";
                                        b.driverId = response.body().getData().getDriverId();
                                        Log.d("driver Name", response.body().getData().getDrivername());


                                        edit.putString("driverId", response.body().getData().getDriverId());
                                        edit.apply();
                                        Intent i = new Intent(loginActivity.this, MainActivity.class);
                                        startActivity(i);
                                        finish();

                                        Toast.makeText(loginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();



                                    }else {

                                        Toast.makeText(loginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                        Log.d("sfdsd", response.body().getData().getCabName());
                                        progress.setVisibility(View.GONE);
                                    }
                                }

                                @Override
                                public void onFailure(Call<loginBean> call, Throwable t) {

                                    progress.setVisibility(View.GONE);
                                }
                            });



                        }else {
                            password.setError("Field is empty");
                            password.requestFocus();
                        }

                    }else {
                        phone.setError("Field is empty");
                        phone.requestFocus();
                    }

                }else {

                    Toast.makeText(getApplication(), "No Internet Connection", Toast.LENGTH_SHORT).show();

                }


            }
        });

    }
}
