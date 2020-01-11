package com.example.teconecta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Actividad_Detalle extends AppCompatActivity {
    private TextView name;
    private TextView time;
    private TextView location;
    private TextView type;
    private TextView detail;
    private TextView user;
    private TextView registertxt;
    private TextView date;

    private ImageButton register;
    private ImageButton notificate;

    private LinearLayout contac;

    private Actividad thisActividad;

    private String fk_cuenta;
    private Contacto contacto;

    NotificationCompat.Builder notificacion;

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
        user = findViewById(R.id.user);
        registertxt= findViewById(R.id.registertxt);

        date = findViewById(R.id.date);
        register = findViewById(R.id.register_button);
        notificate = findViewById(R.id.notification_button);

        contac = findViewById(R.id.Contact);

        this.thisActividad = mc.getSelectecActivity();

        name.setText(thisActividad.getNombre().toUpperCase());
        time.setText(thisActividad.getHoraI()+" - "+thisActividad.getHoraF());
        location.setText(thisActividad.getLugar()+" - "+thisActividad.getSede());
        type.setText(thisActividad.getTipo());
        detail.setText(thisActividad.getDescripcion());
        this.fk_cuenta = thisActividad.getFKCuenta();
        contacto = mc.getContactobyID(fk_cuenta);
        user.setText(contacto.getNombre());
        date.setText(mc.formatedDate("\n",thisActividad.getFecha()));


        if(!thisActividad.getAsistencia()){
            registertxt.setVisibility(View.INVISIBLE);
            register.setVisibility(View.INVISIBLE);
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Se va a registar", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Actividad_Register.class);
                startActivity(intent);
            }
        });

        notificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "¡Se le notificara 30 minutos previo a este evento!", Toast.LENGTH_SHORT).show();
            }
        });

        contac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mc.setSelectecContact(contacto);
                Intent intent = new Intent(getApplicationContext(), Contacto_Detalle.class);
                startActivity(intent);
            }
        });

    }

}
