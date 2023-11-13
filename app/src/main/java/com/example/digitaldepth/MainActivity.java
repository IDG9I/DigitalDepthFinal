package com.example.digitaldepth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.digitaldepth.DatabaseHelper.DatabaseAdapter;

public class MainActivity extends AppCompatActivity {

    Button button2;

    private EditText Usuario;
    private EditText Contraseña;
    private DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button2 = (Button)findViewById(R.id.btnIniciarS);

        Usuario = findViewById(R.id.txtUsuario);
        Contraseña = findViewById(R.id.txtContraseña);

        databaseAdapter = new DatabaseAdapter(this);
        databaseAdapter.open();

        //lleva al Menu Principal de botones con inicio de sesion
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usuario = Usuario.getText().toString();
                String contraseña = Contraseña.getText().toString();

                if (databaseAdapter.loginUser(usuario, contraseña)) {
                    Toast.makeText(MainActivity.this, "Sesion Exitoso", Toast.LENGTH_SHORT).show();
                    Intent button2 = new Intent(MainActivity.this, MenuS.class);
                    startActivity(button2);
                } else {
                    Toast.makeText(MainActivity.this, "Usuario o Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}