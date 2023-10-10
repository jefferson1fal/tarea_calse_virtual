package edu.umg.domain;


public class Usuario {
    private int id_usuario;
    private String nombre_usuario;
    private String contraseña;

    public Usuario() {
    }

    public int getId_usuario() {
        return this.id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return this.nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContraseña() {
        return this.contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "Usuario{id_usuario=" + this.id_usuario + ", nombre_usuario=" + this.nombre_usuario + ", contraseña=" + this.contraseña + "}";
    }
}
