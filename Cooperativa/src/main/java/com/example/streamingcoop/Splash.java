package com.example.streamingcoop;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;


public class Splash extends Activity {
    private AlertDialog.Builder mBuilderWait;
    private static final long SPLASH_SCREEN_DELAY = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
       // progressbar = (ProgressBar) findViewById(R.id.mi_loading);
       // lanzarthread();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Start the next activity


                Intent mainIntent = new Intent().setClass(
                        Splash.this, MainActivity.class);
                startActivity(mainIntent);

                // Close the activity so the user won't able to go back this
                // activity pressing Back button
                finish();
            }
        };
        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }
    private void lanzarthread() {
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2000); //se duerme por 2 segundos
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();//muestra error de ejecucion en consola
                } finally {//pase lo que pase debe saltar a la siguiente pantalla.
                    Intent intent = new Intent(Splash.this, MainActivity.class); //instanciamos el objeto Intent y lo inicializamos desde splash hasta MainActivity
                    startActivity(intent);
                }
            }

        };
        timer.start();
    }

  /*  @Override
    protected void onPause() { //se implementa este metodo para que al presionar el boton atras
        //no regrese a la pantalla del splash.
        //cuando el activity splash pase a segundo plano por debajo de la principal
        //se cierra con finish();
        // TODO Auto-generated method stub

        super.onPause();
        finish();
    }*/

}
