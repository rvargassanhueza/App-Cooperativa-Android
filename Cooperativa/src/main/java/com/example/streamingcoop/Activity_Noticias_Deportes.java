package com.example.streamingcoop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Activity_Noticias_Deportes extends Activity {

    public ArrayList<Noticia> Array_Noticias = new ArrayList<Noticia>();
    public ArrayList<NoticiaSitio> noticias = new ArrayList<NoticiaSitio>();

   // Noticia articulo;
    public String pa="";
    ListView lista;
    Trae t = new Trae();
    public String manda="";
    public String id_publica="";
    private Noticias_Adapter adapter;
  private String URL = "http://especiales2.cooperativa.cl/noticias/site/tax/port/all/rss_1___1.xml";
   // protected String URL = "http://especiales2.cooperativa.cl/noticias/site/tax/port/all/rss_2___1.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        rellenarNoticias();
      //  SetResultado(pa);
        //recoger_id("");

    }
    public void recoger_id(String id) {
        /**se crea esta función para obtener la url correcta para abrir la noticia con
         * en base al código obtenido de la clase XMLParser etiqueta del XML prontus_ts
         *una vez obtenido este código (que lo genera por sierto el gestor de contenidos prontus)
         * podemos armar la URL que debe abrir la aplicación*/

        id_publica=id;
        Thread lanza = new Thread(){
            public void run () {
                String n_id=id_publica;
                System.out.println("Muestra id en recoger id, clase Activity_Noticias_Deportes: " + n_id);
                String codigo = n_id.substring(0, 8);
                //System.out.println("muestra codigo cortado, clase Arma_URL: "+codigo);
                String path = ("http://especiales2.cooperativa.cl/noticias/site/artic/" + codigo + "/xml/" + n_id + ".xml");
                System.out.println("Clase Activity_Noticias_Deportes Mostrando Path, metodo recoger id: " + path);
                manda=path;
                //  enviar_path(manda);
               // Activity_Noticias_Deportes cl_and =null;
               // cl_and.inicializarListView(manda);
            }
        };
        lanza.start();
    }
    public void inicializarListView() {
        lista = (ListView) findViewById(R.id.noticias_listview);
        adapter = new Noticias_Adapter(this, Array_Noticias);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent intent = new Intent(Activity_Noticias_Deportes.this, Activity_Articulo.class);
                intent.putExtra("parametro", Array_Noticias.get(arg2));
                //intent.putExtra("parametro", "Artículo número "+(arg2+1));
                startActivity(intent);
            }
        });
    }


        /*System.out.println("Clase Activity_Noticias_Deportes, metodo inicializarListView, mostrando url: "+pe);
      //  Arma_URL arma = new Arma_URL();
      //  String codigo_ = arma.recogerid(pe);
        //System.out.println("muestra codigo de clase Arma: "+codigo_);
        lista = (ListView) findViewById(R.id.noticias_listview);
        adapter = new Noticias_Adapter(this, Array_Noticia);
        lista.setAdapter(adapter);
       // final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        lista.setOnItemClickListener(new OnItemClickListener() {
            String po = "http://especiales2.cooperativa.cl/2016/pruebas/rvargas_test/prueba.xml";

            //  public URL url= new URL("http://especiales2.cooperativa.cl/noticias/site/artic/20160229/xml/20160229140045.xml");
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                System.out.println("Muestra po al click: " + po);
                // recoger_id(id_publica);
                try {
                    t.rellenarNoticias(po);
                    Intent intent = new Intent(Activity_Noticias_Deportes.this, Activity_Articulo.class);
                    intent.putExtra("parametro", Array_Noticia.get(arg2));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Aqui cae, Activity_Noticias_Deportes, al click en try/catch"+e);
                }
            }
        });
        }*/
       private void rellenarNoticias() {
        if (isOnline()) {
            new DescargarNoticias(getBaseContext(), URL).execute();
        }
    }
    private class DescargarNoticias extends AsyncTask<String, Void, Boolean> {
        private String feedUrl;
        private Context ctx;
        public DescargarNoticias(Context c, String url) {
            this.feedUrl = url;
            this.ctx = c;
        }
        protected Boolean doInBackground(final String... args) {
            XMLParser parser = new XMLParser(feedUrl, getBaseContext());
            System.out.println("muestra arreglo de primer parseo, clase Activity_Noticias_deportes, metodo doInBackgroung: "+Array_Noticias.size());
            Array_Noticias = parser.parse();
            System.out.println("muestra arreglo de primer parseo posterior a parseo, clase Activity_Noticias_deportes, metodo doInBackgroung: "+Array_Noticias.size());
            return true;
        }
        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                try {
                    inicializarListView();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(ctx, "Error en la lectura", Toast.LENGTH_LONG)
                        .show();
            }
        }
    }


    public void SetResultado(String s) {
        System.out.println("muestra strng: "+s);
        //pa=s;
       // System.out.println("muestra asdapa: " + pa);
       // this.inicializarListView(s);
      //  return s;
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