package com.example.parking;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


public class ComprarCredito extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public ComprarCredito() {

    }


    public static ComprarCredito newInstance(String param1, String param2) {
        ComprarCredito fragment = new ComprarCredito();
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

    RelativeLayout btn_mercado_pago;

    ConstraintLayout btn_puntos_de_venta;

    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_comprar_credito, container, false);

        navController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);

        btn_mercado_pago = (RelativeLayout)vista.findViewById(R.id.img_mercado_pago);
        btn_mercado_pago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(ComprarCreditoDirections.actionComprarCreditoToMercadoPago());
            }
        });

        btn_puntos_de_venta = (ConstraintLayout)vista.findViewById(R.id.img_punto_de_venta);
        btn_puntos_de_venta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(ComprarCreditoDirections.actionComprarCreditoToMapaPuntosVenta());
            }
        });


        return vista;
    }
}