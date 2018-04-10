package com.example.angga.b_sport;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginUser extends AppCompatActivity {

    Toolbar toolbar;
    Button login;
    EditText email, pass;
    String emai, pas, id;

    SesionLogin session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_user);

        login = (Button) findViewById(R.id.btn_login);
        email = (EditText) findViewById(R.id.email_user);
        pass = (EditText) findViewById(R.id.password_user);

        session = new SesionLogin(getApplicationContext());

        Toast.makeText(getApplicationContext(),
                "User Login Status: " + session.isUserLoggedIn(),
                Toast.LENGTH_LONG).show();

        //toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar_user);
        setSupportActionBar(toolbar);

        //back_button
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emai = email.getText().toString();
                pas = pass.getText().toString();

                if (JsonUtils.isNetworkAvailable(LoginUser.this)) {
                    new Tampil().execute("https://anggariansah.000webhostapp.com/LoginUser.php?email=" + email + "&&pass=" + pass);
                } else {
                    Toast.makeText(LoginUser.this, "No Network Connection!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public class Tampil extends AsyncTask<String, Void, String> {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(LoginUser.this);
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
                Toast.makeText(LoginUser.this, "Tidak Ada data!!!", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    JSONObject JsonUtama = new JSONObject(hasil);

                    JSONArray res = JsonUtama.getJSONArray("hasil");
                    JSONObject re = null;
                    re = res.getJSONObject(0);

                    final String result = re.getString("result");

                    if (result.equals("true")) {

                        JSONArray jsonArray = JsonUtama.getJSONArray("data");
                        JSONObject JsonObj = null;

                        JsonObj = jsonArray.getJSONObject(0);

                        final String id = JsonObj.getString("id");

                        new AlertDialog.Builder(LoginUser.this)
                                .setTitle("Succes")
                                .setMessage("Login Berhasil!")
                                .setCancelable(false)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent a = new Intent(LoginUser.this, MenuUtamaUser.class);
                                        a.putExtra("id", id);
                                        startActivity(a);
                                    }
                                }).show();
                    } else {
                        new AlertDialog.Builder(LoginUser.this)
                                .setTitle("Failed")
                                .setMessage("Username Atau Password Salah!")
                                .setCancelable(false)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

    }

}
