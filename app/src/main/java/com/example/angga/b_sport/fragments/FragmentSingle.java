package com.example.angga.b_sport.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.angga.b_sport.Pemesanan_10;
import com.example.angga.b_sport.R;
import com.example.angga.b_sport.adapter.SingleListAdapter;
import com.example.angga.b_sport.model.Single;
import com.example.angga.b_sport.widgets.GridMarginDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSingle extends Fragment implements SingleListAdapter.OnGridItemSelectedListener {

    private RecyclerView lvSingle;
    private GridLayoutManager gridLayoutManager;
    private SingleListAdapter singleListAdapter;

    private Context context;

    public static FragmentSingle newInstance() {
        return new FragmentSingle();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_single, container, false);

        lvSingle = (RecyclerView) rootView.findViewById(R.id.lv);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        singleListAdapter = new SingleListAdapter(this);
        gridLayoutManager = new GridLayoutManager(context, 2);

        lvSingle.setLayoutManager(gridLayoutManager);
        lvSingle.addItemDecoration(new GridMarginDecoration(context, 2, 2, 2, 2));
        lvSingle.setAdapter(singleListAdapter);

        loadData();
    }

    private void loadData() {
        List<Single> singleList = new ArrayList<>();
        Single single;

        int img[] = {R.drawable.lapangan_badminton, R.drawable.lapangan_badminton,
                R.drawable.lapangan_badminton, R.drawable.lapangan_badminton,
                R.drawable.lapangan_badminton};

        String title[] = {"Lap. Badminton 1", "Lap. Badminton 2",
                "Lap. Badminton 3", "Lap. Badminton 4",
                "Lap. Badminton 5"};

        for (int i = 0; i < img.length; i++) {
            single = new Single();

            single.setImg(img[i]);
            single.setTitle(title[i]);

            singleList.add(single);
        }

        singleListAdapter.addAll(singleList);
    }

    @Override
    public void onGridItemClick(View v, int position) {
        Intent a = new Intent(getActivity(), Pemesanan_10.class);
        startActivity(a);

    }
}
