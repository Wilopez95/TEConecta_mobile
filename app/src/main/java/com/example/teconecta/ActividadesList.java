package com.example.teconecta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
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

    private ArrayList<Actividad> listaActividades = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LvActividades = findViewById(R.id.ListViewActividades);
        mc =  MainController.getInstance();
        mc.setActiveContex(this);
        mc.getData();

        //DEV TEST DELETE ON PRODUCTION!!
        //Llamar funcion de llenado del controlador
        mc.filltest();
        //DEV TEST DELETE ON PRODUCTION!!


        adaptadorActividades = new ActividadAdapter(this,listaActividades);
        LvActividades.setAdapter(adaptadorActividades);

        categories = findViewById(R.id.spinner_categories);
        filter = findViewById(R.id.spinner_selection);

        contacs= findViewById(R.id.Contacs);


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
                "Todos", "Sedes", "Tipo", "Categoria"
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
                        "Deportiva","Ludica","Cultura","Educativa"
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
