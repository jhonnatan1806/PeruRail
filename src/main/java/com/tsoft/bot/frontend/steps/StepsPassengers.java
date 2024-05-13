package com.tsoft.bot.frontend.steps;

import com.tsoft.bot.frontend.helpers.Hook;
import cucumber.api.java.en.And;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class StepsPassengers {

    private WebDriver driver;
    public StepsPassengers(){
        this.driver = Hook.getDriver();
    }

    @And("^se agrega los datos de los pasajeros \"([^\"]*)\"$")
    public void seAgregaLosDatosDeLosPasajeros(String arg0) throws Throwable {
        String[] collection = arg0.split(",");
        List<Integer> indexes = new ArrayList<>();
        for(String item : collection){
            indexes.add(Integer.parseInt(item));
        }
    }

}
