package com.example.teconecta;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Actividad {
    private String ID;
    private String Nombre;
    private String Descripcion;
    private String Fecha;
    private String Lugar;
    private String Tipo;
    private String Sede;
    private String urlImagen;
    private String HoraI;
    private String HoraF;
    private String FKCuenta;
    private String Estado;
    private boolean Asistencia;
    private int Cupo;



    public Actividad(String id,String name,String description ,String date ,String location , String type , String place,String urlImagen , String horai , String horaf,String fkcuenta , String state , boolean assistance , int space ){
        this.ID = id;
        this.Nombre =  name;
        this.Descripcion = description;
        this.Fecha = date;
        this.Lugar = location;
        this.Tipo = type;
        this.Sede = place;
        this.urlImagen = urlImagen;
        this.HoraI = horai;
        this.HoraF=horaf;
        this.FKCuenta = fkcuenta;
        this.Estado = state;
        this.Asistencia = assistance;
        this.Cupo = space;
    }

    public Actividad(){

    }




    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getLugar() {
        return Lugar;
    }

    public void setLugar(String lugar) {
        Lugar = lugar;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getHoraI() {

        String[] arrOfTimes = HoraI.split(":", 3);
        if (arrOfTimes[1].length()==1) {
            if(arrOfTimes[1].equals("0")){
                arrOfTimes[1]="00";
            }else{
                arrOfTimes[1]="0"+arrOfTimes[1];
            }
        }
        return arrOfTimes[0]+":"+arrOfTimes[1];
    }

    public void setHoraI(String horaI) {
        HoraI = horaI;
    }

    public String getHoraF() {

        String[] arrOfTimes = HoraF.split(":", 3);
        if (arrOfTimes[1].length()==1) {
            if(arrOfTimes[1].equals("0")){
                arrOfTimes[1]="00";
            }else{
                arrOfTimes[1]="0"+arrOfTimes[1];
            }
        }
        return arrOfTimes[0]+":"+arrOfTimes[1];
    }

    public void setHoraF(String horaF) {
        HoraF = horaF;
    }

    public String getFKCuenta() {
        return FKCuenta;
    }

    public void setFKCuenta(String FKCuenta) {
        this.FKCuenta = FKCuenta;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public boolean getAsistencia() {
        return Asistencia;
    }

    public void setAsistencia(boolean asistencia) {
        Asistencia = asistencia;
    }


    public int getCupo() {
        return Cupo;
    }

    public void setCupo(int cupo) {
        Cupo = cupo;
    }

    public String getSede() {
        return Sede;
    }

    public void setSede(String sede) {
        Sede = sede;
    }

}
