package com.example.teconecta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ContactosList extends AppCompatActivity {
    private ListView LvContactos;
    private ContactoAdapter AdaptadorContacto;
    private MainController mc;


    private ArrayList<Contacto> listaContactos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactos_list);

        LvContactos = findViewById(R.id.ListViewContactos);
        mc =  MainController.getInstance();

        AdaptadorContacto = new ContactoAdapter(this,listaContactos);
        LvContactos.setAdapter(AdaptadorContacto);

        this.listaContactos.addAll(mc.getListContac());

        LvContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Click", "click en el elemento " + position + " de mi ListView");
                Intent intent = new Intent(getApplicationContext(), Contacto_Detalle.class);
                startActivity(intent);
            }
        });
    }
}