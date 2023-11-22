package com.example.digitaldepth.Modelo;

public class usuarios {
    private int id;
    private String nombreUsuario;
    private String Correo;
    private String Contraseña;

    public usuarios() {
    }

    public usuarios(int id, String nombreUsuario, String correo, String contraseña) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        Correo = correo;
        Contraseña = contraseña;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "usuarios{" +
                "id=" + id +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", Correo='" + Correo + '\'' +
                ", Contraseña='" + Contraseña + '\'' +
                '}';
    }
}
