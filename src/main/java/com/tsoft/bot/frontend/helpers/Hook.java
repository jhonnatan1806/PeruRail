package com.tsoft.bot.frontend.helpers;

import com.tsoft.bot.frontend.listener.Listener;
import com.tsoft.bot.frontend.utility.FileHelper;
import com.tsoft.bot.frontend.utility.GenerateWord;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.sikuli.script.Screen;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static com.tsoft.bot.frontend.base.BaseClass.stepFailNoShoot;

public class Hook extends Listener {

	private static final String GECKO_KEY 		= "webdriver.gecko.driver";
	private static final String GECKO_DRIVER 	= "/src/main/resources/driver/firefox/0.26/geckodriver.exe";
	private static final String CHROME_KEY 		= "webdriver.chrome.driver";
	private static final String CHROME_DRIVER 	= "/src/main/resources/driver/chrome/123.0/chromedriver.exe";
	private static final String IE_KEY 			= "webdriver.ie.driver";
	private static final String IE_DRIVER 		= "/src/main/resources/driver/ie/3.5/IEDriverServer.exe";
	private static final long DELAY = 10;
	private static WebDriver driver = null;
	private static Screen screen;

	private static GenerateWord generateWord = new GenerateWord();

	@Before
	public void scenario(Scenario scenario){
		onTestStart(scenario.getName());
	}

	@Before
	public static void setUpWeb(Scenario scenario) throws IOException {
		generateWord.startUpWord(scenario.getName());

		try {
			Logger.getLogger("[LOG] NAVEGADOR: resources");

			switch ("Chrome"){
				case "IE":
					System.setProperty(IE_KEY, FileHelper.getProjectFolder() + IE_DRIVER);
					InternetExplorerOptions options = new InternetExplorerOptions();
					options.introduceFlakinessByIgnoringSecurityDomains();
					options.setCapability("requireWindowFocus", true);
					options.setCapability("enablePersistentHover", false);
					options.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
					options.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
					options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
					driver = new InternetExplorerDriver(options);
					break;
				case "Chrome":
                    System.setProperty(CHROME_KEY, FileHelper.getProjectFolder() + CHROME_DRIVER);
					//WebDriverManager.chromedriver().setup();
					System.setProperty("webdriver.chrome.silentOutput", "true");
					ChromeOptions chromeOptions = new ChromeOptions();
					chromeOptions.addArguments(
							"--verbose",
							"--disable-web-security",
							"--ignore-certificate-errors",
							"--allow-running-insecure-content",
							"--allow-insecure-localhost",
							"--no-sandbox",
							"--disable-gpu",
							"enable-automation",
							"--disable-infobars",
							"--disable-dev-shm-usage",
							"--disable-browser-side-navigation"
					);
					driver = new ChromeDriver(chromeOptions);
					getDriver().manage().window().maximize();
					getDriver().manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);
					break;

				case "Firefox":
					System.setProperty(GECKO_KEY, FileHelper.getProjectFolder() + GECKO_DRIVER);
					FirefoxOptions firefoxOptions = new FirefoxOptions();
					driver = new FirefoxDriver(firefoxOptions);
					getDriver().manage().window().maximize();
					getDriver().manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);
					break;
				default:
					break;
			}

		}catch (Exception t){
			generateWord.sendText("Error : Navegador se cerro inesperandamente." + t.getMessage());
			stepFailNoShoot("Error : Navegador se cerro inesperandamente." + t.getMessage());
			throw t;
		}
	}

	@After
	public void tearDown(Scenario scenario) throws IOException {

		onFinish();

		generateWord.endToWord(scenario.getStatus());
		driver.quit();

	}

	public static WebDriver getDriver() { return driver; }


	public static Screen getScreen(){


		return (screen==null)?new Screen() : screen;
	}

}