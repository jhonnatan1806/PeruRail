package com.tsoft.bot.frontend.steps;

import com.tsoft.bot.frontend.helpers.Hook;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

public class StepsPayment {

    private WebDriver driver;
    public StepsPayment(){
        this.driver = Hook.getDriver();
    }

    @And("^se selecciona el metodo de pago y se aceptan los TyC$")
    public void seSeleccionaElMetodoDePagoYSeAceptanLosTyC() {
    }

    @Then("^se validan los detalles de la compra$")
    public void seValidanLosDetallesDeLaCompra() {
        
    }

    @And("^se realiza la compra de los boleto$")
    public void seRealizaLaCompraDeLosBoleto() {
    }
}
