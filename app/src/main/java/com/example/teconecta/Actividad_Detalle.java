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

    private MainController mc;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad__detalle);

        mc =  MainController.getInstance();

        name = findViewById(R.id.name);
        time = findViewById(R.id.time);
        location = findViewById(R.id.location);
        type = findViewById(R.id.type);
        detail = findViewById(R.id.details);

        this.thisActividad = mc.getSelectecActivity();

        name.setText(thisActividad.getNombre());
        time.setText(thisActividad.getHoraI()+" - "+thisActividad.getHoraF());
        location.setText(thisActividad.getLugar()+" - "+thisActividad.getSede());
        type.setText(thisActividad.getTipo());
        detail.setText(thisActividad.getDescripcion());


    }
}
