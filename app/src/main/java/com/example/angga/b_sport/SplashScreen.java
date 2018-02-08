package com.example.angga.b_sport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        splashScreen();
    }
    //untuk animasi splash screen

    public void splashScreen(){
        logo = (ImageView) findViewById(R.id.splash_screen);
        AlphaAnimation animation1 = new AlphaAnimation(0.0f, 1.0f);
        animation1.setDuration(4000);
        animation1.setStartOffset(1000);

        logo.startAnimation(animation1);

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashScreen.this, LoginMenu.class);
                    startActivity(intent);
                }
            }
        };
        timer.start();
    }
}
