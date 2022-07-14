package com.example.gardenpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//import org.w3c.dom.Text;

public class    Registration_Activity extends AppCompatActivity {

    EditText username, email, password, passwordn;
    Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_);

        //match id of the UI components to the variable

        username = findViewById(R.id.txtusername);
        email = findViewById(R.id.txtuseremail);
        password = findViewById(R.id.txtuserpassword);
        passwordn = findViewById(R.id.txtconfirmpassword);
        register = findViewById(R.id.btnreg);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (username.getText().length() == 0 && email.getText().length() == 0 && password.getText().length() == 0) {
                    username.setError("Please enter username");
                    email.setError("Please enter email");
                    password.setError("Please enter password");

                } else if (username.getText().length() > 0 && email.getText().length() > 0 && password.getText().length() > 0 && passwordn.getText().length() == 0)
                {

                    passwordn.setError("Please confirm the password");

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Successfully register the user",Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(Registration_Activity.this,Login_Activity.class);
                    startActivity(i);
                }

            }
        });

    }




        }



