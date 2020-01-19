package com.example.teconecta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.util.concurrent.Delayed;

public class splash_activity extends AppCompatActivity {

    private final int DURACION_SPLASH = 4000;
    private MainController mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_activity);
        mc =  MainController.getInstance();
        mc.setActiveContex(this);
        mc.getData();
        //mc.filltest();




        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent(splash_activity.this, ActividadesList.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);
    }
}