package com.tsoft.bot.frontend.pages.objects;

import org.openqa.selenium.By;

public class SauceDemoObjects {

    public static final By INPUT_USERNAME = By.id("user-name");
    public static final By INPUT_PASSWORD = By.id("password");
    public static final By INPUT_BTN = By.id("login-button");

    public static final By SPAN_TITLE = By.xpath("//*[@id='header_container']/div[2]/span");

    public static final By PRODUCTO1 = By.xpath("//*[@id='add-to-cart-sauce-labs-bike-light']");

    public static final By PRODUCTO2 = By.xpath("//*[@id='add-to-cart-sauce-labs-fleece-jacket']");

    public static final By PRODUCTO3 = By.xpath("//*[@id='add-to-cart-test.allthethings()-t-shirt-(red)']");

    public static final By IR_AL_CARRO = By.xpath("//*[@id='shopping_cart_container']/a");

    public static final By IR_AL_CHECKOUT = By.xpath("//*[@id='checkout']");

    public static final By FIRST_NAME = By.xpath("//*[@id='first-name']");

    public static final By LAST_NAME = By.xpath("//*[@id='last-name']");

    public static final By POSTAL_CODE = By.xpath("//*[@id='postal-code']");

    public static final By BTN_CONTINUE = By.xpath("//*[@id='continue']");

    public static final By BTN_FINISH = By.xpath("//*[@id='finish']");

    public static final By VALIDACION = By.xpath("//*[@id='checkout_complete_container']/h2");

    public static final By LINK_PRODUCTO = By.id("item_0_title_link");

    public static final By DIV_NOMBRE = By.className("inventory_details_name");
    public static final By DIV_DESCRIPCION = By.className("inventory_details_desc");
    public static final By DIV_PRECIO = By.className("inventory_details_price");
}
