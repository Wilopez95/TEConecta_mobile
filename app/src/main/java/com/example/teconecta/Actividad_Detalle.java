package com.example.teconecta;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
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
    private ImageView imagen;

    private ImageButton register;
    private ImageButton notificate;

    private LinearLayout contac;

    private Actividad thisActividad;

    private String fk_cuenta;
    private Contacto contacto;

    NotificationCompat.Builder notificacion;
    private static final int idUnica = 123456;


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

        imagen = findViewById(R.id.imageView3);

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

        notificacion = new NotificationCompat.Builder(this);
        notificacion.setAutoCancel(true);



        Picasso.get()
                .load(thisActividad.getUrlImagen())
                .placeholder(R.drawable.defaultimg)
                .error(R.drawable.defaultimg)
                .into(imagen);


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

                String[] dateArr = thisActividad.getFecha().split("T", 2);
                if (!mc.isToday(dateArr[0])){
                    Toast.makeText(getApplicationContext(),"El dia del evento podras activar una notificación",Toast.LENGTH_LONG).show();
                }else {
                    String Hora=thisActividad.getHoraI();
                    String[] timeArr = Hora.split(":", 3);
                    Log.d("TIME0", Hora);
                    Calendar c = Calendar.getInstance();
                    Log.d("TIME1", c.getTime().toString());

                    c.set(Calendar.HOUR_OF_DAY,Integer.parseInt(timeArr[0]));
                    c.set(Calendar.MINUTE,Integer.parseInt(timeArr[1]));
                    c.set(Calendar.SECOND,0);
                    c.add(Calendar.MINUTE,-30);
                    Log.d("TIME2", c.getTime().toString());
                    Toast.makeText(getApplicationContext(),"Se te notificara 30 minutos antes de este evento",Toast.LENGTH_LONG).show();
                    startAlarm(c);
                }



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

    private void startAlarm(Calendar c){
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent= new  Intent(this,AlertReciver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1 ,intent,0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
    }
    
}
