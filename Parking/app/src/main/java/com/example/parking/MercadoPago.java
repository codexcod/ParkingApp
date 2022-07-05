package com.example.parking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mercadopago.android.px.core.MercadoPagoCheckout;
import com.mercadopago.android.px.model.Item;
import com.mercadopago.android.px.model.Payer;


public class MercadoPago extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public MercadoPago() {

    }


    public static MercadoPago newInstance(String param1, String param2) {
        MercadoPago fragment = new MercadoPago();
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

    Button btn_pagar;
    EditText txt_monto,txt_mail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_mercado_pago, container, false);





        btn_pagar = (Button)vista.findViewById(R.id.btn_iniciar_carga_mercado_pago);
        txt_mail = (EditText) vista.findViewById(R.id.txt_mail_mercado_pàgo);
        txt_monto = (EditText) vista.findViewById(R.id.txt_monto_mercado_pàgo);

        btn_pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MercadoPagoCheckout.Builder("ENV_PUBLIC_KEY", "checkoutPreferenceId").build()
                        .startPayment(getContext(), 1);

            }
        });




        return vista;
    }
}