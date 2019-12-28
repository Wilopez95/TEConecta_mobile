package com.example.teconecta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Actividad_Detalle extends AppCompatActivity {
    private TextView name;
    private TextView time;
    private TextView location;
    private TextView type;
    private TextView detail;

    private ImageButton register;
    private ImageButton notificate;

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

        register = findViewById(R.id.register_button);
        notificate = findViewById(R.id.notification_button);

        this.thisActividad = mc.getSelectecActivity();

        name.setText(thisActividad.getNombre().toUpperCase());
        time.setText(thisActividad.getHoraI()+" - "+thisActividad.getHoraF());
        location.setText(thisActividad.getLugar()+" - "+thisActividad.getSede());
        type.setText(thisActividad.getTipo());
        detail.setText(thisActividad.getDescripcion());

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Se va a registar", Toast.LENGTH_SHORT).show();
            }
        });

        notificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "¡Se le notificara 30 minutos previo a este evento!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
