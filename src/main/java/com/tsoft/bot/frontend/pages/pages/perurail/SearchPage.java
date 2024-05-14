package com.tsoft.bot.frontend.pages.pages.perurail;

import com.tsoft.bot.frontend.base.BaseClass;
import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.pages.objects.ExcelDataObjects;
import com.tsoft.bot.frontend.pages.objects.PeruRailObjects;
import com.tsoft.bot.frontend.utility.ExcelReader;
import com.tsoft.bot.frontend.utility.GenerateWord;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.HashMap;

public class SearchPage extends BaseClass {

    private final WebDriver driver;
    private static final GenerateWord generateWord = new GenerateWord();
    String mensaje;

    public SearchPage(WebDriver driver) {
        super(driver);
        this.driver = Hook.getDriver();
    }

    public void openUrl(String readProperties) throws Exception {
        mensaje = "Se ingresa a la URL de PeruRail";
        try{
            driver.get(readProperties);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
            stepPass(driver,mensaje);
        }catch(Exception we){
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            mensaje = "Fallo en tiempo de respuesta";
            stepFail(driver,mensaje);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
            Assert.fail(mensaje);
        }
    }

    public void searchTrip(int indexRow) throws Throwable{
        mensaje = "Se ingresa los datos de busqueda";
        try {
            int index = indexRow - 1;
            HashMap<String, String> data = getSheets(ExcelDataObjects.EXCEL_PERURAIL, ExcelDataObjects.SHEET_SEARCH).get(index);
            // validaciones
            validationType(data.get(ExcelDataObjects.SEARCH_COLUMN_TYPE));
            validationFrom(data.get(ExcelDataObjects.SEARCH_COLUMN_FROM));
            validationTo(data.get(ExcelDataObjects.SEARCH_COLUMN_TO));
            validationService(data.get(ExcelDataObjects.SEARCH_COLUMN_SERVICE));
            // se ingresan los datos correctamente
            stepPass(driver,mensaje);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
            // click en boton buscar
            click(driver, PeruRailObjects.BUTTON_SEARCH);
            // cambiar de pestaña
            switchToNewTab(driver);
        } catch (Exception we) {
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            mensaje = "Fallo en tiempo de respuesta";
            stepFail(driver,mensaje);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
            Assert.fail(mensaje);
        }
    }

    private void validationType(String type) throws Exception {
        sleep(500);
        if (type.equals("SOLO IDA")) {
            click(driver, PeruRailObjects.BUTTON_TYPE_ONEWAY);
        } else if (type.equals("IDA Y VUELTA")) {
            click(driver, PeruRailObjects.BUTTON_TYPE_ROUNDTRIP);
        }
        else {
            mensaje = "No se encontro el tipo de viaje";
            stepFail(driver,mensaje);
            Assert.fail(mensaje); // retorna error en la prueba
        }
    }

    private void validationFrom(String from) throws Exception {
        sleep(500);
        if(from.equals("PUNO")){
            click(driver, PeruRailObjects.SELECT_FROM);
            sleep(250);
            click(driver, PeruRailObjects.OPTION_FROM_PUNO);
        } else if(from.equals("AREQUIPA")){
            click(driver, PeruRailObjects.SELECT_FROM);
            sleep(250);
            click(driver, PeruRailObjects.OPTION_FROM_AREQUIPA);
        } else {
            mensaje = "No se encontro el campo partida";
            stepFail(driver,mensaje);
            Assert.fail(mensaje); // retorna error en la prueba
        }
    }

    private void validationTo(String to) throws Exception {
        sleep(500);
        if(to.equals("CUSCO")){
            click(driver, PeruRailObjects.SELECT_TO);
            sleep(250);
            click(driver, PeruRailObjects.OPTION_TO_CUSCO);
        } else {
            mensaje = "No se encontro el campo destino";
            stepFail(driver,mensaje);
            Assert.fail(mensaje); // retorna error en la prueba
        }
    }

    private void validationService(String service) throws Exception {
        sleep(500);
        if(service.equals("BELMOND ANDEAN EXPLORER")){
            click(driver, PeruRailObjects.SELECT_SERVICE);
            sleep(250);
            click(driver, PeruRailObjects.OPTION_SERVICE_BELMOND);
        } else if(service.equals("PERURAIL EXPEDITION")){
            click(driver, PeruRailObjects.SELECT_SERVICE);
            sleep(250);
            click(driver, PeruRailObjects.OPTION_SERVICE_EXPEDITION);
        }
        else {
            mensaje = "No se encontro el campo servicio";
            stepFail(driver,mensaje);
            Assert.fail(mensaje); // retorna error en la prueba
        }
    }

    public void validateTrainsPage() throws IOException {
        sleep(15000);
    }
}
