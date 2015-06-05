package com.example.kasss.bd;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;




public class MainActivity extends ActionBarActivity {
    EditText et_idmateria, et_nombre, et_creditos, et_semestre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_idmateria = (EditText) findViewById(R.id.et_idmateria);
        et_nombre = (EditText) findViewById(R.id.et_nombre);
        et_creditos = (EditText) findViewById(R.id.et_creditos);
        et_semestre = (EditText) findViewById(R.id.et_semestre);

    }
    public void alta (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "materia", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String idmateria = et_idmateria.getText().toString();
        String nombre = et_nombre.getText().toString();
        String creditos = et_creditos.getText().toString();
        String semestre = et_semestre.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("id_materia", idmateria);
        registro.put("nombre", nombre);
        registro.put("creditos", creditos);
        registro.put("semestre", semestre);

        bd.insert("materias", null, registro);
        bd.close();

        et_idmateria.setText("");
        et_nombre.setText("");
        et_creditos.setText("");
        et_semestre.setText("");

        Toast.makeText(this,"Se agrego una nueva materia",Toast.LENGTH_SHORT).show();

    }
    public void consulta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "materia", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String idmateria = et_idmateria.getText().toString();
        Cursor fila = bd.rawQuery("select nombre, creditos, semestre from materias where id_materia=" + idmateria, null);
        if (fila.moveToFirst()) {
            et_nombre.setText(fila.getString(0));
            et_creditos.setText(fila.getString(1));
            et_semestre.setText(fila.getString(2));
        } else {
            Toast.makeText(this,"No existe la materia",Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }

    public void baja(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "materia", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String idmateria = et_idmateria.getText().toString();
        int cant = bd.delete("materias","id_materia=" + idmateria, null);
        bd.close();

        et_idmateria.setText("");
        et_nombre.setText("");
        et_creditos.setText("");
        et_semestre.setText("");

        if (cant == 1) {
            Toast.makeText(this, "Se borró la materia",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe la materia",Toast.LENGTH_SHORT).show();
        }
    }

    public void modificacion (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "materia", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String idmateria = et_idmateria.getText().toString();
        String nombre = et_nombre.getText().toString();
        String creditos = et_creditos.getText().toString();
        String semestre = et_semestre.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("id_materia", idmateria);
        registro.put("nombre", nombre);
        registro.put("creditos", creditos);
        registro.put("semestre", semestre);

        int cant = bd.update("materias", registro, "id_materia=" + idmateria, null);
        bd.close();

        if (cant == 1) {
            Toast.makeText(this, "Se modificaron los datos de la materia",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe la materia",Toast.LENGTH_SHORT).show();
        }

    }

    public void limpia (View v){
        et_idmateria.setText("");
        et_nombre.setText("");
        et_creditos.setText("");
        et_semestre.setText("");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}