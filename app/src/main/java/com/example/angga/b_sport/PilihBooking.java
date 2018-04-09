package com.example.angga.b_sport;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;

import static com.example.angga.b_sport.R.id.month;
import static com.example.angga.b_sport.R.string.calendar;

public class PilihBooking extends AppCompatActivity {

    Button lanjutkan,ck;
    Toolbar toolbar;
    TextView n1,n2;
    CalendarDay cal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_booking);


        n1 = (TextView)findViewById(R.id.nama1);
        n2 = (TextView)findViewById(R.id.nama2);

        cal = CalendarDay.from(2018, 0, 17);



        MaterialCalendarView mcv = (MaterialCalendarView)findViewById(R.id.calendarView);
        mcv.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(1999, 1, 1))
                .setMaximumDate(CalendarDay.from(2050, 1, 1))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();


        mcv.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                if(cal.equals(date)){
                    n1.setVisibility(View.VISIBLE);
                    n2.setVisibility(View.VISIBLE);
                }else{
                    n1.setVisibility(View.GONE);
                    n2.setVisibility(View.GONE);
                }
            }
        });

        //toolbar
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar_pilih_book);
        setSupportActionBar(toolbar);

        //back_button
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        cek();

        //intent ke menu pembayaran
        lanjut();
    }

    public void cek(){
        ck = (Button) findViewById(R.id.cek);
        ck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(PilihBooking.this)
                        .setTitle("Tersedia")
                        .setMessage("Bokingan Anda Tersedia!")
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Whatever...
                            }
                        }).show();
            }
        });
    }

    public void lanjut(){
        lanjutkan = (Button) findViewById(R.id.next);
        lanjutkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(PilihBooking.this)
                        .setTitle("Apakah Anda Yakin?")
                        .setMessage("Lanjutkan Bookingan Anda!!")
                        .setCancelable(true)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent pindah = new Intent(PilihBooking.this, Pemesanan_10.class);
                                startActivity(pindah);
                            }
                        })
                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();

            }
        });
    }
}
