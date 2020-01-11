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

import java.util.ArrayList;

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


    private RelativeLayout terms;


    private ArrayList<Actividad> listaActividades = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LvActividades = findViewById(R.id.ListViewActividades);
        mc =  MainController.getInstance();
        mc.setActiveContex(this);






        adaptadorActividades = new ActividadAdapter(this,listaActividades);
        LvActividades.setAdapter(adaptadorActividades);

        categories = findViewById(R.id.spinner_categories);
        filter = findViewById(R.id.spinner_selection);

        contacs= findViewById(R.id.Contacs);

        terms = findViewById(R.id.terms);
        termsButton = findViewById(R.id.ternsButton);

        SharedPreferences prefs = getSharedPreferences("My_terms_flag", MODE_PRIVATE);
        termsflag = prefs.getBoolean("Flag",false);
        if(termsflag){
            terms.setVisibility(View.INVISIBLE);
        }






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
                setFilter(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
                        "Cartago","San Jose","San Carlos","Limon","Alajuela"
                };
                break;
            case 2:
                arraySpinnerFilter = new  String[] {
                        "Cultural","Deportiva","Educativa","Ludica","Otros"
                };
                break;
            case 3:
                arraySpinnerFilter = new  String[] {
                        "Grupos culturales","Asociación","Grupo de interes"
                };
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

    }
}
