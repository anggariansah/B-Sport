package com.example.angga.b_sport;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Menu_Booking_11 extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu__booking_11);

        //oncreate
        viewPager=(ViewPager)findViewById(R.id.vp_booking);
        tabLayout=(TabLayout)findViewById(R.id.tab_booking);
        Menu_Booking_11.MyAdapter adapter = new Menu_Booking_11.MyAdapter(getSupportFragmentManager());
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
                f = new Menu_Booking_Berjalan();
            }
            if(position==1){
                f = new Menu_Booking_Selesai();
            }
            return f;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position){
            String name = null;
            if (position==0){
                name = "Berjalan";
            }
            if (position==1){
                name = "Selesai";
            }
            return name;
        }
    }
}
