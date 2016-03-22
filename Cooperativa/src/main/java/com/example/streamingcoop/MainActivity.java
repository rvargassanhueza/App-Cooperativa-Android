package com.example.streamingcoop;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.io.IOException;

public class MainActivity extends Activity implements View.OnClickListener {

    static MediaPlayer mPlayer;
    ImageButton buttonPlay;
    ImageButton buttonStop;
    String url = "http://unlimited1-us.digitalproserver.com/cooperativafm/mp3/icecast.audio";
    Button btn_politica, btn_deportes, btn_espectaculo, btn_entretencion;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.splash);//analiza el archivo XML, traduce a objetos cada componente,
        //le asigna los atributos, establece contenedores y todas las relaciones
        //padre e hijo necesarias.

        progressbar = (ProgressBar) findViewById(R.id.mi_loading);
        // progressbar_splash=(ProgressBar) findViewById(R.id.mi_loading_splash);
        progressbar.setVisibility(View.VISIBLE);

        try {
            //Bloque de codigo para el streaming al cargar el activity
            mPlayer = new MediaPlayer();
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mPlayer.setDataSource(url);
            mPlayer.prepare();
        } catch (IOException e) {
            Log.e("ERROR", e.getMessage());
            Toast.makeText(getApplicationContext(), "Ocurrio un ERROR!!", Toast.LENGTH_LONG).show();
        }

        mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                progressbar.setVisibility(View.INVISIBLE);
               // Toast.makeText(getApplicationContext(), "Ya cargó!!", Toast.LENGTH_LONG).show();

                mPlayer.start();
            }
        });
        //Finaliza bloque de codigo para cargar el streaming al inicio de la aplicacion+
        initialize();
        //Bloque de codigo para el streaming al presionar play
        buttonPlay = (ImageButton) findViewById(R.id.play);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
               progressbar.setVisibility(View.VISIBLE);
                if (mPlayer != null && !mPlayer.isPlaying()) {
                    try {
                        mPlayer.prepare();
                        mPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    buttonPlay.setVisibility(View.INVISIBLE);
                    buttonStop.setVisibility(View.VISIBLE);
                }

            }
        });

        //Bloque de codigo para el streaming al presionar pause
        buttonStop = (ImageButton) findViewById(R.id.stop);
        buttonStop.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
              if (mPlayer != null && mPlayer.isPlaying()) {
                    mPlayer.stop();
                    buttonPlay.setVisibility(View.VISIBLE);
                    buttonStop.setVisibility(View.INVISIBLE);
                }
            }
        });
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