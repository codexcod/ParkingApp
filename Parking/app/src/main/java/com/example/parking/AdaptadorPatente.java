package com.example.parking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdaptadorPatente extends RecyclerView.Adapter<AdaptadorPatente.ViewHolderItems_2> implements View.OnClickListener {


    ArrayList<Item_Patente> listaItems;
    private View.OnClickListener listener;

    public AdaptadorPatente(ArrayList<Item_Patente> listaItems) {
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
    public ViewHolderItems_2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patente, null, false);

        view.setOnClickListener(this);
        return new ViewHolderItems_2(view);
    }

    public void SetOnClickListener(View.OnClickListener listener) {
        this.listener = listener;


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderItems_2 holder, int position) {

        Item_Patente item = listaItems.get(position);
        holder.txt_patente.setText(item.getPatente());
        holder.txt_modelo.setText(item.getModelo());
        holder.foto.setImageResource(item.getImagen());
    }


    @Override
    public int getItemCount() {
        return listaItems.size();
    }




    public class ViewHolderItems_2 extends RecyclerView.ViewHolder {
        TextView txt_patente,txt_modelo;

        CircleImageView foto;

        public ViewHolderItems_2(@NonNull View itemView) {
            super(itemView);

            txt_patente = (TextView) itemView.findViewById(R.id.txt_patente);
            txt_modelo = (TextView) itemView.findViewById(R.id.txt_patente_modelo);
            foto = (CircleImageView) itemView.findViewById(R.id.img_auto_patente);


        }
    }
}
