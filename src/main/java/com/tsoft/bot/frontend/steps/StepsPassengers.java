package com.tsoft.bot.frontend.steps;

import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.models.Cabin;
import com.tsoft.bot.frontend.pages.pages.perurail.PassengersPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class StepsPassengers {

    PassengersPage passengersPage;
    private WebDriver driver;
    public StepsPassengers(){
        this.driver = Hook.getDriver();
        passengersPage = new PassengersPage(driver);
    }

    @And("^se completa los datos de los pasajeros \"([^\"]*)\"$")
    public void seCompletaLosDatosDeLosPasajeros(String arg0) throws Throwable {
        String[] collection = arg0.split(",");
        List <Cabin> cabins = new ArrayList<>();
        for(String item : collection){
            List<Cabin> cabinCollection = passengersPage.getPassenger(Integer.parseInt(item));
            if  (cabinCollection != null) {
                cabins.addAll(cabinCollection);
            }
        }
        passengersPage.fillPassengers(cabins);
        passengersPage.clickContinue();
    }
}
