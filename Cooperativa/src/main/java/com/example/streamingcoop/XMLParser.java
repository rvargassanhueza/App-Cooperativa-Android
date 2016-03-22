package com.example.streamingcoop;

/**
 * Created by innova6 on 17-02-2016.
 */

import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLParser {
   // Arma_URL arma = new Arma_URL();
   // Activity_Noticias_Deportes aaa = new Activity_Noticias_Deportes();
  public String cod_prontus = "";
    Context contextor;
    private URL url;
    public String titulo="";
    public String contenido="";
    public String resumen="";
    public String p_noticia="";

    public XMLParser(String url, Context contexto) {
        contextor = contexto;
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Noticia> parse() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        ArrayList<Noticia> noticias = new ArrayList<Noticia>();
        DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy kk:mm:ss Z", Locale.ENGLISH);
        Noticia noticia;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(this.url.openConnection().getInputStream());
            Element root = dom.getDocumentElement();
            NodeList items = root.getElementsByTagName("item");
            for (int i = 0; i < items.getLength(); i++) {
                noticia = new Noticia();
                Node item = items.item(i);
                NodeList properties = item.getChildNodes();
                for (int j = 0; j < properties.getLength(); j++) {
                    Node property = properties.item(j);
                    String name = property.getNodeName();
                    if (name.equalsIgnoreCase("title")) {
                        int delimiter= (property.getFirstChild().getNodeValue()).indexOf("]");
                        noticia.setTitulo(property.getFirstChild().getNodeValue().substring(delimiter+1));

                        int startdelimiterImage = property.getFirstChild().getNodeValue().indexOf("prontus_foto50");
                        if (startdelimiterImage != -1) {
                            String urlpart = property.getFirstChild().getNodeValue().substring(startdelimiterImage + 5);
                            int enddelimiterImage = urlpart.indexOf("\"");
                            noticia.setImage(property.getFirstChild().getNodeValue().substring(startdelimiterImage + 5, startdelimiterImage + 5 + enddelimiterImage));
                        }
                    } else if (name.equalsIgnoreCase("description")) {
                    int delimiter = (property.getFirstChild().getNodeValue()).indexOf("]");
                        noticia.setContenido(property.getFirstChild().getNodeValue().substring(delimiter+1));
                       // System.out.println("Muestra Prueba de Noticia: "+p_noticia);
                    } else if (name.equalsIgnoreCase("link")) {
                        noticia.setEnlace(property.getFirstChild().getNodeValue());
                    } else if (name.equalsIgnoreCase("pubDate")) {
                        noticia.setFecha(formatter.parse("" + property.getFirstChild().getNodeValue()));
                    } else if (name.equalsIgnoreCase("prontus_ts")) {
                      cod_prontus=noticia.setProntus_ts(property.getFirstChild().getNodeValue());
                      // arma.recoger_id(cod_prontus);
                       // aaa.recoger_id(cod_prontus);
                        //noticia.setProntus_ts(property.getFirstChild().getNodeValue());

                        //System.out.println("muestra codigo en clase xmlpareser: " + cod_prontus);
                    }
                }
                noticias.add(noticia);
                //Log.i("Parsher", "noticia:" + i);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
            return noticias;
}
    public ArrayList<Noticia> parsex() {
       // System.out.println("Entra a clase XMLParser, metodo parsex segundo parseo");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        ArrayList<Noticia> noticias_s = new ArrayList<Noticia>();
        DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy kk:mm:ss Z", Locale.ENGLISH);
        Noticia noticia_k;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(this.url.openConnection().getInputStream());
            System.out.println("Imprime URl de XMLParse, metodo parsex: "+this.url);
            Element root = dom.getDocumentElement();
            NodeList items = root.getElementsByTagName("item");
            for (int i = 0; i < items.getLength(); i++) {
                //System.out.println("Entra a clase XMLParser, metodo parsex segundo parseo, despues de for i");
                noticia_k = new Noticia();
                Node item = items.item(i);
                NodeList properties = item.getChildNodes();
                for (int j = 0; j < properties.getLength(); j++) {
                    Node property = properties.item(j);
                    String name = property.getNodeName();
                    if (name.equalsIgnoreCase("title")) {
                        System.out.println("Entra a if de txt_");
                        int delimiter= (property.getFirstChild().getNodeValue()).indexOf(" ] ");
                        titulo=noticia_k.setN_titulo(property.getFirstChild().getNodeValue().substring(delimiter+1));
                        System.out.println("muestra titulo en parsex: "+titulo);
                         } else if (name.equalsIgnoreCase("description")) {
                      //  int delimiter = (property.getFirstChild().getNodeValue()).indexOf("]");
                        contenido=noticia_k.setN_contenido(property.getFirstChild().getNodeValue().substring(0));
                        System.out.println("muestra contenido en parsex: "+contenido);
                    } else if (name.equalsIgnoreCase("twittertime")) {
                        resumen=noticia_k.setN_resumen(property.getFirstChild().getNodeValue());
                        System.out.println("muestra resumen en parsex: "+resumen);
                    }
                }
                noticias_s.add(noticia_k);
              //  Log.i("Parsher", "noticia:" + i);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return noticias_s;
    }
}
