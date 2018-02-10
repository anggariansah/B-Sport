package com.example.angga.b_sport;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class TambahTempat extends AppCompatActivity {
    Toolbar tb;
    Button bah;
    int vis = 1;

    private ImageButton mSelectImage;
    private static final int GALLERY_REQUEST = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_tempat);

        //Membuat toolbar Manual

        tb = (Toolbar) findViewById(R.id.tb);
        setSupportActionBar(tb);
        //back_button
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        mSelectImage = (ImageButton)findViewById(R.id.iton);

        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });



        bah = (Button) findViewById(R.id.bah);
        bah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(TambahTempat.this)
                        .setTitle("Tersedia")
                        .setMessage("Bokingan Anda Tersedia!")
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent pindah = new Intent(TambahTempat.this, MenuUtamaOwner.class );
                                pindah.putExtra("view",vis);
                                startActivity(pindah);
                            }
                        }).show();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){

            Uri imageUri = data.getData();
            mSelectImage.setImageURI(imageUri);

        }
    }
}
