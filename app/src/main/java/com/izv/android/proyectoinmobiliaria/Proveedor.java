package com.izv.android.proyectoinmobiliaria;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by Ivan on 26/01/2015.
 */
public class Proveedor extends ContentProvider {
    private Ayudante abd;
    static String AUTORIDAD = "com.izv.android.proyectoinmobiliaria";
    private static final UriMatcher convierteUri2Int;
    private static final int INMUEBLES = 1;
    private static final int INMUEBLE_ID = 2;

    static {
        convierteUri2Int = new UriMatcher(UriMatcher.NO_MATCH);
        convierteUri2Int.addURI(AUTORIDAD,Contrato.TablaInm.TABLA, INMUEBLES);
        convierteUri2Int.addURI(AUTORIDAD,Contrato.TablaInm.TABLA+ "/#", INMUEBLE_ID);
    }

    public Proveedor() {
    }

    @Override
    public boolean onCreate() {
        abd = new Ayudante(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch(convierteUri2Int.match(uri)) {
            case INMUEBLES: break;
            case INMUEBLE_ID:
                //Opción 1
                // selection = selection + Contrato.TablaJugador._ID + " = "  +
                //   uri.getLastPathSegment();
                //Opción 2
                selection = Contrato.TablaInm._ID + " = ? ";
                selectionArgs = new String[]{uri.getLastPathSegment()};
                break;
            default:
                throw new IllegalArgumentException("URI " + uri);
        }

        //Hemos ignorado la URI -> pendiente
        SQLiteDatabase bd = abd.getReadableDatabase();

        Cursor cursor = bd.query(Contrato.TablaInm.TABLA,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        if(convierteUri2Int.match(uri) != INMUEBLES){
            throw new IllegalArgumentException("URI " + uri);
        }
        SQLiteDatabase  bd = abd.getWritableDatabase();
        long id = bd.insert(Contrato.TablaInm.TABLA, null, values);
        if(id > 0) {
            Uri uriElemento = ContentUris.withAppendedId(Contrato.TablaInm.CONTENT_URI, id);
            getContext().getContentResolver().notifyChange(uriElemento,null);
            return uriElemento;
        }
        return null;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db= abd.getWritableDatabase();
        switch(convierteUri2Int.match(uri)) {
            case INMUEBLES: break;
            case INMUEBLE_ID:
                selection = Contrato.TablaInm._ID + " = ? ";
                selectionArgs = new String[]{uri.getLastPathSegment()};
                break;
            default:throw new IllegalArgumentException("URI " + uri);
        }
        int cuenta = db.delete(Contrato.TablaInm.TABLA, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cuenta;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        SQLiteDatabase db= abd.getWritableDatabase();
        int cuenta;
        switch(convierteUri2Int.match(uri)) {
            case INMUEBLE_ID:
                selection = Contrato.TablaInm._ID + " = ? ";
                selectionArgs = new String[]{uri.getLastPathSegment()};
                break;
            case INMUEBLES:
                break;
            default:
                throw new IllegalArgumentException("URI " + uri);
        }
        cuenta = db.update(
                Contrato.TablaInm.TABLA, values, selection,
                selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cuenta;
    }

    @Override
    public String getType(Uri uri) {
        switch(convierteUri2Int.match(uri)) {
            case INMUEBLES:
                return Contrato.TablaInm.CONTENT_TYPE_INMUEBLES;
            case INMUEBLE_ID:
                return Contrato.TablaInm.CONTENT_TYPE_INMUEBLE_ID;
            default:
                return null;
        }
    }

}
