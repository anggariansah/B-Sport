package com.example.angga.b_sport;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PilihLapang extends AppCompatActivity {

    Toolbar toolbar;

    GridView listData;
    List<ItemPilihLapang> arrayItembaru;
    AdapterPilihLapang objAdapter;
    private ItemPilihLapang semuaItemobj;
    ArrayList<String> allid, allnomer, allldesk, allgambar;
    String[] arrayid, arraynomer, arraydesk, arraygambar;
    ProgressBar progress;

    String data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_lapang);


         data = getIntent().getExtras().getString("id");

        //toolbar
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.tb);
        setSupportActionBar(toolbar);

        //back_button
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);



        progress = (ProgressBar)findViewById(R.id.progressBar);

        listData = (GridView)findViewById(R.id.gridLapang);
        arrayItembaru = new ArrayList<ItemPilihLapang>();

        allid = new ArrayList<String>();
        allnomer = new ArrayList<String>();
        allldesk = new ArrayList<String>();
        allgambar = new ArrayList<String>();

        arrayid = new String[allid.size()];
        arraynomer = new String[allnomer.size()];
        arraydesk = new String[allldesk.size()];
        arraygambar = new String[allgambar.size()];


        if(JsonUtils.isNetworkAvailable(PilihLapang.this)){
            new Tampil().execute("https://anggariansah.000webhostapp.com/KelolaLapangan.php?id="+data);
        }else{
            new AlertDialog.Builder(PilihLapang.this)
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
                final String i = semuaItemobj.getId();


                Intent a = new Intent(PilihLapang.this,PilihBooking.class);
                a.putExtra("id",i);
                startActivity(a);



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
                new AlertDialog.Builder(PilihLapang.this)
                        .setTitle("Kosong")
                        .setMessage("Lapangan Belum Tersedia!")
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

                        ItemPilihLapang data = new ItemPilihLapang();
                        data.setId(JsonObj.getString("id"));
                        data.setNomer(JsonObj.getString("nomer"));
                        data.setDesk(JsonObj.getString("desk"));
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

                    allnomer.add(semuaItemobj.getNomer());
                    arraynomer = allnomer.toArray(arraynomer);

                    allldesk.add(semuaItemobj.getDesk());
                    arraydesk = allldesk.toArray(arraydesk);

                    allgambar.add(semuaItemobj.getGambar());
                    arraygambar = allgambar.toArray(arraygambar);

                }

                setAllAdapter();

            }
        }
    }

    public void setAllAdapter(){
        objAdapter = new AdapterPilihLapang(PilihLapang.this,R.layout.item_pilih_lapang,arrayItembaru);
        listData.setAdapter(objAdapter);
    }
}
