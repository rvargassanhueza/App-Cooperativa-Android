package com.example.streamingcoop;

/**
 * Created by innova6 on 17-02-2016.
 */

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Noticia implements Serializable {

    //Definicion de atributos de la clase Noticia primer Parseo
    private String titulo;
    private String contenido;
    private String resumen;
    private String enlace;
    private String imagen;
    private String prontus_ts;
    private Date fecha;
    private String path_artic_xml;

    //Definicion de atributos de la clase Noticia segundo Parseo
    private String n_titulo;
    private String n_contenido;
    private String n_resumen;

    public String getN_titulo() {
        return n_titulo;
    }

    public String setN_titulo(String n_titulo) {
        this.n_titulo = n_titulo;
        return n_titulo;
    }

    public String getN_contenido() {
        return n_contenido;
    }

    public String setN_contenido(String n_contenido) {
        this.n_contenido = n_contenido;
        return n_contenido;
    }

    public String getN_resumen() {
        return n_resumen;
    }

    public String setN_resumen(String n_resumen) {
        this.n_resumen = n_resumen;
        return n_resumen;
    }
//*******************************************************************************************//
    public void setTitulo(String title) {
        this.titulo = title;
    }

    public String setContenido(String content) {
        this.contenido = content;
        return content;
    }

    public String setResumen(String text) {
       this.resumen = text;
        return text;
    }

    public void setEnlace(String url) {
        this.enlace = url;
    }

    public void setImage(String image) {
        this.imagen = image;
    }

    public void setFecha(Date date) {
        this.fecha = date;
    }
    public String setProntus_ts(String ts){
            this.prontus_ts=ts;
            return ts;
    }

    public String getProntus_ts (){
        return this.prontus_ts;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getContenido() {
        return this.contenido;
    }

    public String getResumen() {
        return this.resumen;
    }

    public String getEnlace() {
        return this.enlace;
    }

    public String getImagen() {
        return this.imagen;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public String getPath_artic_xml(String path) {
        return path_artic_xml;
    }

    public String setPath_artic_xml(String path_artic_xml) {
        this.path_artic_xml = path_artic_xml;
        System.out.println("Clase Noticia, setPath: "+path_artic_xml);
        return path_artic_xml;
    }

  /*  public String getPath_artic_xml() {
        return path_artic_xml;
    }

    public void setPath_artic_xml(String path_artic_xml) {
        this.path_artic_xml = path_artic_xml;
        return path_artic_xml;
    }*/
}