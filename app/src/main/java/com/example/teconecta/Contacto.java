package com.example.teconecta;

public class Contacto {
    private String ID;
    private String Nombre;
    private String Descripcion;
    private String Telefono;
    private String Direccion;
    private String Sede;
    private String urlImgPerfil;
    private String Encargado;



    public Contacto(){

    }

    public Contacto(String id,String name,String description,String phone,String location,String place , String urlimagen , String manager){
        this.ID=id;
        this.Nombre=name;
        this.Descripcion = description;
        this.Telefono = phone;
        this.Direccion = location;
        this.Sede = place;
        this.urlImgPerfil = urlimagen;
        this.Encargado = manager;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getSede() {
        return Sede;
    }

    public void setSede(String sede) {
        Sede = sede;
    }

    public String getUrlImgPerfil() {
        return urlImgPerfil;
    }

    public void setUrlImgPerfil(String urlImgPerfil) {
        this.urlImgPerfil = urlImgPerfil;
    }

    public String getEncargado() {
        return Encargado;
    }

    public void setEncargado(String encargado) {
        Encargado = encargado;
    }
}
