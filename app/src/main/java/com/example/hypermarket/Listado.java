package com.example.hypermarket;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class Listado extends AppCompatActivity {

    SQLHelper sqlHelper;
    Adaptador adapter;
    ArrayList<ListadoCompras> lista;
    ListadoCompras listadoCompras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        sqlHelper = new SQLHelper(Listado.this);
        lista = sqlHelper.verTodo();
        adapter = new Adaptador(this, lista, sqlHelper);
        ListView list = findViewById(R.id.ListV);

        Button insertar = findViewById(R.id.btnInsertar);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //dialogo para ver vista previa de registro
                int posicion = i;
                ListadoCompras contacto = sqlHelper.verUno(posicion);
            }
        });

        Button maps = findViewById(R.id.btnMaps);
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goMaps(v);
            }
        });

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dialogo para agregar
                Dialog dialog = new Dialog(Listado.this);
                dialog.setTitle("Nuevo Registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialogo);
                dialog.show();

                final EditText nombre = dialog.findViewById(R.id.edit_Nombre);
                final EditText categoria = dialog.findViewById(R.id.edit_Categoria);
                final EditText precio = dialog.findViewById(R.id.edit_Precio);
                final EditText cantidad = dialog.findViewById(R.id.edit_Cantidad);
                final EditText ubicacion = dialog.findViewById(R.id.edit_Ubicacion);
                final EditText codbarra = dialog.findViewById(R.id.edit_CodBarra);

                Button guardar = dialog.findViewById(R.id.btnAgregarDia);
                guardar.setText("Agregar");

                Button cancelar = dialog.findViewById(R.id.btnCancelarDia);

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nombreX = nombre.getText().toString();
                        String cataegoriaX = categoria.getText().toString();
                        String precioX = precio.getText().toString();
                        String cantidadX = cantidad.getText().toString();
                        String ubicacionX = ubicacion.getText().toString();
                        String codBarraX = codbarra.getText().toString();

                        // Validar que al menos un campo tenga información
                        if (!nombreX.isEmpty() || !cataegoriaX.isEmpty() || !precioX.isEmpty() || !cantidadX.isEmpty() || !ubicacionX.isEmpty() || !codBarraX.isEmpty()){
                            listadoCompras = new ListadoCompras(nombreX, cataegoriaX, precioX, cantidadX, ubicacionX, codBarraX);
                            sqlHelper.insertar(listadoCompras);
                            lista = sqlHelper.verTodo();
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }else {
                            Toast.makeText(getApplicationContext(), "Por favor, complete al menos un campo.", Toast.LENGTH_SHORT).show();
                        }
                  }
                });
                   cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss(); // Cierra el diálogo
                    }
                });
            }
        });
    }
    public void goMaps (View view){
        Intent intent = new Intent(this, Maps.class);
        startActivity(intent);
    }
}
