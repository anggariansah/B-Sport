package com.example.angga.b_sport;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

public class UbahDeskripsi extends AppCompatActivity {

    private ImageButton mSelectImage;
    private static final int GALLERY_REQUEST = 1;
    Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_deskripsi);

        tb = (Toolbar)findViewById(R.id.tb);
        setSupportActionBar(tb);

        mSelectImage = (ImageButton)findViewById(R.id.imageButton);

        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
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
