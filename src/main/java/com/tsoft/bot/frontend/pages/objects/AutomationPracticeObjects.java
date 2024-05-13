package com.tsoft.bot.frontend.pages.objects;

import org.openqa.selenium.By;

public class AutomationPracticeObjects {

    //Home Page
    public static final By BTN_SIGNIN = By.xpath("//a[contains(text(),'Sign in')]");

    //LOG IN PAGE
    //Inputs
    public static final By INPUT_EMAILADDRESS= By.xpath("//input[@id='email_create']");
    public static final By INPUT_EMAILLOGIN= By.xpath("//*[@id='user-name']");
    public static final By INPUT_PASSWORDLOGIN= By.xpath("//*[@id='password']");
    public static final By INPUT_FIRSTNAME= By.id("customer_firstname");
    public static final By INPUT_LASTNAME= By.id("customer_lastname");
    public static final By INPUT_PASSWORD= By.id("passwd");
    public static final By INPUT_ADDRESS= By.id("address1");
    public static final By INPUT_CITY= By.id("city");
    public static final By INPUT_ZIP= By.id("postcode");
    public static final By INPUT_PHONENUMBER= By.id("phone_mobile");
    public static final By INPUT_ALIAS= By.id("alias");
    //Dropdowns
    public static final By DROPDOWN_DAY = By.id("days");
    public static final By DROPDOWN_MONTH = By.id("months");
    public static final By DROPDOWN_YEAR = By.id("years");
    public static final By DROPDOWN_STATE = By.id("id_state");
    //ERROR
    public static final By DIV_ALERT = By.id("create_account_error");
    //Buttons
    public static final By BTN_CREATEACCOUNT = By.xpath("//button[@id='SubmitCreate']");
    public static final By BTN_REGISTER = By.xpath("//button[@id='submitAccount']");
    public static final By BTN_LOGIN = By.xpath("//button[@id='SubmitLogin']");
    //Text
    public static final By HDR_CREATEANACCOUNT = By.xpath("//h1[contains(text(),'Create an account')]");
    public static final By HDR_CREATEACCOUNT = By.xpath("//h3[contains(text(),'Create an account')]");

    //RadioButtons
    public static final By RADIOBTN_MR = By.xpath("//label[@for='id_gender1']");

    //DATA USER PAGE
    public static final By HDR_MYACCOUNT = By.xpath("//h1[contains(text(),'My account')]");
    public static final By SPAN_NAME = By.xpath("//a[@class='account']/span");



    //HomePage
    public static final By BTN_WOMEN = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/a");
    public static final By BTN_EVENINGDRESSES = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/ul/li[2]/ul/li[2]/a");



    //WomenPage
    public static final By IMG_ADDPRINTEDDRESS = By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[1]/div/a[1]/img");
    public static final By BTN_ADDPRINTEDDRESS = By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[2]/div[2]/a[1]/span");



    public static final By BTN_CHECKOUT = By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a");
    public static final By BTN_PROCEDERBUYCHECKOUT = By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]/span");
    public static final By BTN_PROCEDERBUYCHECKOUT1 = By.xpath("//*[@id=\"center_column\"]/form/p/button/span");
    public static final By CHECKBOX = By.id("cgv");
    public static final By BTN_PROCEDERBUYCHECKOUT2 = By.xpath("//*[@id=\"form\"]/p/button/span");
    public static final By BTN_PAYBYBANKWIRE = By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a/span");
    public static final By BTN_CONFIRM = By.xpath("//*[@id=\"cart_navigation\"]/button/span");

    public static final By COMPLETE = By.xpath("//*[@id=\"center_column\"]/div/p/strong");


}
