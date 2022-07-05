package com.example.parking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;


public class Cuenta extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Cuenta() {

    }


    public static Cuenta newInstance(String param1, String param2) {
        Cuenta fragment = new Cuenta();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    Button btn_transacciones, btn_patentes, btn_ayuda,btn_cerrar_sesion,btn_cambiar_contraseña;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista =  inflater.inflate(R.layout.fragment_cuenta, container, false);

        navController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);

        btn_transacciones = (Button)vista.findViewById(R.id.btn_transacciones_cuenta);

        btn_transacciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navController.navigate(CuentaDirections.actionCuentaToTransacciones());
            }
        });


        btn_patentes = (Button)vista.findViewById(R.id.btn_administrar_patentes);

        btn_patentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navController.navigate(CuentaDirections.actionCuentaToAdministrarPatentes());

            }
        });

        btn_ayuda = (Button)vista.findViewById(R.id.btn_ayuda);

        btn_ayuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        btn_cambiar_contraseña = (Button)vista.findViewById(R.id.btn_modificar_contrasena);

        btn_cambiar_contraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_cerrar_sesion = (Button)vista.findViewById(R.id.btn_cerrar_sesion);

        btn_cerrar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences = getActivity().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("usuario","");
                editor.putString("contraseña","");
                editor.putBoolean("recordar",false);

                editor.commit();



                Intent intent = new Intent (getActivity(),Login.class);
                startActivity(intent);

            }
        });





        return  vista;
    }
}