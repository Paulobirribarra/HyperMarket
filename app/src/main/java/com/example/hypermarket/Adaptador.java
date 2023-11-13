package com.example.hypermarket;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    ArrayList<ListadoCompras> lista;
    SQLHelper sqlHelper;
    ListadoCompras listadoCompras;
    Activity activity;
    private int id = 0;

    public Adaptador(Activity activity, ArrayList<ListadoCompras> lista, SQLHelper sqlHelper) {
        this.activity = activity;
        this.sqlHelper = sqlHelper;
        this.lista = lista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        listadoCompras = lista.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        listadoCompras = lista.get(position);
        return listadoCompras.getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item, null);
        }

        listadoCompras = lista.get(position);

        TextView nombre = view.findViewById(R.id.txtNombre);
        TextView categoria = view.findViewById(R.id.txtCategoria);
        TextView precio = view.findViewById(R.id.txtPrecio);
        TextView cantidad = view.findViewById(R.id.txtCantidad);
        TextView ubicacion = view.findViewById(R.id.txtUbicacion);
        TextView codbarra = view.findViewById(R.id.txtCodBarra);

        Button editar = view.findViewById(R.id.btnEditar);
        Button detalles = view.findViewById(R.id.btnDetalles);
        Button eliminar = view.findViewById(R.id.btnEliminar);

        nombre.setText(listadoCompras.getNombre());
        categoria.setText(listadoCompras.getCategoria());
        precio.setText(listadoCompras.getPrecio());
        cantidad.setText(listadoCompras.getCantidad());
        ubicacion.setText(listadoCompras.getUbicacion());
        codbarra.setText(listadoCompras.getCodBarra());

        editar.setTag(position);
        detalles.setTag(position);
        eliminar.setTag(position);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(v.getTag().toString());
                final Dialog dialog = new Dialog(activity);
                dialog.setTitle("Editar registro");
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
                Button cancelar = dialog.findViewById(R.id.btnCancelarDia);

                listadoCompras = lista.get(pos);
                setId(listadoCompras.getId());
                nombre.setText(listadoCompras.getNombre());
                categoria.setText(listadoCompras.getCategoria());
                precio.setText(listadoCompras.getPrecio());
                cantidad.setText(listadoCompras.getCantidad());
                ubicacion.setText(listadoCompras.getUbicacion());
                codbarra.setText(listadoCompras.getCodBarra());

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!precio.getText().toString().isEmpty() && !cantidad.getText().toString().isEmpty()) {
                            try {
                                listadoCompras = new ListadoCompras(getId(), nombre.getText().toString(),
                                        categoria.getText().toString(),
                                        precio.getText().toString(),
                                        cantidad.getText().toString(),
                                        ubicacion.getText().toString(),
                                        codbarra.getText().toString());
                                sqlHelper.editar(listadoCompras);
                                lista = sqlHelper.verTodo();
                                notifyDataSetChanged();
                                dialog.dismiss();
                            } catch (Exception e) {
                                Toast.makeText(activity, "Error al guardar. Asegúrate de ingresar números válidos.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(activity, "Los campos de precio y cantidad no pueden estar vacíos.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                eliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = Integer.parseInt(v.getTag().toString());
                        listadoCompras = lista.get(pos);
                        setId(listadoCompras.getId());
                        AlertDialog.Builder del = new AlertDialog.Builder(activity);
                        del.setMessage("¿Estás seguro?");
                        del.setCancelable(false);
                        del.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                sqlHelper.eliminar(getId());
                                lista = sqlHelper.verTodo();
                                notifyDataSetChanged();
                            }
                        });
                        del.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        del.show();
                    }
                });

                detalles.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = Integer.parseInt(v.getTag().toString());
                        mostrarDetalles(pos);
                    }
                });
            }
        });

        return view;
    }

    private void mostrarDetalles(int posicion) {
        listadoCompras = lista.get(posicion);

        final Dialog dialog = new Dialog(activity);
        dialog.setTitle("Detalles del Producto");
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialogo);

        final EditText nombre = dialog.findViewById(R.id.edit_Nombre);
        final EditText categoria = dialog.findViewById(R.id.edit_Categoria);
        final EditText precio = dialog.findViewById(R.id.edit_Precio);
        final EditText cantidad = dialog.findViewById(R.id.edit_Cantidad);
        final EditText ubicacion = dialog.findViewById(R.id.edit_Ubicacion);
        final EditText codbarra = dialog.findViewById(R.id.edit_CodBarra);

        Button aceptar = dialog.findViewById(R.id.btnAgregarDia);
        Button cancelar = dialog.findViewById(R.id.btnCancelarDia);

        nombre.setText(listadoCompras.getNombre());
        categoria.setText(listadoCompras.getCategoria());
        precio.setText(listadoCompras.getPrecio());
        cantidad.setText(listadoCompras.getCantidad());
        ubicacion.setText(listadoCompras.getUbicacion());
        codbarra.setText(listadoCompras.getCodBarra());

        nombre.setEnabled(false);
        categoria.setEnabled(false);
        precio.setEnabled(false);
        cantidad.setEnabled(false);
        ubicacion.setEnabled(false);
        codbarra.setEnabled(false);

        aceptar.setVisibility(View.GONE);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
