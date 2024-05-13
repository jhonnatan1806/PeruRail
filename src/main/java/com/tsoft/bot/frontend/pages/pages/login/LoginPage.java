package com.tsoft.bot.frontend.pages.pages.login;
import com.tsoft.bot.frontend.base.BaseClass;
import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.pages.objects.AutomationPracticeObjects;
import com.tsoft.bot.frontend.utility.ExcelReader;
import com.tsoft.bot.frontend.utility.GenerateWord;
import org.openqa.selenium.WebDriver;
import java.util.HashMap;
import java.util.List;
import com.tsoft.bot.frontend.pages.objects.ExcelDataObjects;
import com.tsoft.bot.frontend.pages.objects.McssObjects;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage extends BaseClass {
    private WebDriver driver;
    private static GenerateWord generateWord = new GenerateWord();
    String mensaje;
    public LoginPage(WebDriver driver){
        super(driver);
        this.driver = Hook.getDriver();
    }

    private List<HashMap<String, String>> getData() throws Throwable {
        return ExcelReader.data(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME);
    }

    public void enterUrl(String urlTestCase) throws Throwable {
        mensaje = "Se ingresa a la url y se selecciona el boton Sign In";
        try {
            int countPage = Integer.parseInt(urlTestCase) - 1;
            String url = getData().get(countPage).get(ExcelDataObjects.COLUMN_URL);
            driver.get(url);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
            System.out.println(mensaje);
            click(driver, AutomationPracticeObjects.BTN_SIGNIN);
            stepPass(driver, mensaje);
        }
        catch (Exception we)
        {
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            stepFail(driver,"Fallo en tiempo de respuesta : " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }
    }
    /*
    public void login(String casoPrueba) throws Throwable {
        mensaje = "Se ingresa el email y se selecciona el boton Create an Account";
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(AutomationPracticeObjects.HDR_CREATEACCOUNT));

            int countPage = Integer.parseInt(casoPrueba) - 1;
            String email = getData().get(countPage).get(ExcelDataObjects.COLUMN_USER);
            String passwd = getData().get(countPage).get(ExcelDataObjects.COLUMN_PASSWORD);
            String firstName = getData().get(countPage).get(ExcelDataObjects.COLUMN_NAME);
            String lastName = getData().get(countPage).get(ExcelDataObjects.COLUMN_LASTNAME);
            String city = getData().get(countPage).get(ExcelDataObjects.COLUMN_CITY);
            String zip = getData().get(countPage).get(ExcelDataObjects.COLUMN_ZIP);
            String day = getData().get(countPage).get(ExcelDataObjects.COLUMN_DAY);
            String month = getData().get(countPage).get(ExcelDataObjects.COLUMN_MONTH);
            String year = getData().get(countPage).get(ExcelDataObjects.COLUMN_YEAR);
            String state = getData().get(countPage).get(ExcelDataObjects.COLUMN_STATE);
            String direction = getData().get(countPage).get(ExcelDataObjects.COLUMN_DIRECTION);
            String phoneNumber = getData().get(countPage).get(ExcelDataObjects.COLUMN_PHONENUMBER);
            String alias = getData().get(countPage).get(ExcelDataObjects.COLUMN_ALIAS);

            //typeText(driver, AutomationPracticeObjects.INPUT_EMAILADDRESS, email);
            //click(driver, AutomationPracticeObjects.BTN_CREATEACCOUNT);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
            sleep(5000);
            try{
                if(isDisplayed(driver,AutomationPracticeObjects.DIV_ALERT)){
                    //typeText(driver, AutomationPracticeObjects.INPUT_EMAILLOGIN, email);
                    //typeText(driver, AutomationPracticeObjects.INPUT_PASSWORDLOGIN, passwd);
                    mensaje="Se intenta crear un usuario, dado que el Email ya esta registrado, se llenan los campos de login y se inicia sesión";
                    System.out.println(mensaje);
                    stepPass(driver, mensaje);
                    generateWord.sendText(mensaje);
                    generateWord.addImageToWord(driver);
                    //click(driver, AutomationPracticeObjects.BTN_LOGIN);
                    //sleep(3500);
                }
            }catch(Exception e){
                wait.until(ExpectedConditions.visibilityOfElementLocated(AutomationPracticeObjects.HDR_CREATEANACCOUNT));
                click(driver, AutomationPracticeObjects.RADIOBTN_MR);
                typeText(driver, AutomationPracticeObjects.INPUT_FIRSTNAME, firstName);
                typeText(driver, AutomationPracticeObjects.INPUT_LASTNAME, lastName);
                typeText(driver, AutomationPracticeObjects.INPUT_PASSWORD, passwd);
                typeText(driver, AutomationPracticeObjects.INPUT_CITY, city);
                typeText(driver, AutomationPracticeObjects.INPUT_ZIP, zip);
                typeText(driver, AutomationPracticeObjects.INPUT_ADDRESS, direction);
                typeText(driver, AutomationPracticeObjects.INPUT_PHONENUMBER, phoneNumber);
                clear(driver, AutomationPracticeObjects.INPUT_ALIAS);
                typeText(driver, AutomationPracticeObjects.INPUT_ALIAS, alias);
                selectByValue(driver, AutomationPracticeObjects.DROPDOWN_DAY, day);
                selectByValue(driver, AutomationPracticeObjects.DROPDOWN_MONTH, month);
                selectByValue(driver, AutomationPracticeObjects.DROPDOWN_YEAR, year);
                selectByVisibleText(driver, AutomationPracticeObjects.DROPDOWN_STATE, state);
                mensaje="Se llena los campos con los datos del cliente para el registro ";
                System.out.println(mensaje);
                generateWord.sendText(mensaje);
                generateWord.addImageToWord(driver);
                stepPass(driver, mensaje);
                click(driver, AutomationPracticeObjects.BTN_REGISTER);
                sleep(3500);
            }
        }
        catch (Exception we)
        {
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            System.out.println("Fallo en tiempo de respuesta: " + we.getMessage());
            stepFail(driver, "Fallo en tiempo de respuesta: " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }
    }

    public void validarLogin(String casoPrueba) throws Throwable {
        mensaje = "Se valida que el usuario ha sido logueado correctamente";
        try {

            WebDriverWait wait = new WebDriverWait(driver, 30);
            int countPage = Integer.parseInt(casoPrueba) - 1;
            String firstName = getData().get(countPage).get(ExcelDataObjects.COLUMN_NAME);

            wait.until(ExpectedConditions.visibilityOfElementLocated(AutomationPracticeObjects.HDR_MYACCOUNT));

            String nameToValidate = getText(driver,AutomationPracticeObjects.SPAN_NAME);
            Boolean cliente = false;
            if (nameToValidate.contains(firstName))cliente=true;
            if (Boolean.TRUE.equals(cliente))
            {
                stepPass(driver, mensaje);
                generateWord.sendText(mensaje);
                generateWord.addImageToWord(driver);

            }
            else
            {
                System.out.println("No se pudo validar el login");
                stepFail(driver,"Usuario o contraseña inválidos");
                generateWord.sendText("Usuario o contraseña inválidos");
                generateWord.addImageToWord(driver);
            }
            //Consulta
            //driver.quit();
        }
        catch (Exception we)
        {
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            System.out.println("No se pudo validar el login");
            stepFail(driver,"Fallo en tiempo de respuesta: " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }
    }
 */
}
