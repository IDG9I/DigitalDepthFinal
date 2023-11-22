package com.example.digitaldepth.BD;

public class Utility {

    public static final String TABLA_USUARIO = "RegistroUsuario";
    public static final String CAMPO_ID = "ID";
    public static final String CAMPO_NOMBREUSUARIO = "NombreUsuario";
    public static final String CAMPO_CORREO = "Correo";
    public static final String CAMPO_CONTRASEÑA = "Contraseña";

    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE " + TABLA_USUARIO + " (" +
            CAMPO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CAMPO_NOMBREUSUARIO + " TEXT, " +
            CAMPO_CORREO + " TEXT," +
            CAMPO_CONTRASEÑA + " TEXT)";

}
