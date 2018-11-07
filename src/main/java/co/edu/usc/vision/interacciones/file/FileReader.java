package co.edu.usc.vision.interacciones.file;

import co.edu.usc.vision.interacciones.dao.model.*;
import co.edu.usc.vision.interacciones.traductor.Traduccion;
import co.edu.usc.vision.interacciones.utiles.DbUtils;
import co.edu.usc.vision.interacciones.utiles.Util;
import org.apache.ibatis.session.SqlSession;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    //private static String nombArchivoXml = "/Users/foxcar/Desktop/full database - corta.xml";
    private static String nombArchivoXml;
    private static int totalLineasArchivo;
    final String regexDrugId = "<drugbank-id primary=\"true\">(.+?)<.drugbank-id>";
    int totalLineas = 0;

    /**
     * @param nombArchivoXml
     */
    public FileReader(String nombArchivoXml) {
        FileReader.nombArchivoXml = nombArchivoXml;

        init();

    }

    public static int countLinesNew() throws IOException {

        InputStream is = new BufferedInputStream(new FileInputStream(nombArchivoXml));
        try {
            byte[] c = new byte[1024];

            int readChars = is.read(c);
            if (readChars == -1) {
                // bail out if nothing to read
                return 0;
            }

            // make it easy for the optimizer to tune this loop
            int count = 0;
            while (readChars == 1024) {
                for (int i = 0; i < 1024; ) {
                    if (c[i++] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }

            // count remaining characters
            while (readChars != -1) {
                System.out.println(readChars);
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }

            return count == 0 ? 1 : count;
        } finally {
            is.close();
        }
    }

    public void lecturaArchivo() throws Exception {

        Drugbank drugbank = new Drugbank();
        int contador = 0;

        // pass the path to the file as a parameter
        File file = new File(nombArchivoXml);
        Scanner sc = new Scanner(file);

        SqlSession sqlSession = DbUtils.getSession();
        DrugbankidMapper drugbankidMapper = sqlSession.getMapper(DrugbankidMapper.class);
        DrugbankidExample drugbankidExample;

        DrugbankMapper drugbankMapper = sqlSession.getMapper(DrugbankMapper.class);
        DrugbankExample drugbankExample = new DrugbankExample();
        drugbankExample.createCriteria().andIdIsNotNull();
        List<Drugbank> drugbankList = drugbankMapper.selectByExample(drugbankExample);

        if (drugbankList.size() != 0) {
            System.out.println("\n**********************************************************************");
            System.out.println("Borrando " + drugbankList.size() + " registro(s) de la tabla Drugbank...");
            drugbankMapper.deleteByExample(drugbankExample);
            sqlSession.commit();
            System.out.println("Fin del borrado de la tabla Drugbank");
            System.out.println("**********************************************************************\n");
        }


        while (sc.hasNextLine()) {

            String txt = sc.nextLine().trim();
            incremento(); // totalLineas += 1;

            if (txt.matches("<drug type=\\\"\\w+\\\" created=\\\".*\\\">")) {
                //System.out.println("Nuevo medicamento...");
                //System.out.println(txt);                            // Ej: <drug type="biotech" created="2005-06-13" updated="2016-08-17">

                txt = sc.nextLine().trim();
                incremento(); // totalLineas += 1;

                // verifica si es una llave de Drugbank
                if (txt.matches(regexDrugId)) {
                    //System.out.println("    " + txt);
                    drugbank.setDrugbankId(Util.getText(txt));      //Ej: <drugbank-id primary="true">DB00001</drugbank-id>
                }

                while (!txt.equals("<atc-codes>")) {
                    txt = sc.nextLine().trim();
                    incremento(); // totalLineas += 1;
                }

                txt = sc.nextLine().trim();
                incremento(); // totalLineas += 1;
                // obtiene el código ATC
                if (txt.matches("<atc-code code=\"(.+?)\">")) {
                    //System.out.println("        " + txt);
                    drugbank.setAtc(Util.getAtcText(txt));          //Ej: <atc-code code="B01AE02">
                }

                while (!txt.equals("<drug-interactions>")) {
                    txt = sc.nextLine().trim();
                    incremento(); // totalLineas += 1;
                }

                txt = sc.nextLine().trim();
                incremento(); // totalLineas += 1;
                if (txt.equals("<drug-interaction>")) {

                    while (txt.equals("<drug-interaction>")) {

                        // System.out.println("            " + txt);       // <drug-interaction>

                        txt = sc.nextLine().trim();
                        incremento(); // totalLineas += 1;
                        //System.out.println("                " + txt);   // <drugbank-id>
                        drugbank.setDrugbankId(Util.getText(txt));

                        drugbankidExample = new DrugbankidExample();
                        drugbankidExample.createCriteria().andDrugbankIdEqualTo(Util.getText(txt));
                        List<Drugbankid> drugbankids = drugbankidMapper.selectByExample(drugbankidExample);
                        if (drugbankids.size() != 0) {
                            drugbank.setInteraccion(drugbankids.get(0).getAtc());
                        } else {
                            drugbank.setInteraccion("N/A");
                        }

                        txt = sc.nextLine().trim();
                        incremento(); // totalLineas += 1;
                        //System.out.println("                " + txt);   // <name>

                        txt = sc.nextLine().trim();
                        incremento(); // totalLineas += 1;
                        //System.out.println("                " + txt);   // <description>
                        drugbank.setDescripcionEn(Util.getText(txt));

                        //TODO se debe realizar la traduccion del text de EN a ES
                        //drugbank.setDescripcionEs("Pendiente de traducción");
                        drugbank.setDescripcionEs(Traduccion.Spanish(drugbank.getDescripcionEn()));


                        txt = sc.nextLine().trim();
                        incremento(); // totalLineas += 1;
                        //System.out.println("            " + txt);       // </drug-interaction>


                        txt = sc.nextLine().trim();
                        incremento(); // totalLineas += 1;

                        contador++;
                        drugbank.setId(new BigDecimal(contador + ""));
/*
                        //System.out.println("\n ************************************* \n");
                        System.out.println("Id.............. " + drugbank.getId());
                        System.out.println("DrugbankId...... " + drugbank.getDrugbankId());
                        System.out.println("Atc............. " + drugbank.getAtc());
                        System.out.println("Interaccion..... " + drugbank.getInteraccion());
                        System.out.println("DescripcionEn... " + drugbank.getDescripcionEn());
                        System.out.println("DescripcionEs... " + drugbank.getDescripcionEs());
                        System.out.println("\n ************************************* \n");
*/
                        // realiza el insert
                        drugbankMapper.insert(drugbank);
                        sqlSession.commit();


                    }

                }

            }

            sqlSession.commit();

        }

        System.out.println("\n\nFin de la lectura de archivo. " + Util.saltoDeLinea + "Total de lineas = " + Util.formatoNumero(totalLineas));

    }

    private void incremento() {

        totalLineas++;
        // totalLineas += 1;


        int porcent = (totalLineas * 100) / totalLineasArchivo;

        //Util.printProgBar(porcent);

        if (totalLineas == 4774) { //2529 - 2539
            System.out.println(" ");
        }

        StringBuilder bar = new StringBuilder("[");
        bar = new StringBuilder(Util.formatoNumero(totalLineas) + " lineas - " + porcent + "%");
        System.out.print("\r  " + bar.toString());

    }

    private void init() {

        try {
            totalLineasArchivo = countLinesNew();
            System.out.println("El total de las lineas a procesar [" + Util.formatoNumero(totalLineasArchivo) + "]");

        } catch (IOException e) {
            System.out.println("Error contando el total del lineas del archivo " + nombArchivoXml);
            e.printStackTrace();
        }


        CargarDrugbankId();

    }

    private void CargarDrugbankId() {

        System.out.println("\n+-------------------------------------------------------------------+");
        System.out.println("Se cargan los drugbank-id con el ATC respectivo\n");

        FileDrugbankId fileDrugbankId = new FileDrugbankId(nombArchivoXml);
        fileDrugbankId.cargarDrugbankId();

        System.out.println("\nTermino de cargar los drugbank-id con el ATC respectivo");
        System.out.println("+-------------------------------------------------------------------+\n");

    }

}
