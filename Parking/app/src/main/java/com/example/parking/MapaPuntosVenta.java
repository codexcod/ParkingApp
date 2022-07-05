package com.example.parking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapaPuntosVenta extends Fragment {

    Double[] mLatitudes = new Double[]{-34.6990131, -34.698891334404465, -34.699694020297514, -34.69970284099005};
    Double[] mLongitudes = new Double[]{-58.3141506, -58.31297932922058, -58.31305979548377, -58.31417023002605};
    String[] nombre = new String[]{"Casa", "random", "random", "random"};



    private static final int AZUL = 0x88F9A825;
    private static final int BLANCO = 0xffffffff;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {


            for (int i = 0; i < mLatitudes.length;i++)
            {
                LatLng coordenada = new LatLng(mLatitudes[i], mLongitudes[i]);
                googleMap.addMarker(new MarkerOptions().position(coordenada).title(nombre[i]));
            }
            LatLng zoom = new LatLng(mLatitudes[0], mLongitudes[0]);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(zoom,15));




        }
    };



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mapa_puntos_venta, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapa_puntos_de_venta);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}