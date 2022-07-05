package com.example.parking;

import android.widget.ImageView;

public class Movimiento {

    String monto,fecha,estado;
    Integer tipo;

    public Movimiento(String monto, String fecha, String estado, Integer tipo) {
        this.monto = monto;
        this.fecha = fecha;
        this.estado = estado;
        this.tipo = tipo;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
}
