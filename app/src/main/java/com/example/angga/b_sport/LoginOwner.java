package com.example.angga.b_sport;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginOwner extends AppCompatActivity {

    Button login;
    Toolbar toolbar;
    EditText email,pass;
    String emai,pas,Result;

    int vis = 0;

    SesionLogin session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_owner);


        login = (Button)findViewById(R.id.btn_ownerlgn);
        email = (EditText) findViewById(R.id.email_owner);
        pass = (EditText) findViewById(R.id.password_owner);

//        session = new SesionLogin(getApplicationContext());
//
//        Toast.makeText(getApplicationContext(),
//                "User Login Status: " + session.isUserLoggedIn(),
//                Toast.LENGTH_LONG).show();

        //toolbar
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar_owner);
        setSupportActionBar(toolbar);

        //back_button
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emai = email.getText().toString();
                pas = pass.getText().toString();

                new Masuk().execute();


            }
        });
    }

    public boolean validasiEmail(String email){
        Pattern pattern;
        Matcher matcher;
        final String email_pattern = "^[_A-Za-z0-9]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(email_pattern);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }


    //Simpan Data Method
    public class Masuk extends AsyncTask<Void, Void, Void> {
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = ProgressDialog.show(LoginOwner.this,"","Harap Tunggu Sedang Memverifikasi",true);

        }

        @Override
        protected Void doInBackground(Void... params) {

            Result = getSimpan(emai,pas);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            dialog.dismiss();
            resultSimpan(Result);
        }
    }

    public void resultSimpan(String HasilProses){
        if(HasilProses.trim().equalsIgnoreCase("OK")){
            Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_SHORT).show();

         //   session.createUserLoginSession("User Session ", emai);

            Intent a = new Intent(LoginOwner.this, MenuUtamaOwner.class);
            a.putExtra("view",vis);
            startActivity(a);
        }else if(HasilProses.trim().equalsIgnoreCase("Failed")){
            Toast.makeText(getApplicationContext(), "Username Atau Password Anda Salah!!", Toast.LENGTH_SHORT).show();
        }else{
            Log.d("HasilProses", HasilProses);
        }
    }

    public String getSimpan(String email, String pass){
        String result = "";

        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost("http://anggariansah.000webhostapp.com/LoginOwner.php");
        try{
            List<NameValuePair> nvp = new ArrayList<NameValuePair>(6);
            nvp.add(new BasicNameValuePair("email",email));
            nvp.add(new BasicNameValuePair("pass",pass));
            request.setEntity(new UrlEncodedFormEntity(nvp, HTTP.UTF_8));
            HttpResponse response = client.execute(request);
            result = request(response);

        }catch (Exception ex){
            result = "Unable To connect";
        }

        return result;
    }

    //Request Method

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
