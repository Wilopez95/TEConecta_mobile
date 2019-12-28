package com.example.teconecta;

import android.util.Log;

import java.util.ArrayList;

public class MainController {

    private static MainController singleton;
    private ArrayList<Actividad> lista_Actividades = new ArrayList<>();
    private ArrayList<Actividad> lista_Actividades_Filtrada = new ArrayList<>();
    private Actividad selectecActivity;

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

    //DEV TEST DELETE ON PRODUCTION!!
    public void filltest(){

        lista_Actividades.add(new Actividad("1","Cartago1","Nombre 0","12/12/2020","Pretil","Ludica","Cartago","urlimage","20:00","23:00","1","Estado",true,20));
        lista_Actividades.add(new Actividad("2","San Jose1","Nombre 1","12/12/2020","Lugar","Deportiva","San Jose","urlimage","20:00","23:00","2","Estado",true,20));
        lista_Actividades.add(new Actividad("3","Cartago2","Nombre 2","12/12/2020","Pretil azul","Educativa","Cartago","urlimage","20:00","23:00","3","Estado",true,20));
        lista_Actividades.add(new Actividad("4","Limon1","Nombre 3","12/12/2020","Lugar","Ludica","Limon","urlimage","20:00","23:00","4","Estado",true,20));
        lista_Actividades.add(new Actividad("5","Cartago3","Nombre 4","12/12/2020","Lugar","Semana","Cartago","urlimage","20:00","23:00","5","Estado",true,20));
        lista_Actividades.add(new Actividad("6","San Carlos1","Nombre 5","12/12/2020","Lugar","Cultural","San Carlos","urlimage","20:00","23:00","6","Estado",true,20));
        lista_Actividades.add(new Actividad("7","Alajuela1","Nombre 6","12/12/2020","Lugar","Cultural","Alajuela","urlimage","20:00","23:00","7","Estado",true,20));
        lista_Actividades.add(new Actividad("8","San Jose2","Nombre 7","12/12/2020","Lugar","Ludica","San Jose","urlimage","20:00","23:00","8","Estado",true,20));
        lista_Actividades.add(new Actividad("9","Limon2","Nombre 8","12/12/2020","Lugar","Cultural","Limon","urlimage","20:00","23:00","9","Estado",true,20));

        lista_Contactos.add(new Contacto("1","Contato1","Some contact","8888888","B3","Cartago","http//:imagen.com","Someone"));
        lista_Contactos.add(new Contacto("2","Contato2","Some contact","8888888","B3","Cartago","http//:imagen.com","Someone"));
        lista_Contactos.add(new Contacto("3","Contato3","Some contact","8888888","B3","Cartago","http//:imagen.com","Someone"));
        lista_Contactos.add(new Contacto("4","Contato4","Some contact","8888888","B3","Cartago","http//:imagen.com","Someone"));
        lista_Contactos.add(new Contacto("5","Contato5","Some contact","8888888","B3","Cartago","http//:imagen.com","Someone"));
    }
    //DEV TEST DELETE ON PRODUCTION!!

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


    public Actividad getSelectecActivity(){
        return selectecActivity;
    }

    public void setSelectecActivity(Actividad act){
        this.selectecActivity = act;
    }


}
