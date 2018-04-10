package com.example.angga.b_sport;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class Notifikasi_Owner_19 extends AppCompatActivity {

    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> nama,lapangan,tanggal,jam;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifikasi__owner_19);

        //toolbar
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.tb_notifikasi_owner);
        setSupportActionBar(toolbar);

        //back_button
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        nama = new ArrayList<>();
        lapangan = new ArrayList<>();
        tanggal = new ArrayList<>();
        jam = new ArrayList<>();
        initDataset();

        rvView = (RecyclerView) findViewById(R.id.rv_notif);
        rvView.setHasFixedSize(true);

        /**
         * Kita menggunakan LinearLayoutManager untuk list standar
         * yang hanya berisi daftar item
         * disusun dari atas ke bawah
         */
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

        adapter = new NotifikasiAdapter(nama,lapangan,tanggal,jam);
        rvView.setAdapter(adapter);

    }

    @Override
    public boolean onNavigateUp() {
        finish();
        return false;
    }

    private void initDataset(){

        /**
         * Tambahkan item ke dataset
         * dalam prakteknya bisa bermacam2
         * tidak hanya String seperti di kasus ini
         */
        nama.add("Angga");
        lapangan.add("Lapangan 1");
        tanggal.add("12 April 2018");
        jam.add("08.00 - 09.00");
    }
}
