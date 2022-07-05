package com.example.parking;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Movimientos extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public Movimientos() {

    }

    public static Movimientos newInstance(String param1, String param2) {
        Movimientos fragment = new Movimientos();
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

    RecyclerView rv_movimientos;
    AdaptadorMovimientos adapter;

    ConstraintLayout con_sin_movimientos;

    ArrayList<Movimiento> movimientos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_movimientos, container, false);


        movimientos = new ArrayList<>();

        movimientos.add(new Movimiento("450","24/10/20","activo",0));
        movimientos.add(new Movimiento("450","24/10/20","activo",1));
        movimientos.add(new Movimiento("450","24/10/20","activo",1));

        movimientos.add(new Movimiento("23","24/10/20","activo",0));
        movimientos.add(new Movimiento("244","24/10/20","cancelado",1));
        movimientos.add(new Movimiento("340","24/10/20","activo",0));


        rv_movimientos = (RecyclerView)vista.findViewById(R.id.rv_movimientos_movimientos);
        adapter = new AdaptadorMovimientos(movimientos);
        rv_movimientos.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_movimientos.setAdapter(adapter);


        con_sin_movimientos =(ConstraintLayout) vista.findViewById(R.id.con_sin_movimientos);

        if (movimientos.size() == 0)
        {
            con_sin_movimientos.setVisibility(View.VISIBLE);
            rv_movimientos.setVisibility(View.GONE);
        }else
        {
            con_sin_movimientos.setVisibility(View.GONE);
            rv_movimientos.setVisibility(View.VISIBLE);
        }

        return vista;
    }
}