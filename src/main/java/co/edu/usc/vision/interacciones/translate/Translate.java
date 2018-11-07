package co.edu.usc.vision.interacciones.translate;

public class Translate {

    /*
    // Replace the subscriptionKey string value with your valid subscription key.
    static String subscriptionKey = "d3ef21fb62c54c58a9d39b698286703a";

    static String host = "https://api.cognitive.microsofttranslator.com";
    static String path = "/translate?api-version=3.0";

    // Translate to German and Italian.
    static String params = "&to=en&to=es";

    static String text = "Hello world!";
*/
/*
    public static void main(String[] args) {
        try {
            System.out.println("Starting....");
            String response = Translate ();
            //System.out.println (prettify (response));
            System.out.println (response);
            System.out.println("Ending...");
            //TraduccionEs(response);

        }
        catch (Exception e) {
            System.out.println (e);
        }
    }
*/

    /*
    public static String Translate () throws Exception {

        URL url = new URL (host + path + params);

        List<RequestBody> objList = new ArrayList<RequestBody>();
        objList.add(new RequestBody(text));
        String content = new Gson().toJson(objList);

        return Post(url, content);
    }
*/


/*
    public static String Post (URL url, String content) throws Exception {
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Content-Length", content.length() + "");
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);
        connection.setRequestProperty("X-ClientTraceId", java.util.UUID.randomUUID().toString());
        connection.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        byte[] encoded_content = content.getBytes("UTF-8");
        wr.write(encoded_content, 0, encoded_content.length);
        wr.flush();
        wr.close();

        StringBuilder response = new StringBuilder ();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();

        return response.toString();
    }
*/


/*
    public static class RequestBody {
        String Text;

        public RequestBody(String text) {
            this.Text = text;
        }
    }
*/


/*
    public static String prettify(String json_text) {
        JsonParser parser = new JsonParser();
        JsonElement json = parser.parse(json_text);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(json);
    }
*/


    /*
    public static String TraduccionEs (String cadena){

        JsonParser parser = new JsonParser();
        JsonArray json = (JsonArray) parser.parse(cadena);

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


        return json.get(0).getAsJsonObject().get("translations").getAsJsonArray().get(1).getAsJsonObject().get("text").getAsString();
    }
*/
}
