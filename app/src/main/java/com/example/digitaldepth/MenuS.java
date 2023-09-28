package com.example.digitaldepth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuS extends AppCompatActivity {


    Button btvolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_s);

        btvolver = (Button)findViewById(R.id.btvolver);

        btvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btvolver = new Intent(MenuS.this, MainActivity.class);
                startActivity(btvolver);
            }
        });
    }
}