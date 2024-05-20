package com.tsoft.bot.frontend.pages.pages.perurail;

import com.tsoft.bot.frontend.base.BaseClass;
import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.models.Cabin;
import com.tsoft.bot.frontend.models.Passenger;
import com.tsoft.bot.frontend.pages.objects.ExcelDataObjects;
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
        int nCabins = cabins.size();
        int nPassegersAdults = getNPassengersAdults(cabins);
        int nPassegersChildren = getNPassengersChildren(cabins);

        // cabinas por tipo
        List<Cabin> cabinsSC = filterCabin("SC", cabins);
        List<Cabin> cabinsTW = filterCabin("TW", cabins);
        List<Cabin> cabinsBU = filterCabin("BU", cabins);

        List<Cabin> cabinSorted = new ArrayList<>();
        cabinSorted.addAll(cabinsBU);
        cabinSorted.addAll(cabinsTW);
        cabinSorted.addAll(cabinsSC);

        // pasajeros por tipo
        List<Passenger> passengersAdults = getPassengersAdults(nPassegersAdults);
        List<Passenger> passengersChildren = getPassengersChildren(nPassegersChildren);

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

    private int  fillPassengersAdult(Cabin cabin, int i, List<Passenger> passengers) throws IOException {
        for (int j = 0; j < cabin.getAdults(); j++) {
            if (i != 0 || j != 0) {
                sleep(500);
                By BUTTON_PASSENGER = By.xpath("//a[@id='expandirPasajero-" + (i+j) + "']");
                click(driver, BUTTON_PASSENGER);
            }

            String INPUT_FIRSTNAME = "//input[@id='ListaPasajeros[" + i + "].NombrePax']";
            String INPUT_SURNAME = "//input[@id='ListaPasajeros[" + i + "].ApellidoPax']";
            String INPUT_GENDER = "//input[@name='ListaPasajeros[" + i + "].SexoPax' and @value='1']";

            Passenger passenger = passengers.remove(0);
            System.out.println("Adulto: " + passenger.getFirstName());
            System.out.println("Adulto: " + passenger.getSurname());
            System.out.println("Adulto: " + passenger.getGender());
            System.out.println("Adulto: " + passenger.getCountry());
            System.out.println("Adulto: " + passenger.getTypedoc());
            System.out.println("Adulto: " + passenger.getDocument());
            System.out.println("Adulto: " + passenger.getBirthdate().toString());
            System.out.println("Adulto: " + passenger.getTelephone());
            System.out.println("Adulto: " + passenger.getEmail());
            //typeText(driver, By.xpath(INPUT_FIRSTNAME), passenger.getFirstName());
            //typeText(driver, By.xpath(INPUT_SURNAME), passenger.getSurname());
            //click(driver, By.xpath(INPUT_GENDER));
        }
        return cabin.getAdults();
    }

    private int fillPassengersChildren(Cabin cabin, int i, List<Passenger> passengers) throws IOException {
        for (int j = 0; j < cabin.getChildren(); j++) {
            sleep(500);
            By BUTTON_PASSENGER = By.xpath("//a[@id='expandirPasajero-" + (i+j) + "']");
            click(driver, BUTTON_PASSENGER);

            Passenger passenger = passengers.remove(0);
            System.out.println("Niño: " + passenger.getFirstName());
            System.out.println("Niño: " + passenger.getSurname());
            System.out.println("Niño: " + passenger.getGender());
            System.out.println("Niño: " + passenger.getCountry());
            System.out.println("Niño: " + passenger.getTypedoc());
            System.out.println("Niño: " + passenger.getDocument());
            System.out.println("Niño: " + passenger.getBirthdate().toString());
        }
        return cabin.getChildren();
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

    private int getNPassengersAdults(List<Cabin> cabins){
        int passegersAdults = 0;
        for (Cabin cabin : cabins) {
            passegersAdults += cabin.getAdults();
        }
        return passegersAdults;
    }

    private int getNPassengersChildren(List<Cabin> cabins){
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

}
