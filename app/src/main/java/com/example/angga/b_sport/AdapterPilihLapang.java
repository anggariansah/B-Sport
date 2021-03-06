package com.example.angga.b_sport;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by anggariansah on 08/04/2018.
 */

public class AdapterPilihLapang extends ArrayAdapter<ItemPilihLapang> {
    private Activity activity;
    private List<ItemPilihLapang> itembaru;
    private ItemPilihLapang semuaobj;
    private int row;
    Context ctx;

    public AdapterPilihLapang(Activity act, int resource, List<ItemPilihLapang> arraylist) {
        super(act, resource, arraylist);
        this.activity = act;
        this.row = resource;
        this.itembaru = arraylist;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
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

        holder.nomer = (TextView) view.findViewById(R.id.nomer);
        holder.desk = (TextView) view.findViewById(R.id.desk);
        holder.gambar = (ImageView) view.findViewById(R.id.gambar);

        holder.nomer.setText("Lapangan "+semuaobj.getNomer().toString());
        holder.desk.setText(semuaobj.getDesk().toString());


        Picasso
                .with(activity)
                .load(semuaobj.getGambar().toString())
                .fit()
                .into(holder.gambar);

        return view;
    }

    public class ViewHolder{
        public TextView nomer;
        public TextView desk;
        public ImageView gambar;
    }
}
