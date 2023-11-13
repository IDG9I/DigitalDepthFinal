package com.example.digitaldepth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.opengl.GLES20;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.File;

public class Informacion extends AppCompatActivity {

    private TextView txtModelo, txtMarca, txtCPU, txtGPU, txtRAM, txtAlmacenamiento, txtPantalla, txtBateria, txtConectividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        txtModelo = findViewById(R.id.txtModelo);
        txtMarca = findViewById(R.id.txtMarca);
        txtCPU = findViewById(R.id.txtCPU);
        txtGPU = findViewById(R.id.txtGPU);
        txtRAM = findViewById(R.id.txtRAM);
        txtAlmacenamiento = findViewById(R.id.txtAlmacenamiento);
        txtPantalla = findViewById(R.id.txtPantalla);
        txtBateria = findViewById(R.id.txtBateria);
        txtConectividad = findViewById(R.id.txtConectividad);


        //OBTENER INFO DE LA RAM
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);

        long totalMemory = memoryInfo.totalMem;
        long availableMemory = memoryInfo.availMem;

        // Convertir bytes a megabytes
        long totalMemoryMB = totalMemory / (1024 * 1024);
        long availableMemoryMB = availableMemory / (1024 * 1024);
        //--------------------------------------------------------


        // Informacion almacenamiento Interno
        File Almacenamiento = Environment.getDataDirectory();
        StatFs StatFs = new StatFs(Almacenamiento.getAbsolutePath());
        long AlmacenamientoTotal = StatFs.getTotalBytes();
        long EspacioLibre = StatFs.getFreeBytes();

        // Convertir bytes a gigabytes del almacenamiento interno
        long TotalGB = AlmacenamientoTotal / (1024 * 1024 * 1024);
        long DisponibleGB = EspacioLibre / (1024 * 1024 * 1024);

        //--------------------------------------------------------

        // Informacion sobre la pantalla
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);

        int Ancho = displayMetrics.widthPixels;
        int Alto = displayMetrics.heightPixels;
        float Densidad = displayMetrics.density;
        int dpi = displayMetrics.densityDpi;

        //--------------------------------------------------------

        // Obtener información y guardarlo en Variables
        String Modelo = Build.MODEL;
        String Marca = Build.BRAND;
        String CPU = Build.CPU_ABI;
        String renderer = GLES20.glGetString(GLES20.GL_VENDOR);
        String AlmacenamientoInterno = Environment.getDataDirectory().getAbsolutePath();

        // Mostrar la información en los TextViews
        txtModelo.setText("Modelo:   " + Modelo);
        txtMarca.setText("Marca:   " + Marca);
        txtCPU.setText("CPU:   " + CPU);
        txtGPU.setText("GPU:   " + renderer);
        txtRAM.setText("RAM:   " + totalMemoryMB + " MB / " + "Ram Disp: " + availableMemoryMB + " MB");

        txtAlmacenamiento.setText("Storage: Total " + TotalGB + " GB, Disponible " + DisponibleGB + " GB");

        txtPantalla.setText("Pantalla:  Ancho: " + Ancho + "" + " / Largo : " + Alto + "\n"
                + "    - Density: " + Densidad + "\n"
                + "    - DPI: " + dpi);



    }
}