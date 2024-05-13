package com.tsoft.bot.frontend.steps;

import com.tsoft.bot.frontend.helpers.Hook;
import cucumber.api.PendingException;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

public class StepsPayment {

    private WebDriver driver;
    public StepsPayment(){
        this.driver = Hook.getDriver();
    }

}
