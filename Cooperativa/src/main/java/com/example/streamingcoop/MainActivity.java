package com.example.streamingcoop;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends Activity implements View.OnClickListener {

    static MediaPlayer mPlayer;
    ImageButton buttonPlay;
    ImageButton buttonStop;
    String url = "http://unlimited1-us.digitalproserver.com/cooperativafm/mp3/icecast.audio";
    Button btn_politica, btn_deportes, btn_espectaculo, btn_entretencion;
    ProgressBar progressbar;

    /* estos elementos se ocultan al tener la imagen del loading mostrandose en pantalla*/
    TableRow tb_r1;
    TableRow tb_r2;
    TextView txt_not;
    RelativeLayout reproductor;

    //Declaración de las tareas ejecutadas en segundo plano
    //tarea1-> inicialización del player al cargar el activity
    private MiTareaAsincrona tarea1;
    //tarea2-> mostrar loading al cargar url del mplayer
    private MiTareaAsincrona_2 tarea2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.splash);//analiza el archivo XML, traduce a objetos cada componente,
        //le asigna los atributos, establece contenedores y todas las relaciones
        //padre e hijo necesarias.
        initialize();

        progressbar = (ProgressBar) findViewById(R.id.mi_loading);
        buttonPlay = (ImageButton) findViewById(R.id.play);
        buttonStop = (ImageButton) findViewById(R.id.stop);


         txt_not= (TextView) findViewById(R.id.txt_noticias);
         tb_r1= (TableRow) findViewById(R.id.tableRow1);
         tb_r2= (TableRow) findViewById(R.id.tableRow2);
         reproductor= (RelativeLayout) findViewById(R.id.reproductor);

         txt_not.setVisibility(View.INVISIBLE);
         tb_r1.setVisibility(View.INVISIBLE);
         tb_r2.setVisibility(View.INVISIBLE);
         reproductor.setVisibility(View.INVISIBLE);

       // buttonStop.setVisibility(View.VISIBLE);
       // buttonPlay.setVisibility(View.INVISIBLE);
        progressbar.setVisibility(View.VISIBLE);

        mPlayer = new MediaPlayer();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        tarea2=new MiTareaAsincrona_2();
        tarea2.execute();


        //Bloque de codigo para el streaming al presionar play
       buttonPlay.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               tarea1 = new MiTareaAsincrona();
               tarea1.execute();
               progressbar.setVisibility(View.VISIBLE);
               buttonPlay.setVisibility(View.INVISIBLE);
               buttonStop.setVisibility(View.VISIBLE);
           }
        });

        //Bloque de codigo para el streaming al presionar pause
            buttonStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              if (mPlayer != null && mPlayer.isPlaying()) {
                   // mPlayer.stop();
                  mPlayer.pause();
                  buttonPlay.setVisibility(View.VISIBLE);
                    buttonStop.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
      public class MiTareaAsincrona extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
           // play_radio();
            progressbar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
    //            Toast.makeText(getApplicationContext(), "Ya cargó!!", Toast.LENGTH_LONG).show();
           // mPlayer.start();
            try {
                mPlayer.reset();
                mPlayer.setDataSource(url);
                mPlayer.prepare();
                mPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
                return true;
        }
    }

    private class MiTareaAsincrona_2 extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            progressbar.setVisibility(View.INVISIBLE);
            txt_not.setVisibility(View.VISIBLE);
            tb_r1.setVisibility(View.VISIBLE);
            tb_r2.setVisibility(View.VISIBLE);
            reproductor.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            //            Toast.makeText(getApplicationContext(), "Ya cargó!!", Toast.LENGTH_LONG).show();
            // mPlayer.start();
            try {
                mPlayer.reset();
                mPlayer.setDataSource(url);
                mPlayer.prepare();
                mPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }
    private void initialize() {
        btn_deportes = (Button) findViewById(R.id.button1);
        btn_deportes.setOnClickListener(this);

        btn_politica = (Button) findViewById(R.id.button2);
        btn_politica.setOnClickListener(this);

        btn_espectaculo = (Button) findViewById(R.id.button3);
        btn_espectaculo.setOnClickListener(this);

        btn_entretencion = (Button) findViewById(R.id.button4);
        btn_entretencion.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                startActivity(new Intent(this, Activity_Noticias_Deportes.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this, Activity_Noticias_Politica.class));
                break;
            case R.id.button3:
                startActivity(new Intent(this, Activity_Noticias_Economia.class));
                break;
            case R.id.button4:
                startActivity(new Intent(this, Activity_Noticias_Entretencion.class));
                break;
        }
    }
    protected void onDestroy() {
        super.onDestroy();
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
}