package com.example.digitaldepth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MenuS extends AppCompatActivity {


    Button btvolver;
    Button btnInfo;
    Button btnSensor;
    Button btnWifi;

    ImageView imgAnalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_s);

        btvolver = (Button)findViewById(R.id.btvolver);
        btnInfo = (Button)findViewById(R.id.btnInfo);
        btnSensor = (Button)findViewById(R.id.btnSensor);
        btnWifi = (Button)findViewById(R.id.btnWifi);
        imgAnalizar = findViewById(R.id.imgAnalizar);

        imgAnalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imgAnalizar = new Intent(MenuS.this, Analizando.class);
                startActivity(imgAnalizar);
            }
        });

        btnWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnWifi = new Intent(MenuS.this, Wifi.class);
                startActivity(btnWifi);
            }
        });


        btnSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnSensor = new Intent(MenuS.this, Sensor.class);
                startActivity(btnSensor);
            }
        });
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnInfo = new Intent(MenuS.this, Informacion.class);
                startActivity(btnInfo);
            }
        });

        btvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btvolver = new Intent(MenuS.this, MainActivity.class);
                startActivity(btvolver);
            }
        });
    }
}