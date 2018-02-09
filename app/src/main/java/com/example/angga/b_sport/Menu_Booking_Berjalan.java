package com.example.angga.b_sport;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Menu_Booking_Berjalan extends Fragment {

    Button timer;

    public Menu_Booking_Berjalan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.menu__booking__berjalan, container, false);

        //Intent ke Timer
        timer = (Button) rootView.findViewById(R.id.timer);
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent time = new Intent(getActivity(), WaktuMain.class);
                startActivity(time);
            }
        });

        return rootView;
    }
}
