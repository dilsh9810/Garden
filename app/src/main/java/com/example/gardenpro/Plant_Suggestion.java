package com.example.gardenpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Plant_Suggestion extends AppCompatActivity {


    EditText texthumid,texttemp,textph,textspace,textsoilmoisture,textwlevel;
    Button btnpredict;
    TextView output;
    String url = "https://sampletest0.herokuapp.com/predict";
    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase firebaseDatabase;

        texthumid = findViewById(R.id.edithumidity);
        texttemp = findViewById(R.id.tempedittxt);
        textsoilmoisture = findViewById(R.id.soilmoisturetxt);
        textph = findViewById(R.id.edittxtph);
        textspace = findViewById(R.id.editarealevel);
        textwlevel = findViewById(R.id.editwlevel);
        btnpredict = findViewById(R.id.btnpredict);
        output = findViewById(R.id.txtresult);

        DatabaseReference db = FirebaseDatabase.getInstance("https://gardenpro-84659-default-rtdb.firebaseio.com/").getReference("SensorRealtime");

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String humid = dataSnapshot.child("Air_Humidity").getValue().toString();
                String temp = dataSnapshot.child("Air_Temperature").getValue().toString();
                String soilmoisture = dataSnapshot.child("Soil_Moisture").getValue().toString();
                String waterlevel = dataSnapshot.child("Water_Level").getValue().toString();

                texthumid.setText(humid);
                texttemp.setText(temp);
                textsoilmoisture.setText(soilmoisture);
                textwlevel.setText(waterlevel);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        //Get Instance of Firebase db

        btnpredict.setOnClickListener(new View.OnClickListener() {
    
            @Override
            public void onClick(View view) {



                //Hit the Volley API
                StringRequest stringRequest = new StringRequest (Request.Method.POST,url,
                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                try {

                                    JSONObject jsonObject = new JSONObject(response);
                                    String data = jsonObject.getString("suitable crop is");

                                    Intent i = new Intent(Plant_Suggestion.this,Crop_Instructions.class);

                                    i.putExtra("crop",data);

                                    startActivity(i);



                                } catch (JSONException jsonException) {
                                    jsonException.printStackTrace();
                                }

                            }

                        },


                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError onErrorResponse) {
                        Toast.makeText(Plant_Suggestion.this, onErrorResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<>();
                        params.put("Humidity",texthumid.getText().toString());
                        params.put("Temperature",texttemp.getText().toString());
                        params.put("SoilMoisture",textsoilmoisture.getText().toString());
                        params.put("PH", textph.getText().toString());
                        params.put("Waterlevel",textwlevel.getText().toString());
                        params.put("Space",textspace.getText().toString());
                        return params;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(Plant_Suggestion.this);
                queue.add(stringRequest);

            }

        });


    }



}








