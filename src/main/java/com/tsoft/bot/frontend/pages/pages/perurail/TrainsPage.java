package com.tsoft.bot.frontend.pages.pages.perurail;

import com.tsoft.bot.frontend.base.BaseClass;
import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.pages.objects.ExcelDataObjects;
import com.tsoft.bot.frontend.pages.objects.PeruRailObjects;
import com.tsoft.bot.frontend.utility.ExcelReader;
import com.tsoft.bot.frontend.utility.GenerateWord;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.HashMap;

public class TrainsPage extends BaseClass {

    private WebDriver driver;
    private static GenerateWord generateWord = new GenerateWord();
    String mensaje;

    public TrainsPage(WebDriver driver) {
        super(driver);
        this.driver = Hook.getDriver();
    }

    public void selectCabin(int indexRow) throws Exception {
        mensaje = "Se selecciona los tipos de cabinas";
        try{
            int index = indexRow - 1;
            HashMap<String, String> data = getSheets(ExcelDataObjects.EXCEL_PERURAIL, ExcelDataObjects.SHEET_TRAINS).get(index);
            selectTypeCabin(data.get(ExcelDataObjects.TRAIN_COLUMN_TYPE), Integer.parseInt(data.get(ExcelDataObjects.TRAIN_COLUMN_N)));
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
            stepPass(driver,mensaje);
        }catch(Throwable we){
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            mensaje = "Fallo en tiempo de respuesta";
            stepFail(driver,mensaje);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
            Assert.fail(mensaje);
        }
    }

    private void selectTypeCabin(String type, int n) throws Exception {
        sleep(500);
        // seleccionar tipo de cabina dinamicamente
        if( type.length() == 2 && n > 0){
            String SELECT_CABIN = "//select[@id='ddl-SelectRooms-"+type.toLowerCase()+"']";
            click(driver, By.xpath(SELECT_CABIN));
            sleep(250);
            click(driver, By.xpath(SELECT_CABIN+"/option[@value='"+n+"']"));
        }else{
            mensaje = "Ocurrio un error al seleccionar las cabinas";
            stepFail(driver, mensaje);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
            Assert.fail(mensaje);
        }
    }

    public void selectPassengers(int indexRow) throws Exception {
        mensaje = "Se selecciona los pasajeros";
        try{
            int index = indexRow - 1;
            HashMap<String, String> data = getSheets(ExcelDataObjects.EXCEL_PERURAIL, ExcelDataObjects.SHEET_TRAINS).get(index);
            String type = data.get(ExcelDataObjects.TRAIN_COLUMN_TYPE);
            int n = Integer.parseInt(data.get(ExcelDataObjects.TRAIN_COLUMN_N));
            for(int i = 1; i <= n; i++){
                // variables dinamicas
                String CABIN_SUITE_ADULT = "//select[@name='selectRooms["+type.toLowerCase()+"][cabinas][cab"+i+"][adult]']";
                String CABIN_SUITE_CHILDREN = "//select[@name='selectRooms["+type.toLowerCase()+"][cabinas][cab"+i+"][nin]']";
                int CABIN_SUITE_ADULT_N = Integer.parseInt(data.get("cab"+i+" adulto"));
                int CABIN_SUITE_CHILDREN_N = Integer.parseInt(data.get("cab"+i+" ninho"));

                // xpaths dinamicos
                By SELECT_CABIN_SUITE_CI_ADULT = By.xpath(CABIN_SUITE_ADULT);
                By SELECT_CABIN_SUITE_CI_CHILDREN = By.xpath(CABIN_SUITE_CHILDREN);
                By OPTION_CABIN_SUITE_CI_ADULT = By.xpath(CABIN_SUITE_ADULT+"/option[@value='"+CABIN_SUITE_ADULT_N+"']");
                By OPTION_CABIN_SUITE_CI_CHILDREN = By.xpath(CABIN_SUITE_CHILDREN+"/option[@value='"+CABIN_SUITE_CHILDREN_N+"']");

                sleep(500);
                click(driver, SELECT_CABIN_SUITE_CI_ADULT);
                sleep(250);
                click(driver, OPTION_CABIN_SUITE_CI_ADULT);

                if(CABIN_SUITE_ADULT_N < 2) {
                    sleep(500);
                    click(driver, SELECT_CABIN_SUITE_CI_CHILDREN);
                    sleep(250);
                    click(driver, OPTION_CABIN_SUITE_CI_CHILDREN);
                }
            }
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
            stepPass(driver,mensaje);
        }catch(Throwable we){
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            mensaje = "Fallo en tiempo de respuesta";
            stepFail(driver,mensaje);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
            Assert.fail(mensaje);
        }
    }

    public void clickContinue() throws Exception {
        mensaje = "Se da click en el boton continuar";
        try{
            sleep(500);
            click(driver, PeruRailObjects.BUTTON_CONTINUE_TRAINS);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
            stepPass(driver,mensaje);
        }catch(Throwable we){
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            mensaje = "Fallo en tiempo de respuesta";
            stepFail(driver,mensaje);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
            Assert.fail(mensaje);
        }
    }
}