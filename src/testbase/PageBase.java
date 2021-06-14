package testbase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {
	public static String webBrowser;
	private String chromeDriverPath;
	public  RemoteWebDriver remoteWebDriver;
	
	public PageBase(RemoteWebDriver webDriver) {
		this.remoteWebDriver = webDriver;
		PageFactory.initElements(remoteWebDriver, this);
	}

	public void initiateBrowser() throws Exception {

		webBrowser = getAppProperties("browser");

		if (webBrowser.equalsIgnoreCase("chrome")) {
			chromeDriverPath = getAppProperties("chromeDriverPath");
			System.setProperty("ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY", "true");
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--test-type");
			options.addArguments("start-maximized");
			options.addArguments("--disable-extentions");
			remoteWebDriver = new ChromeDriver(options);
			remoteWebDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		}

	}

	public void launchUrl(String url, String pageTitle) throws Exception {
		remoteWebDriver.get(url);
		System.out.println(pageTitle + " is launched");

	}

	public String getAppProperties(String key) throws IOException {
		String value = "";

		FileInputStream fileInputStream = new FileInputStream("src/config.properties");
		Properties property = new Properties();
		property.load(fileInputStream);
		value = property.getProperty(key);

		fileInputStream.close();
		return value;

	}

	public void click(By object, String elementName) throws Exception {

		WebDriverWait waitSelenium = new WebDriverWait(remoteWebDriver, 60, 500);
		waitSelenium.until(ExpectedConditions.visibilityOf(remoteWebDriver.findElement(object)));
		waitSelenium.until(ExpectedConditions.elementToBeClickable(remoteWebDriver.findElement(object)));
		remoteWebDriver.findElement(object).click();
		System.out.println(elementName+" is clicked");

	}

	public void enterText(By object, String input, String elementName) throws Exception {

		WebDriverWait waitSelenium = new WebDriverWait(remoteWebDriver, 120, 500);
		waitSelenium.until(ExpectedConditions.visibilityOf(remoteWebDriver.findElement(object)));

		remoteWebDriver.findElement(object).clear();
		remoteWebDriver.findElement(object).sendKeys(input);
		System.out.println(input+" is entered in "+elementName);

	}
	
	public String getText(By object) throws Exception {

		WebDriverWait waitSelenium = new WebDriverWait(remoteWebDriver, 120, 500);
		waitSelenium.until(ExpectedConditions.visibilityOf(remoteWebDriver.findElement(object)));

		String text=remoteWebDriver.findElement(object).getText();
		return text;

	}

	public void closeDriver() {
		remoteWebDriver.close();

	}

	public void keyBoardEnter(By object) throws InterruptedException {

		WebDriverWait waitSelenium = new WebDriverWait(remoteWebDriver, 60, 500);
		waitSelenium.until(ExpectedConditions.visibilityOf(remoteWebDriver.findElement(object)));
		waitSelenium.until(ExpectedConditions.elementToBeClickable(remoteWebDriver.findElement(object)));
		remoteWebDriver.findElement(object).sendKeys(Keys.ENTER);
	}
}
