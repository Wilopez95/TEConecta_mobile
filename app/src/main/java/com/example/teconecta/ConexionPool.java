package com.example.teconecta;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ConexionPool {

    private static ConexionPool singleton;
    private RequestQueue queue;
    private ArrayList<Actividad> lista_Actividades = new ArrayList<Actividad>();
    private ArrayList<Contacto> lista_Contactos = new ArrayList<>();
    private Context context;



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
        this.context = context;
        queue = Volley.newRequestQueue(context);
    }


    public void getActivities(final ServerCallback callback){
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
                    callback.onSuccess(response);


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

    public void getContacs(final ServerCallback callback){
        //Log.d("RESPONSE", "GET CONTACS");
        String url = "https://teconecta-noisy-rhinocerous-te.mybluemix.net/account";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0;i<response.length();i++){
                        JSONObject contacto = new JSONObject(response.get(i).toString());
                        String id = contacto.getString("id");
                        String name = contacto.getString("name");
                        String description = contacto.getString("description");
                        String phone = contacto.getString("phone");
                        String location = contacto.getString("location");
                        String place = contacto.getString("place");  //SEDE
                        String urlImgProfile =  contacto.getString("urlImgProfile");
                        String manager = contacto.getString("manager");

                        lista_Contactos.add(new Contacto(id,name,description,phone,location,place,urlImgProfile,manager));
                    }
                    callback.onSuccess(response);

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


    public void RegisterAssistance(final String fkact, final String name, final String credential, final String email , final String state){
        String url = "https://teconecta-noisy-rhinocerous-te.mybluemix.net/assistances";
        JSONObject js = new JSONObject();
        try {
            js.put("fk_activity", fkact);
            js.put("name", name);
            js.put("credential", credential);
            js.put("email", email);
            js.put("state", state);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, url, js, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("RESPONSE", response.toString() + " i am queen");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("RESPONSE-ERROR", error.toString());

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        queue.add(jsonObjReq);


    }


    public ArrayList<Actividad> getListaActividades(){
        return  lista_Actividades;
    }

    public ArrayList<Contacto> getLista_Contactos(){
        return  lista_Contactos;
    }




}
