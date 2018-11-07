package co.edu.usc.vision.interacciones.utiles;

import java.text.DecimalFormat;

/**
 * Created by vision on 1/09/17.
 */
public class Util {

    /**
     * Obtiene el salto de linea dependiento del sistema operativo
     */
    public static String saltoDeLinea = System.getProperty("line.separator");//This will retrieve line separator dependent on OS.

    /**
     * Formatea un numero con los separadores
     *
     * @param numero
     * @return
     */
    public static String formatoNumero(int numero) {
        DecimalFormat myFormatter = new DecimalFormat("###,###.###");
        return myFormatter.format(numero);
    }

    public static String getText(String txt) {

        int inicio = txt.indexOf(">");
        int fin = txt.lastIndexOf("</");

        String s = txt.substring((inicio + 1), fin);

        return s;
    }


    public static String getAtcText(String txt) {

        int inicio = txt.indexOf("\"");
        int fin = txt.lastIndexOf("\">");

        String s = txt.substring((inicio + 1), fin);

        return s;
    }

    public static void printProgBar(int percent) {

        StringBuilder bar = new StringBuilder("[");

        for (int i = 0; i < 100; i++) {
            if (i < (percent / 2)) {
                bar.append("=");
            } else if (i == (percent / 2)) {
                bar.append(">");
            } else {
                bar.append(" ");
            }
        }

        bar.append("]   " + percent + "%     ");
        System.out.print("\r" + bar.toString());
    }

}
