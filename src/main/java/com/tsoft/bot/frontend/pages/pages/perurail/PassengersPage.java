package com.tsoft.bot.frontend.pages.pages.perurail;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.tsoft.bot.frontend.base.BaseClass;
import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.models.Cabin;
import com.tsoft.bot.frontend.models.Passenger;
import com.tsoft.bot.frontend.pages.objects.ExcelDataObjects;
import com.tsoft.bot.frontend.pages.objects.PeruRailObjects;
import com.tsoft.bot.frontend.utility.ExcelReader;
import com.tsoft.bot.frontend.utility.GenerateWord;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.*;

public class PassengersPage extends BaseClass {
    private WebDriver driver;
    private static GenerateWord generateWord = new GenerateWord();
    String mensaje;

    public PassengersPage(WebDriver driver) {
        super(driver);
        this.driver = Hook.getDriver();
    }

    public List<Cabin> getPassenger(int indexRow) {
        List<Cabin> cabins = new ArrayList<>();
        try{
            int index = indexRow - 1;
            HashMap<String, String> data = getSheets(ExcelDataObjects.EXCEL_PERURAIL, ExcelDataObjects.SHEET_TRAINS).get(index);
            int n = Integer.parseInt(data.get(ExcelDataObjects.TRAIN_COLUMN_N));
            for(int i = 1; i <= n; i++) {
                String type = data.get(ExcelDataObjects.TRAIN_COLUMN_TYPE);
                int adults = Integer.parseInt(data.get("cab"+ i +" adulto"));
                int childrens = Integer.parseInt(data.get("cab"+ i +" ninho"));
                cabins.add(new Cabin(type, adults, childrens));
            }
            return cabins;
        }catch(Throwable we){
            Assert.fail(mensaje);
            return null;
        }
    }

    public void fillPassengers(List<Cabin> cabins) throws Throwable {
        // número de pasajeros

        List<Cabin> cabinSorted = new ArrayList<>();
        cabinSorted.addAll(filterCabin("BU", cabins));
        cabinSorted.addAll(filterCabin("TW", cabins));
        cabinSorted.addAll(filterCabin("SC", cabins));

        // pasajeros por tipo
        List<Passenger> passengersAdults = getPassengersAdults(passengerAdultSize(cabins));
        List<Passenger> passengersChildren = getPassengersChildren(passengerChildrenSize(cabins));

        int counter = 0;

        // llenar datos de pasajeros
        for (int i = 0; i < cabinSorted.size(); i++) {
            // expandir cabinas si es necesario
            if (i != 0) {
                By BUTTON_CABIN = By.xpath("//a[@id='expandirCabina-" + (i + counter) + "']");
                click(driver, BUTTON_CABIN);
            }

            // llenar datos de pasajeros adultos
            counter += fillPassengersAdult(cabinSorted.get(i), (i + counter), passengersAdults);

            // llenar datos de pasajeros niños
            counter += fillPassengersChildren(cabinSorted.get(i), (i + counter), passengersChildren);

            // normalizar contador
            counter--;
        }
    }

    private int  fillPassengersAdult(Cabin cabin, int i, List<Passenger> passengers) throws Exception {
        try {
            for (int j = 0; j < cabin.getAdults(); j++) {
                Passenger passenger = passengers.remove(0);

                // si no es el primer pasajero
                if (i != 0 || j != 0) {
                    sleep(500);
                    By BUTTON_PASSENGER = By.xpath("//a[@id='expandirPasajero-" + (i + j) + "']");
                    click(driver, BUTTON_PASSENGER);
                }

                String INPUT_FIRSTNAME = "//input[@id='ListaPasajeros[" + (i + j) + "].NombrePax']";
                String INPUT_SURNAME = "//input[@id='ListaPasajeros[" + (i + j) + "].ApellidoPax']";
                String INPUT_GENDER = "//input[@name='ListaPasajeros[" + (i + j) + "].SexoPax']"; // no implementado
                String INPUT_COUNTRY = "//select[@name='ListaPasajeros[" + (i + j) + "].NacionalidadPax']";
                String INPUT_TYPEDOC = "//select[@name='ListaPasajeros[" + (i + j) + "].TipoDocumentoPax']";
                String INPUT_DOCUMENT = "//input[@name='ListaPasajeros[" + (i + j) + "].NroDocumentoPax']";
                String INPUT_TELEPHONE = "//input[@name='ListaPasajeros[" + (i + j) + "].TelefonoPax']";
                String INPUT_EMAIL = "//input[@name='ListaPasajeros[" + (i + j) + "].EmailPax']";
                String INPUT_EMAIL_CONFIRM = "//input[@name='ListaPasajeros[" + (i + j) + "].EmailPaxConfirm']";
                String INPUT_BIRTHDAY = "//input[@name='ListaPasajeros[" + (i + j) + "].NacimientoPax']";

                typeText(driver, By.xpath(INPUT_FIRSTNAME), passenger.getFirstName());
                sleep(250);
                typeText(driver, By.xpath(INPUT_SURNAME), passenger.getSurname());
                sleep(250);
                typeText(driver, By.xpath(INPUT_COUNTRY), passenger.getCountry());
                // click(driver, By.xpath(INPUT_GENDER)); // falta implementar
                sleep(250);
                typeText(driver, By.xpath(INPUT_TYPEDOC), passenger.getTypedoc());
                sleep(250);
                typeText(driver, By.xpath(INPUT_DOCUMENT), passenger.getDocument());
                sleep(250);
                click(driver, By.xpath(INPUT_BIRTHDAY));
                fillBirthday(18, 6,2000);

                if (i == 0 && j == 0) {
                    sleep(250);
                    typeText(driver, By.xpath(INPUT_TELEPHONE), passenger.getTelephone());
                    sleep(250);
                    typeText(driver, By.xpath(INPUT_EMAIL), passenger.getEmail());
                    sleep(250);
                    typeText(driver, By.xpath(INPUT_EMAIL_CONFIRM), passenger.getEmail());
                }
                mensaje = "pasajero aldulto " + (i+j) + " agregado correctamente";
                generateWord.sendText(mensaje);
                generateWord.addImageToWord(driver);
                stepPass(driver,mensaje);
            }
        }catch(Throwable we){
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            mensaje = "Fallo en tiempo de respuesta";
            stepFail(driver,mensaje);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
            Assert.fail(mensaje);
        }
        return cabin.getAdults();
    }

