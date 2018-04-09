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

public class Register extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        //toolbar
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //back_button
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        //oncreate
        viewPager=(ViewPager)findViewById(R.id.vp);
        tabLayout=(TabLayout)findViewById(R.id.tab);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        //oncreate end
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
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
                f = new RegUser();
            }
            if(position==1){
                f = new RegOwner();
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
                name = "User";
            }
            if (position==1){
                name = "Owner";
            }
            return name;
        }
    }

}
