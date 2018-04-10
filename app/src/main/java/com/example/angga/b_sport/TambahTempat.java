package com.example.angga.b_sport;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TambahTempat extends AppCompatActivity {
    Toolbar tb;
    Button bah;

    private ImageButton mSelectImage;
    private static final int GALLERY_REQUEST = 1;

    String Result,nam,des,harg,la,ja,ln,alama,notel;

    Bitmap bitmap;

    boolean check = true;

    ImageView imageView;

    EditText nama,desk,alamat,lat,lng,jam,harga,notelp;

    ProgressDialog progressDialog ;

    String GetImageNameEditText;

    String ServerUploadPath = "https://anggariansah.000webhostapp.com/TambahTempat.php" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_tempat);

        //Membuat toolbar Manual

        tb = (Toolbar) findViewById(R.id.tb_ubah_deskripsi);
        setSupportActionBar(tb);
        //back_button
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


        nama = (EditText)findViewById(R.id.name);
        desk = (EditText)findViewById(R.id.desk);
        alamat = (EditText)findViewById(R.id.lokasi);
        lat = (EditText)findViewById(R.id.lat);
        lng = (EditText)findViewById(R.id.lng);
        jam = (EditText)findViewById(R.id.jam);
        harga = (EditText)findViewById(R.id.Harga);
        notelp = (EditText)findViewById(R.id.nope);

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
                nam = nama.getText().toString();
                des = desk.getText().toString();
                alama = alamat.getText().toString();
                la = lat.getText().toString();
                ln = lng.getText().toString();
                ja = jam.getText().toString();
                harg = harga.getText().toString();
                notel = notelp.getText().toString();

                UploadImageServer();

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }

    @Override
    protected void onActivityResult(int RC, int RQC, Intent I) {

        super.onActivityResult(RC, RQC, I);

        if (RC == 1 && RQC == RESULT_OK && I != null && I.getData() != null) {

            Uri uri = I.getData();

            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                mSelectImage.setImageBitmap(bitmap);

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    //Simpan Data Method
    public void UploadImageServer(){

        ByteArrayOutputStream byteArrayOutputStreamObject ;
        byteArrayOutputStreamObject = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);
        byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();

        final String ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);

        class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {

            ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = ProgressDialog.show(TambahTempat.this, "", "Harap Tunggu Sedang Mengirim", true);
            }

            @Override
            protected String doInBackground(Void... params) {
                Result = getUpload(nam,des,alama,la,ln,ja,harg,notel,ConvertImage);
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                dialog.dismiss();
                resultUpload(Result);
            }
        }

        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();
        AsyncTaskUploadClassOBJ.execute();
    }

    public void resultUpload(String HasilProses){
        if(HasilProses.trim().equalsIgnoreCase("OK")){
            Toast.makeText(TambahTempat.this, "Data Berhasi Ditambahkan", Toast.LENGTH_SHORT).show();
            Intent a = new Intent(TambahTempat.this, MenuUtamaOwner.class);
            startActivity(a);
        }else if(HasilProses.trim().equalsIgnoreCase("Failed")){
            Toast.makeText(TambahTempat.this, "Data Gagal Or Failed", Toast.LENGTH_SHORT).show();
        }else{
            Log.d("HasilProses", HasilProses);
        }
    }

    public String getUpload(String nama, String desk, String alamat, String lat, String lng, String jam, String harga, String notelp, String gambar){
        String result = "";

        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost("https://anggariansah.000webhostapp.com/TambahTempat.php");
        try{
            List<NameValuePair> nvp = new ArrayList<NameValuePair>(6);
            nvp.add(new BasicNameValuePair("nama",nama));
            nvp.add(new BasicNameValuePair("desk",desk));
            nvp.add(new BasicNameValuePair("alamat",alamat));
            nvp.add(new BasicNameValuePair("lat",lat));
            nvp.add(new BasicNameValuePair("lng",lng));
            nvp.add(new BasicNameValuePair("jam",jam));
            nvp.add(new BasicNameValuePair("harga",harga));
            nvp.add(new BasicNameValuePair("notelp",notelp));
            nvp.add(new BasicNameValuePair("gambar",gambar));
            request.setEntity(new UrlEncodedFormEntity(nvp, HTTP.UTF_8));
            HttpResponse response = client.execute(request);
            result = request(response);

        }catch (Exception ex){
            result = "Unable To connect";
        }

        return result;
    }

    public static String request(HttpResponse response){
        String result = "";
        try{
            InputStream in = response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder str = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null){
                str.append(line + "\n");
            }
            in.close();
            result = str.toString();

        }catch (Exception ex){
            result = "Error";
        }

        return result;
    }
}
