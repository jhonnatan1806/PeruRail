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
            validateCabin(data.get(ExcelDataObjects.TRAIN_COLUMN_TYPE), Integer.parseInt(data.get(ExcelDataObjects.TRAIN_COLUMN_N)));
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

    private void validateCabin(String type, int n) throws Exception {
        sleep(500);
        if(type.equals("CS") && n > 0){
            click(driver, PeruRailObjects.SELECT_CABIN_SUITE);
            sleep(250);
            click(driver, By.xpath("//select[@id='ddl-SelectRooms-sc']/option[@value='"+n+"']"));
        }else if (type.equals("TW") && n > 0){
            click(driver, PeruRailObjects.SELECT_CABIN_TWIN);
            sleep(250);
            click(driver, By.xpath("//select[@id='ddl-SelectRooms-tw']/option[@value='"+n+"']"));
        }else if (type.equals("BU") && n > 0){
            click(driver, PeruRailObjects.SELECT_CABIN_TWIN);
            sleep(250);
            click(driver, By.xpath("//select[@id='ddl-SelectRooms-bu']/option[@value='"+n+"']"));
        }else{
            mensaje = "Ocurrio un error al seleccionar las cabinas";
            stepFail(driver, mensaje);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
            Assert.fail(mensaje);
        }
    }

    public void selectPassengers(int indexRow) {
    }
}
