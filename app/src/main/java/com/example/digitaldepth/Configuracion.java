package com.example.digitaldepth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.digitaldepth.BD.Utility;
import com.example.digitaldepth.BD.bdHelper;

public class Configuracion extends AppCompatActivity {

    bdHelper conn;
    String NombreUsuario;
    TextView CtxtUsuario;
    TextView CtxtCorreo;
    TextView CtxtContraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        conn = new bdHelper(getApplicationContext(), "bd_usuarios", null, 2);

        Intent intent = getIntent();

        NombreUsuario = intent.getStringExtra("NombreUsuario");

        CtxtUsuario = findViewById(R.id.CtxtUsuario);
        CtxtCorreo = findViewById(R.id.CtxtCorreo);
        CtxtContraseña = findViewById(R.id.CtxtContraseña);



        VideoView mivision = findViewById(R.id.mivideo);

        String videoop = "android.resource://" + getPackageName() + "/" + R.raw.fondovideo;
        Uri uri = Uri.parse(videoop);

        mivision.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        mivision.setMediaController(mediaController);
        mediaController.setAnchorView(mivision);

        final MediaPlayer mediaPlayer = MediaPlayer.create(this, uri);
        mediaPlayer.setVolume(0.05f, 0.05f);
        mivision.start();
        mediaPlayer.setLooping(true);
        consultar();
    }


    private void consultar() {

        //conexion a la base de dato para leer los datos
        SQLiteDatabase db = conn.getReadableDatabase();

        String[] parametros = {NombreUsuario};

        try {
            //Consulta de base de dato
            Cursor cursor = db.rawQuery("SELECT " + Utility.CAMPO_NOMBREUSUARIO + ","+ Utility.CAMPO_CORREO + "," + Utility.CAMPO_CONTRASEÑA +
                    " FROM " + Utility.TABLA_USUARIO + " WHERE " + Utility.CAMPO_NOMBREUSUARIO + "=? ", parametros);

            cursor.moveToFirst();

            //obtener los datos del cursor Usuario y Contraseña de la base de dato
            String UsuarioBD = cursor.getString(0);
            String CorreoBD = cursor.getString(1);
            String ContraseñaBD = cursor.getString(2);

            CtxtUsuario.setText("Usuario: "+UsuarioBD);
            CtxtCorreo.setText("Correo: "+CorreoBD);
            CtxtContraseña.setText("Contraseña: "+ContraseñaBD);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error de inicio de sesion (Error de BD)", Toast.LENGTH_LONG).show();
        }
    }

}