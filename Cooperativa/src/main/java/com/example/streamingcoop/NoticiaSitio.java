package com.example.streamingcoop;

/**
 * Created by innova6 on 17-02-2016.
 */

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class NoticiaSitio implements Serializable {

    //Definicion de atributos de la clase NoticiaSitio
    private String titulo;
    private String contenido;
    private String resumen;
    private String imagen;
    private Date fecha;
    private String categoria;
    // private String enlace; // private String prontus_ts;//  private String path_artic_xml;



    public String getTitulo() {
        return titulo;
    }

    public String setTitulo(String titulo) {
        this.titulo = titulo;
        return titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public String setContenido(String contenido) {
        this.contenido = contenido;
        return contenido;
    }

    public String getResumen() {
        return resumen;
    }

    public String setResumen(String resumen) {
        this.resumen = resumen;
        return resumen;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}


