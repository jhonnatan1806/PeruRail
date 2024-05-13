package com.tsoft.bot.frontend.pages.pages.perurail;

import com.tsoft.bot.frontend.base.BaseClass;
import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.utility.GenerateWord;
import org.openqa.selenium.WebDriver;

public class TrainsPage extends BaseClass {

    private WebDriver driver;
    private static GenerateWord generateWord = new GenerateWord();
    String mensaje;

    public TrainsPage(WebDriver driver) {
        super(driver);
        this.driver = Hook.getDriver();
    }
}
