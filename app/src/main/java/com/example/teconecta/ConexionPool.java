package com.example.teconecta;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ConexionPool {

    private static ConexionPool singleton;
    private RequestQueue queue;
    private ArrayList<Actividad> lista_Actividades = new ArrayList<Actividad>();
    private boolean dataflag = false;



    private static synchronized void createinstance() {
        if(singleton == null){
            singleton = new ConexionPool();
        }
    }

    public static ConexionPool getInstance(){

        if(singleton == null){
            createinstance();
        }
        return singleton;
    }

    public void initQueue(Context context){
        queue = Volley.newRequestQueue(context);
    }


    public void getActivities(){
        Log.d("RESPONSE", "GET ACTIVIDADES");

        String url = "https://teconecta-noisy-rhinocerous-te.mybluemix.net/allactivities";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Log.d("RESPONSE.OK", response.toString());
                try {
                    for (int i = 0;i<response.length();i++){
                        JSONObject actividad = new JSONObject(response.get(i).toString());
                        String id = actividad.getString("id");
                        String name = actividad.getString("name");
                        String description = actividad.getString("description");
                        String date = actividad.getString("date");
                        String location = actividad.getString("location");
                        String type = actividad.getString("type");
                        String place = actividad.getString("place");
                        String urlImgActivity = actividad.getString("urlImgActivity");
                        String timeI = actividad.getString("timeI");
                        String timeF = actividad.getString("timeF");
                        String fk_user = actividad.getString("fk_user");
                        String assistance = actividad.getString("assistance");
                        String state = actividad.getString("state");
                        String space = actividad.getString("space");

                        lista_Actividades.add(new Actividad(id,name,description,date,location,type,place,urlImgActivity,timeI,timeF,fk_user,state,Boolean.parseBoolean(assistance),Integer.parseInt(space)));

                    }
                    dataflag = true;
                    Log.d("RESPONSE.SIZE-->",Integer.toString(lista_Actividades.size()));

                }catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        },

                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("RESPONSE.ERROR", error.toString());
            }
        });

        queue.add(request);
    }

    public void getContacs(){
        //Log.d("RESPONSE", "GET CONTACS");
        String url = "https://teconecta-noisy-rhinocerous-te.mybluemix.net/account";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Log.d("RESPONSE.OK", response.toString());


            }
        },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("RESPONSE.ERROR", error.toString());
                    }
                });

        queue.add(request);

    }


    public ArrayList<Actividad> getListaActividades(){
        return  lista_Actividades;
    }

    public boolean getDataFlag(){
        return dataflag;
    }


}
