package com.example.hypermarket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class SQLHelper {

    SQLiteDatabase bd;//Intancea la base de datos
    ArrayList<ListadoCompras> lista = new ArrayList<ListadoCompras>();//almacena una lista de objetos de tipo contacto
    ListadoCompras claseCompras;//Instncia de la clase contacto, utilizada para operaciones de busqueda
    Context context; //contexto de la aplicacion
    String nombreBD = "BDCompras";//nombre de la base de datos

    //Cadena de sentencia sql que crea  la tabla de ListaCompras si no existe, define las columnas de las tablas
    String tabla = "CREATE TABLE IF NOT EXISTS tablacompras(id integer PRIMARY KEY AUTOINCREMENT, nombre text, categoria text, precio text, cantidad text, ubicacion text, codbarra text)";

    public SQLHelper (Context c){
        //contructor de la clase, acepta un parametro de tipo context y se utiliza para inicializar la clase
        this.context=c;
        bd=c.openOrCreateDatabase(nombreBD,Context.MODE_PRIVATE,null);
        bd.execSQL(tabla);
    }

    public boolean insertar (ListadoCompras listadoCompras){
        //metodo para insertar un nuevo contacto a la base de datos
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombre",listadoCompras.getNombre());
        contenedor.put("categoria",listadoCompras.getCategoria());
        contenedor.put("precio",listadoCompras.getPrecio());
        contenedor.put("cantidad",listadoCompras.getCantidad());
        contenedor.put("ubicacion",listadoCompras.getUbicacion());
        contenedor.put("codbarra",listadoCompras.getCodBarra());
        return (bd.insert("tablacompras",null, contenedor))>0;
    }
    public boolean eliminar (int id){
    return (bd.delete("tablacompras","id=" + id, null))>0;
    }
    public boolean editar (ListadoCompras listadoCompras){
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombre",listadoCompras.getNombre());
        contenedor.put("categoria",listadoCompras.getCategoria());
        contenedor.put("precio",listadoCompras.getPrecio());
        contenedor.put("cantidad",listadoCompras.getCantidad());
        contenedor.put("ubicacion",listadoCompras.getUbicacion());
        contenedor.put("codbarra",listadoCompras.getCodBarra());
        return (bd.update("tablacompras",contenedor,"id=" + listadoCompras.getId(),null))>0;
    }
    public ArrayList<ListadoCompras>verTodo(){
        //metodo para recurar una lista de contactos de una base de datos
        lista.clear();
        Cursor cursor= bd.rawQuery("SELECT * FROM tablacompras",null);
        if(cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                lista.add(new ListadoCompras(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4),
                        cursor.getString(5), cursor.getString(6)));
            }while (cursor.moveToNext());
        }
        return lista;
    }
    public ListadoCompras verUno (int posicion){
        //metodo para buscar el contacto segun su id en la base de datos y devolverlo como un obejto contacto
        Cursor cursor = bd.rawQuery("SELECT * FROM tablacompras",null);
        cursor.moveToPosition(posicion);
        claseCompras = new ListadoCompras(cursor.getInt(0),
                cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4),
                cursor.getString(5), cursor.getString(6));
        return claseCompras;
    }


}
