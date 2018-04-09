package com.example.angga.b_sport;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.angga.b_sport.fragments.FragmentSingle;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailPlace extends AppCompatActivity {

    Button book_now;
    Toolbar toolbar;
    ImageView gambar;
    TextView nama,desk,alamat,jam;

    String id,nam,gam,des,alam,ja;

    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_place);

        data = getIntent().getExtras().getString("id");

        //toolbar
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.tb);
        setSupportActionBar(toolbar);

        //back_button
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        gambar = (ImageView)findViewById(R.id.img);
        nama = (TextView) findViewById(R.id.nama);
        desk = (TextView) findViewById(R.id.desk);
        alamat = (TextView) findViewById(R.id.alamat);
        jam = (TextView) findViewById(R.id.jam);

        if(JsonUtils.isNetworkAvailable(DetailPlace.this)){
            new Tampil().execute("https://anggariansah.000webhostapp.com/DetailTempat.php?id="+data);
        }else{
            Toast.makeText(DetailPlace.this,"No Network Connection!!!",Toast.LENGTH_SHORT).show();
        }

        book_now = (Button) findViewById(R.id.book_now);
        book_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(DetailPlace.this, PilihLapang.class);
                pindah.putExtra("id",data);
                startActivity(pindah);
            }
        });

    }

    public class Tampil extends AsyncTask<String, Void, String> {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(DetailPlace.this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            return JsonUtils.getJSONString(params[0]);
        }

        @Override
        protected void onPostExecute(String hasil) {
            super.onPostExecute(hasil);

            if (null != pDialog && pDialog.isShowing()) {
                pDialog.dismiss();
            }

            if (null == hasil || hasil.length() == 0) {
                Toast.makeText(DetailPlace.this, "Tidak Ada data!!!", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    JSONObject JsonUtama = new JSONObject(hasil);
                    JSONArray jsonArray = JsonUtama.getJSONArray("data");
                    JSONObject JsonObj = null;
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JsonObj = jsonArray.getJSONObject(i);

                        nam = JsonObj.getString("nama");
                        des = JsonObj.getString("desk");
                        alam = JsonObj.getString("alamat");
                        ja = JsonObj.getString("harga");
                        gam = JsonObj.getString("gambar");

                        nama.setText(nam);
                        desk.setText(des);
                        alamat.setText(alam);
                        jam.setText("Rp."+ja);

                        Picasso
                                .with(DetailPlace.this)
                                .load(gam)
                                .fit()
                                .into(gambar);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
