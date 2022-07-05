package com.example.parking;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class Principal extends Fragment  {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;


    RecyclerView recycler_patente;

    AdaptadorPatente adaptadorPatente;

    ArrayList<Item_Patente> items;


    ConstraintLayout con_noEstacionado, con_patente, con_zona, con_datos, con_todo, con_zona_unica;

    Button btn_comenzar_estacionamiento, btn_agregar_patente, btn_ver_ubicacion;

    ImageButton btn_ver_mapa_zona_unica;
    Boolean zona_unica_visible;

    MotionLayout motionLayout, motionEstados, motionZona, motionAutos;

    String Estado;

    String patente, modelo;

    Integer imagen;

    ImageView img_patente_zona;

    TextView txt_patente_zona, txt_modelo_zona;



    FusedLocationProviderClient fusedLocationProviderClient;
    Location ubicacion;

    CircleImageView img_auto_estacionar;
    TextView txt_patente_estacionar,txt_modelo_estacionar;

    Button btn_comprar_credito;

    NavController navController;


    public Principal() {

    }


    public static Principal newInstance(String param1, String param2) {
        Principal fragment = new Principal();
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

    Double[] mLatitudes = new Double[]{-34.6990131, -34.698891334404465, -34.699694020297514, -34.69970284099005};
    Double[] mLongitudes = new Double[]{-58.3141506, -58.31297932922058, -58.31305979548377, -58.31417023002605};
    String[] nombre = new String[]{"Casa", "random", "random", "random"};



    private static final int AZUL = 0x88F9A825;
    private static final int BLANCO = 0xffffffff;

    private OnMapReadyCallback zonaUnica = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {



            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            googleMap.setMyLocationEnabled(true);
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            Polygon polygon1 = googleMap.addPolygon(new PolygonOptions()
                    .clickable(true)
                    .add(
                            new LatLng(mLatitudes[0], mLongitudes[0]),
                            new LatLng(mLatitudes[1], mLongitudes[1]),
                            new LatLng(mLatitudes[2], mLongitudes[2]),
                            new LatLng(mLatitudes[3], mLongitudes[3])));
            // Store a data object with the polygon, used here to indicate an arbitrary type.
            polygon1.setTag("Zona unica");
            polygon1.setFillColor(AZUL);
            polygon1.setStrokeColor(BLANCO);
            polygon1.setStrokeWidth(4);


            LatLng zoom = new LatLng(mLatitudes[0], mLongitudes[0]);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(zoom,17));



        }
    };

    private OnMapReadyCallback ubicacion_estacionamiento = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {



            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            googleMap.setMyLocationEnabled(true);
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            Polygon polygon1 = googleMap.addPolygon(new PolygonOptions()
                    .clickable(true)
                    .add(
                            new LatLng(mLatitudes[0], mLongitudes[0]),
                            new LatLng(mLatitudes[1], mLongitudes[1]),
                            new LatLng(mLatitudes[2], mLongitudes[2]),
                            new LatLng(mLatitudes[3], mLongitudes[3])));
            // Store a data object with the polygon, used here to indicate an arbitrary type.
            polygon1.setTag("Zona unica");
            polygon1.setFillColor(AZUL);
            polygon1.setStrokeColor(BLANCO);
            polygon1.setStrokeWidth(4);


            LatLng zoom = new LatLng(mLatitudes[0], mLongitudes[0]);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(zoom,17));



        }
    };






    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            if(getContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            {

                fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location!=null)
                        {
                            ubicacion = location;

                        }else
                        {
                            Toast.makeText(getContext(),"nulo",Toast.LENGTH_LONG).show();
                        }
                    }
                });


            }
            else{
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }


        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapa);
        if (mapFragment != null) {
            mapFragment.getMapAsync(zonaUnica);
        }
        SupportMapFragment mapa_estacionado =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapa_ubicacion_estacionado);
        if (mapa_estacionado != null) {
            mapa_estacionado.getMapAsync(zonaUnica);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_principal, container, false);


        navController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);




        // Llama a los motionLaoyut
        motionLayout = (MotionLayout) vista.findViewById(R.id.motion_home);
        motionEstados = (MotionLayout) vista.findViewById(R.id.con_todo);
        motionZona = (MotionLayout) vista.findViewById(R.id.con_seleccionar_zona);


        // Define el estado
        Estado = "Comenzar estacionamiento";

        // Carga los items de patentes
        items = new ArrayList<>();

        items.add(new Item_Patente("KGX-720", "TOYOTA RAV4 2x4", R.drawable.autos_patente_1));
        items.add(new Item_Patente("TK8-765", "VOLKSWAGEN GOL", R.drawable.autos_patente_2));
        items.add(new Item_Patente("UTI-890", "BMW X5 4x4", R.drawable.autos_patente_3));
        items.add(new Item_Patente("JGP-720", "Mercedez Benz sprinter", R.drawable.autos_patente_4));


        //LLma al rv patentes
        recycler_patente = (RecyclerView) vista.findViewById(R.id.rv_patentes);

        //llama a la patente de la zona
        txt_patente_zona = (TextView) vista.findViewById(R.id.txt_patente_zona);
        txt_modelo_zona = (TextView) vista.findViewById(R.id.txt_patente_modelo_zona);
        img_patente_zona = (ImageView) vista.findViewById(R.id.img_auto_patente_zona);


        //llama a los constrainlayout
        con_noEstacionado = (ConstraintLayout) vista.findViewById(R.id.con_no_estacionado);
        con_patente = (ConstraintLayout) vista.findViewById(R.id.con_seleccionar_patente);
        con_zona = (ConstraintLayout) vista.findViewById(R.id.con_seleccionar_zona);
        con_datos = (ConstraintLayout) vista.findViewById(R.id.con_datos);
        con_todo = (ConstraintLayout) vista.findViewById(R.id.con_todo);
        con_zona_unica = (ConstraintLayout) vista.findViewById(R.id.con_zona_unica);



        //llama a la patente en el estado estacionado
        img_auto_estacionar = (CircleImageView)vista.findViewById(R.id.img_auto_patente_estacionar);
        txt_patente_estacionar = (TextView)vista.findViewById(R.id.txt_patente_estacionar);
        txt_modelo_estacionar = (TextView)vista.findViewById(R.id.txt_patente_modelo_estacionar);

        btn_comprar_credito = (Button)vista.findViewById(R.id.btn_credito_principal);
        btn_comprar_credito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(PrincipalDirections.actionPrincipalToComprarCredito());
            }
        });



        //al hacer click en la zona unica
        con_zona_unica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_auto_estacionar.setImageResource(imagen);
                txt_patente_estacionar.setText(patente);
                txt_modelo_estacionar.setText(modelo);
                motionEstados.transitionToState(R.id.estacionado);
                btn_comenzar_estacionamiento.setText("Terminar estacionamiento");
                Estado = "estacionado";
            }
        });


        //setea lo de esconder el mapa de zonaUnica
        btn_ver_mapa_zona_unica = (ImageButton) vista.findViewById(R.id.btn_ver_mapa_zona_unica);
        btn_ver_mapa_zona_unica.setImageResource(R.drawable.ic_visible);
        zona_unica_visible = false;
        btn_ver_mapa_zona_unica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!zona_unica_visible)
                {
                    motionZona.transitionToEnd();
                    btn_ver_mapa_zona_unica.setImageResource(R.drawable.img_not_visible);


                }else{

                    motionZona.transitionToStart();
                    btn_ver_mapa_zona_unica.setImageResource(R.drawable.ic_visible);

                }
                zona_unica_visible = !zona_unica_visible;

            }
        });

        //boton para mostrar la ubicacion del estacionamiento
        btn_ver_ubicacion = (Button) vista.findViewById(R.id.btn_ver_ubicacion);
        btn_ver_ubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                motionEstados.transitionToState(R.id.ubicacion_estacionado);
                Estado = "ubicacion_estacionado";
                btn_comenzar_estacionamiento.setText("Volver");
            }
        });


        // boton para agregar una patente en seleccionar patente
        btn_agregar_patente = (Button) vista.findViewById(R.id.btn_agregar_patente);
        btn_agregar_patente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Estado.equals("patente")) {
                    motionEstados.transitionToState(R.id.agregar_patente);
                    Estado = "agregar patente";

                }


            }
        });

        //boton principal que controla los estados
        btn_comenzar_estacionamiento = (Button) vista.findViewById(R.id.btn_comenzar_estacionamiento);
        btn_comenzar_estacionamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (Estado.equals("Comenzar estacionamiento")) {

                    motionLayout.transitionToEnd();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {

                            motionEstados.transitionToState(R.id.patente);

                            // adptador de las patentes
                            adaptadorPatente = new AdaptadorPatente(items);
                            adaptadorPatente.SetOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    patente = items.get(recycler_patente.getChildAdapterPosition(view)).getPatente();
                                    modelo = items.get(recycler_patente.getChildAdapterPosition(view)).getModelo();
                                    imagen = items.get(recycler_patente.getChildAdapterPosition(view)).getImagen();

                                    img_patente_zona.setImageResource(imagen);
                                    txt_patente_zona.setText(patente);
                                    txt_modelo_zona.setText(modelo);


                                    motionEstados.transitionToState(R.id.zona);
                                    Estado = "zona";

                                }
                            });
                            recycler_patente.setLayoutManager(new LinearLayoutManager(getContext()));
                            recycler_patente.setAdapter(adaptadorPatente);
                            Estado = "patente";
                        }
                    }, 500);


                }
                if (Estado.equals("patente")) {


                    motionEstados.transitionToState(R.id.no_estacionado);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            motionLayout.transitionToStart();
                            Estado = "Comenzar estacionamiento";
                        }
                    }, 500);

                }
                if (Estado.equals("zona")) {
                    motionEstados.transitionToState(R.id.patente);
                    Estado = "patente";
                }
                if (Estado.equals("agregar patente")) {
                    motionEstados.transitionToState(R.id.patente);
                    Estado = "patente";
                }

                if (Estado.equals("estacionado")) {
                    motionEstados.transitionToState(R.id.no_estacionado);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            motionLayout.transitionToStart();
                            Estado = "Comenzar estacionamiento";
                            btn_comenzar_estacionamiento.setText("Terminar estacionamiento");
                        }
                    }, 500);
                }
                if (Estado.equals("ubicacion_estacionado")) {
                    motionEstados.transitionToState(R.id.estacionado);
                    Estado = "estacionado";
                    btn_comenzar_estacionamiento.setText("Terminar estacionamiento");
                }


            }
        });

        return vista;
    }








}