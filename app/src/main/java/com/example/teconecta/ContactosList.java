package com.example.teconecta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ContactosList extends AppCompatActivity {
    private ListView LvContactos;
    private ContactoAdapter AdaptadorContacto;
    private MainController mc;
    private EditText find;


    private ArrayList<Contacto> listaContactos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactos_list);

        LvContactos = findViewById(R.id.ListViewContactos);
        mc =  MainController.getInstance();
        mc.setActiveContex(this);

        find = findViewById(R.id.findText);



        this.listaContactos.addAll(mc.getListContac());


        AdaptadorContacto = new ContactoAdapter(this,listaContactos);
        LvContactos.setAdapter(AdaptadorContacto);

        find.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Log.d("RESPONSE", s.toString());
                onTextChange(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        LvContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Click", "click en el elemento " + position + " de mi ListView");
                Contacto ctc = listaContactos.get(position);
                mc.setSelectecContact(ctc);
                Intent intent = new Intent(getApplicationContext(), Contacto_Detalle.class);
                startActivity(intent);
            }
        });
    }

    private void onTextChange(String s){
        listaContactos.clear();
        listaContactos.addAll(mc.getListContacsFilter(s));
        AdaptadorContacto.notifyDataSetChanged();

    }
}
