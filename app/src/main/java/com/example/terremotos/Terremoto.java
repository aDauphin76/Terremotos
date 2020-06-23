package com.example.terremotos;

public class Terremoto {
    private double magnitud;
    private String lugar;
    private long hora;
    private String url;

    public Terremoto(double magnitud, String lugar, long hora, String url) {
        this.magnitud   = magnitud;
        this.lugar      = lugar;
        this.hora       = hora;
        this.url        = url;
    }

    public double getMagnitud() { return magnitud; }

    public String getLugar() {
        return lugar;
    }

    public long getHora() { return hora; }

    public String getUrl() { return url; }
}

