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

public class Activity_Noticias_Entretencion extends Activity {

    public ArrayList<Noticia> Array_Noticias = new ArrayList<Noticia>();
    ListView lista;
    private Noticias_Adapter adapter;
    //private String URL = "http://especiales2.cooperativa.cl/noticias/site/tax/port/all/rss_3_156_2063_1.xml";
    private String URL = "http://especiales2.cooperativa.cl/noticias/site/tax/port/all/rss_4___1.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        rellenarNoticias();
    }
    private void inicializarListView() {
        lista = (ListView) findViewById(R.id.noticias_listview);
        adapter = new Noticias_Adapter(this, Array_Noticias);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent intent = new Intent(Activity_Noticias_Entretencion.this, Activity_Articulo.class);
                intent.putExtra("parametro", Array_Noticias.get(arg2));
                //intent.putExtra("parametro", "Artículo número "+(arg2+1));
                startActivity(intent);
            }
        });
    }

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

        @Override
        protected Boolean doInBackground(final String... args) {
            XMLParser parser = new XMLParser(feedUrl, getBaseContext());
            Array_Noticias = parser.parse();
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
