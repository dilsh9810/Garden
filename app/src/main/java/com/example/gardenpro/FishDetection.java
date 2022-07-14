package com.example.gardenpro;

import com.example.gardenpro.ml.M1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Timer;
import java.util.TimerTask;

public class FishDetection extends AppCompatActivity {

    TextView result, confidence;
    ImageView imageView;
    Button picture;
    int imageSize = 224;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_fish_detection);

        result = findViewById(R.id.result);
        confidence = findViewById(R.id.conftext);
        imageView = findViewById(R.id.img);
        picture = findViewById(R.id.btnpic);



        picture.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                // Launch camera if we have permission
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 1);
                } else {
                    //Request camera permission if we don't have it.
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                }
            }
        });
    }

// AUTO GENERATE
    public void classifyImage(Bitmap image){

        try {
                M1 model = M1.newInstance(getApplicationContext());

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);


            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());

            int[] intValues = new int[imageSize * imageSize];
            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());
            int pixel = 0;
            for (int i = 0; i < imageSize; i++) {
                for (int j = 0; j < imageSize; j++) {
                    int val = intValues[pixel++];
                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255.f));
                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255.f));
                    byteBuffer.putFloat((val >> 0xFF) * (1.f / 255.f));
                }
            }


            inputFeature0.loadBuffer(byteBuffer);

            // Runs model inference and gets result.
            M1.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();


            float[] confidences = outputFeature0.getFloatArray();
            int maxPos = 0;
            float maxConfidence = 0;

            for (int i = 0; i < confidences.length; i++) {
                if (confidences[i] > maxConfidence) {
                    maxConfidence = confidences[i];

                    maxPos = i;
                }
            }

            String[] classes = {"Fins Melt Disease", "Gold Fish", "Grow Tetra", "Gurami", "Injured", "Red Common", "Red Spot", "White Spot Disease"};


            if (maxConfidence >= 0.7){

                result.setText(classes[maxPos]);
                String a = classes[maxPos];

                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {

                        if(a== "Fins Melt Disease"){

                            Intent intent = new Intent(FishDetection.this,Disease_1.class);
                            startActivity(intent);
                            finish();

                        }
                        else if (a == "Injured"){

                            Intent intent = new Intent(FishDetection.this,Disease_2.class);
                            startActivity(intent);
                            finish();

                        }
                        else if (a == "Red Spot"){

                            Intent intent = new Intent(FishDetection.this,Disease_3.class);
                            startActivity(intent);
                            finish();

                        }

                        else if (a == "White Spot Disease"){

                            Intent intent = new Intent(FishDetection.this,Disease_4.class);
                            startActivity(intent);
                            finish();

                        }


                    }
                }, 5000);


            }

            else {
                result.setText("Out of range, Please try again");
            }






            String s = "";


            for(int i=0; i < classes.length; i++){
                s += String.format("%s: %.1f%%\n", classes[i], confidences[i] * 100);

            }

            confidence.setText(s);




            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            // TODO Handle the exception
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            int dimension = Math.min(image.getWidth(), image.getHeight());
            image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
            imageView.setImageBitmap(image);

            image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
            classifyImage(image);


        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}