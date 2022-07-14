package com.example.gardenpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class System_Dashboard extends AppCompatActivity {

    ImageView plant,soil,iot,fish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_dashboard);

        plant = findViewById(R.id.plant_img);
        soil = findViewById(R.id.soil_fert);
        iot = findViewById(R.id.iotm);
        fish = findViewById(R.id.fish);

        soil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i1 = new Intent(System_Dashboard.this,SoilDetection.class);
                startActivity(i1);

            }
        });

        iot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i2 = new Intent(System_Dashboard.this,Main.class);
                startActivity(i2);
            }
        });

        fish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i3 = new Intent(System_Dashboard.this,FishDetection.class);
                startActivity(i3);
            }
        });



        plant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(System_Dashboard.this,Plant_Suggestion.class);
                startActivity(i);
            }


        });



    }




}
