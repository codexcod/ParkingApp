package com.example.parking;

import android.os.Bundle;

import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Transacciones extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";




    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    MotionLayout motionLayout;

    public Transacciones() {

    }


    public static Transacciones newInstance(String param1, String param2) {
        Transacciones fragment = new Transacciones();
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

    Button btn_credito,btn_movimientos,btn_infracciones,btn_punto_venta,btn_transferir_credito;

    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_transacciones, container, false);

        navController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);

        btn_movimientos = (Button)vista.findViewById(R.id.btn_movimientos);
        btn_movimientos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(TransaccionesDirections.actionTransaccionesToMovimientos());
            }
        });


        btn_credito = (Button)vista.findViewById(R.id.btn_credito);
        btn_credito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               navController.navigate(TransaccionesDirections.actionTransaccionesToComprarCredito());
            }
        });

        btn_transferir_credito = (Button)vista.findViewById(R.id.btn_transferir_credito);
        btn_transferir_credito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(TransaccionesDirections.actionTransaccionesToTransferirCredito());
            }
        });

        btn_infracciones = (Button)vista.findViewById(R.id.btn_infracciones);
        btn_infracciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(TransaccionesDirections.actionTransaccionesToBuscarInfracciones());
            }
        });

        btn_punto_venta = (Button)vista.findViewById(R.id.btn_punto_de_venta);
        btn_punto_venta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(TransaccionesDirections.actionTransaccionesToMapaPuntosVenta());
            }
        });


        return vista;
    }
}