    private int fillPassengersChildren(Cabin cabin, int i, List<Passenger> passengers) throws Exception {
        try{
            for (int j = 0; j < cabin.getChildren(); j++) {
                Passenger passenger = passengers.remove(0);

                sleep(500);
                By BUTTON_PASSENGER = By.xpath("//a[@id='expandirPasajero-" + (i+j) + "']");
                click(driver, BUTTON_PASSENGER);

                String INPUT_FIRSTNAME = "//input[@id='ListaPasajeros[" + (i + j) + "].NombrePax']";
                String INPUT_SURNAME = "//input[@id='ListaPasajeros[" + (i + j) + "].ApellidoPax']";
                String INPUT_COUNTRY = "//select[@name='ListaPasajeros[" + (i + j) + "].NacionalidadPax']";
                String INPUT_TYPEDOC = "//select[@name='ListaPasajeros[" + (i + j) + "].TipoDocumentoPax']";
                String INPUT_DOCUMENT = "//input[@name='ListaPasajeros[" + (i + j) + "].NroDocumentoPax']";
                String INPUT_BIRTHDAY = "//input[@name='ListaPasajeros[" + (i + j) + "].NacimientoPax']";

                typeText(driver, By.xpath(INPUT_FIRSTNAME), passenger.getFirstName());
                sleep(250);
                typeText(driver, By.xpath(INPUT_SURNAME), passenger.getSurname());
                sleep(250);
                typeText(driver, By.xpath(INPUT_COUNTRY), passenger.getCountry());
                // click(driver, By.xpath(INPUT_GENDER)); // falta implementar
                sleep(250);
                typeText(driver, By.xpath(INPUT_TYPEDOC), passenger.getTypedoc());
                sleep(250);
                typeText(driver, By.xpath(INPUT_DOCUMENT), passenger.getDocument());
                sleep(250);
                click(driver, By.xpath(INPUT_BIRTHDAY));
                fillBirthday(6, 1,2010);
                mensaje = "pasajero ninho " + (i+j) + " agregado correctamente";
                generateWord.sendText(mensaje);
                generateWord.addImageToWord(driver);
                stepPass(driver,mensaje);
            }
        }
        catch(Throwable we){
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            mensaje = "Fallo en tiempo de respuesta";
            stepFail(driver,mensaje);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
            Assert.fail(mensaje);
        }
        return cabin.getChildren();
    }

    private void fillBirthday(int day, int month, int year) throws IOException {
        // falta implementa;

        String SPAN_BIRTHDATE_YEAR_TITLE = "//div[@class='datepicker-years']//th[@class='datepicker-switch']";
        String BUTTON_BIRTHDATE_YEAR_PREV = "//div[@class='datepicker-years']//th[@class='prev']";
        String BUTTON_BIRTHDATE_YEAR_NEXT = "//div[@class='datepicker-years']//th[@class='next']";
        String SPAN_BIRTHDATE_YEAR = "//div[@class='datepicker-years']//span[@class='year'][1]";
        String SPAN_BIRTHDATE_MONTH = "//div[@class='datepicker-months']//span[@class='month']["+month+"]";
        String SPAN_BIRTHDATE_DAY  = "//div[@class='datepicker-days']//td[text()='"+day+"']";

        if(year == 2000){
            sleep(250);
            click(driver, By.xpath(BUTTON_BIRTHDATE_YEAR_PREV));
        }
        sleep(250);
        click(driver, By.xpath(SPAN_BIRTHDATE_YEAR));
        sleep(250);
        click(driver, By.xpath(SPAN_BIRTHDATE_MONTH));
        sleep(250);
        click(driver, By.xpath(SPAN_BIRTHDATE_DAY));
    }

