package com.example.angga.b_sport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginMenu extends AppCompatActivity {

    Button user,owner;
    TextView daftar_disini;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_menu);

        //intent ke register
        kedaftar();

        //intent ke login
        login();
    }

    //intent ke halaman register
    public void kedaftar(){
        daftar_disini = (TextView)findViewById(R.id.daftar_disini);
        daftar_disini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent daftar = new Intent (LoginMenu.this, Register.class);
                startActivity(daftar);
            }
        });
    }

    //intent ke halaman login user dan owner
    public void login(){
        user = (Button)findViewById(R.id.button_user);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user = new Intent(LoginMenu.this, LoginUser.class);
                startActivity(user);
            }
        });

        owner = (Button)findViewById(R.id.button_owner);
        owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent owner = new Intent(LoginMenu.this, LoginOwner.class);
                startActivity(owner);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
