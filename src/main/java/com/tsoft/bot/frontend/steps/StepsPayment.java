package com.tsoft.bot.frontend.steps;

import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.pages.pages.perurail.PaymentPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

public class StepsPayment {

    private WebDriver driver;
    private PaymentPage paymentPage;
    public StepsPayment(){
        this.driver = Hook.getDriver();
        paymentPage = new PaymentPage(driver);
    }

    @And("^se selecciona el metodo de pago y se aceptan los TyC \"([^\"]*)\"$")
    public void seSeleccionaElMetodoDePagoYSeAceptanLosTyC(String arg0) throws Throwable {
        paymentPage.waitPreloading();
        paymentPage.selectPaymentMethod(Integer.parseInt(arg0));
        paymentPage.acceptTyC();
        paymentPage.clickPayNow();
    }

    @Then("^se validan los detalles de la compra$")
    public void seValidanLosDetallesDeLaCompra() {
        //
    }

    @And("^se realiza la compra de los boletos$")
    public void seRealizaLaCompraDeLosBoletos() {
        //
    }


}
