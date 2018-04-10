package com.example.angga.b_sport;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Alvin Tandiardi on 05/02/2018.
 */

public class NotifikasiAdapter extends RecyclerView.Adapter<NotifikasiAdapter.ViewHolder> {

    private ArrayList<String> nam,lap,tang,ja;

    public NotifikasiAdapter(ArrayList<String> nama,ArrayList<String> lapang, ArrayList<String> tanggal, ArrayList<String> jam) {
        nam = nama;
        lap = lapang;
        tang = tanggal;
        ja = jam;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // di tutorial ini kita hanya menggunakan data String untuk tiap item
        public TextView nama_book;
        public TextView lapangan_book;
        public TextView tanggal_book;
        public TextView jam_book;


        public ViewHolder(View v) {
            super(v);
            nama_book = (TextView) v.findViewById(R.id.nama_notif);
            lapangan_book = (TextView) v.findViewById(R.id.lapangan_notif);
            tanggal_book = (TextView) v.findViewById(R.id.tanggal_notif);
            jam_book = (TextView) v.findViewById(R.id.jam_notif);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // membuat view baru
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.isi_notifikasi_owner_19, parent, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - mengambil elemen dari dataset (ArrayList) pada posisi tertentu
        // - mengeset isi view dengan elemen dari dataset tersebut
        holder.nama_book.setText(nam.get(position));
        holder.lapangan_book.setText(lap.get(position));
        holder.tanggal_book.setText(tang.get(position));
        holder.jam_book.setText(ja.get(position));
    }

    @Override
    public int getItemCount() {
        // menghitung ukuran dataset / jumlah data yang ditampilkan di RecyclerView
        return nam.size();
    }
}
