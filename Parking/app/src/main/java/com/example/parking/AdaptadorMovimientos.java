package com.example.parking;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorMovimientos extends RecyclerView.Adapter<AdaptadorMovimientos.ViewHolderMovimientos> implements View.OnClickListener {


    ArrayList<Movimiento> listaItems;
    private View.OnClickListener listener;
    Integer[] imagenes = new Integer[]{R.drawable.img_punto_venta,R.drawable.img_mercado_pago};

    public AdaptadorMovimientos(ArrayList<Movimiento> listaItems) {
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
    public ViewHolderMovimientos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movimientos, null, false);

        view.setOnClickListener(this);
        return new ViewHolderMovimientos(view);
    }

    public void SetOnClickListener(View.OnClickListener listener) {
        this.listener = listener;


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMovimientos holder, int position) {

        Movimiento item = listaItems.get(position);
        holder.txt_monto.setText("Monto : $" + item.getMonto());
        holder.txt_fecha.setText("Fecha : " + item.getFecha());
        holder.txt_estado.setText("Estado : " + item.getEstado());
        holder.img_tipo.setImageResource(imagenes[item.getTipo()]);




    }


    @Override
    public int getItemCount() {
        return listaItems.size();
    }




    public class ViewHolderMovimientos extends RecyclerView.ViewHolder {
        TextView txt_monto,txt_fecha,txt_estado;
        ImageView img_tipo;



        public ViewHolderMovimientos(@NonNull View itemView) {
            super(itemView);

            txt_monto = (TextView) itemView.findViewById(R.id.txt_monto_movimiento);
            txt_fecha = (TextView) itemView.findViewById(R.id.txt_fecha_movimiento);
            txt_estado = (TextView) itemView.findViewById(R.id.txt_estado_movimiento);
            img_tipo = (ImageView)itemView.findViewById(R.id.img_tipo_de_movimiento);
        }
    }
}
