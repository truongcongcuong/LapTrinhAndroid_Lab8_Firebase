package com.example.laptrinhandroid_lab8_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_main_sign_in, btn_main_sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_main_sign_in = findViewById(R.id.btn_sign_in_action);
        btn_main_sign_up = findViewById(R.id.btn_main_sign_up);

        btn_main_sign_in.setText("SIGN IN");
        btn_main_sign_up.setText("REGISTRY");

        btn_main_sign_in.setOnClickListener(v->startActivity(new Intent(MainActivity.this,Acticity_Login.class)));
        btn_main_sign_up.setOnClickListener(v->startActivity(new Intent(MainActivity.this,Registry_Activity.class)));



    }
}