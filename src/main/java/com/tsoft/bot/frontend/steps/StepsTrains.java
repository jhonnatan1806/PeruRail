package com.tsoft.bot.frontend.steps;

import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.pages.pages.perurail.TrainsPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

public class StepsTrains {

    private WebDriver driver;
    private TrainsPage trainsPage;
    public StepsTrains(){
        this.driver = Hook.getDriver();
        this.trainsPage = new TrainsPage(driver);
    }

    @And("^selecciona la cantidad de cabinas y pasajeros \"([^\"]*)\"$")
    public void seleccionaLaCantidadDeCabinasYPasajeros(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("^el sistema muestra un mensaje de error$")
    public void elSistemaMuestraUnMensajeDeError() {
    }
}
