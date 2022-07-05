package com.example.parking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ComenzarEstacionamiento extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    RecyclerView recycler;

    AdaptadorPatente adaptadorPatente;

    ArrayList<Item_Patente> items;

    public ComenzarEstacionamiento() {

    }


    public static ComenzarEstacionamiento newInstance(String param1, String param2) {
        ComenzarEstacionamiento fragment = new ComenzarEstacionamiento();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_comenzar_estacionamiento, container, false);

        items = new ArrayList<>();

        items.add(new Item_Patente("KGX-720","TOYOTA RAV4 2x4",R.drawable.autos_patente_1));
        items.add(new Item_Patente("TK8-765","VOLKSWAGEN GOL",R.drawable.autos_patente_2));
        items.add(new Item_Patente("UTI-890","BMW X5 4x4",R.drawable.autos_patente_3));
        items.add(new Item_Patente("JGP-720","Mercedez Benz sprinter",R.drawable.autos_patente_4));

        recycler = (RecyclerView)vista.findViewById(R.id.rv_patentes);
        adaptadorPatente = new AdaptadorPatente(items);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adaptadorPatente);


        return vista;


    }
}