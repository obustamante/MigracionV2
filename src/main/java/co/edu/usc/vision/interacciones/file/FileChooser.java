package co.edu.usc.vision.interacciones.file;

import javax.swing.*;
import java.awt.*;

public class FileChooser {

    //Create a file chooser
    final JFileChooser fc = new JFileChooser();

    public static void main(String[] args) {

        FileChooser chooser = new FileChooser();
        getFile();

    }

    public static String getFile() {

        FileDialog dialog = new FileDialog((Frame) null, "Seleccionar archivo para abrir");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);

        String file = dialog.getFile();
        String path = dialog.getDirectory() + file;
        /*
            System.out.println(file + " chosen.");
            System.out.println("path:" + dialog.getDirectory());
        */

        System.out.println("Full path: " + path);

        return path;
    }


}
