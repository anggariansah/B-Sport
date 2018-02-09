package com.example.angga.b_sport;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class PilihBooking extends AppCompatActivity {

    Button lanjutkan;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_booking);

        //toolbar
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar_pilih_book);
        setSupportActionBar(toolbar);

        //back_button
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        //intent ke menu pembayaran
        lanjut();
    }

    public void lanjut(){
        lanjutkan = (Button) findViewById(R.id.next);
        lanjutkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(PilihBooking.this, Pemesanan_10.class);
                startActivity(pindah);
            }
        });
    }
}
