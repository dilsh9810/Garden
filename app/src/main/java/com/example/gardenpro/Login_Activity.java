package com.example.gardenpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login_Activity extends AppCompatActivity {

    //Variable Decleration

    TextView signuptext;
    EditText username, password;
    Button Login;


     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

         username = findViewById(R.id.usernameedittxt);
         password = findViewById(R.id.passwordedittxt);
         Login = findViewById(R.id.btnlogin);
         signuptext = findViewById(R.id.txtsignup);

         signuptext.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 Intent i1 = new Intent(Login_Activity.this,Registration_Activity.class);
                 startActivity(i1);
             }
         });


     }




    //Click Function Implementation

    public void Login(View view){

         if(username.getText().length() == 0 && password.getText().length() == 0)
         {
             username.setError("Please enter username");
             password.setError("Please enter password");

         }
         else
         {
                Intent i = new Intent(Login_Activity.this,System_Dashboard.class);
                startActivity(i);

         }


     }







}
