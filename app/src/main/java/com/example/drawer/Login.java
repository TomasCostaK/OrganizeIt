package com.example.drawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    private Button signIn;

    private EditText username;
    private EditText password;

    private TextView signUptext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUptext = findViewById(R.id.signUptext);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        signIn = (Button) findViewById(R.id.signIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate_username() && validate_password())
                    openMainPage();
            }
        });

        signUptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp = new Intent(Login.this, Register.class);
                startActivity(signUp);
            }
        });
    }

    public void openMainPage(){
        Intent intent = new Intent(Login.this, NewMainActivity.class);
        startActivity(intent);
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

}
