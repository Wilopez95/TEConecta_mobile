package com.example.teconecta;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class MainController {

    private static MainController singleton;
    private ArrayList<Actividad> lista_Actividades = new ArrayList<>();
    private ArrayList<Actividad> lista_Actividades_Filtrada = new ArrayList<>();
    private ArrayList<String> listaCuentasIDs = new ArrayList<>();
    private Actividad selectecActivity;
    private Contacto selectecContact;
    private Context ActiveContex;
    private ConexionPool cp;
    private boolean FlagActByUsr = false;


    private ArrayList<Contacto> lista_Contactos = new ArrayList<>();
    private ArrayList<Contacto> lista_Contactos_filtrados = new ArrayList<>();


    private static synchronized void createinstance() {
        if (singleton == null) {
            singleton = new MainController();
        }
    }

    public static MainController getInstance() {
        if (singleton == null) {
            createinstance();
        }
        return singleton;
    }


    //DEV TEST DELETE ON PRODUCTION!!
    /*
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
    }*/
    //DEV TEST DELETE ON PRODUCTION!!




    public ArrayList<Actividad> getLista_Actividadesbyaccount(String user){
        lista_Actividades_Filtrada.clear();
        for (int i = 0; i < lista_Actividades.size(); i++) {
            if (lista_Actividades.get(i).getFKCuenta().equals(user)) {
                lista_Actividades_Filtrada.add(lista_Actividades.get(i));
            }
        }
        return lista_Actividades_Filtrada;
    }

    public ArrayList<Contacto> getListContac() {
        return lista_Contactos;
    }

    public ArrayList<Contacto> getListContacsFilter(String s){
        lista_Contactos_filtrados.clear();
        for (int i=0;i<lista_Contactos.size();i++){
            if(findMach(s.toUpperCase(),lista_Contactos.get(i).getNombre().toUpperCase())){
                lista_Contactos_filtrados.add(lista_Contactos.get(i));
            }
        }

        return lista_Contactos_filtrados;
    }

    private boolean findMach(String a, String b){
        return b.contains(a);
    }

    public ArrayList<Actividad> getListActivities(int category, int filter) {
        switch (category) {
            case 1:
                //SEDE
                switch (filter) {
                    case 0:
                        //CARTAGO
                        lista_Actividades_Filtrada.clear();
                        for (int i = 0; i < lista_Actividades.size(); i++) {
                            if (lista_Actividades.get(i).getSede().equals("Cartago")) {
                                lista_Actividades_Filtrada.add(lista_Actividades.get(i));
                            }
                        }
                        return lista_Actividades_Filtrada;
                    case 1:
                        //SAN JOSE
                        lista_Actividades_Filtrada.clear();
                        for (int i = 0; i < lista_Actividades.size(); i++) {
                            if (lista_Actividades.get(i).getSede().equals("San Jose")) {
                                lista_Actividades_Filtrada.add(lista_Actividades.get(i));
                            }
                        }
                        return lista_Actividades_Filtrada;
                    case 2:
                        //SAN CARLOS
                        lista_Actividades_Filtrada.clear();
                        for (int i = 0; i < lista_Actividades.size(); i++) {
                            if (lista_Actividades.get(i).getSede().equals("San Carlos")) {
                                lista_Actividades_Filtrada.add(lista_Actividades.get(i));
                            }
                        }
                        return lista_Actividades_Filtrada;
                    case 3:
                        //LIMON
                        lista_Actividades_Filtrada.clear();
                        for (int i = 0; i < lista_Actividades.size(); i++) {
                            if (lista_Actividades.get(i).getSede().equals("Limon")) {
                                lista_Actividades_Filtrada.add(lista_Actividades.get(i));
                            }
                        }
                        return lista_Actividades_Filtrada;
                    case 4:
                        //ALAJUELA
                        lista_Actividades_Filtrada.clear();
                        for (int i = 0; i < lista_Actividades.size(); i++) {
                            if (lista_Actividades.get(i).getSede().equals("Alajuela")) {
                                lista_Actividades_Filtrada.add(lista_Actividades.get(i));
                            }
                        }
                        return lista_Actividades_Filtrada;
                }
            case 2:
                //TIPO EVENTO
                switch (filter) {
                    case 0:
                        //CULTURAL
                        lista_Actividades_Filtrada.clear();
                        for (int i = 0; i < lista_Actividades.size(); i++) {
                            if (lista_Actividades.get(i).getTipo().equals("Cultural")) {
                                lista_Actividades_Filtrada.add(lista_Actividades.get(i));
                            }
                        }
                        return lista_Actividades_Filtrada;
                    case 1:
                        //DEPORTIVA
                        lista_Actividades_Filtrada.clear();
                        for (int i = 0; i < lista_Actividades.size(); i++) {
                            if (lista_Actividades.get(i).getTipo().equals("Deportiva")) {
                                lista_Actividades_Filtrada.add(lista_Actividades.get(i));
                            }
                        }
                        return lista_Actividades_Filtrada;
                    case 2:
                        //EDUCATIVA
                        lista_Actividades_Filtrada.clear();
                        for (int i = 0; i < lista_Actividades.size(); i++) {
                            if (lista_Actividades.get(i).getTipo().equals("Educativa")) {
                                lista_Actividades_Filtrada.add(lista_Actividades.get(i));
                            }
                        }
                        return lista_Actividades_Filtrada;
                    case 3:
                        //LUDICA
                        lista_Actividades_Filtrada.clear();
                        for (int i = 0; i < lista_Actividades.size(); i++) {
                            if (lista_Actividades.get(i).getTipo().equals("Ludica")) {
                                lista_Actividades_Filtrada.add(lista_Actividades.get(i));
                            }
                        }
                        return lista_Actividades_Filtrada;
                    case 4:
                        //OTRAS
                        lista_Actividades_Filtrada.clear();
                        for (int i = 0; i < lista_Actividades.size(); i++) {
                            if (lista_Actividades.get(i).getTipo().equals("Otras")) {
                                lista_Actividades_Filtrada.add(lista_Actividades.get(i));
                            }
                        }
                        return lista_Actividades_Filtrada;
                }
            case 3:
                //CATEGORIA
            default:
                return lista_Actividades;
        }
    }


    public String[] getIDLiscaAcc(){
        String[] lista = new String[lista_Contactos.size()];
        for (int i = 0; i< lista_Contactos.size(); i++){
            lista[i]=lista_Contactos.get(i).getID();
        }
        return lista;
    }

    public String[] getLiscaAcc(){
        String[] lista = new String[lista_Contactos.size()];
        for (int i = 0; i< lista_Contactos.size(); i++){
            lista[i]=lista_Contactos.get(i).getNombre();
        }
        return lista;
    }

    public void getData() {

        lista_Actividades.clear();
        lista_Contactos.clear();

        cp = ConexionPool.getInstance();
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

    public void setFlagActByUser(boolean flag){
        this.FlagActByUsr = flag;
    }

    public boolean getFlagActByUser(){
        return FlagActByUsr;
    }

    public void registerAssistance(String fkact , String name,String email,String credential , String state){
        cp = ConexionPool.getInstance();
        cp.initQueue(ActiveContex);
        cp.RegisterAssistance(fkact,name,email,credential,state);
    }


    public Actividad getSelectecActivity() {
        return selectecActivity;
    }

    public void setSelectecActivity(Actividad act) {
        this.selectecActivity = act;
    }

    public Contacto getSelectecContact() {
        return selectecContact;
    }

    public void setSelectecContact(Contacto ctc) {
        this.selectecContact = ctc;
    }

    public Contacto getContactobyID(String id){
        Contacto responsectc = new Contacto();

        for (int i=0; i<lista_Contactos.size(); i++){
            if (lista_Contactos.get(i).getID().equals(id)){
                responsectc = lista_Contactos.get(i);
                break;
            }
        }
        return responsectc;
    }


    public Context getActiveContex(){
        return ActiveContex;
    }

    public void setActiveContex(Context activeContex) {
        this.ActiveContex = activeContex;
    }

    public String formatedDate(String separador , String date){
        String formatedDate="DEV";
        String[] arrOfDates = date.split("T", 2);
        String realdate = arrOfDates[0];
        //AÑO/MES/DIA/
        String[] arrOfStr = realdate.split("-", 3);
        formatedDate = arrOfStr[2]+separador;
        String Mes = arrOfStr[1];

        if (isToday(realdate)){
            formatedDate = "HOY";
        }else{
            switch (Mes) {
                case "01":
                    formatedDate = formatedDate + "ENE";
                    break;
                case "02":
                    formatedDate = formatedDate + "FEB";
                    break;
                case "03":
                    formatedDate = formatedDate + "MAR";
                    break;
                case "04":
                    formatedDate = formatedDate + "ABR";
                    break;
                case "05":
                    formatedDate = formatedDate + "MAY";
                    break;
                case "06":
                    formatedDate = formatedDate + "JUN";
                    break;
                case "07":
                    formatedDate = formatedDate + "JUL";
                    break;
                case "08":
                    formatedDate = formatedDate + "AGO";
                    break;
                case "09":
                    formatedDate = formatedDate + "SET";
                    break;
                case "10":
                    formatedDate = formatedDate + "OCT";
                    break;
                case "11":
                    formatedDate = formatedDate + "NOV";
                    break;
                case "12":
                    formatedDate = formatedDate + "DIC";
                    break;
            }
        }
        return formatedDate;
    }

    public boolean isToday(String date){
        java.util.Date fecha = new Date();
        String strDateFormat = "MM/dd/y";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        //Log.d("RESPONSE_DATE_DAY_SYS", objSDF.format(fecha));
        //M/D/A
        //Log.d("RESPONSE_DATE_DAY_ACT", date);

        String[] dateArr = date.split("-", 3);
        String[] todayArr = objSDF.format(fecha).split("/", 3);

        if(dateArr[0].equals(todayArr[2])){
            //Log.d("RESPONSE_DATE_YEAR", dateArr[0] + " -AÑO- "+todayArr[2]);
            if(dateArr[2].equals(todayArr[1])){
                //Log.d("RESPONSE_DATE_MONTH", dateArr[1] + " -MES- "+todayArr[1]);
                if(Integer.parseInt(dateArr[1])- Integer.parseInt(todayArr[0]) == 0){
                   // Log.d("RESPONSE_DATE_DAY", dateArr[0] + " -DIA- "+todayArr[0]);
                    return true;

                }
            }
        }
        return false;
    }
}
