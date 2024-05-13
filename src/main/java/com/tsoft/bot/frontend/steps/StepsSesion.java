package com.tsoft.bot.frontend.steps;

import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.pages.pages.Sauce.SauceLoginPage;
import com.tsoft.bot.frontend.pages.pages.login.LoginPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.WebDriver;

public class StepsSesion {
    private WebDriver driver;
    private final SauceLoginPage sauceLoginPage = new SauceLoginPage(driver);
    public StepsSesion(){
        this.driver= Hook.getDriver();
    }

    @Dado("^Usuario ingresa pagina seleccionada \"([^\"]*)\"$")
    public void usuarioIngresaPaginaSeleccionada(String setNumberURL) throws Throwable {
        sauceLoginPage.ingresarURL(setNumberURL);
    }

    @Cuando("^Usuario ingresa sus datos \"([^\"]*)\"$")
    public void usuarioIngresaSusDatos(String setNumberRow) throws Throwable {

        sauceLoginPage.iniciarSesion(setNumberRow);
    }

    @Entonces("^Valido que el ingreso sea satisfactorio$")
    public void validoQueElIngresoSeaSatisfactorio() throws Throwable {
        sauceLoginPage.validarPaginaProductos();
    }


    @Dado("^Usuario selecciona items a comprar \"([^\"]*)\"$")
    public void usuarioSeleccionaItemsAComprar(String casoPrueba) throws Throwable {

        sauceLoginPage.ingresarURL(casoPrueba);
        sauceLoginPage.iniciarSesion(casoPrueba);
        sauceLoginPage.validarPaginaProductos();
        sauceLoginPage.seleccionarProductos();
    }

    @Cuando("^Realiza la compra de los items \"([^\"]*)\"$")
    public void realizaLaCompraDeLosItems(String testCase) throws Throwable {

        sauceLoginPage.completarCompra(testCase);
    }

    @Entonces("^Valido la compra exitosa$")
    public void validoLaCompraExitosa() throws Throwable {
        sauceLoginPage.validarCompra();
    }

    //*****Escenario: Obtener Informacion***

    @Dado("^El usuario inicio sesion en la web SauceDemo \"([^\"]*)\"$")
    public void elUsuarioInicioSesionEnLaWebSauceDemo(String RowCasoPrueba) throws Throwable {

        sauceLoginPage.ingresarURL(RowCasoPrueba);
        sauceLoginPage.iniciarSesion(RowCasoPrueba);
    }

    @Cuando("^Ingrese a visualizar un producto \"([^\"]*)\"$")
    public void ingreseAVisualizarUnProducto(String RowCasoPrueba) throws Throwable {
        sauceLoginPage.DetallesProductos(RowCasoPrueba);
    }

    @Entonces("^Obtendra la informacion del producto \"([^\"]*)\"$")
    public void obtendraLaInformacionDelProducto(String RowCasoPrueba) throws Throwable {
        sauceLoginPage.ObtenerDataProducto(RowCasoPrueba);

    }

}
