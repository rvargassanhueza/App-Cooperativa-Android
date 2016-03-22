package com.example.streamingcoop;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import java.util.ArrayList;


/**
 * Created by innova6 on 02-03-2016.
 */
public class Trae extends Activity {
    NoticiaSitio articulo;
   // NoticiaSitio codigo;
   // Activity_Articulo aa;

   // public ArrayList<NoticiaSitio> Array_Noticias = new ArrayList<NoticiaSitio>();
    public ArrayList<Noticia> Array_Noticias = new ArrayList<Noticia>();
    // private String URL = "http://especiales2.cooperativa.cl/2016/pruebas/rvargas_test/prueba.xml";

  // public String dato = "http://especiales2.cooperativa.cl/noticias/site/artic/20160229/xml/20160229140045.xml";
  //  public String pa ="";
    public String dato ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        String path="";
        rellenarNoticias(path);

    }

  /*  public void setResult(String s) {
      //  pa=s;
        dato=s;
        System.out.println("recibe variable  clase Trae dato:" + dato);
    }
    public void recibe_url(String u){
System.out.println("trae datos vargas: " + u);
Activity_Noticias_Deportes url_vargas =new Activity_Noticias_Deportes();
        url_vargas.inicializarListView(""+u);
    }*/
      public void rellenarNoticias(String path) {
          //cc=url que se envia desde Activity Desportes
            dato=path;
//
          System.out.println("URL recibida en metodo rellenarnoticias clase trae: " + dato.toString());
          new DescargarNoticias(getBaseContext(), dato).execute();


       /* if (isOnline()) {
            //Log.i("muestra path Trae"+dato,"");
            System.out.println("Clase Trae metodo rellenar noticia, mostrando path: "+dato);
            new DescargarNoticias(getBaseContext(), dato).execute();

        }else {
            //Log.i("elseisOnline"+dato,"");
            System.out.println("Else de isOnline, ejecutando desargarNoticias Activity Trae");
            new DescargarNoticias(getBaseContext(), dato).execute();

        }*/


    }

   public class DescargarNoticias extends AsyncTask<String, Void, Boolean> {

        private String feedUrl;
        public Context ctx;

        public DescargarNoticias(Context c, String url) {
            this.feedUrl = url;
            this.ctx = c;
        }
            @Override
       protected Boolean doInBackground(final String... args) {
           XMLParser parser = new XMLParser(feedUrl, getBaseContext());
           System.out.println("Mostrando Path en clase Trae funcion doInBackground: " + feedUrl);
           if (Array_Noticias.size() > 0) {
               Array_Noticias = parser.parsex();
           } else {
               Array_Noticias = parser.parsex();
               System.out.println("pasa");
               // System.out.println("Clase Trae, método doInBackground");
               System.out.println("muestra arreglo de segundo parseo posterior a parseo, clase Trae, metodo doInBackgroung: " + Array_Noticias.size());
           }
           return true;
       }
    }
        public boolean isOnline() {
            ConnectivityManager cm = (ConnectivityManager) getApplication()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()) {
                return true;
            }
            return false;
        }
}
