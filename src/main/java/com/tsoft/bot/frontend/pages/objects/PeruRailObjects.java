package com.tsoft.bot.frontend.pages.objects;

import org.openqa.selenium.By;
public class PeruRailObjects {

    public static final By INPUT_ONE_WAY = By.xpath("//input[@id='oneway']/following-sibling::label");
    public static final By INPUT_ROUND_TRIP = By.xpath("//input[@id='roundtrip']/following-sibling::label");
    public static final By INPUT_FROM = By.xpath("//select[@id='Filtros_Ida_Origen']");
    public static final By INPUT_FROM_PUNO = By.xpath("//option[@value='6028']");

}
