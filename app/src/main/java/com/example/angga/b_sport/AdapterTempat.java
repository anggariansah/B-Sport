package com.example.angga.b_sport;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by anggariansah on 06/04/2018.
 */

public class AdapterTempat extends ArrayAdapter<ItemTempat> {
    private Activity activity;
    private List<ItemTempat> itembaru;
    private ItemTempat semuaobj;
    private int row;
    Context ctx;

    public AdapterTempat(Activity act, int resource, List<ItemTempat> arraylist) {
        super(act, resource, arraylist);
        this.activity = act;
        this.row = resource;
        this.itembaru = arraylist;
    }


    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(row, null);

            holder = new ViewHolder();
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        if((itembaru == null) || ((position + 1) > itembaru.size()))
            return view;

        semuaobj = itembaru.get(position);

        holder.nama = (TextView) view.findViewById(R.id.nama);
        holder.lokasi = (TextView) view.findViewById(R.id.lokasi);
        holder.harga = (TextView) view.findViewById(R.id.harga);
        holder.gambar = (ImageView) view.findViewById(R.id.gambar);
        holder.edit = (Button) view.findViewById(R.id.ubah);
        holder.kelola = (Button) view.findViewById(R.id.kelola);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GridView) parent).performItemClick(v, position, 0);
            }
        });

        holder.kelola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GridView) parent).performItemClick(v, position, 0);
            }
        });

        holder.nama.setText(semuaobj.getNama().toString());
        holder.lokasi.setText(semuaobj.getLokasi().toString());
        holder.harga.setText(semuaobj.getHarga().toString());

        Picasso
                .with(activity)
                .load(semuaobj.getGambar().toString())
                .fit()
                .into(holder.gambar);

        return view;
    }

    public class ViewHolder{
        public TextView nama;
        public TextView lokasi;
        public TextView harga;
        public ImageView gambar;
        public Button edit,kelola;
    }
}
