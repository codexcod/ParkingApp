package com.example.parking;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Codigo_verificacion extends AppCompatActivity {

    LinearLayout lin_exitoso;
    Button btn_aceptar;

    ImageButton btn_volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo_verificacion);

        lin_exitoso = (LinearLayout)findViewById(R.id.linearLayaout_codigo);
        btn_aceptar = (Button)findViewById(R.id.btn_codigo);
        btn_volver = (ImageButton) findViewById(R.id.btn_volver_codigo);

        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(),Registro.class);
                startActivity(intent);
            }
        });



        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                esperar(3000);
            }
        });


    }

    public void esperarYCerrar(int milisegundos) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent (getApplicationContext(),Login.class);
                startActivity(intent);
            }
        }, milisegundos);
    }

    public void esperar(int milisegundos) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                lin_exitoso.setVisibility(View.VISIBLE);
                esperarYCerrar(4000);
            }
        }, milisegundos);
    }
}