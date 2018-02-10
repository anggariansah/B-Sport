package com.example.angga.b_sport;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.DebugUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragMaps extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    CameraPosition cameraPosition;

    public FragMaps() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_maps, container, false);

        // Inflate the layout for this fragment

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return view;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        float zoom = 16.0f;
        LatLng GorSima = new LatLng(-6.373752, 106.818450);
        LatLng brumbun = new LatLng(-6.370685, 106.815823);
        LatLng gloria = new LatLng(-6.374536, 106.821045);
        mMap.addMarker(new MarkerOptions().position(GorSima).title("Gor Sima"));
        mMap.addMarker(new MarkerOptions().position(brumbun).title("Lapangan Futsal Brumbun"));
        mMap.addMarker(new MarkerOptions().position(gloria).title("Gor Gloria"));
        mMap.setOnInfoWindowCloseListener(new GoogleMap.OnInfoWindowCloseListener() {
            @Override
            public void onInfoWindowClose(Marker marker) {
                Intent pindah = new Intent(getActivity(), DetailPlace.class);
                startActivity(pindah);
            }
        });
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(GorSima,zoom));

    }


}
