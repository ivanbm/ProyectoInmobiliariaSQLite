package com.izv.android.proyectoinmobiliaria;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Ivan on 29/11/2014.
 */
public class Adaptador extends CursorAdapter {

    private ArrayList<Vendedor> lista;
    private Context contexto;
    private int recurso;
    private static LayoutInflater i;


    public Adaptador(Context context, Cursor c) {
        super(context, c, true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater i = LayoutInflater.from(parent.getContext());
        View v = i.inflate(R.layout.detalle, parent, false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv1, tv2, tv3;
        ImageView iv;
        iv = (ImageView)view.findViewById(R.id.ivFtipo);
        tv1 = (TextView)view.findViewById(R.id.tvDireccion);
        tv2 = (TextView)view.findViewById(R.id.tvTipo);
        tv3 = (TextView)view.findViewById(R.id.tvPrecio);
        Vendedor j = GestorInmobiliaria.getRow(cursor);
        tv1.setText(j.getDireccion());
        tv2.setText(j.getTipo()+"");
        tv3.setText(""+j.getPrecio());

        if(j.getTipo().equals("Casa")) {
            iv.setImageResource(R.drawable.house);
        }else if(j.getTipo().equals("Piso")) {
            iv.setImageResource(R.drawable.flat);
        }else if(j.getTipo().equals("Cochera")) {
            iv.setImageResource(R.drawable.garage);
        }else if(j.getTipo().equals("Habitación")) {
            iv.setImageResource(R.drawable.room);
        }
    }
}