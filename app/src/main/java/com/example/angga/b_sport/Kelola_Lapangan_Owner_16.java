package com.example.angga.b_sport;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class Kelola_Lapangan_Owner_16 extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kelola__lapangan__owner_16);

        //toolbar
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.tb_booking_list);
        setSupportActionBar(toolbar);

        //back_button
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        //oncreate
        viewPager=(ViewPager)findViewById(R.id.vp_booking);
        tabLayout=(TabLayout)findViewById(R.id.tab_booking);
        Kelola_Lapangan_Owner_16.MyAdapter adapter = new Kelola_Lapangan_Owner_16.MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        //oncreate end
    }

    //Mulai Adapter
    class MyAdapter extends FragmentStatePagerAdapter {
        public MyAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position){
            Fragment f = null;
            if (position ==0){
                f = new Lapangan1();
            }
            if(position==1){
                f = new Lapangan2();
            }
            if(position==2){
                f = new Lapangan3();
            }
            if(position==3){
                f = new Lapangan4();
            }
            if(position==4){
                f = new Lapangan5();
            }
            return f;
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position){
            String name = null;
            if (position==0){
                name = "Lap. 1";
            }
            if (position==1){
                name = "Lap. 2";
            }
            if(position==2){
                name = "Lap. 3";
            }
            if(position==3){
                name = "Lap. 4";
            }
            if(position==4){
                name = "Lap. 5";
            }
            return name;
        }
    }
}
