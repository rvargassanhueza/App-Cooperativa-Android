/**package com.example.streamingcoop;


 * Created by innova6 on 14-03-2016.

public class Arma_URL implements Runnable{

    public String manda="";
    public String id_publica="";
   // public ArrayList<String> armado = new ArrayList<String>();
    public void recoger_id(String id) {
        /**se crea esta función para obtener la url correcta para abrir la noticia con
         * en base al código obtenido de la clase XMLParser etiqueta del XML prontus_ts
         *una vez obtenido este código (que lo genera por sierto el gestor de contenidos prontus)
         * podemos armar la URL que debe abrir la aplicación

        id_publica=id;
        Thread lanza = new Thread(){
        public void run () {
            String n_id=id_publica;
            System.out.println("Muestra id en recoger id, clase Arma_URL: " + n_id);
            String codigo = n_id.substring(0, 8);
            //System.out.println("muestra codigo cortado, clase Arma_URL: "+codigo);
            String path = ("http://especiales2.cooperativa.cl/noticias/site/artic/" + codigo + "/xml/" + n_id + ".xml");
            System.out.println("Clase Arma_URL Mostrando Path, metodo recoger id: " + path);
            manda=path;
          //  enviar_path(manda);
            Activity_Noticias_Deportes cl_and =null;
            cl_and.inicializarListView(manda);
        }
    };
        lanza.start();
    }

    public void enviar_path (final String path_1){
        System.out.println("Clase Arma_URL, metodo enviar_path, antes de iniciar thread");
        Thread enviar = new Thread(){
            public void run(){
                System.out.println("Clase Arma_URL, metodo enviar_path, dentro del run, mostrnado path_1 enviado :"+path_1);
                String path_enviar = path_1;
                Activity_Noticias_Deportes cl_and =null;
                try {
                    cl_and.inicializarListView(path_enviar);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Se cae en Arma_URL, metodo enviar path...");
                }finally {
                    System.out.println("Se cae en Arma_URL, metodo enviar path, mostrando enviar path: "+path_enviar);
                    cl_and.inicializarListView(path_enviar);
                }
            }
        };
        enviar.start();
    }

    @Override
    public void run() {
                Thread enviar_1 = new Thread(){

                };

       enviar_1.start();
    }

}*/
