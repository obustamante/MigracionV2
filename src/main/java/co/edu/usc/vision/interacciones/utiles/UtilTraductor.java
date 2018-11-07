package co.edu.usc.vision.interacciones.utiles;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class UtilTraductor {

    // Clave de suscripción válida
    public static String subscriptionKey = "d3ef21fb62c54c58a9d39b698286703a";

    public static String host = "https://api.cognitive.microsofttranslator.com";

    public static String path = "/translate?api-version=3.0";

    // Traducir al español desde el ingles.
    public static String params = "&to=en&to=es";


    public static String getText(String cadena) {

        JsonParser parser = new JsonParser();
        JsonArray json = (JsonArray) parser.parse(cadena);
/*
        System.out.println("\nTraduccionEs:");
        System.out.println(" 1 --> " + json);
        System.out.println(" 2 --> " + json.get(0));
        System.out.println(" 3 --> " + json.get(0).getAsJsonObject());
        System.out.println(" 4 --> " + json.get(0).getAsJsonObject().get("translations"));
        System.out.println(" 5 --> " + json.get(0).getAsJsonObject().get("translations").getAsJsonArray());
        System.out.println(" 6 --> " + json.get(0).getAsJsonObject().get("translations").getAsJsonArray().get(1));
        System.out.println(" 7 --> " + json.get(0).getAsJsonObject().get("translations").getAsJsonArray().get(1).getAsJsonObject());
        System.out.println(" 8 --> " + json.get(0).getAsJsonObject().get("translations").getAsJsonArray().get(1).getAsJsonObject().get("text"));
        System.out.println(" 9 --> " + json.get(0).getAsJsonObject().get("translations").getAsJsonArray().get(1).getAsJsonObject().get("text").getAsString());
        System.out.println("\n");
*/
        return json.get(0).getAsJsonObject().get("translations").getAsJsonArray().get(1).getAsJsonObject().get("text").getAsString();
    }


}
