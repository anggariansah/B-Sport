package com.example.angga.b_sport;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.angga.b_sport.R;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class RegUser extends Fragment {

    EditText nama,email,pass,nohp;
    Button reg,fb,gmail;
    String emai,nam,noh,pas,Result;


    public RegUser() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.reg_user, container, false);

        nama = (EditText) view.findViewById(R.id.nama);
        email = (EditText) view.findViewById(R.id.email);
        pass = (EditText) view.findViewById(R.id.password);
        nohp = (EditText) view.findViewById(R.id.nomorhp);

        reg = (Button) view.findViewById(R.id.register);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emai = email.getText().toString();
                pas = pass.getText().toString();
                noh = nohp.getText().toString();
                nam = nama.getText().toString();


                new simpan().execute();

            }
        });


        return view;
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
    public class simpan extends AsyncTask<Void, Void, Void> {
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = ProgressDialog.show(getActivity(),"","Harap Tunggu Sedang Mengirim",true);

        }

        @Override
        protected Void doInBackground(Void... params) {

            Result = getSimpan(nam,emai,noh,pas);
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
            Toast.makeText(getActivity(), "Pendaftaran Berhasil", Toast.LENGTH_SHORT).show();
            Intent a = new Intent(getActivity(), LoginUser.class);
            startActivity(a);
        }else if(HasilProses.trim().equalsIgnoreCase("Failed")){
            Toast.makeText(getActivity(), "Pendaftaran Gagal Coba Kembali", Toast.LENGTH_SHORT).show();
        }else{
            Log.d("HasilProses", HasilProses);
        }
    }

    public String getSimpan(String nama, String email, String nohp, String pass){
        String result = "";

        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost("http://anggariansah.000webhostapp.com//RegisterUser.php");
        try{
            List<NameValuePair> nvp = new ArrayList<NameValuePair>(6);
            nvp.add(new BasicNameValuePair("nama",nama));
            nvp.add(new BasicNameValuePair("email",email));
            nvp.add(new BasicNameValuePair("nohp",nohp));
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
