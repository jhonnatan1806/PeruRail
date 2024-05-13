package com.tsoft.bot.frontend.steps;

import com.tsoft.bot.frontend.helpers.Hook;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

public class StepsPassengers {

    private WebDriver driver;
    public StepsPassengers(){
        this.driver = Hook.getDriver();
    }

    @And("^se agrega los datos de los pasajeros \"([^\"]*)\"$")
    public void seAgregaLosDatosDeLosPasajeros(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
