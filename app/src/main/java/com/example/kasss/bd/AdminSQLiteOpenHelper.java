package com.example.kasss.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by USUARIO on 14/05/2015.
 */
    public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

        // Creamos el constructor
        public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // Se crea la tabla
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table materias (id_materia integer primary key unique,nombre text unique, creditos text, semestre text) ");
        }

        // borrar la tabla y crear la nueva tabla
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists materias");
            db.execSQL("create table materias (id_materia integer primary key unique, nombre text unique, creditos text, semestre text) ");
        }
    }
