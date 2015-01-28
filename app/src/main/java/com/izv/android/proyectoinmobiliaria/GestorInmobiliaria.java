package com.izv.android.proyectoinmobiliaria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 24/01/2015.
 */
public class GestorInmobiliaria {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorInmobiliaria(Context c) {
        abd = new Ayudante(c);
    }
    public void open() {
        bd = abd.getWritableDatabase();
    }
    public void openRead() {
        bd = abd.getReadableDatabase();
    }
    public void close() {
        abd.close();
    }

    public long insert(Vendedor objeto) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaInm.DIRECCION, objeto.getDireccion());
        valores.put(Contrato.TablaInm.TIPO, objeto.getTipo());
        valores.put(Contrato.TablaInm.PRECIO, objeto.getPrecio());
        valores.put(Contrato.TablaInm.SUBIDO, 1);
        long id = bd.insert(Contrato.TablaInm.TABLA, null, valores);
        //id es el código autonumérico
        return id;
    }

    public int update(Vendedor objeto) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaInm.DIRECCION, objeto.getDireccion());
        valores.put(Contrato.TablaInm.TIPO, objeto.getTipo());
        valores.put(Contrato.TablaInm.PRECIO, objeto.getPrecio());
        String condicion = Contrato.TablaInm._ID + " = ?";
        String[] argumentos = { objeto.getId() + "" };
        int cuenta = bd.update(Contrato.TablaInm.TABLA, valores, condicion, argumentos);
        return cuenta;
    }

    public int delete(Vendedor objeto) {
        String condicion = Contrato.TablaInm._ID + " = ?";
        String[] argumentos = { objeto.getId() + "" };
        int cuenta = bd.delete(Contrato.TablaInm.TABLA, condicion,argumentos);
        return cuenta;
    }

    public int delete(int id){
        return delete(new Vendedor(id,null,null,0));
    }

    public List<Vendedor> select(String condicion, String[] parametros, String orden) {
        List<Vendedor> lo = new ArrayList<Vendedor>();
        Cursor cursor = bd.query(Contrato.TablaInm.TABLA, null, condicion, parametros, null, null, orden);
        /*
        String[] campos = {"nombre", "valoracion"};
        String[] parametros = {"Pepe", "6"};
        bd.query("tabla", campos, "nombre = ? and valoracion = ?", parametros, "groupBy", "having", "orderBy");
        //select nombre, valoracion from tabla
        //where nombre = ? and valoracion = ?
        //...
        */
        cursor.moveToFirst();
        Vendedor objeto;
        while (!cursor.isAfterLast()) {
            objeto = getRow(cursor);
            lo.add(objeto);
            cursor.moveToNext();
        }
        cursor.close();
        return lo;
    }

    public ArrayList<Vendedor> select() {
        ArrayList<Vendedor> lo = new ArrayList<Vendedor>();
        Cursor cursor = bd.query(Contrato.TablaInm.TABLA, null, null, null, null, null, null);
        cursor.moveToFirst();
        Vendedor objeto;
        while (!cursor.isAfterLast()) {
            objeto = getRow(cursor);
            lo.add(objeto);
            cursor.moveToNext();
        }
        cursor.close();
        return lo;
    }

    public static Vendedor getRow(Cursor c) {
        Vendedor objeto = new Vendedor();
        objeto.setId(c.getInt(0));
        objeto.setDireccion(c.getString(1));
        objeto.setTipo(c.getString(2));
        objeto.setPrecio(c.getDouble(3));
        return objeto;
    }

    public Cursor getCursor() {
        Cursor cursor = bd.query(Contrato.TablaInm.TABLA, null, null, null, null, null, null);
        return cursor;
    }
}
