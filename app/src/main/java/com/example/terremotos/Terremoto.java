package com.example.terremotos;

public class Terremoto {
    private String magnitud;
    private String lugar;
    private String hora;

    public Terremoto(String magnitud, String lugar, String hora) {
        this.magnitud = magnitud;
        this.lugar = lugar;
        this.hora = hora;
    }

    public String getMagnitud() {
        return magnitud;
    }


    public String getLugar() {
        return lugar;
    }


    public String getHora() {
        return hora;
    }

}

