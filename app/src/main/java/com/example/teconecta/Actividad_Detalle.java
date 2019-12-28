package com.example.teconecta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Actividad_Detalle extends AppCompatActivity {
    private TextView name;
    private TextView time;
    private TextView location;
    private TextView type;
    private TextView detail;

    private Actividad thisActividad;


    private String id;
    private String nombre;
    private String descripcion;
    private String fecha;
    private String lugar;
    private String tipo;
    private String Sede;
    private String urlImagen;
    private String horaI;
    private String horaF;
    private String id_user;
    private boolean asistencia;
    private String estado;
    private int cupo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad__detalle);

        name = findViewById(R.id.name);
        time = findViewById(R.id.time);
        location = findViewById(R.id.location);
        type = findViewById(R.id.type);
        detail = findViewById(R.id.details);


        Intent intent = this.getIntent();
        thisActividad = new Actividad(intent.getStringExtra("id"),intent.getStringExtra("name"),intent.getStringExtra("description"),intent.getStringExtra("date"),
                intent.getStringExtra("location"),intent.getStringExtra("type"),intent.getStringExtra("place"),intent.getStringExtra("urlImagen"),
                intent.getStringExtra("timeI"),intent.getStringExtra("timeF"),intent.getStringExtra("id_user"),intent.getStringExtra("state"),
                intent.getBooleanExtra("assistance",false),intent.getIntExtra("space",0));

        name.setText(thisActividad.getNombre());
        time.setText(thisActividad.getHoraI()+" - "+thisActividad.getHoraF());
        location.setText(thisActividad.getLugar()+" - "+thisActividad.getSede());
        type.setText(thisActividad.getTipo());
        detail.setText(thisActividad.getDescripcion());


    }
}
