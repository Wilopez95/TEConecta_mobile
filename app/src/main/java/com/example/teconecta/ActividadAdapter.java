package com.example.teconecta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ActividadAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Actividad> listaActividades;
    private MainController mc =  MainController.getInstance();

    public ActividadAdapter(Context context, ArrayList<Actividad> listaActividades) {
        this.context = context;
        this.listaActividades = listaActividades;
    }

    @Override
    public int getCount() {
        return listaActividades.size();
    }

    @Override
    public Object getItem(int position) {
        return listaActividades.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Actividad Item = (Actividad) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.actividad,null);
        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView nombre = convertView.findViewById(R.id.nombre);
        TextView hora = convertView.findViewById(R.id.hora);
        TextView ubicacion = convertView.findViewById(R.id.ubicacion);
        TextView tipo = convertView.findViewById(R.id.tipo);
        TextView date = convertView.findViewById(R.id.date);


        nombre.setText(Item.getNombre().toUpperCase());
        hora.setText(Item.getHoraI()+" - "+Item.getHoraF());
        ubicacion.setText(Item.getLugar());
        tipo.setText(Item.getTipo());
        date.setText(mc.formatedDate(" ",Item.getFecha()));
        return convertView;
    }


}
