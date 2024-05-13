package com.tsoft.bot.frontend.steps;

import com.tsoft.bot.frontend.helpers.Hook;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

public class StepsTrains {

    private WebDriver driver;
    public StepsTrains(){
        this.driver = Hook.getDriver();
    }

    @And("^selecciona la cantidad de cabinas y boletos \"([^\"]*)\"$")
    public void seleccionaLaCantidadDeCabinasYBoletos(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
    }
}
