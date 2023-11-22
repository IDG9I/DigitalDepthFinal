package com.example.digitaldepth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.digitaldepth.BD.Utility;
import com.example.digitaldepth.BD.bdHelper;

public class MainActivity extends AppCompatActivity {

    Button button2;
    Button btnRegistrar;

    private EditText Usuario;
    private EditText Contraseña;

    bdHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conn = new bdHelper(getApplicationContext(), "bd_usuarios", null, 2);

        button2 = (Button)findViewById(R.id.btnIniciarS);
        btnRegistrar = (Button)findViewById(R.id.btnRegistrar);

        Usuario = findViewById(R.id.txtUsuario);
        Contraseña = findViewById(R.id.txtContraseña);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnRegistrar = new Intent(MainActivity.this, Registrar.class);
                startActivity(btnRegistrar);
            }
        });

        //lleva al Menu Principal de botones con inicio de sesion
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultar();
            }
        });
    }

    private void consultar() {
        //obtener los datod de los campos de texto
        String UsuarioC = Usuario.getText().toString();
        String ContraseñaC = Contraseña.getText().toString();

        //conexion a la base de dato para leer los datos
        SQLiteDatabase db = conn.getReadableDatabase();

        String[] parametros = {Usuario.getText().toString()};

        try {
            //Consulta de base de dato
            Cursor cursor = db.rawQuery("SELECT " + Utility.CAMPO_NOMBREUSUARIO + "," + Utility.CAMPO_CONTRASEÑA +
                    " FROM " + Utility.TABLA_USUARIO + " WHERE " + Utility.CAMPO_NOMBREUSUARIO + "=? ", parametros);

            cursor.moveToFirst();

            //obtener los datos del cursor Usuario y Contraseña de la base de dato
            String UsuarioBD = cursor.getString(0);
            String ContraseñaBD = cursor.getString(1);


            //verificar si los datos son iguales a la de la base de datos
            if(UsuarioC.equals(UsuarioBD) && ContraseñaC.equals(ContraseñaBD)){
                Toast.makeText(getApplicationContext(), "Sesion Iniciado", Toast.LENGTH_LONG).show();
                Intent button2 = new Intent(MainActivity.this, MenuS.class);
                startActivity(button2);
            }else{
                Toast.makeText(getApplicationContext(), "Usuario o Contraseña Incorrecto!!", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Usuario: "+ UsuarioBD + "Contraseña: " +ContraseñaBD, Toast.LENGTH_LONG).show();
            }



        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error de inicio de sesion (Error de BD)", Toast.LENGTH_LONG).show();
        }
    }
}