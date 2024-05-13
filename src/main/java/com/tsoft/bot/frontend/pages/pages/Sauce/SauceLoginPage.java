package com.tsoft.bot.frontend.pages.pages.Sauce;

import com.tsoft.bot.frontend.base.BaseClass;
import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.pages.objects.ExcelDataObjects;
import com.tsoft.bot.frontend.pages.objects.SauceDemoObjects;
import com.tsoft.bot.frontend.utility.ExcelReader;
import com.tsoft.bot.frontend.utility.GenerateWord;
import gherkin.lexer.Th;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.List;

public class SauceLoginPage extends BaseClass {
    private WebDriver driver;
    private static GenerateWord generateWord = new GenerateWord();
    String mensaje;
    public SauceLoginPage(WebDriver driver) {
        super(driver);
        this.driver = Hook.getDriver();
    }

    private List<HashMap<String, String>> getData() throws Throwable {
        return ExcelReader.data(ExcelDataObjects.EXCEL_SAUCE, ExcelDataObjects.HOJA_LOGIN_SAUCE);
    }

   // private List<HashMap<String, String>> getDataProducts() throws Throwable {
    //    return ExcelReader.data(ExcelDataObjects.EXCEL_SAUCE, ExcelDataObjects.HOJA_PRODUCTOS);
    //}

    public void ingresarURL(String urlTestCase) throws Throwable{
        mensaje = "Se ingresa a la URL SauceDemo";
        try{
            int CountPage = Integer.parseInt(urlTestCase) - 1;
            String url = getData().get(CountPage).get(ExcelDataObjects.COLUMN_URL);
            driver.get(url);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
            System.out.println(mensaje);
            stepPass(driver,mensaje);
        }   catch (Exception we)
        {
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            stepFail(driver,"Fallo en tiempo de respuesta : " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }
    }

    public void iniciarSesion(String fila_casoPrueba) throws Throwable{
        System.out.println("Se ingresa el usuario y la contraseña y se inicia sesion");
        try{
            int CountPage = Integer.parseInt(fila_casoPrueba)-1;
            String usuario = getData().get(CountPage).get(ExcelDataObjects.COLUMN_USER);
            String password = getData().get(CountPage).get(ExcelDataObjects.COLUMN_PASSWORD);
            typeText(driver, SauceDemoObjects.INPUT_USERNAME,usuario);
            typeText(driver, SauceDemoObjects.INPUT_PASSWORD, password);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
            click(driver,SauceDemoObjects.INPUT_BTN);
        }catch (Exception we){
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            stepFail(driver,"Fallo en tiempo de respuesta : " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }

    }

    public void validarPaginaProductos() throws Throwable{
        mensaje ="Se valida que se inicie la pantalla productos";
        try{
            String tituloAValidar =getText(driver,SauceDemoObjects.SPAN_TITLE);
            if (tituloAValidar.equals("PRODUCTS")){
                stepPass(driver,mensaje);
                generateWord.sendText(mensaje);
                generateWord.addImageToWord(driver);
            }else{
                stepFail(driver,"No se visualiza de productos");
                System.out.println("ERROR: No se visualiza la pagina de productos");
                generateWord.sendText("ERROR: No se visualiza la pagina de productos");
                generateWord.addImageToWord(driver);
            }



        }catch (Exception we){
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            stepFail(driver,"Fallo en tiempo de respuesta : " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }
    }

    public void seleccionarProductos() throws Throwable{
        mensaje ="Se seleccionan los articulos a comprar";
        try{
            click(driver,SauceDemoObjects.PRODUCTO1);
            click(driver,SauceDemoObjects.PRODUCTO2);
            click(driver,SauceDemoObjects.PRODUCTO3);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
            click(driver,SauceDemoObjects.IR_AL_CARRO);
            generateWord.addImageToWord(driver);
            click(driver,SauceDemoObjects.IR_AL_CHECKOUT);

        }catch (Exception we){
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            stepFail(driver,"Fallo en tiempo de respuesta : " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }
    }

    public void completarCompra(String casoPrueba) throws Throwable{
        mensaje ="Se completara la compra y se llenan datos de envio";
        try{
            int CountPage = Integer.parseInt(casoPrueba)-1;
            String nombre = getData().get(CountPage).get(ExcelDataObjects.COLUMN_NAME);
            typeText(driver, SauceDemoObjects.FIRST_NAME,nombre);
            String apellido = getData().get(CountPage).get(ExcelDataObjects.COLUMN_LASTNAME);
            typeText(driver, SauceDemoObjects.LAST_NAME,apellido);
            String codigoPostal = getData().get(CountPage).get(ExcelDataObjects.COLUMN_POSTAL);
            typeText(driver, SauceDemoObjects.POSTAL_CODE,codigoPostal);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
            click(driver,SauceDemoObjects.BTN_CONTINUE);
            generateWord.addImageToWord(driver);
            click(driver,SauceDemoObjects.BTN_FINISH);
        }catch (Exception we){
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            stepFail(driver,"Fallo en tiempo de respuesta : " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }
    }
    public void validarCompra() throws Throwable{
        mensaje= "Se valida que la compra se realizara exitosamente";
        try{
            String textoValidacion = getText(driver,SauceDemoObjects.VALIDACION);
            if (textoValidacion.equals("THANK YOU FOR YOUR ORDER")){
                stepPass(driver,mensaje);
                generateWord.sendText(mensaje);
                generateWord.addImageToWord(driver);
            }else{
                stepFail(driver,"No se completo la compra");
                System.out.println("ERROR: No se completo la compra");
                generateWord.sendText("ERROR: No se completo la compra");
                generateWord.addImageToWord(driver);
            }

        }catch (Exception we){
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            stepFail(driver,"Fallo en tiempo de respuesta : " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }
    }

    public void DetallesProductos(String RowCasoPrueba) throws Throwable{
        mensaje = "Se ingresa a ver el detalle del producto";
        try{
            click(driver,SauceDemoObjects.LINK_PRODUCTO);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
        }catch (Exception we){
        ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
        stepFail(driver,"Fallo en tiempo de respuesta : " + we.getMessage());
        generateWord.sendText("Fallo en tiempo de respuesta");
        generateWord.addImageToWord(driver);
        }

    }


    public  void ObtenerDataProducto(String RowCasoPrueba) throws Throwable{
        mensaje = "Se obtiene la información del producto";
        int num_Row  = Integer.parseInt(RowCasoPrueba);
        try{
            String nombre = getText(driver,SauceDemoObjects.DIV_NOMBRE);
            String descripcion = getText(driver,SauceDemoObjects.DIV_DESCRIPCION);
            String precio = getText(driver,SauceDemoObjects.DIV_PRECIO);

            System.out.println(nombre);
            System.out.println(descripcion);
            System.out.println(precio);


            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_SAUCE,ExcelDataObjects.HOJA_PRODUCTOS,num_Row,1,nombre);
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_SAUCE,ExcelDataObjects.HOJA_PRODUCTOS,num_Row,2,descripcion);
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_SAUCE,ExcelDataObjects.HOJA_PRODUCTOS,num_Row,3,precio);



            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
        }catch (Exception we){
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_SAUCE, ExcelDataObjects.HOJA_PRODUCTOS, 1, 19, "FAIL");
            stepFail(driver,"Fallo en tiempo de respuesta : " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }

    }

}
