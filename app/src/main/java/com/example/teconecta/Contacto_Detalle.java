package com.example.teconecta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Contacto_Detalle extends AppCompatActivity {
    private TextView name;
    private TextView description;
    private TextView phone;
    private  TextView location;
    private  TextView place;
    private  TextView email;
    private  TextView manager;

    private MainController mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_detalle);


        mc =  MainController.getInstance();

        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        phone = findViewById(R.id.phone);
        location = findViewById(R.id.location);
        place = findViewById(R.id.place);
        email = findViewById(R.id.email);
        manager = findViewById(R.id.manager);


    }
}
