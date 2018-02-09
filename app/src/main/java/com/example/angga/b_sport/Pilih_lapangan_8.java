package com.example.angga.b_sport;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.angga.b_sport.fragments.FragmentSingle;


public class Pilih_lapangan_8 extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pilih_lapangan_8);

        //toolbar
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.tb_pemesanan);
        setSupportActionBar(toolbar);

        //back_button
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        Fragment fragment = getSupportFragmentManager().findFragmentById(android.R.id.content);
        if(fragment == null){
            fragment = FragmentSingle.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(android.R.id.content, fragment, "")
                    .commit();

        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .attach(fragment)
                    .commit();
        }

    }
}
