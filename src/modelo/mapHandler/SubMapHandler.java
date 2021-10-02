package modelo.mapHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public  class SubMapHandler {

    //Cantones
    public static ArrayList<String> mostrarCantones(String s){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        ArrayList<String> cantones = new ArrayList<>();
        try {
            archivo = new File ("src/modelo/dataBase/db.csv");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            // Lectura del fichero
            String linea;
            String aux;//para guardar el canton
            linea=br.readLine();
            while (linea!=null){
                if (linea.equals(s)) {
                    int limit = Integer.parseInt(s) + 1;
                    while ((linea) != null && !linea.equals(String.valueOf(limit))) {
                        if (linea.equals(linea.toUpperCase()) && linea.length() > 2) {
                            cantones.add(linea);
                        }
                        linea = br.readLine();
                    }
                    break;
                }
                linea = br.readLine();
            }



        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        System.out.println(cantones);
        return  cantones;

    }

    //Distritos
    public static ArrayList<String> mostrarDistritos(String s){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;


        ArrayList<String> distritos = new ArrayList<>();


        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File ("src/modelo/dataBase/db.csv");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            linea=br.readLine();
            while (linea!= null){
                if (linea.equals(s)){
                    linea=br.readLine();
                    while (!linea.equals(linea.toUpperCase())){
                        distritos.add(linea);
                        linea=br.readLine();
                    }
                    break;
                }
                linea=br.readLine();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return distritos;
    }
}
