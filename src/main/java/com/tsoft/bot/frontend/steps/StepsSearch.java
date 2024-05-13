package com.tsoft.bot.frontend.steps;

import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.pages.pages.perurail.SearchPage;
import com.tsoft.bot.frontend.utility.ReadProperties;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

public class StepsSearch {

    private WebDriver driver;
    private SearchPage searchPage;

    public StepsSearch(){
        this.driver = Hook.getDriver();
        searchPage = new SearchPage(driver);
    }

    @Given("^que el usuario se encuentra en la pagina de inicio del sitio web$")
    public void queElUsuarioSeEncuentraEnLaPaginaDeInicioDelSitioWeb() throws Exception {
        searchPage.openUrl(ReadProperties.get("url.website", "qa"));
    }

    @When("^el usuario selecciona las opciones de busqueda de su viaje \"([^\"]*)\"$")
    public void elUsuarioSeleccionaLasOpcionesDeBusquedaDeSuViaje(String arg0) throws Throwable {
        searchPage.searchTrip(Integer.parseInt(arg0));
    }

}
