package com.example.parking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


public class Administrar_patentes extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public Administrar_patentes() {


    }


    public static Administrar_patentes newInstance(String param1, String param2) {
        Administrar_patentes fragment = new Administrar_patentes();
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

    RecyclerView rv_administrar_patentes;
    ArrayList<Item_Patente> items;
    AdaptadorAdministrarPatente adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_administrar_patentes, container, false);

        rv_administrar_patentes= (RecyclerView)vista.findViewById(R.id.rv_administrar_patentes);

        items = new ArrayList<>();

        items.add(new Item_Patente("KGX-720", "TOYOTA RAV4 2x4", R.drawable.autos_patente_1));
        items.add(new Item_Patente("TK8-765", "VOLKSWAGEN GOL", R.drawable.autos_patente_2));
        items.add(new Item_Patente("UTI-890", "BMW X5 4x4", R.drawable.autos_patente_3));
        items.add(new Item_Patente("JGP-720", "Mercedez Benz sprinter", R.drawable.autos_patente_4));


        adapter = new AdaptadorAdministrarPatente(items);

        adapter.setOnClickListener(new AdaptadorAdministrarPatente.OnItemClickListener() {
            @Override
            public void onEditClick(int position) {
                Toast.makeText(getContext(),"Editar "+ items.get(position).getPatente(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onBorrarClick(int position) {
                Toast.makeText(getContext(),"Borrar "+ items.get(position).getPatente(),Toast.LENGTH_LONG).show();
            }
        });

        rv_administrar_patentes.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_administrar_patentes.setAdapter(adapter);






        return  vista;
    }
}