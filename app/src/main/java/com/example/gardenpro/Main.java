package com.example.gardenpro;


import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main extends AppCompatActivity {


    //Soil moisture, temperature, humidity, water level, water temperature, motor status, fish status
    private TextView soilMoisture, temperature, humidity, waterLevel, waterTemperature, motorStatus, fishStatus;
    private Button switchBtn;
    private String status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iotdata);

        soilMoisture = findViewById(R.id.tv_soil_moisture);
        temperature = findViewById(R.id.tv_temperature);
        humidity = findViewById(R.id.tv_humidity);
        waterLevel = findViewById(R.id.tv_water_level);
        waterTemperature = findViewById(R.id.tv_water_temperature);
        motorStatus = findViewById(R.id.tv_motor_status);
        fishStatus = findViewById(R.id.tv_fish_status);


        // update the model class from firebase

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        reference.child("SensorRealtime").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                StatusModel statusModel = new StatusModel();

                statusModel.setHumidity(snapshot.child("Air_Humidity").getValue().toString());
                statusModel.setTemperature(snapshot.child("Air_Temperature").getValue().toString());
                statusModel.setFishStatus(snapshot.child("Motion_Detected").getValue().toString());
                statusModel.setSoilMoisture(snapshot.child("Soil_Moisture").getValue().toString());
                statusModel.setWaterLevel(snapshot.child("Water_Level").getValue().toString());
                statusModel.setWaterTemperature(snapshot.child("Tank_Temperature").getValue().toString());
                statusModel.setMotorStatus(snapshot.child("Water_Pump_Status").getValue().toString());

                // update the user interface
                soilMoisture.setText(statusModel.getSoilMoisture());
                temperature.setText(statusModel.getTemperature());
                humidity.setText(statusModel.getHumidity());
                waterLevel.setText(statusModel.getWaterLevel());
                if (statusModel.getFishStatus() == "1") {
                    fishStatus.setText("yes.");
                    fishStatus.setTextColor(-65536);
                } else if (statusModel.getFishStatus() == "0") {
                    fishStatus.setText("No.");
                    fishStatus.setTextColor(-16711936);

                }


                if (statusModel.getMotorStatus() == "1") {
                    motorStatus.setText("started");
                    motorStatus.setTextColor(-65536);
                } else if (statusModel.getMotorStatus() == "0") {
                    motorStatus.setText("Stopped");
                    motorStatus.setTextColor(-16711936);

                }

                waterTemperature.setText(statusModel.getWaterTemperature());
//

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Main.this, "error occurred"+error.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

    }//onCreate
}

