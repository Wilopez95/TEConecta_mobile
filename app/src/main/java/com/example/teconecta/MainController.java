package com.example.teconecta;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;

public class MainController {

    private static MainController singleton;
    private ArrayList<Actividad> lista_Actividades = new ArrayList<>();
    private ArrayList<Actividad> lista_Actividades_Filtrada = new ArrayList<>();
    private Actividad selectecActivity;
    private Contacto selectecContact;
    private Context ActiveContex;
    private ConexionPool cp;


    private ArrayList<Contacto> lista_Contactos = new ArrayList<>();



    private static synchronized void createinstance() {
        if(singleton == null){
            singleton = new MainController();
        }
    }

    public static MainController getInstance(){
        if(singleton == null){
            createinstance();
        }
        return singleton;
    }


    public ArrayList<Contacto> getListContac(){
        return lista_Contactos;
    }

    public ArrayList<Actividad> getListActivities(int category , int filter ){
        switch (category){
            case 1:
                //SEDE
                switch (filter) {
                    case 0:
                        //CARTAGO
                        lista_Actividades_Filtrada.clear();
                        for (int i = 0; i < lista_Actividades.size(); i++) {
                            if (lista_Actividades.get(i).getSede() == "Cartago") {
                                lista_Actividades_Filtrada.add(lista_Actividades.get(i));
                            }
                        }
                        return lista_Actividades_Filtrada;
                    case 1:
                        //SAN JOSE
                        lista_Actividades_Filtrada.clear();
                        for(int i=0; i<lista_Actividades.size(); i++){
                            if (lista_Actividades.get(i).getSede()=="San Jose"){
                                lista_Actividades_Filtrada.add(lista_Actividades.get(i));
                            }
                        }
                        return lista_Actividades_Filtrada;
                    case 2:
                        //SAN CARLOS
                        lista_Actividades_Filtrada.clear();
                        for(int i=0; i<lista_Actividades.size(); i++){
                            if (lista_Actividades.get(i).getSede()== "San Carlos"){
                                lista_Actividades_Filtrada.add(lista_Actividades.get(i));
                            }
                        }
                        return lista_Actividades_Filtrada;
                    case 3:
                        //LIMON
                        lista_Actividades_Filtrada.clear();
                        for(int i=0; i<lista_Actividades.size(); i++){
                            if (lista_Actividades.get(i).getSede()== "Limon"){
                                lista_Actividades_Filtrada.add(lista_Actividades.get(i));
                            }
                        }
                        return lista_Actividades_Filtrada;
                    case 4:
                        //ALAJUELA
                        lista_Actividades_Filtrada.clear();
                        for(int i=0; i<lista_Actividades.size(); i++){
                            if (lista_Actividades.get(i).getSede()== "Alajuela"){
                                lista_Actividades_Filtrada.add(lista_Actividades.get(i));
                            }
                        }
                        return lista_Actividades_Filtrada;
                }
            case 2:
                //TIPO
            case 3:
                //CATEGORIA
            default:
                return lista_Actividades;
        }
    }

    public void getData(){

        cp =  ConexionPool.getInstance();
        cp.initQueue(ActiveContex);
        cp.getActivities(new ServerCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                lista_Actividades.addAll(cp.getListaActividades());
            }
        });
        cp.getContacs(new ServerCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                lista_Contactos.addAll(cp.getLista_Contactos());
            }
        });




    }


    public Actividad getSelectecActivity(){
        return selectecActivity;
    }

    public void setSelectecActivity(Actividad act){
        this.selectecActivity = act;
    }

    public Contacto getSelectecContact() {
        return selectecContact;
    }

    public void  setSelectecContact(Contacto ctc){
        this.selectecContact = ctc;
    }

    public Context getActiveContex(){
        return ActiveContex;
    }

    public void setActiveContex(Context activeContex) {
        this.ActiveContex = activeContex;
    }
}
