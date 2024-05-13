package com.tsoft.bot.frontend.base;

import com.tsoft.bot.frontend.exceptions.FrontEndException;
import com.tsoft.bot.frontend.pages.objects.ExcelDataObjects;
import com.tsoft.bot.frontend.utility.ExcelReader;
import com.tsoft.bot.frontend.utility.ExtentReportUtil;
import com.tsoft.bot.frontend.utility.GenerateWord;
import com.tsoft.bot.frontend.utility.Sleeper;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;


public class BaseClass {

    private static GenerateWord generateWord = new GenerateWord();

    public BaseClass(WebDriver driver){
    }

    protected void errorPresented(WebDriver driver, By locator, String errorMessage) throws IOException {
        try {
            if(driver.findElement(locator).isDisplayed()){
                System.out.println(errorMessage);
                generateWord.sendText(errorMessage);
                generateWord.addImageToWord(driver);
                driver.quit();
                Assert.assertFalse(true);
            } else {
                return;
            }
        }catch (RuntimeException we){
            errorNoElementFound(driver, locator);
            throw we;
        }
    }

    protected void click(WebDriver driver, By locator) throws IOException {
        try {
            driver.findElement(locator).click();
        }catch (RuntimeException we){
            errorNoElementFound(driver, locator);
            throw we;
        }
    }

    protected WebElement findElement(WebDriver driver,By locator)throws IOException {
        try {
            return driver.findElement(locator);
        }
        catch (RuntimeException we){
            errorNoElementFound(driver, locator);
            throw we;
        }
    }

    protected void clear(WebDriver driver, By locator) throws IOException {
        try {
            driver.findElement(locator).clear();
        }catch (RuntimeException we){
            errorNoElementFound(driver, locator);
            throw we;
        }
    }

    //OBTENER TEXTO DEL ELEMENTO
    protected String getText(WebDriver driver, By locator) throws IOException {
        try {
            return driver.findElement(locator).getText();
        }catch (RuntimeException we){
            errorNoElementFound(driver, locator);
            throw we;
        }
    }

    protected void typeText(WebDriver driver, By locator, String inputText) throws IOException {
        try {
            driver.findElement(locator).sendKeys(inputText);
        }catch (RuntimeException we){
            errorNoElementFound(driver, locator);
            throw we;
        }
    }

    protected Boolean isDisplayed(WebDriver driver, By locator){
        try {
            return driver.findElement(locator).isDisplayed();
        }catch (NoSuchElementException we){
            driver.close();
            return false;
        }
    }

    protected void selectByVisibleText(WebDriver driver, By locator, String text) throws IOException {
        try {
            Select typeSelect = new Select(driver.findElement(locator));
            typeSelect.selectByVisibleText(text);
        }catch (RuntimeException we){
            errorNoElementFound(driver, locator);
            throw we;
        }
    }

    //SELECT por Valor
    protected void selectByValue(WebDriver driver, By locator, String text) throws IOException {
        try {
            Select typeSelect = new Select(driver.findElement(locator));
            typeSelect.selectByValue(text);
        }catch (RuntimeException we){
            errorNoElementFound(driver, locator);
            throw we;
        }
    }

    public static Exception handleError(WebDriver driver, String codigo, String msg) throws Throwable {
        stepWarning(driver, msg);
        return new FrontEndException(StringUtils.trimToEmpty(codigo), msg);
    }

    protected static void sleep(int milisegundos) {
        Sleeper.sleep(milisegundos);
    }

    protected static void stepPass(WebDriver driver, String descripcion) {
        try {
            ExtentReportUtil.INSTANCE.stepPass(driver, descripcion);
        } catch (RuntimeException t) {
            Logger.getLogger("[LOG]-StepPass: " + t);
            throw t;
        } catch (Exception e) {
            Logger.getLogger("[LOG]-StepPass: " + e);
        }
    }

    private static void stepWarning(WebDriver driver, String descripcion) throws Throwable {
        try {
            ExtentReportUtil.INSTANCE.stepWarning(driver, descripcion);
        } catch (RuntimeException t) {
            Logger.getLogger("[LOG]-StepWarning: " + t);
            throw t;
        }
    }

    protected void stepFail(WebDriver driver, String descripcion) throws Exception {
        try {
            ExtentReportUtil.INSTANCE.stepFail(driver, descripcion);
        } catch (RuntimeException t) {
            Logger.getLogger("[LOG]-StepFail: " + t);
            throw t;
        }
    }

    public static void stepFailNoShoot(String descripcion) {
        try {
            ExtentReportUtil.INSTANCE.stepFailNoShoot(descripcion);
        } catch (RuntimeException t) {
            Logger.getLogger("[LOG]-StepFailNoShoot: " + t);
            throw t;
        }
    }

    public static void scroll(WebDriver driver, int x, int y) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(" + x + "," + y + ")", "");
        } catch (RuntimeException t) {
            Logger.getLogger("[LOG]-Scroll: " + t);
            throw t;
        }
    }

    public static void zoom(WebDriver driver, int size) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.body.style.zoom = '" + size + "%'");
        } catch (RuntimeException t) {
            Logger.getLogger("[LOG]-zoom: " + t);
            throw t;
        }
    }

    private void errorNoElementFound(WebDriver driver, By locator) throws IOException {
        generateWord.sendText("Error : No se encontró el elemento : " + locator);
        generateWord.addImageToWord(driver);
        driver.close();
    }

    public List<HashMap<String, String>> getSheets(String excel ,String sheet) throws Throwable {
        return ExcelReader.data(excel, sheet);
    }

    protected void scrollToElement(WebDriver driver, By locator) {
        try {
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}