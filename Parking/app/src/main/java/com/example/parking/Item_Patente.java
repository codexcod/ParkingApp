package com.example.parking;

public class Item_Patente {

    String patente, modelo;
    Integer imagen;

    public Item_Patente(String patente, String modelo, Integer imagen) {
        this.patente = patente;
        this.modelo = modelo;

        this.imagen = imagen;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }



    public Integer getImagen() {
        return imagen;
    }

    public void setImagen(Integer imagen) {
        this.imagen = imagen;
    }
}
