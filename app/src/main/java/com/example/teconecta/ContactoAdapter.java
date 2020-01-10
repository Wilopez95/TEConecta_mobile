package com.example.teconecta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Contacto> listaContactos;

    public ContactoAdapter(Context context ,ArrayList<Contacto> listaContactos){
        this.context = context;
        this.listaContactos =  listaContactos;
    }

    @Override
    public int getCount() {
        return listaContactos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaContactos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contacto Item = (Contacto)getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.contacto,null);
        TextView nombre = convertView.findViewById(R.id.name);
        TextView telefono= convertView.findViewById(R.id.phone);

        nombre.setText(Item.getID());
        telefono.setText(Item.getTelefono());
        return convertView;
    }
}
