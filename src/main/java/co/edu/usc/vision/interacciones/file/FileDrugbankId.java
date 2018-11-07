package co.edu.usc.vision.interacciones.file;

import co.edu.usc.vision.interacciones.dao.model.Drugbankid;
import co.edu.usc.vision.interacciones.dao.model.DrugbankidExample;
import co.edu.usc.vision.interacciones.dao.model.DrugbankidMapper;
import co.edu.usc.vision.interacciones.utiles.DbUtils;
import co.edu.usc.vision.interacciones.utiles.Util;
import org.apache.ibatis.session.SqlSession;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class FileDrugbankId {

    private static String nombArchivoXml;

    public FileDrugbankId(String archivoXml) {
        nombArchivoXml = archivoXml;
    }


    public void cargarDrugbankId() {

        final String regexDrugId = "<drugbank-id primary=\"true\">(.+?)<.drugbank-id>";

        Scanner sc = null;

        try {

            sc = new Scanner(new File(nombArchivoXml));
            int totalId = 0;

            SqlSession sqlSession = DbUtils.getSession();

            DrugbankidMapper drugbankidMapper = sqlSession.getMapper(DrugbankidMapper.class);

            DrugbankidExample example = new DrugbankidExample();
            example.createCriteria().andIdIsNotNull();

            List<Drugbankid> drugbankids = drugbankidMapper.selectByExample(example);

            System.out.println("Se encontraron " + drugbankids.size() + " registro(s)");

            //Borrando registros existentes
            if (drugbankids.size() != 0) {
                System.out.println("Borrando " + drugbankids.size() + " registro(s) de la tabla Drugbankid...");
                drugbankidMapper.deleteByExample(example);
                sqlSession.commit();
                System.out.println("Fin del borrado de la tabla Drugbankid");
                //drugbankids = drugbankidMapper.selectByExample(example);
                //System.out.println(drugbankids.size() + "\n");
            }

            while (sc.hasNextLine()) {
                String txt = sc.nextLine().trim();

                if (txt.matches("<drug type=\\\"\\w+\\\" created=\\\".*\\\">")) {
                    //System.out.println(txt);
                    totalId++;
                    Drugbankid drugbankid = new Drugbankid();
                    drugbankid.setId(totalId);

                    txt = sc.nextLine().trim();

                    // verifica si es una llave de Drugbank
                    if (txt.matches(regexDrugId)) {
                        //System.out.println("    " + txt);
                        drugbankid.setDrugbankId(Util.getText(txt));
                    }

                    while (!txt.equals("<atc-codes>")) {
                        txt = sc.nextLine().trim();
                    }

                    txt = sc.nextLine().trim();
                    // obtiene el código ATC
                    if (txt.matches("<atc-code code=\"(.+?)\">")) {
                        //System.out.println("        " + txt);
                        drugbankid.setAtc(Util.getAtcText(txt));
                    }


                    //int reslut = drugbankidMapper.insert(drugbankid);

                    if (drugbankidMapper.insert(drugbankid) < 0) {
                        System.out.println("Error guardando el drugbankid  {" + drugbankid.getDrugbankId() + "} con su atc {" + drugbankid.getAtc() + "}");
                    }

                }

            }

            sqlSession.commit();

            drugbankids = drugbankidMapper.selectByExample(example);
            System.out.println("Se cargaron " + drugbankids.size() + " registro(s)");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
