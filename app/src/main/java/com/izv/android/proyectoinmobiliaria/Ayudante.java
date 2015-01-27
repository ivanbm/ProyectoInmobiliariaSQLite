package com.izv.android.proyectoinmobiliaria;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ivan on 24/01/2015.
 */
public class Ayudante extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "inmobiliaria.db";
    public static final int DATABASE_VERSION = 1;

    public Ayudante(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        sql="create table "+Contrato.TablaInm.TABLA+
                " ("+ Contrato.TablaInm._ID+
                " integer primary key autoincrement, "+
                Contrato.TablaInm.DIRECCION+" text, "+
                Contrato.TablaInm.TIPO+" text, "+
                Contrato.TablaInm.PRECIO+" double, "+
                Contrato.TablaInm.SUBIDO+" int)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="drop table if exists " + Contrato.TablaInm.TABLA;
        db.execSQL(sql);
        onCreate(db);
    }
}
