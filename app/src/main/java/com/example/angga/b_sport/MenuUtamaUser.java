package com.example.angga.b_sport;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MenuUtamaUser extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar tb;
    ActionBarDrawerToggle ab;
    DrawerLayout dl;
    NavigationView nv;
    ViewPager viewPager;
    TabLayout tl;
    ImageView userimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama_user);

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

        //Mendefiniskan Tab Layout
        viewPager = (ViewPager) findViewById(R.id.vp);
        tl = (TabLayout) findViewById(R.id.tl);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tl.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.beranda:
                Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
                break;
            case R.id.search:
                Intent a = new Intent(MenuUtamaUser.this, CariTempat.class);
                startActivity(a);
                Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
                break;
            case R.id.booking:
                Intent book = new Intent(MenuUtamaUser.this, Menu_Booking_11.class);
                startActivity(book);

                break;
            case R.id.about:
                Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
                break;
            case R.id.keluar:
                Intent keluar = new Intent(MenuUtamaUser.this, LoginMenu.class);
                startActivity(keluar);
                finish();
                break;
        }

        return true;
    }


    //Membuat TabLayout

    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = null;
            if (position == 0) {
                f = new FragMaps();
            }


            return f;
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String name = null;
            if (position == 0) {
                name = "Maps";
            }

            return name;
        }
    }


    @Override
    public void onBackPressed() {
        finishAffinity();
    }

}
