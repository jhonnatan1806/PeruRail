package com.tsoft.bot.frontend.pages.pages.perurail;

import com.tsoft.bot.frontend.base.BaseClass;
import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.pages.objects.ExcelDataObjects;
import com.tsoft.bot.frontend.pages.objects.PeruRailObjects;
import com.tsoft.bot.frontend.utility.ExcelReader;
import com.tsoft.bot.frontend.utility.GenerateWord;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class PaymentPage extends BaseClass {

    private WebDriver driver;
    private static GenerateWord generateWord = new GenerateWord();
    String mensaje;


    public PaymentPage(WebDriver driver) {
        super(driver);
        this.driver = Hook.getDriver();
    }

    public void waitPreloading(){
        sleep(30000);
    }

    public void selectPaymentMethod(int method) throws Exception {
        mensaje = "Seleccion de tarjeta visa";
        try {
            sleep(250);
            click(driver, PeruRailObjects.BUTTON_SELECT_VISA);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
            stepPass(driver,mensaje);
        } catch (Throwable we) {
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            mensaje = "Fallo en tiempo de respuesta";
            stepFail(driver, mensaje);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);

        }
    }

    public void acceptTyC() throws Exception {
        mensaje = "Se aceptan los terminos y condiciones";
        try {
            sleep(250);
            click(driver, PeruRailObjects.BUTTON_TYC);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
            stepPass(driver,mensaje);
        } catch (Throwable we) {
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            mensaje = "Fallo en tiempo de respuesta";
            stepFail(driver, mensaje);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);

        }
    }

    public void clickPayNow() throws Exception {
        mensaje = "Se realiza el pago";
        try {
            sleep(250);
            click(driver, PeruRailObjects.BUTTON_PAYNOW);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
            stepPass(driver,mensaje);
        } catch (Throwable we) {
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            mensaje = "Fallo en tiempo de respuesta";
            stepFail(driver, mensaje);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);

        }
    }
}
