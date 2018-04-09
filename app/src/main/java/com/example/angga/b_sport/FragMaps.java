package com.example.angga.b_sport;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.DebugUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragMaps extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    MapFragment mapFragment;
    MarkerOptions markerOptions = new MarkerOptions();
    CameraPosition cameraPosition;
    LatLng center, latLng;
    String title;

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

        if(JsonUtils.isNetworkAvailable(getActivity())){
            new Tampil().execute("https://anggariansah.000webhostapp.com/TampilTempat.php");
        }else{
            Toast.makeText(getActivity(),"No Network Connection!!!",Toast.LENGTH_SHORT).show();
        }

        return view;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        float zoom = 16.0f;
        LatLng GorSima = new LatLng(-6.373752, 106.818450);
//        LatLng brumbun = new LatLng(-6.370685, 106.815823);
//        LatLng gloria = new LatLng(-6.374536, 106.821045);
//        mMap.addMarker(new MarkerOptions().position(GorSima).title("Gor Sima"));
//        mMap.addMarker(new MarkerOptions().position(brumbun).title("Lapangan Futsal Brumbun"));
//        mMap.addMarker(new MarkerOptions().position(gloria).title("Gor Gloria"));
//        mMap.setOnInfoWindowCloseListener(new GoogleMap.OnInfoWindowCloseListener() {
//            @Override
//            public void onInfoWindowClose(Marker marker) {
//                Intent pindah = new Intent(getActivity(), DetailPlace.class);
//                startActivity(pindah);
//            }
//        });
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(GorSima,zoom));

    }


    public class Tampil extends AsyncTask<String, Void, String> {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(getActivity());
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
                Toast.makeText(getActivity(), "Tidak Ada data!!!", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    JSONObject JsonUtama = new JSONObject(hasil);
                    JSONArray jsonArray = JsonUtama.getJSONArray("data");
                    JSONObject JsonObj = null;
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JsonObj = jsonArray.getJSONObject(i);

                        title = JsonObj.getString("nama");
                        latLng = new LatLng(Double.parseDouble(JsonObj.getString("lat")), Double.parseDouble(JsonObj.getString("lng")));

                        addMarker(latLng, title);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

        private void addMarker(LatLng latlng, final String title) {
            markerOptions.position(latlng);
            markerOptions.title(title);
            mMap.addMarker(markerOptions);

            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    Toast.makeText(getActivity(), marker.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }


}
