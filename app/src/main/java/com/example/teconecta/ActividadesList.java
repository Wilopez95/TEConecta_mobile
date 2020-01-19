package com.example.teconecta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class ActividadesList extends AppCompatActivity {

    private ListView LvActividades;
    private ActividadAdapter adaptadorActividades;
    private Spinner categories;
    private  Spinner filter;
    private ImageButton contacs;
    private MainController mc;
    private String[] arraySpinnerCategory;
    private String[] arraySpinnerFilter;
    private int cagtegoryid;
    private int filterid;
    private boolean termsflag;
    private  Button termsButton;
    private TextView t1,t2;
    private String[] lista;

    private boolean ActByUserFlag;


    private RelativeLayout terms;
    private RelativeLayout footer;


    private ArrayList<Actividad> listaActividades = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LvActividades = findViewById(R.id.ListViewActividades);
        mc =  MainController.getInstance();
        mc.setActiveContex(this);



        categories = findViewById(R.id.spinner_categories);
        filter = findViewById(R.id.spinner_selection);

        contacs= findViewById(R.id.Contacs);
        t1 = findViewById(R.id.textView1);
        t2 = findViewById(R.id.texView2);

        terms = findViewById(R.id.terms);
        footer = findViewById(R.id.footer_RL);
        termsButton = findViewById(R.id.ternsButton);

        SharedPreferences prefs = getSharedPreferences("My_terms_flag", MODE_PRIVATE);
        termsflag = prefs.getBoolean("Flag",false);
        if(termsflag){
            terms.setVisibility(View.INVISIBLE);
        }

        adaptadorActividades = new ActividadAdapter(this,listaActividades);
        LvActividades.setAdapter(adaptadorActividades);
        ActByUserFlag = mc.getFlagActByUser();
        lista = mc.getIDLiscaAcc();






        termsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                terms.setEnabled(false);
                terms.setVisibility(View.INVISIBLE);
                SharedPreferences.Editor editor = getSharedPreferences("My_terms_flag", MODE_PRIVATE).edit();
                editor.putBoolean("Flag", true);
                editor.apply();
            }
        });





        contacs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("contactos","Abriendo contactos");
                Intent intent = new Intent(getApplicationContext(), ContactosList.class);
                startActivity(intent);
            }
        });


        LvActividades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Actividad act = listaActividades.get(position);
                Log.i("ACT", "click en el elemento " + position + " de mi ListView");
                mc.setSelectecActivity(act);
                Intent intent = new Intent(getApplicationContext(), Actividad_Detalle.class);
                startActivity(intent);
            }
        });


        arraySpinnerCategory = new String[] {
                "Todos", "Sedes", "Tipo Evento", "Organizador"
        };

        ArrayAdapter<String> adapterCategory = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arraySpinnerCategory);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categories.setAdapter(adapterCategory);


        footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AcercaDe.class);
                startActivity(intent);
            }
        });

        categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                cagtegoryid = position;
                setCategory(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });


        filter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                filterid = position;
                if (ActByUserFlag){
                    setFilterAct(mc.getSelectecContact().getID());
                    categories.setVisibility(View.INVISIBLE);
                    filter.setVisibility(View.INVISIBLE);
                    contacs.setVisibility(View.INVISIBLE);
                    t1.setVisibility(View.INVISIBLE);
                    t2.setVisibility(View.INVISIBLE);
                    footer.setVisibility(View.INVISIBLE);
                }else {
                    if(cagtegoryid == 3){
                        setFilterAct(lista[position]);
                    }else {
                        setFilter(position);
                    }


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mc.setFlagActByUser(false);
    }


    private void setCategory(int i){
        switch(i) {
            case 0:
                arraySpinnerFilter = new  String[] {
                        "Todos"
                };
                break;
            case 1:
                arraySpinnerFilter = new  String[] {
                        "Cartago","San José","San Carlos","Limón","Alajuela"
                };
                break;
            case 2:
                arraySpinnerFilter = new  String[] {
                        "Cultural","Deportiva","Educativa","Ludica","Otros"
                };
                break;
            case 3:
                arraySpinnerFilter = mc.getLiscaAcc();
                break;

        }

        ArrayAdapter<String> adapterFilter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arraySpinnerFilter);
        adapterFilter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filter.setAdapter(adapterFilter);
    }

    private void setFilter(int i){
                listaActividades.clear();
                listaActividades.addAll(mc.getListActivities(cagtegoryid,i));
                adaptadorActividades.notifyDataSetChanged();
                if(mc.getListActivities(cagtegoryid,i).size()==0){
                    Toast.makeText(getApplicationContext(),"¡No existen eventos con estas caracteristicas!",Toast.LENGTH_LONG).show();
                }


    }

    private void setFilterAct(String ID){
        listaActividades.clear();
        listaActividades.addAll(mc.getLista_Actividadesbyaccount(ID));
        adaptadorActividades.notifyDataSetChanged();
        if(mc.getLista_Actividadesbyaccount(ID).size()==0){
            Toast.makeText(getApplicationContext(),"¡No existen eventos con estas caracteristicas!",Toast.LENGTH_LONG).show();
        }

    }


}
