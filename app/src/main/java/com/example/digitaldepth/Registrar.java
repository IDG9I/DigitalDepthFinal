package com.example.digitaldepth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.digitaldepth.BD.Utility;
import com.example.digitaldepth.BD.bdHelper;

public class Registrar extends AppCompatActivity {

    EditText RtxtUsuario;
    EditText RtxtCorreo;

    //Para cambiar el tipo de texto en Contraseña
    Switch RswMostrarContra;

    //Campo de Contraseñas
    EditText RtxtContraseña;
    EditText RtxtConfir;

    Button RbtnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        RtxtUsuario = findViewById(R.id.RtxtUsuario);
        RtxtCorreo = findViewById(R.id.RtxtCorreo);

        RswMostrarContra = findViewById(R.id.RswMostrarContra);
        RtxtContraseña = findViewById(R.id.RtxtContraseña);
        RtxtConfir = findViewById(R.id.RtxtConfir);

        RbtnRegistrar = (Button)findViewById(R.id.RbtnRegistrar);
        RswMostrarContra.setOnClickListener(new View.OnClickListener() {

            //cambiar el tipo de texto de Contraseña a Texto
            @Override
            public void onClick(View v) {
                if(RswMostrarContra.isChecked()){

                    RtxtContraseña.setInputType(InputType.TYPE_CLASS_TEXT);
                    RtxtConfir.setInputType(InputType.TYPE_CLASS_TEXT);
                }else{
                    RtxtContraseña.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    RtxtConfir.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        RbtnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrarUsuario();
            }
        });

    }

    private void RegistrarUsuario(){
        String Usuarios = RtxtUsuario.getText().toString();
        String Correos = RtxtCorreo.getText().toString();
        String Contraseñas = RtxtContraseña.getText().toString();
        String CContraseñas = RtxtConfir.getText().toString();

        try {
            //verificar si los campos estan vacios
            if(Usuarios.equals("") || Correos.equals("") || Contraseñas.equals("") || CContraseñas.equals("")){
                Toast.makeText(getApplicationContext(), "RELLENE LOS CAMPOS VACIOS", Toast.LENGTH_LONG).show();

            }else{
                //verificar si la contraseña son iguales en los 2 campos de texto
                if(Contraseñas.equals(CContraseñas)){
                    //Conexion a la base de datos
                    bdHelper conn = new bdHelper(this, "bd_usuarios", null, 2);
                    SQLiteDatabase db = conn.getWritableDatabase();


                    //Obtener los valores de cada EditTex y Insertarlos en Campos
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(Utility.CAMPO_NOMBREUSUARIO, RtxtUsuario.getText().toString());
                    contentValues.put(Utility.CAMPO_CORREO, RtxtCorreo.getText().toString());
                    contentValues.put(Utility.CAMPO_CONTRASEÑA, RtxtContraseña.getText().toString());

                    //obtener los campos y insertarlo en la base de dato
                    long ID_Resultante = db.insert(Utility.TABLA_USUARIO, null, contentValues);

                    //mostrar Mensaje del usuario Registrado
                    Toast.makeText(getApplicationContext(),"Usuario Registrado: " + ID_Resultante, Toast.LENGTH_LONG).show();
                    //cerrar conexion
                    db.close();

                    //Pasar al Layout de Inicio de Sesion
                    Intent RbtnRegistrar = new Intent(Registrar.this, MainActivity.class);
                    startActivity(RbtnRegistrar);

                }else{
                    Toast.makeText(getApplicationContext(), "La Contraseña no son iguales", Toast.LENGTH_LONG).show();
                    RtxtContraseña.setText("");
                    RtxtConfir.setText("");
                }
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "ERROR de Registro", Toast.LENGTH_LONG).show();
            limpiar();
        }
    }

    private void limpiar(){
        RtxtUsuario.setText("");
        RtxtCorreo.setText("");
        RtxtContraseña.setText("");
        RtxtConfir.setText("");
    }

}