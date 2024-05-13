package com.tsoft.bot.frontend.steps;

import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.pages.pages.perurail.TrainsPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

public class StepsTrains {

    private WebDriver driver;
    private TrainsPage trainsPage;
    public StepsTrains(){
        this.driver = Hook.getDriver();
        this.trainsPage = new TrainsPage(driver);
    }

    @And("^selecciona la cantidad de cabinas y boletos \"([^\"]*)\"$")
    public void seleccionaLaCantidadDeCabinasYBoletos(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
    }
}
