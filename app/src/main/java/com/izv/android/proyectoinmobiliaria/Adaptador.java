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

/*
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if(convertView == null){
            convertView = i.inflate(recurso, null);


            vh = new ViewHolder();
            vh.tv1 = (TextView)convertView.findViewById(R.id.tvDireccion);
            vh.tv2 = (TextView)convertView.findViewById(R.id.tvTipo);
            vh.tv3 = (TextView)convertView.findViewById(R.id.tvPrecio);
            vh.iv = (ImageView)convertView.findViewById(R.id.ivFtipo);

            convertView.setTag(vh);
        }else{
            vh = (ViewHolder)convertView.getTag();
        }

        vh.position = position;
        vh.tv1.setTag(position);
        //Log.v("LOG",vh.tv1.getTag().toString());
        vh.tv1.setText(lista.get(position).getDireccion());
        vh.tv2.setText(lista.get(position).getTipo());
        vh.tv3.setText(""+lista.get(position).getPrecio()+" €");

        if(lista.get(position).getTipo().equals("Casa")) {
            vh.iv.setImageResource(R.drawable.house);
        }else if(lista.get(position).getTipo().equals("Piso")) {
            vh.iv.setImageResource(R.drawable.flat);
        }else if(lista.get(position).getTipo().equals("Cochera")) {
            vh.iv.setImageResource(R.drawable.garage);
        }else if(lista.get(position).getTipo().equals("Habitación")) {
            vh.iv.setImageResource(R.drawable.room);
        }
        vh.iv.setTag(position);

        return convertView;
    }*/
}