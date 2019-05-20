package com.example.drawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    Button signUp;
    EditText email;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signUp = (Button) findViewById(R.id.signUp);
        email = (EditText) findViewById(R.id.email_register);
        username = (EditText) findViewById(R.id.username_register);
        password = (EditText) findViewById(R.id.password_register);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                if (validate_username() && validate_password() && validate_email()) {
                    Intent intent = new Intent(Register.this, Login.class);
                    startActivity(intent);
                }
            }
        });
    }

    public boolean validate_username() {
        String usernameinput = username.getText().toString().trim();
        if(usernameinput.isEmpty()){
            Toast.makeText(this, "Username can't be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        else {return true;}

    }

    public boolean validate_password() {
        String passwordinput = password.getText().toString().trim();
        if(passwordinput.isEmpty()){
            Toast.makeText(this, "Password can't be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        else {return true;}
    }

    public boolean validate_email() {
        String emailinput = email.getText().toString().trim();
        if(emailinput.isEmpty()){
            Toast.makeText(this, "Email can't be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        else {return true;}
    }
}
