package com.tsoft.bot.frontend.pages.pages.compra;
import com.tsoft.bot.frontend.base.BaseClass;
import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.pages.objects.AutomationPracticeObjects;
import com.tsoft.bot.frontend.pages.objects.ExcelDataObjects;
import com.tsoft.bot.frontend.utility.ExcelReader;
import com.tsoft.bot.frontend.utility.GenerateWord;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.List;

public class AutomationPractice extends BaseClass {
    private WebDriver driver;
    private static GenerateWord generateWord = new GenerateWord();
    Actions actions;
    String mensaje;

    public AutomationPractice(WebDriver driver) {
        super(driver);
        this.driver = Hook.getDriver();
    }

    private List<HashMap<String, String>> getData() throws Throwable {
        return ExcelReader.data(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME);
    }

    public void clickButtonWomen() throws Throwable {
        mensaje = "Hacemos click en la pestaña WOMEN";
        try {

            actions = new Actions(driver);
            Thread.sleep(500);;
            WebElement element = driver.findElements((AutomationPracticeObjects.BTN_WOMEN)).get(0);
            actions.moveToElement(element).perform();
            element.findElement(AutomationPracticeObjects.BTN_EVENINGDRESSES).click();
            sleep(3000);
            stepPass(driver, mensaje);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
        }
        catch (Exception we)
        {
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            stepFail(driver,"Fallo en tiempo de respuesta : " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }

    }

    public void agregarCarritoFadedShortSleeve() throws Throwable {
        mensaje = "Agregamos al carrito el articulo Faded Short Sleeve";
        try {
            Thread.sleep(3000);
            scroll(driver,0,400);
            actions = new Actions(driver);
            Thread.sleep(500);;
            WebElement element = driver.findElements((AutomationPracticeObjects.IMG_ADDPRINTEDDRESS)).get(0);
            actions.moveToElement(element).perform();
            element.findElement(AutomationPracticeObjects.BTN_ADDPRINTEDDRESS).click();
            sleep(3000);
            stepPass(driver, mensaje);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);

        }
        catch (Exception we)
        {
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            stepFail(driver,"Fallo en tiempo de respuesta : " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }
    }
    public void procederCheckout() throws Throwable {
        mensaje = "Luego de verificar que el precio sea el correcto hacemos click en checkout";
        try {
            sleep(2500);
            click(driver, AutomationPracticeObjects.BTN_CHECKOUT);
            sleep(3000);
            scroll(driver,0,600);
            click(driver, AutomationPracticeObjects.BTN_PROCEDERBUYCHECKOUT);
            sleep(3000);
            scroll(driver,0,600);
            click(driver, AutomationPracticeObjects.BTN_PROCEDERBUYCHECKOUT1);
            sleep(3000);
            scroll(driver,0,600);
            click(driver, AutomationPracticeObjects.CHECKBOX);
            sleep(3000);
            click(driver, AutomationPracticeObjects.BTN_PROCEDERBUYCHECKOUT2);
            sleep(3000);
            scroll(driver,0,600);
            click(driver, AutomationPracticeObjects.BTN_PAYBYBANKWIRE);
            sleep(3000);
            scroll(driver,0,600);
            click(driver, AutomationPracticeObjects.BTN_CONFIRM);
            sleep(3000);
            if(isDisplayed(driver,AutomationPracticeObjects.COMPLETE))
            System.out.println("Compra realizada con exito");
            sleep(3000);
            stepPass(driver, mensaje);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
        }
        catch (Exception we)
        {
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            stepFail(driver,"Fallo en tiempo de respuesta : " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }
        driver.quit();
    }

}
