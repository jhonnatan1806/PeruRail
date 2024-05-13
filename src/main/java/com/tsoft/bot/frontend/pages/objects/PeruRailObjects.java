package com.tsoft.bot.frontend.pages.objects;

import org.openqa.selenium.By;
public class PeruRailObjects {

    public static final By TYPE_BUTTON_ONEWAY = By.xpath("//input[@id='oneway']/following-sibling::label/span");
    public static final By TYPE_BUTTON_ROUNDTRIP = By.xpath("//input[@id='roundtrip']/following-sibling::label/span");
    public static final By FROM_SELECT = By.xpath("//select[@id='Filtros_Ida_Origen']");
    public static final By FROM_OPTION_PUNO = By.xpath("//select[@id='Filtros_Ida_Origen']/*/option[@value='6028']");
    public static final By FROM_OPTION_AREQUIPA = By.xpath("//select[@id='Filtros_Ida_Origen']/*/option[@value='6012']");
    public static final By TO_SELECT = By.xpath("//select[@id='Filtros_Ida_Destino']");
    public static final By TO_OPTION_CUSCO = By.xpath("//select[@id='Filtros_Ida_Destino']/*/option[@value='6022']");
    public static final By SERVICE_SELECT = By.xpath("//select[@id='cbTrenSelect']");
    public static final By SERVICE_OPTION_BELMOND = By.xpath("//select[@id='cbTrenSelect']/option[@value='1']");
    public static final By SERVICE_OPTION_EXPEDITION = By.xpath("//select[@id='cbTrenSelect']/option[@value='2']");

    public static final By SEARCH_BUTTON = By.xpath("//button[@id='btn_search']");

}
