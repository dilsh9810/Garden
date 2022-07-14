package com.example.gardenpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Crop_Instructions extends AppCompatActivity {

    TextView Crop, instructions, Fertilzer;
    String output;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_instructions);

        Crop = findViewById(R.id.TextCrop);
        instructions = findViewById(R.id.insttextid);
        Fertilzer = findViewById(R.id.txtfertilizer);
        imageView = findViewById(R.id.vegid);


        Intent i = getIntent();

        String output = i.getStringExtra("crop");

        Crop.setText(output);


        if(Crop.getText().equals("Mukunuwenna"))
        {
            instructions.setText("Water supply" +
                    "                Irrigation is done frequently to maintain the moisture at the top layer of the bed. Water lodging should be avoided. Weed Control" +
                    "                During the land preparation weeds should be controlled as much as possible. Hand weeding should be practiced throughout the cropping cycle Stem cuttings are planted in specified spacing or randomly. Single cutting is planted at one point. Raised beds for low lands (paddy fields) and sunken beds for highlands are more suitable" +
                    "Soil is ploughed and allowed weeds to decompose. Then the fields are filled with water and take a fine filth and levelled. Drains are prepared to facilitate the drainage");
            Fertilzer.setText("Organic,chemical fertilizers");

            //imageView.setImageResource(R.mipmap.mukunuwenna);

            imageView.setVisibility(View.VISIBLE);



        }

        else if(Crop.getText().equals("Capsicum"))
        {
            instructions.setText("Water supply" +
                    "                Irrigation to be practiced in 4-5 day intervals at early stages and 1 week interval at latter stages of the crop. This depends on the rainfall. Irrigation required at before and after fertilizer application, and during flowering and fruit development stages.Weed Control" +
                    "                Weeding is required at 2,4 and 8 weeks after planting fertilizer can be applied after weeding. Soils can be added to the plants after weeding and fertilizer application 3m x 90 cm and 15 cm height beds are prepared. Apply compost or cow dung 3-4 kg per square meter. Use burning, solar sterilization or agro chemicals to sterilize soils. Put seeds in rows 10-15 cm apart and less than to 1 cm depth. Apply a suitable mulch and provide irrigation. Seed germinate in 8-10 days. Seedlings are ready to plant in 21 days.");
            Fertilzer.setText("UREA, TSP, MOP");

            imageView.setImageResource(R.mipmap.capsicum_front);

        }

        //imageView.setImageResource(R.mipmap.capsicum_front);

        else if(Crop.getText().equals("Brinjals")){

            instructions.setText("Water supply" +
                    "                    Better to supply water for better growth, 2,4,7,9,12 days after planting , better to remove weeds 3m x 1m beds in better sunlight conditions are suitable. Beds should be sterilized. Add 1:1 ratio of surface soil and cow dung. Seeds should be placed in rows in 6 inches spacing.");

            Fertilzer.setText("UREA, TSP, MOP");


        }
    }



}