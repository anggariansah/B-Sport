package com.example.angga.b_sport;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Pemesanan_10 extends AppCompatActivity {

    Toolbar toolbar;
    Button bayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pemesanan_10);

        //toolbar
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.tb_pemesanan);
        setSupportActionBar(toolbar);

        //back_button
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        //pindah ke halaman booking
        pay();
    }

    public void pay(){
        bayar = (Button) findViewById(R.id.button_lanjutkan);
        bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Pemesanan_10.this)
                        .setTitle("Lanjutkan?")
                        .setMessage("Lanjutkan Ke Tahap Pembayaran?")
                        .setCancelable(true)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                new AlertDialog.Builder(Pemesanan_10.this)
                                        .setTitle("Segera!")
                                        .setMessage("Harap Segera Lakukan Pembayaran!")
                                        .setCancelable(true)
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent bayar = new Intent(Pemesanan_10.this, Menu_Booking_11.class);
                                                startActivity(bayar);

                                            }
                                        })
                                        .show();
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
    }
}
