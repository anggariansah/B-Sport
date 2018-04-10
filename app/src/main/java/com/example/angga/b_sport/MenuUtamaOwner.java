package com.example.angga.b_sport;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MenuUtamaOwner extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar tb;
    ActionBarDrawerToggle ab;
    DrawerLayout dl;
    NavigationView nv;
    Button ubah;
    RelativeLayout des;
    ImageView ownerimage;

    GridView listData;
    List<ItemTempat> arrayItembaru;
    AdapterTempat objAdapter;
    private ItemTempat semuaItemobj;
    ArrayList<String> allid, allnama, alllokasi, allharga ,allgambar;
    String[] arrayid, arraynama, arraylokasi, arrayharga, arraygambar;
    Toolbar toolbar;
    FloatingActionButton tambah;
    ProgressBar progress;
    EditText by;

    String data;
    TextView name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama_owner);

        data = getIntent().getExtras().getString("id");


        //Membuat toolbar Manual

        tb = (Toolbar) findViewById(R.id.tb);
        setSupportActionBar(tb);

        //Mendefinisikan Navigation Drawer

        dl = (DrawerLayout) findViewById(R.id.drawer_layout);
        ab = new ActionBarDrawerToggle(this, dl, tb, R.string.open, R.string.close);
        dl.addDrawerListener(ab);
        ab.syncState();
        nv = (NavigationView) findViewById(R.id.navigation);
        nv.setNavigationItemSelectedListener(this);


        View header = nv.getHeaderView(0);

        name = (TextView)header.findViewById(R.id.nama);

        if(JsonUtils.isNetworkAvailable(MenuUtamaOwner.this)){
            new TampilNama().execute("https://anggariansah.000webhostapp.com/TampilNamaOwner.php?id="+data);
        }else{
            Toast.makeText(MenuUtamaOwner.this,"No Network Connection!!!",Toast.LENGTH_SHORT).show();
        }


        tambah = (FloatingActionButton)findViewById(R.id.tambah);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(MenuUtamaOwner.this, TambahTempat.class);
                startActivity(pindah);
            }
        });

        progress = (ProgressBar)findViewById(R.id.progressBar);

        listData = (GridView)findViewById(R.id.gridOwner);
        arrayItembaru = new ArrayList<ItemTempat>();

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


        if(JsonUtils.isNetworkAvailable(MenuUtamaOwner.this)){
            new Tampil().execute("https://anggariansah.000webhostapp.com/TampilKelolaTempat.php?id="+data);
        }else{
            new AlertDialog.Builder(MenuUtamaOwner.this)
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
                long viewId = view.getId();
                semuaItemobj = arrayItembaru.get(position);
                final String i = semuaItemobj.getId();


                if (viewId == R.id.kelola) {
                    Intent a = new Intent(MenuUtamaOwner.this,Kelola_Lapangan_Owner_16.class);
                    a.putExtra("id",i);
                    startActivity(a);
                } else if (viewId == R.id.ubah) {
                    Intent b = new Intent(MenuUtamaOwner.this,UbahDeskripsi.class);
                    b.putExtra("id",i);
                    startActivity(b);
                }


            }
        });




    }



    public class TampilNama extends AsyncTask<String, Void, String> {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(MenuUtamaOwner.this);
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
                Toast.makeText(MenuUtamaOwner.this, "Tidak Ada data!!!", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    JSONObject JsonUtama = new JSONObject(hasil);
                    JSONArray jsonArray = JsonUtama.getJSONArray("data");
                    JSONObject JsonObj = null;
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JsonObj = jsonArray.getJSONObject(i);

                        name.setText(JsonObj.getString("nama"));


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

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

                progress.setVisibility(View.GONE);
            }else{
                try {
                    JSONObject JsonUtama =  new JSONObject(hasil);
                    JSONArray jsonArray = JsonUtama.getJSONArray("data");
                    JSONObject JsonObj = null;
                    for(int i = 0;i < jsonArray.length();i++){

                        JsonObj = jsonArray.getJSONObject(i);

                        ItemTempat data = new ItemTempat();
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
        objAdapter = new AdapterTempat(MenuUtamaOwner.this,R.layout.item_tempat,arrayItembaru);
        listData.setAdapter(objAdapter);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.beranda:
                Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
                break;
            case R.id.notif:
                Intent notifikasi = new Intent(MenuUtamaOwner.this, Notifikasi_Owner_19.class);
                startActivity(notifikasi);
                Toast.makeText(getApplicationContext(), "Harap Tunggu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tambah:
                Intent pindah = new Intent(MenuUtamaOwner.this, TambahTempat.class);
                startActivity(pindah);
                break;
            case R.id.about:
                Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
                break;
            case R.id.keluar:
                Intent keluar = new Intent(MenuUtamaOwner.this, LoginMenu.class);
                startActivity(keluar);
                finish();
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
