package com.example.angga.b_sport;

import android.content.Intent;
import android.support.v7.app.ActionBar;
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
                Intent bayar = new Intent(Pemesanan_10.this, Menu_Booking_11.class);
                startActivity(bayar);
            }
        });
    }
}
