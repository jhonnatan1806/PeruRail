/*
    @author: Abraham Hernandez - TSOFT
*/
package com.tsoft.bot.frontend.utility;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.Screen;
import org.sikuli.script.ScreenImage;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Logger;


public class GenerateWord {

    private static final String PATH_RELATIVE_WORD = "/src/main/resources/template/Evidencia.docx";
    private static final String TEMPLATE = "/src/main/resources/template/Plantila.png";
    private static final String FILE_PATH_STANDAR = FileHelper.getProjectFolder() + "/target/resultado/";
    private static final String H_ZONE = "America/Bogota";
    private static final String WORD_EXTENSION = ".docx";

    private static String TEMP_WORD_FILE;
    private static XWPFDocument document;
    private static XWPFRun run;
    private static FileOutputStream fileOutputStream;

    private static final String WORD_NAME_STANDARD = "Evidencia.docx";
    private static final String RESULT_FOLDER = "/target/resultado/";
    private static final String TEMPLATE_FOLDER = "/src/main/resources/template/";

    public static void startUpWord(String name) throws IOException {
        // Obtener la ubicacion del archivo de origen
        File sourceFile = new File(FileHelper.getProjectFolder() + TEMPLATE_FOLDER + WORD_NAME_STANDARD);
        // Obtener la carpeta de destino
        File targetFolder = new File(FileHelper.getProjectFolder() + RESULT_FOLDER);
        // Verificar si la carpeta de destino no existe y crearla si es necesario
        if (!targetFolder.exists()) {
            targetFolder.mkdirs();
        }

        // Crear el nombre del archivo de destino
        String fileName = name + "-" + generarSecuencia() + WORD_EXTENSION;
        // Crear el archivo de destino
        File targetFile = new File(targetFolder, fileName);
        // Obtener la ruta absoluta del archivo de destino
        TEMP_WORD_FILE = new File(targetFolder, fileName).getAbsolutePath();
        // Copiar el archivo de origen al archivo de destino
        Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("[LOG] Word generado");

        // Abrir el archivo de destino para lectura
        FileInputStream fis = new FileInputStream(targetFile);
        // Crear un documento XWPFDocument a partir del archivo de destino
        document = new XWPFDocument(fis);
        // Cerrar el flujo de entrada
        fis.close();

        // TODO: Refactorizar - mover el bloque del metodo a un metodo nuevo llamado writeFrontPage
        // Obtener el primer parrafo del documento
        XWPFParagraph paragraph = document.getParagraphs().get(0);
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        run = paragraph.getRuns().get(0);
        // Agregar saltos de linea al inicio del documento
        breakLine(run, 12, BreakType.TEXT_WRAPPING);

        // Crear el primer parrafo del documento
        writeParagraph("Escenario: \"" + name +"\"", 24, true);
        breakLine(run, 1, BreakType.TEXT_WRAPPING);

        // Crear una nueva linea del primer parrafo
        run = paragraph.createRun();
        writeParagraph("DOCUMENTO DE EVIDENCIA", 18, true);
        breakLine(run, 4, BreakType.TEXT_WRAPPING);

        // Crear el segundo parrafo del documento
        XWPFParagraph paragraph2 = document.createParagraph();
        paragraph2.setAlignment(ParagraphAlignment.RIGHT);

        // Crear una nueva linea del segundo parrafo
        run = paragraph2.createRun();
        writeParagraph(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)), 14, true);
        breakLine(run, 1, BreakType.TEXT_WRAPPING);

        // Crear una nueva linea del segundo parrafo
        writeParagraph("Lima - Perú", 14, true);
        breakLine(run, 1, BreakType.TEXT_WRAPPING);

        // Crear el tercer parrafo del documento vacio con alineacion a la izquierda
        XWPFParagraph paragraph3 = document.createParagraph();
        paragraph3.setAlignment(ParagraphAlignment.LEFT);
        run = paragraph3.createRun();

        // Agregar un salto de pagina al final del documento
        breakLine(run, 1, BreakType.PAGE);

        // Inicializar el FileOutputStream
        fileOutputStream = new FileOutputStream(targetFile);
    }

    public static void writeParagraph(String text, int fontSize, boolean isBold) {
        // Modificar el estilo del texto
        run.setText(text);
        run.setFontSize(fontSize);
        run.setBold(isBold);
        run.setFontFamily("Calibri");
        run.setColor("000000");
    }

    public static void breakLine(XWPFRun run, int n, BreakType breakType) {
        // Agregar saltos de linea al documento
        for(int i = 0; i < n; i++) {
            run.addBreak(breakType);
        }
    }

    public static void endToWord(String status) throws IOException {
        // Escribir el contenido del documento en el FileOutputStream
        document.write(fileOutputStream);
        // Cerrar el FileOutputStream
        fileOutputStream.close();
        // Crear un nuevo objeto File para el archivo de destino con el nuevo nombre
        File fileWithNewName = new File(TEMP_WORD_FILE.split("\\.docx")[0] + "-" + status.toUpperCase() + ".docx");
        // Crear un nuevo objeto File para el archivo de destino original
        File renamedFile = new File(TEMP_WORD_FILE);

        // Intentar renombrar el archivo original con el nuevo nombre
        if (renamedFile.renameTo(fileWithNewName)) {
            // Imprimir mensaje de confirmacion si se pudo renombrar el archivo
            System.out.println("[LOG] WORD: Evidencia renombrada - Se añadió el estado final del escenario");
        } else {
            // Imprimir mensaje de error si no se pudo renombrar el archivo
            System.out.println("[LOG] WORD: Evidencia no pudo ser renombrada - No Se añadió el estado final del escenario");
        }

        System.out.println("[LOG] Word cerrado");
    }

    public void sendBreak() {
        run.addBreak();
    }

    public void addImageToWord(WebDriver driver) throws IOException {
        InputStream inputStream = null;
        try {

            TakesScreenshot screenshot = ((TakesScreenshot) driver);

            File source = screenshot.getScreenshotAs(OutputType.FILE);

            inputStream = new FileInputStream(source);

            run.addPicture(inputStream, Document.PICTURE_TYPE_PNG, "1", Units.toEMU(465), Units.toEMU(200));

            run.addBreak();

        } catch (IOException | InvalidFormatException ex) {

            Logger.getLogger("Show: " + ex);
        }finally {
            if (inputStream != null) inputStream.close();
        }

    }


    public void addImageToWord(Screen screen) throws IOException {
        InputStream inputStream = null;
        try {

            ScreenImage screenshot = (screen.capture());

            File source = new File(screenshot.getFile());

            inputStream = new FileInputStream(source);

            run.addPicture(inputStream, Document.PICTURE_TYPE_PNG, "1", Units.toEMU(465), Units.toEMU(200));
            run.addBreak();

        } catch (Exception e) {

            Logger.getLogger("Show: " + e);

        }finally {

            if (inputStream != null) inputStream.close();
        }

    }

    public void sendText(String texto) {
        run.setText("Fecha : " + generarFecha() + ", Hora : " + generarHora() + " | " + texto);
        run.addTab();
        run.setFontFamily("Century Gothic");
        run.setFontSize(9);
    }

    public void sendBoldText(String texto) {
        run.setBold(true);
        run.setText("Fecha : " + generarFecha() + ", Hora : " + generarHora() + " | " + texto);
        run.addTab();
        run.setFontFamily("Century Gothic");
        run.setFontSize(9);
    }

    private static String generarSecuencia() {

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss");

        df.setTimeZone(TimeZone.getTimeZone(H_ZONE));

        return df.format(new Date());

    }

    private static String generarFecha() {

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        df.setTimeZone(TimeZone.getTimeZone(H_ZONE));

        return df.format(new Date());

    }

    private static String generarHora() {
        DateFormat df = new SimpleDateFormat("hh:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone(H_ZONE));

        return df.format(new Date());
    }

}