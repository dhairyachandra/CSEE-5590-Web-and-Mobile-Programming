package com.dhairya.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    Button login;
    String user,pass;
    boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        user = username.getText().toString();
        pass = password.getText().toString();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!user.isEmpty() && !pass.isEmpty()) {
                    if (user.equals("dhairya") && pass.equals("password")) {
                        flag = true;
                    }
                }
                // when username and password are not matched
                if (!flag) {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials! Try again", Toast.LENGTH_SHORT).show();
                }
                // when username and password are matched then activity is changed
                else {
                    Toast.makeText(getApplicationContext(), "Login Successful!!", Toast.LENGTH_SHORT).show();
                    welcomePage();
                }
            }
        });
    }
    public void welcomePage(){
        Intent redirect = new Intent(MainActivity.this, welcome.class);
        startActivity(redirect);
    }

}
