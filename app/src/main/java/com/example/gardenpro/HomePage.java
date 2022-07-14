package com.example.gardenpro;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


            Timer timer = new Timer();

            timer.schedule(new TimerTask() {
                @Override
                public void run() {

                    Intent i = new Intent(HomePage.this,Login_Activity.class);
                    startActivity(i);
                    finish();

                }
            },5000);



        }

    }
