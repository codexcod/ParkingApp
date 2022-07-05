package com.example.parking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Registro extends AppCompatActivity {

    Button btn_enviar;

    ImageButton btn_volver;

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        btn_enviar = (Button)findViewById(R.id.btn_enviar);

        btn_volver = (ImageButton)findViewById(R.id.btn_volver_registrarse);

        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });


        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(),Codigo_verificacion.class);
                startActivity(intent);

            }
        });

        spinner = (Spinner)findViewById(R.id.spin_compania);

        final String[] opciones={"Compania telefonica","Personal","Claro","Movistar","Nextel"};


        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.spinner_item,opciones);
        spinner.setAdapter(adapter);

    }
}