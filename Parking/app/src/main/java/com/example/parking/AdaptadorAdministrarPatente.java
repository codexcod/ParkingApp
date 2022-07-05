package com.example.parking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdaptadorAdministrarPatente extends RecyclerView.Adapter<AdaptadorAdministrarPatente.ViewHolderPatentes> {


    ArrayList<Item_Patente> listaItems;
    private OnItemClickListener mListener;

    public AdaptadorAdministrarPatente(ArrayList<Item_Patente> listaItems) {
        this.listaItems = listaItems;
    }


    @NonNull
    @Override
    public ViewHolderPatentes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_administrar_patente, null, false);

        return new ViewHolderPatentes(view,mListener);
    }

    public void setOnClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }

    public  interface  OnItemClickListener {
        void onEditClick(int position);
        void onBorrarClick(int position);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPatentes holder, int position) {

        Item_Patente item = listaItems.get(position);
        holder.txt_patente.setText(item.getPatente());
        holder.txt_modelo.setText(item.getModelo());
        holder.foto.setImageResource(item.getImagen());
    }


    @Override
    public int getItemCount() {
        return listaItems.size();
    }




    public class ViewHolderPatentes extends RecyclerView.ViewHolder {
        TextView txt_patente,txt_modelo;

        ImageButton img_edit,img_borrar;

        CircleImageView foto;

        public ViewHolderPatentes(@NonNull View itemView, final OnItemClickListener listenerItem) {
            super(itemView);

            txt_patente = (TextView) itemView.findViewById(R.id.txt_patente_administrar);
            txt_modelo = (TextView) itemView.findViewById(R.id.txt_patente_modelo_administrar);
            foto = (CircleImageView) itemView.findViewById(R.id.img_auto_patente_administrar);

            img_borrar = (ImageButton)itemView.findViewById(R.id.img_borrar_patente);

            img_borrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listenerItem != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listenerItem.onBorrarClick(position);

                        }
                    }
                }
            });
            img_edit = (ImageButton)itemView.findViewById(R.id.img_edit_patente);

            img_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listenerItem != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listenerItem.onEditClick(position);

                        }
                    }
                }
            });



        }
    }
}
