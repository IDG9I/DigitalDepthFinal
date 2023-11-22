package com.example.digitaldepth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

public class Analizando extends AppCompatActivity {

    private Handler handler;
    private int suma = 0;
    TextView txtPorcentaje;
    TextView TextoProseso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analizando);

        txtPorcentaje = findViewById(R.id.txtPorcentaje);
        TextoProseso = findViewById(R.id.TextoProseso);

        handler = new Handler();

        handler.postDelayed(actualizaSuma, 1000);
    }

    private Runnable actualizaSuma = new Runnable() {
        @Override
        public void run() {
            suma++;
            txtPorcentaje.setText(suma + "%");
            handler.postDelayed(this, 200);

            if(suma==100){
                TextoProseso.setText("Finalizando...");
                Intent volver = new Intent(Analizando.this, MenuS.class);
                startActivity(volver);
                Toast.makeText(getApplicationContext(),"Proceso Finalizado", Toast.LENGTH_LONG).show();
            }

            if(suma==20){
                TextoProseso.setText("Detectando Sensores...");
            }
            if(suma==50){
                TextoProseso.setText("Detectando Linterna...");
            }
            if(suma==60){
                TextoProseso.setText("Detectando Proximidad...");
            }
            if(suma==80){
                TextoProseso.setText("Detectando Sensores...");
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(actualizaSuma);
    }

    private boolean isActivityRunning = true;
    @Override
    protected void onPause() {
        super.onPause();
        isActivityRunning = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isActivityRunning = true;
    }
}