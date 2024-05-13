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
            String tipo = data.get(ExcelDataObjects.COLUMN_TRIP);
            if (tipo.equals("SOLO IDA")) {
                click(driver, PeruRailObjects.INPUT_ONE_WAY);
            } else if (tipo.equals("IDA Y VUELTA")) {
                click(driver, PeruRailObjects.INPUT_ROUND_TRIP);
            }
            else {
                mensaje = "No se encontro el tipo de viaje";
                stepFail(driver,mensaje);
                generateWord.sendText(mensaje);
                generateWord.addImageToWord(driver);
                Assert.fail(mensaje);
            }
        } catch (Exception we) {
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            stepFail(driver,"Fallo en tiempo de respuesta : " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }
    }
}
