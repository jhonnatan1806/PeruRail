package com.tsoft.bot.frontend.pages.objects;

import org.openqa.selenium.By;
public class PeruRailObjects {

    /* ========================
     *      SEARCH SECTION
     * ======================== */
    public static final By BUTTON_TYPE_ONEWAY = By.xpath("//input[@id='oneway']/following-sibling::label/span");
    public static final By BUTTON_TYPE_ROUNDTRIP = By.xpath("//input[@id='roundtrip']/following-sibling::label/span");
    public static final By SELECT_FROM = By.xpath("//select[@id='Filtros_Ida_Origen']");
    public static final By OPTION_FROM_PUNO = By.xpath("//select[@id='Filtros_Ida_Origen']/*/option[@value='6028']");
    public static final By OPTION_FROM_AREQUIPA = By.xpath("//select[@id='Filtros_Ida_Origen']/*/option[@value='6012']");
    public static final By SELECT_TO = By.xpath("//select[@id='Filtros_Ida_Destino']");
    public static final By OPTION_TO_CUSCO = By.xpath("//select[@id='Filtros_Ida_Destino']/*/option[@value='6022']");
    public static final By SELECT_SERVICE = By.xpath("//select[@id='cbTrenSelect']");
    public static final By OPTION_SERVICE_BELMOND = By.xpath("//select[@id='cbTrenSelect']/option[@value='1']");
    public static final By OPTION_SERVICE_EXPEDITION = By.xpath("//select[@id='cbTrenSelect']/option[@value='2']");
    public static final By SELECT_DATE = By.xpath("//input[@id='Filtros_Ida_Fecha']");
    public static final By BUTTON_CALENDAR_NEXTMONTH = By.xpath("//a[@class='ui-datepicker-next ui-corner-all' and @title='Next']");
    public static final By BUTTON_CALENDAR_FIRSTDATE = By.xpath("//a[@class='ui-state-default']");
    public static final By BUTTON_SEARCH = By.xpath("//button[@id='btn_search']");


    /* ========================
     *      TRAINS SECTION (parcialmente dinamico)
     * ======================== */
    public static final By SPAN_PATH = By.xpath("//span[@id='lbldescripcion']/h5/span");
    public static final By SELECT_CABIN_SUITE = By.xpath("//select[@id='ddl-SelectRooms-sc']");
    public static final By OPTION_CABIN_SUITE_C1 = By.xpath("//select[@id='ddl-SelectRooms-sc']/option[@value='1']");
    public static final By SELECT_CABIN_SUITE_C1_ADULT = By.xpath("//select[@name='selectRooms[sc][cabinas][cab1][adult]']");
    public static final By OPTION_CABIN_SUITE_C1_ADULT_1 = By.xpath("//select[@name='selectRooms[sc][cabinas][cab1][adult]']/option[@value='1']");
    public static final By OPTION_CABIN_SUITE_C1_ADULT_2 = By.xpath("//select[@name='selectRooms[sc][cabinas][cab1][adult]']/option[@value='2']");
    public static final By SELECT_CABIN_SUITE_C1_CHILDREN = By.xpath("//select[@name='selectRooms[sc][cabinas][cab1][nin]']");
    public static final By OPTION_CABIN_SUITE_C1_CHILDREN_0 = By.xpath("//select[@name='selectRooms[sc][cabinas][cab1][nin]']/option[@value='0']");
    public static final By OPTION_CABIN_SUITE_C1_CHILDREN_1 = By.xpath("//select[@name='selectRooms[sc][cabinas][cab1][nin]']/option[@value='1']");
    public static final By SELECT_CABIN_TWIN = By.xpath("//select[@id='ddl-SelectRooms-tw']");
    public static final By SELECT_CABIN_BUNK = By.xpath("//select[@id='ddl-SelectRooms-bu']");
    public static final By BUTTON_CONTINUE_TRAINS = By.xpath("//button[@id='btnSiguiente_trenes']");
    public static final By DIV_PRELOADER = By.xpath("//div[@id='preloader']") ;

    /* ========================
     * PASSENGERS DATA SECTION (sera dinamico)
     * ======================== */
    public static final By ACCORDION_C0 = By.xpath("//a[@id='expandirCabina-0']");
    public static final By ACCORDION_C0_P0 = By.xpath("//a[@id='expandirPasajero-0']");
    public static final By INPUT_P0_FIRSTNAME = By.xpath("//input[@id='ListaPasajeros[0].NombrePax']");
    public static final By INPUT_P0_SURNAME = By.xpath("//input[@id='ListaPasajeros[0].ApellidoPax']");
    public static final By INPUT_P0_GENDER_MALE = By.xpath("//input[@name='ListaPasajeros[0].SexoPax' and @value='1']");
    public static final By INPUT_P0_GENDER_FEMALE = By.xpath("//input[@name='ListaPasajeros[0].SexoPax' and @value='2']");
    public static final By SELECT_P0_COUNTRY = By.xpath("//select[@name='ListaPasajeros[0].NacionalidadPax']");
    public static final By OPTION_P0_COUNTRY_PER = By.xpath("//select[@name='ListaPasajeros[0].NacionalidadPax']/option[@value='230']");
    public static final By SELECT_P0_TYPEDOC = By.xpath("//select[@name='ListaPasajeros[0].TipoDocumentoPax']");
    public static final By OPTION_P0_TYPEDOC_PASSAPORT = By.xpath("//select[@name='ListaPasajeros[0].TipoDocumentoPax']/option[@value='1']");
    public static final By INPUT_P0_TYPEDOC_IMMIGRATION = By.xpath("//input[@id='ListaPasajeros[0].DocumentoPax']/option[@value='2']");
    public static final By OPTION_P0_TYPEDOC_DNI = By.xpath("//select[@name='ListaPasajeros[0].TipoDocumentoPax']/option[@value='5']");
    public static final By INPUT_P0_DOCNUM = By.xpath("//input[@name='ListaPasajeros[0].NroDocumentoPax']");
    public static final By INPUT_P0_BIRTHDATE = By.xpath("//input[@name='ListaPasajeros[0].NacimientoPax']");
    public static final By INPUT_P0_TELEPHONE = By.xpath("//input[@name='ListaPasajeros[0].TelefonoPax']");
    public static final By INPUT_P0_EMAIL = By.xpath("//input[@name='ListaPasajeros[0].EmailPax']");
    public static final By INPUT_P0_EMAIL_CONFIRM = By.xpath("//input[@name='ListaPasajeros[0].EmailPaxConfirm']");

    // Datepicker
    public static final By SPAN_BIRTHDATE_YEAR_TITLE = By.xpath("//div[@class='datepicker-years']//th[@class='datepicker-switch']");
    public static final By BUTTON_BIRTHDATE_YEAR_PREV = By.xpath("//div[@class='datepicker-years']//th[@class='prev']");
    public static final By BUTTON_BIRTHDATE_YEAR_NEXT = By.xpath("//div[@class='datepicker-years']//th[@class='next']");
    public static final By SPAN_BIRTHDATE_YEAR_1 = By.xpath("//div[@class='datepicker-years']//span[@class='year'][1]"); // Depende la seleccion
    public static final By SPAN_BIRTHDATE_MONTH_6 = By.xpath("//div[@class='datepicker-months']//span[@class='month'][6]"); // Junio
    public static final By SPAN_BIRTHDATE_DAY_18 = By.xpath("//div[@class='datepicker-days']//td[text()='18']"); // Dia 18

    public static final By BUTTON_CONTINUE_PASSENGERS = By.xpath("//button[@id='btnSiguienteReserva']");

}
