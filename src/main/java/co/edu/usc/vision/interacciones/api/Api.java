package co.edu.usc.vision.interacciones.api;

import co.edu.usc.vision.interacciones.file.FileChooser;
import co.edu.usc.vision.interacciones.file.FileReader;

public class Api {

    public static void main(String[] args) {

        try {
/*
            for(int i = 0; i <= 100 ; i++){
                Util.printProgBar(i);
                Thread.sleep(100);
            }
/*
            lineasArchivo   = 100%
            totalLineas     =  X

            X = (totalLineas*100%) / lineasArchivo

*/

            System.out.println("\nIniciando migración de interacciones....\n");

            String archivoSeleccionado = FileChooser.getFile();


            if (!archivoSeleccionado.isEmpty()) {
                System.out.println("Se va a leer el archivo " + archivoSeleccionado + "\n");

                FileReader fr = new FileReader(archivoSeleccionado);
                fr.lecturaArchivo();

            } else {
                System.out.println("No existe archivo");
            }


            System.out.println("Finalizando migracion de interacciones...");
            System.exit(0);
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }

}
