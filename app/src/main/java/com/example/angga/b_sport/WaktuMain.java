package com.example.angga.b_sport;

import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class WaktuMain extends AppCompatActivity {

    TextView txDayRem, txwaktu;
    Button end;
    Toolbar toolbar;
    int jam = 12;
    int tanggal = 12;

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

       // txDayRem = (TextView)findViewById(R.id.daysRemain);
        txwaktu = (TextView)findViewById(R.id.waktu);
        end  = (Button)findViewById(R.id.selesai);

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(WaktuMain.this)
                        .setTitle("Selesai")
                        .setMessage("Apakah Anda Yakin Akan Mengakhiri Permainan?")
                        .setCancelable(true)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent pindah = new Intent(WaktuMain.this, MenuUtamaUser.class);
                                startActivity(pindah);
                            }
                        })
                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();
            }
        });

        startTimer();
    }

    private void startTimer() {
        int hmin = ((1000*60*60*24) - (1000*60*60*jam));
        long difference = getRemainDays() - hmin;


        new CountDownTimer(difference,1000)
        {
            public void onTick(long milisUntilFinished){

                int days = (int)(milisUntilFinished/(1000*60*60*24));
                int hours = (int)((milisUntilFinished/(1000*60*60))%24);
                int mins = (int)(milisUntilFinished/(1000*60)%60);
                int sec = (int)(milisUntilFinished/(1000)%60);


             //   txDayRem.setText(String.format("%d",days));
                txwaktu.setText(String.format("%02d:%02d:%02d",hours,mins,sec));
            }

            @Override
            public void onFinish() {
                notifikasi();
                new AlertDialog.Builder(WaktuMain.this)
                        .setTitle("Waktu Main")
                        .setMessage("Waktu Bermain Anda Habis!!")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent pindah = new Intent(WaktuMain.this, Menu_Booking_11.class);
                                startActivity(pindah);
                            }
                        }).show();
            }
        }.start();
    }

    private long getRemainDays() {
        Date currentDate = new Date();
        Date endDate;
        if(currentDate.getMonth() <= 1){
            endDate = new Date(currentDate.getYear(),1,tanggal);
        }else{
            endDate = new Date(currentDate.getYear()+1,1,tanggal);
        }
        return (endDate.getTime() - currentDate.getTime());
    }

    private void notifikasi(){
        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder)new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Waktu Habis")
                .setContentText("Waktu Main Anda Sudah Habis");

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, mBuilder.build());
    }
}
