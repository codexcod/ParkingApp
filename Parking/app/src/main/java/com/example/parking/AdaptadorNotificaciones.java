package com.example.parking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdaptadorNotificaciones extends RecyclerView.Adapter<AdaptadorNotificaciones.ViewHolderNotificaciones> implements View.OnClickListener {


    ArrayList<Notificacion> listaItems;
    private View.OnClickListener listener;

    public AdaptadorNotificaciones(ArrayList<Notificacion> listaItems) {
        this.listaItems = listaItems;
    }
    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    @NonNull
    @Override
    public ViewHolderNotificaciones onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notificaciones, null, false);

        view.setOnClickListener(this);
        return new ViewHolderNotificaciones(view);
    }

    public void SetOnClickListener(View.OnClickListener listener) {
        this.listener = listener;


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderNotificaciones holder, int position) {

        Notificacion item = listaItems.get(position);
        holder.txt_titulo.setText(item.getTitulo());
        holder.txt_mensaje.setText(item.getMensaje());

    }


    @Override
    public int getItemCount() {
        return listaItems.size();
    }




    public class ViewHolderNotificaciones extends RecyclerView.ViewHolder {
        TextView txt_titulo,txt_mensaje;



        public ViewHolderNotificaciones(@NonNull View itemView) {
            super(itemView);

            txt_titulo = (TextView) itemView.findViewById(R.id.txt_titulo_notificacion);
            txt_mensaje = (TextView) itemView.findViewById(R.id.txt_mensaje_notificacion);
        }
    }
}
