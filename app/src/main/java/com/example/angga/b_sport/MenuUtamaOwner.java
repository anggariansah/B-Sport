package com.example.angga.b_sport;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MenuUtamaOwner extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar tb;
    ActionBarDrawerToggle ab;
    DrawerLayout dl;
    NavigationView nv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama_owner);

        //Membuat toolbar Manual

        tb = (Toolbar) findViewById(R.id.tb);
        setSupportActionBar(tb);

        //Mendefinisikan Navigation Drawer

        dl = (DrawerLayout) findViewById(R.id.drawer_layout);
        ab = new ActionBarDrawerToggle(this,dl,tb,R.string.open, R.string.close);
        dl.addDrawerListener(ab);
        ab.syncState();
        nv = (NavigationView)findViewById(R.id.navigation);
        nv.setNavigationItemSelectedListener(this);


    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.beranda:
                Toast.makeText(getApplicationContext(),"Test",Toast.LENGTH_SHORT).show();
                break;
            case R.id.kelola:
                Intent kelola = new Intent(MenuUtamaOwner.this, Kelola_Lapangan_Owner_16.class);
                startActivity(kelola);
                Toast.makeText(getApplicationContext(),"Harap Tunggu",Toast.LENGTH_SHORT).show();
                break;
            case R.id.notif:
                Intent notifikasi = new Intent(MenuUtamaOwner.this, Notifikasi_Owner_19.class );
                startActivity(notifikasi);
                Toast.makeText(getApplicationContext(),"Harap Tunggu",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tambah:
                Toast.makeText(getApplicationContext(),"Test",Toast.LENGTH_SHORT).show();
                break;
            case R.id.topup:
                Toast.makeText(getApplicationContext(),"Test",Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(getApplicationContext(),"Test",Toast.LENGTH_SHORT).show();
                break;
            case R.id.keluar:
                Intent keluar = new Intent(MenuUtamaOwner.this, LoginMenu.class);
                startActivity(keluar);
                Toast.makeText(getApplicationContext(),"Anda Akan Keluar",Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }
}
