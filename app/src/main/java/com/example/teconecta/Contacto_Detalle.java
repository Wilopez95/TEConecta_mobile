package com.example.teconecta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Contacto_Detalle extends AppCompatActivity {
    private TextView name;
    private TextView description;
    private TextView phone;
    private  TextView location;
    private  TextView place;
    private  TextView email;
    private  TextView manager;
    private Contacto thisContacto;
    private ImageButton boton;
    private ImageView imagen;

    private MainController mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_detalle);


        mc =  MainController.getInstance();
        thisContacto = mc.getSelectecContact();

        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        phone = findViewById(R.id.phone);
        location = findViewById(R.id.location);
        place = findViewById(R.id.place);
        email = findViewById(R.id.email);
        manager = findViewById(R.id.manager);
        boton = findViewById(R.id.imageButton2);
        imagen = findViewById(R.id.imagen);
        name.setText(thisContacto.getNombre());
        description.setText(thisContacto.getDescripcion());
        phone.setText("Teléfono : "+thisContacto.getTelefono());
        location.setText("Ubicacion : "+thisContacto.getDireccion());
        place.setText("Sede : "+thisContacto.getSede());
        manager.setText("Encargado : "+thisContacto.getEncargado());

        if (!thisContacto.getUrlImgPerfil().isEmpty()){
            Picasso.get()
                    .load(thisContacto.getUrlImgPerfil())
                    .placeholder(R.drawable.default_user)
                    .error(R.drawable.default_user)
                    .into(imagen);
        }


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mc.setFlagActByUser(true);
                Intent intent = new Intent(getApplicationContext(), ActividadesList.class);
                startActivity(intent);
            }
        });

    }
}
