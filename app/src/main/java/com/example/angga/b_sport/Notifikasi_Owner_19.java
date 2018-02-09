package com.example.angga.b_sport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class Notifikasi_Owner_19 extends AppCompatActivity {

    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> dataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifikasi__owner_19);

        dataSet = new ArrayList<>();
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

        adapter = new NotifikasiAdapter(dataSet);
        rvView.setAdapter(adapter);

    }

    private void initDataset(){

        /**
         * Tambahkan item ke dataset
         * dalam prakteknya bisa bermacam2
         * tidak hanya String seperti di kasus ini
         */
        dataSet.add("Harun booking Lapangan 1 untuk jam 11.00 - 12.00");
        dataSet.add("Alvin booking Lapangan 5 untuk jam 13.00 - 14.00");
        dataSet.add("Angga booking Lapangan 3 untuk jam 10.00 - 11.00");
        dataSet.add("Budi booking Lapangan 4 untuk jam 10.00 - 11.00");
        dataSet.add("Dono booking Lapangan 1 untuk jam 15.00 - 16.00");
        dataSet.add("Susi booking Lapangan 3 untuk jam 12.00 - 13.00");
    }
}
