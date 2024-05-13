package com.tsoft.bot.frontend.pages.pages.perurail;

import com.tsoft.bot.frontend.base.BaseClass;
import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.pages.objects.ExcelDataObjects;
import com.tsoft.bot.frontend.pages.objects.PeruRailObjects;
import com.tsoft.bot.frontend.utility.ExcelReader;
import com.tsoft.bot.frontend.utility.GenerateWord;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.List;

public class SearchPage extends BaseClass {

    private WebDriver driver;
    private static GenerateWord generateWord = new GenerateWord();
    String mensaje;

    public SearchPage(WebDriver driver) {
        super(driver);
        this.driver = Hook.getDriver();
    }

    private List<HashMap<String, String>> getData() throws Throwable {
        return ExcelReader.data(ExcelDataObjects.EXCEL_SAUCE, ExcelDataObjects.HOJA_LOGIN_SAUCE);
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
            stepFail(driver,"Fallo en tiempo de respuesta : " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }
    }

    public void searchTrip(int indexRow) throws Throwable{
        mensaje = "Se ingresa los datos de busqueda";
        try {
            int index = indexRow - 1;
            HashMap<String, String> data = getSheets(ExcelDataObjects.EXCEL_PERURAIL, ExcelDataObjects.SHEET_SEARCH).get(index);
            String tipo = data.get(ExcelDataObjects.SEARCH_COLUMN_TYPE);
            String partida = data.get(ExcelDataObjects.SEARCH_COLUMN_FROM);
            String destino = data.get(ExcelDataObjects.SEARCH_COLUMN_TO);
            String servicio = data.get(ExcelDataObjects.SEARCH_COLUMN_SERVICE);
            // validacion de boton tipo de viaje
            if (tipo.equals("SOLO IDA")) {
                click(driver, PeruRailObjects.TYPE_BUTTON_ONEWAY);
            } else if (tipo.equals("IDA Y VUELTA")) {
                click(driver, PeruRailObjects.TYPE_BUTTON_ROUNDTRIP);
            }
            else {
                mensaje = "No se encontro el tipo de viaje";
                stepFail(driver,mensaje);
                Assert.fail(mensaje);
            }
            sleep(500);
            // validacion de menu desplegable from
            if(partida.equals("PUNO")){
                click(driver, PeruRailObjects.FROM_SELECT);
                sleep(250);
                click(driver, PeruRailObjects.FROM_OPTION_PUNO);
            } else if(partida.equals("AREQUIPA")){
                click(driver, PeruRailObjects.FROM_SELECT);
                sleep(250);
                click(driver, PeruRailObjects.FROM_OPTION_AREQUIPA);
            } else {
                mensaje = "No se encontro el campo partida";
                stepFail(driver,mensaje);
                Assert.fail(mensaje);
            }
            sleep(500);
            // validacion de menu desplegable destino
            if(destino.equals("CUSCO")){
                click(driver, PeruRailObjects.TO_SELECT);
                sleep(250);
                click(driver, PeruRailObjects.TO_OPTION_CUSCO);
            } else {
                mensaje = "No se encontro el campo destino";
                stepFail(driver,mensaje);
                Assert.fail(mensaje);
            }
            sleep(500);
            // validacion de menu desplegable servicio
            if(servicio.equals("BELMOND ANDEAN EXPLORER")){
                click(driver, PeruRailObjects.SERVICE_SELECT);
                sleep(250);
                click(driver, PeruRailObjects.SERVICE_OPTION_BELMOND);
            } else if(servicio.equals("PERURAIL EXPEDITION")){
                click(driver, PeruRailObjects.SERVICE_SELECT);
                sleep(250);
                click(driver, PeruRailObjects.SERVICE_OPTION_EXPEDITION);
            }
            else {
                // aveces no aparece la opcion por lo tanto
                // no deberia generar error el hecho que no este
            }
            sleep(500);
            // click en boton buscar
            click(driver, PeruRailObjects.SEARCH_BUTTON);
            sleep(500);
            // cambiar de pestaña
            switchToNewTab(driver);
            stepPass(driver,mensaje);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
        } catch (Exception we) {
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            stepFail(driver,"Fallo en tiempo de respuesta : " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }
    }
}
