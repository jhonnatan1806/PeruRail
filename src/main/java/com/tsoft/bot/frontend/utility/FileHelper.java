/*
    @author: Abraham Hernandez - TSOFT
*/
package com.tsoft.bot.frontend.utility;

import org.apache.commons.io.FileUtils;

import java.io.File;

public class FileHelper {
    private FileHelper() {
    }

    public static String getProjectFolder() {

        return System.getProperty("user.dir");
    }

    public static void deleteFolderElements(String carpeta) {

        // al iniciar borrar todos los archivos png de la carpeta img
        File folder = new File(FileHelper.getProjectFolder() + carpeta);
        if (folder.exists() && folder.isDirectory()) {
            try {
                FileUtils.cleanDirectory(folder);
            } catch (Exception e) {
                System.out.println("[ERROR CRL-946] Error borrando la carpeta " + carpeta + ": " + e.getMessage());
            }
        }
    }
}