    private List<Cabin> filterCabin(String filter, List<Cabin> cabins){
        List<Cabin> filteredCabins = new ArrayList<>();
        for (Cabin cabin : cabins) {
            if(cabin.getType().equals(filter)){
                filteredCabins.add(cabin);
            }
        }
        return filteredCabins;
    }

    private int passengerAdultSize(List<Cabin> cabins){
        int passegersAdults = 0;
        for (Cabin cabin : cabins) {
            passegersAdults += cabin.getAdults();
        }
        return passegersAdults;
    }

    private int passengerChildrenSize(List<Cabin> cabins){
        int passegersChildren = 0;
        for (Cabin cabin : cabins) {
            passegersChildren += cabin.getChildren();
        }
        return passegersChildren;
    }

    private List<Passenger> getPassengersAdults(int nPassegersAdults) throws Throwable {
        List<Passenger> passengers = new ArrayList<>();
        for(int i = 0; i < nPassegersAdults; i++) {
            HashMap<String, String> data = getSheets(ExcelDataObjects.EXCEL_PERURAIL, ExcelDataObjects.SHEET_PASSENGERS_ADULT).get(i);
            int id = Integer.parseInt(data.get(ExcelDataObjects.PASSENGERS_COLUMN_ID));
            String firstName = data.get(ExcelDataObjects.PASSENGERS_COLUMN_FIRSTNAME);
            String surname = data.get(ExcelDataObjects.PASSENGERS_COLUMN_SURNAME);
            String gender = data.get(ExcelDataObjects.PASSENGERS_COLUMN_GENDER);
            String country = data.get(ExcelDataObjects.PASSENGERS_COLUMN_COUNTRY);
            String typeDoc = data.get(ExcelDataObjects.PASSENGERS_COLUMN_TYPEDOC);
            String docNum = data.get(ExcelDataObjects.PASSENGERS_COLUMN_DOCNUM);
            Calendar calendar = Calendar.getInstance();
            calendar.set(1997, Calendar.JUNE, 18);// Fecha temporal para pruebas de adultos
            Date birthDate = calendar.getTime();
            String telephone = data.get(ExcelDataObjects.PASSENGERS_COLUMN_TELEPHONE);
            String email = data.get(ExcelDataObjects.PASSENGERS_COLUMN_EMAIL);
            passengers.add(new Passenger(id, firstName, surname, gender, country, typeDoc, docNum, birthDate, telephone, email));
        }
        return passengers;
    }
    private List<Passenger> getPassengersChildren(int nPassegersChildren) throws Throwable {
        List<Passenger> passengers = new ArrayList<>();
        for(int i = 0; i < nPassegersChildren; i++) {
            HashMap<String, String> data = getSheets(ExcelDataObjects.EXCEL_PERURAIL, ExcelDataObjects.SHEET_PASSENGERS_CHILDREN).get(i);
            int id = Integer.parseInt(data.get(ExcelDataObjects.PASSENGERS_COLUMN_ID));
            String firstName = data.get(ExcelDataObjects.PASSENGERS_COLUMN_FIRSTNAME);
            String surname = data.get(ExcelDataObjects.PASSENGERS_COLUMN_SURNAME);
            String gender = data.get(ExcelDataObjects.PASSENGERS_COLUMN_GENDER);
            String country = data.get(ExcelDataObjects.PASSENGERS_COLUMN_COUNTRY);
            String typeDoc = data.get(ExcelDataObjects.PASSENGERS_COLUMN_TYPEDOC);
            String docNum = data.get(ExcelDataObjects.PASSENGERS_COLUMN_DOCNUM);
            Calendar calendar = Calendar.getInstance();
            calendar.set(2010, Calendar.JANUARY, 6);// Fecha temporal para pruebas de niños
            Date birthDate = calendar.getTime();
            passengers.add(new Passenger(id, firstName, surname, gender, country, typeDoc, docNum, birthDate));
        }
        return passengers;
    }

    public void clickContinue() throws Exception {
        mensaje = "Se da click en el boton continuar";
        try{
            sleep(500);
            click(driver, PeruRailObjects.BUTTON_CONTINUE_PASSENGERS);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
            stepPass(driver,mensaje);
        }catch(Throwable we){
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            mensaje = "Fallo en tiempo de respuesta";
            stepFail(driver,mensaje);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
            Assert.fail(mensaje);
        }
    }
}
