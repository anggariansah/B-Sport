package com.example.angga.b_sport;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CariTempat extends AppCompatActivity {

    GridView listData;
    List<ItemCariTempat> arrayItembaru;
    AdapterCariTempat objAdapter;
    private ItemCariTempat semuaItemobj;
    ArrayList<String> allid, allnama, alllokasi, allharga ,allgambar;
    String[] arrayid, arraynama, arraylokasi, arrayharga, arraygambar;
    Toolbar tb;
    FloatingActionButton tambah;
    ProgressBar progress;

    SearchView search;
    int textlength = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_tempat);

        tb = (Toolbar) findViewById(R.id.tb);
        setSupportActionBar(tb);

        progress = (ProgressBar)findViewById(R.id.progressBar);

        listData = (GridView)findViewById(R.id.gridUser);
        arrayItembaru = new ArrayList<ItemCariTempat>();

        allid = new ArrayList<String>();
        allnama = new ArrayList<String>();
        alllokasi = new ArrayList<String>();
        allharga = new ArrayList<String>();
        allgambar = new ArrayList<String>();

        arrayid = new String[allid.size()];
        arraylokasi = new String[alllokasi.size()];
        arraynama = new String[allnama.size()];
        arrayharga = new String[allharga.size()];
        arraygambar = new String[allgambar.size()];


        if(JsonUtils.isNetworkAvailable(CariTempat.this)){
            new Tampil().execute("https://anggariansah.000webhostapp.com/TampilKelolaTempat.php");
        }else{
            new AlertDialog.Builder(CariTempat.this)
                    .setTitle("Failed")
                    .setMessage("Please Check Connection!")
                    .setCancelable(false)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Whatever...
                        }
                    }).show();
        }


        listData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                semuaItemobj = arrayItembaru.get(position);

                String ide = semuaItemobj.getId();
                Intent a = new Intent(CariTempat.this, DetailPlace.class);
                a.putExtra("id",ide);
                startActivity(a);

            }
        });

        search = (SearchView) findViewById(R.id.search);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String text) {
                // TODO Auto-generated method stub
                textlength = text.length();
                arrayItembaru.clear();

                for (int i = 0; i < arraynama.length; i++) {
                    if (textlength <= arraynama[i].length()) {
                        if (text.toString().equalsIgnoreCase((String) arraynama[i].subSequence(0, textlength))) {
                            ItemCariTempat data = new ItemCariTempat();

                            data.setId(arrayid[i]);
                            data.setLokasi(arraylokasi[i]);
                            data.setNama(arraynama[i]);
                            data.setHarga(arrayharga[i]);
                            data.setGambar(arraygambar[i]);

                            arrayItembaru.add(data);
                        }
                    }
                }

                setAllAdapter();

                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                // TODO Auto-generated method stub
                return false;
            }
        });
    }


    public class Tampil extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            listData.setVisibility(View.GONE);
        }

        @Override
        protected String doInBackground(String... params) {
            return JsonUtils.getJSONString(params[0]);
        }

        @Override
        protected void onPostExecute(String hasil) {
            super.onPostExecute(hasil);

            if (null != progress) {
                listData.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
            }

            if(null == hasil || hasil.length() == 0){
                new AlertDialog.Builder(CariTempat.this)
                        .setTitle("Failed")
                        .setMessage("Please Check Connection!")
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Whatever...
                            }
                        }).show();
                progress.setVisibility(View.GONE);
            }else{
                try {
                    JSONObject JsonUtama =  new JSONObject(hasil);
                    JSONArray jsonArray = JsonUtama.getJSONArray("data");
                    JSONObject JsonObj = null;
                    for(int i = 0;i < jsonArray.length();i++){

                        JsonObj = jsonArray.getJSONObject(i);

                        ItemCariTempat data = new ItemCariTempat();
                        data.setId(JsonObj.getString("id"));
                        data.setLokasi(JsonObj.getString("lokasi"));
                        data.setNama(JsonObj.getString("nama"));
                        data.setHarga(JsonObj.getString("harga"));
                        data.setGambar(JsonObj.getString("gambar"));
                        arrayItembaru.add(data);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for(int j=0;j<arrayItembaru.size();j++){

                    semuaItemobj = arrayItembaru.get(j);

                    allid.add(semuaItemobj.getId());
                    arrayid = allid.toArray(arrayid);

                    alllokasi.add(semuaItemobj.getLokasi());
                    arraylokasi = alllokasi.toArray(arraylokasi);

                    allnama.add(semuaItemobj.getNama());
                    arraynama = allnama.toArray(arraynama);

                    allharga.add(semuaItemobj.getHarga());
                    arrayharga = allharga.toArray(arrayharga);

                    allgambar.add(semuaItemobj.getGambar());
                    arraygambar = allgambar.toArray(arraygambar);

                }

                setAllAdapter();

            }
        }
    }

    public void setAllAdapter(){
        objAdapter = new AdapterCariTempat(CariTempat.this,R.layout.item_cari_tempat,arrayItembaru);
        listData.setAdapter(objAdapter);
    }
}
