package com.example.streamingcoop;

/**
 * Created by innova6 on 29-02-2016.
 * esta clase arma junto con el diseño que le doy en htmlnoticia, todo el contenido de la noticia seleccionada
 * de la lista que se muestra al seleccionar alguna sección Politica, Deporte, Cultura, Entretención.
 */

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Activity_Articulo extends Activity {
    Noticia articulo;
    public String lk="";
    public static boolean flag = false;
    //NoticiaSitio base_nt;
    //Noticia articulo=new Noticia();

    //public ArrayList<NoticiaSitio> Array_Noticias = new ArrayList<NoticiaSitio>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulo);
        recogerparametro();
        populateWebView();
    }

    private void recogerparametro() {
       articulo = (Noticia) getIntent().getExtras().getSerializable(
                "parametro");
           }

   /* public void recogerid()  {
       //  ArrayList<Noticia> Array_Noticia = new ArrayList<Noticia>();
         String id = articulo.getProntus_ts();
         //Log.i("entra a recoid " ,"");
         String codigo = id.substring(0, 8);
       Activity_Noticias_Deportes cl_ant =new Activity_Noticias_Deportes(); //claseActivity_Noticias_Deportes=cl_ant
        // String path = ("http://especiales2.cooperativa.cl/noticias/site/artic/20160229/xml/20160229140045.xml");
         String path = ("http://especiales2.cooperativa.cl/noticias/site/artic/" + codigo + "/xml/" + id + ".xml");
         System.out.println("Activity_Articulo-método recogerid Mostrando Path: " + path);
        // String prueba =articulo.getPath_artic_xml(path);
        // System.out.println("Muestra path en a_a: "+prueba);
       cl_ant.SetResultado(path);
    }*/

    private void populateWebView() {
      /*  WebView webview = (WebView) findViewById(R.id.articulo_Webview);
                              webview.loadDataWithBaseURL(null, "<!DOCTYPE HTML>"
                                      + populateHTML(R.raw.htmlnoticia), "text/html", "UTF-8", null);*/
        lk=articulo.getEnlace();
        final WebView myWebView;
        myWebView = new WebView(this);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl(lk);
        setContentView(myWebView);
        myWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                if (url.contains("#") && flag == false) {
                    myWebView.loadUrl(url);
                    flag = true;
                } else {
                    flag = false;
                }
            }
        });
    }
    private String populateHTML(int resourceID) {
        String html;
        html = readTextFromResource(resourceID);
        html = html.replace("_TITLE_", articulo.getTitulo());
        html = html.replace("_CONTENT_", articulo.getContenido());
        html= html.replace("_LINK_",articulo.getEnlace());
        html = html.replace("_PUBDATE_",""+articulo.getFecha());
        return html;
    }
    private String readTextFromResource(int resourceID) {
        InputStream raw = getResources().openRawResource(resourceID);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        int i;
        try {
            i = raw.read();
            while (i != -1) {
                stream.write(i);
                i = raw.read();
            }
            raw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream.toString();
    }
}