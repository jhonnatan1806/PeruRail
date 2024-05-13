package com.tsoft.bot.frontend.pages.pages.compra;

import com.tsoft.bot.frontend.base.BaseClass;
import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.pages.objects.AutomationPracticeObjects;
import com.tsoft.bot.frontend.pages.objects.ExcelDataObjects;
import com.tsoft.bot.frontend.pages.objects.McssObjects;
import com.tsoft.bot.frontend.utility.ExcelReader;
import com.tsoft.bot.frontend.utility.GenerateWord;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.List;

public class AutomationPracticeHomePage extends BaseClass {
    private WebDriver driver;
    private static GenerateWord generateWord = new GenerateWord();
    String mensaje;

    public AutomationPracticeHomePage(WebDriver driver) {
        super(driver);
        this.driver = Hook.getDriver();
    }

    private List<HashMap<String, String>> getData() throws Throwable {
        return ExcelReader.data(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME);
    }

    public void clickButtonSignIn() throws Throwable {
        click(driver, AutomationPracticeObjects.BTN_SIGNIN);
    }

    public void clickButtonWomen() throws Throwable {
        click(driver,AutomationPracticeObjects.BTN_WOMEN);
    }
}

