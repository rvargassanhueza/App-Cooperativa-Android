/*package com.example.streamingcoop;



import android.content.Context;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ParseSitio {
    Context contextor;
    private URL url;
    public String titulo="";
    public String contenido="";
    public String resumen="";

   // NoticiaSitio articulo;



    public ParseSitio(String url, Context contexto) {

        System.out.println("Entra a clase ParseSitio constructor");
        contextor = contexto;
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<NoticiaSitio> parsex() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        ArrayList<NoticiaSitio> noticias = new ArrayList<NoticiaSitio>();
       // DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy kk:mm:ss Z", Locale.ENGLISH);
        NoticiaSitio noticia;

       // System.out.println("Entra a clase ParseSitio funcion parse antes del try");
        try {
           // System.out.println("Entra a clase ParseSitio funcion parse dentro del try");
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(this.url.openConnection().getInputStream());
            Element root = dom.getDocumentElement();
            NodeList items = root.getElementsByTagName("_public");
            System.out.println("Mostrando items.getLength: "+items.getLength());

              // for (int i = 0; i < items.getLength(); i++) {
                   int i=0;
                   if (i < items.getLength()){
                noticia = new NoticiaSitio();
                Node item = items.item(i);
                NodeList properties = item.getChildNodes();
                System.out.println("Mostrando properties.getLength: "+properties.getLength());
                for (int j = 0; j < properties.getLength(); j++) {
                   // int j=0;
                    //if(j < properties.getLength()){
                        System.out.println("Entra if de j");
                        Node property = properties.item(j);
                        String name = property.getNodeName();
                        if (name.equalsIgnoreCase("txt_titular_portada")) {
                            System.out.println("Entra a if de txt_");
                            int delimiter= (property.getFirstChild().getNodeValue()).indexOf(" ] ");
                           // System.out.println("mostrando delimiter: "+delimiter);
                            titulo=noticia.setTitulo(property.getFirstChild().getNodeValue().substring(delimiter + 1));

                            //noticia.setTitulo("Hola Radio");
                            System.out.println("mostrando datos gettitulo" + noticia.getTitulo());
                        }else if (name.equalsIgnoreCase("vtxt_cuerpo")){
                             int delimiter = (property.getFirstChild().getNodeValue()).indexOf(" ] ");
                            contenido=noticia.setContenido(property.getFirstChild().getNodeValue().substring(delimiter + 1));
                           // noticia.setContenido("Ingresando nuevos datos al contenido,.,,,,");
                            //System.out.println("mostrando datos getcontenido" + noticia.getContenido());

                        /*int startdelimiterImage = property.getFirstChild().getNodeValue().indexOf("src=");
                        if(startdelimiterImage!=-1){

                            String urlpart = property.getFirstChild().getNodeValue().substring(startdelimiterImage+5);
                            int enddelimiterImage = urlpart.indexOf("\"");
                            noticia.setImage(property.getFirstChild().getNodeValue().substring(startdelimiterImage+5,startdelimiterImage+5+enddelimiterImage));
                        }
                        } else if (name.equalsIgnoreCase("twittertime")) {
                            int delimiter = (property.getFirstChild().getNodeValue()).indexOf(" ] ");
                            resumen= noticia.setResumen(property.getFirstChild().getNodeValue().substring(delimiter + 1));
                           // noticia.setResumen("Ingresando nuevos datos al Resumen");
                   /* } else if (name.equalsIgnoreCase("link")) {
                        noticia.setEnlace(property.getFirstChild().getNodeValue());
                    } else if (name.equalsIgnoreCase("pubDate")) {
                        noticia.setFecha(formatter.parse("" + property.getFirstChild().getNodeValue()));
                    } else if (name.equalsIgnoreCase("prontus_ts")){
                        noticia.setProntus_ts(property.getFirstChild().getNodeValue());
                        }
                        // System.out.println("Dentro de clase ParseSitio, mostrando arreglo noticias: "+noticias.size());

                    }
                noticias.add(noticia);
                System.out.println("Tamaño de arreglo: "+noticias.size());
                System.out.println("Dentro de clase ParseSitio, mostrando titulo: " + ti);
                System.out.println("Dentro de clase ParseSitio, mostrando contenido: "+noticias.get(0));
                System.out.println("Dentro de clase ParseSitio, mostrando titulotwitter: "+noticias.get(0));
                System.out.println("Dentro de clase ParseSitio, mostrando URL: " + url);
                Log.i("Parsher", "notcia:" + i);
                }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return noticias;
    }
*/