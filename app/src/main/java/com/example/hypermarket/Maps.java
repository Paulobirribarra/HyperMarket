package com.example.hypermarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class Maps extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {
    EditText txtlatitud, txtlongitud;
    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        txtlatitud = findViewById(R.id.txtLatitud);
        txtlongitud = findViewById(R.id.txtLongitud);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        txtlatitud.setText(""+latLng.latitude);
        txtlongitud.setText(""+latLng.longitude);
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        txtlatitud.setText(""+latLng.latitude);
        txtlongitud.setText(""+latLng.longitude);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this::onMapClick);//

        List<Direcciones> locations = new ArrayList<>();
        locations.add(new Direcciones(new LatLng(-38.74619764170741, -72.60797815601305),"Santa Isabel, Caopolicán"));
        locations.add(new Direcciones(new LatLng(-38.75677248395097, -72.59333546746481),"Supermercados Trébol, Avenida Villa Alegre 774"));
        locations.add(new Direcciones(new LatLng(-38.729218691188194, -72.59958445192142),"Lider, Prieto Norte 0320"));
        locations.add(new Direcciones(new LatLng(-38.733921170954964, -72.610595643141),"Jumbo, Av. Alemania 633"));
        locations.add(new Direcciones(new LatLng(-38.73918228641117, -72.59236998305617),"El Huerto, Claro Solar 692"));
        locations.add(new Direcciones(new LatLng(-38.735222545724206, -72.57947450275596),"Feria Pinto, Aníbal Pinto 045"));
        locations.add(new Direcciones(new LatLng(-38.740870916881576, -72.63261080257071),"unimarc, Av. Javiera Carrera 1610, Temuco, Araucanía"));

        for (Direcciones direcciones : locations){
            LatLng location = direcciones.getDirectorio();
            String tittle = direcciones.getTitulo();

            mMap.addMarker(new MarkerOptions().position(location).title(tittle));
        }
    }
    public void goBack (View view){
        Intent intent = new Intent(this, Listado.class);
        startActivity(intent);
    }
}