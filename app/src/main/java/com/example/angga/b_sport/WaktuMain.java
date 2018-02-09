package com.example.angga.b_sport;

import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.Date;

public class WaktuMain extends AppCompatActivity {

    TextView txDayRem, txwaktu;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waktu_main);

        //toolbar
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.tb_waktu_main);
        setSupportActionBar(toolbar);

        //back_button
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        txDayRem = (TextView)findViewById(R.id.daysRemain);
        txwaktu = (TextView)findViewById(R.id.waktu);

        startTimer();
    }

    private void startTimer() {
        long difference = getRemainDays();

        new CountDownTimer(difference,1000)
        {
            public void onTick(long milisUntilFinished){
                int hmin = (24 - 5);
                int days = (int)(milisUntilFinished/(1000*60*60*24));
                int hours = (int)((milisUntilFinished/(1000*60*60))%24) - hmin;
                int mins = (int)(milisUntilFinished/(1000*60)%60);
                int sec = (int)(milisUntilFinished/(1000)%60);


                txDayRem.setText(String.format("%d",days));
                txwaktu.setText(String.format("%02d:%02d:%02d",hours,mins,sec));
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    private long getRemainDays() {
        Date currentDate = new Date();
        Date endDate;
        if(currentDate.getMonth() <= 1){
            endDate = new Date(currentDate.getYear(),1,6);
        }else{
            endDate = new Date(currentDate.getYear()+1,1,6);
        }
        return endDate.getTime() - currentDate.getTime();
    }
}
